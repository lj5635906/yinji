package com.yinji.image.quartz;

import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import com.qiniu.constant.Constant;
import com.qiniu.util.Auth;
import com.yinji.image.bean.ImageBean;
import com.yinji.image.bean.UploadInfoBean;
import com.yinji.image.service.ImageService;
import com.yinji.image.service.ImageUploadEngine;

/**
 * @author : Roger
 * @date : 2015-10-29
 * @desc : 图片定时任务
 */
public class ImageQuartz {

	@Resource(name = "imageService")
	private ImageService imageService;

	/**
	 * 云存储初始化身份验证信息
	 */
	static Auth auth = Auth.create(Constant.ACCESS_KEY, Constant.SECRET_KEY);

	/**
	 * 定时任务调用的方法
	 */
	public void execute() {
		System.out.println("ImageQuartz开始执行");
		try {
			List<ImageBean> images = imageService.getImages();
			if (null == images || images.size() == 0) {
				return;
			}
			List<UploadInfoBean> uploads = new ArrayList<UploadInfoBean>();
			// 创建一个执行任务的服务
			ExecutorService es = Executors.newFixedThreadPool(4);
			for (int i = 0; i < images.size(); i++) {
				// 当前ImageBean
				ImageBean image = images.get(i);
				// 需要执行的任务
				ImageUploadEngine thread = new ImageUploadEngine(image, auth);
				// 提交任务并执行，同时返回一个Future对象,通过Future得到任务执行的结果
				Future<UploadInfoBean> future = es.submit(thread);
				// 获取执行结果
				uploads.add(future.get());
			}
			es.shutdown();

			imageService.update(uploads);
			
			toSocket();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void toSocket() {
		Socket socket = new Socket();
		int port = 8888;
		int timeout = 3000;
		// 向服务器传输流
		DataOutputStream os = null;
		try {
			socket.setReuseAddress(true);
			socket.setKeepAlive(true);
			socket.setTcpNoDelay(true);
			socket.setSoLinger(true, 0);
			socket.connect(new InetSocketAddress("192.168.20.10", port),
					timeout);
			socket.setSoTimeout(timeout);
			os = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// 数据长度 起始
			os.write(0xF5F5F5F5);
			os.writeUTF("201511110541020000000662ANet0002");
			os.writeUTF("0A000032");
			os.writeUTF("D9");
			// 将数据传输到服务器
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static byte[] StringToByte(String str) {
		return StringToByte(str, "UTF-8");
	}

	public static byte[] StringToByte(String str, String charEncode) {
		byte[] destObj = null;
		try {
			if (null == str || str.trim().equals("")) {
				destObj = new byte[0];
				return destObj;
			} else {
				destObj = str.getBytes(charEncode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return destObj;
	}
	
	public static void main(String[] args){
		byte[] bs = StringToByte("201511110541020000000662ANet0002");
		
		for(byte b : bs){
			System.out.print(b);
		}
	}
}
