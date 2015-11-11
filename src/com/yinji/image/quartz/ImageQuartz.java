package com.yinji.image.quartz;

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
			if(null == images || images.size() == 0){
				return ;
			}
			List<UploadInfoBean> uploads = new ArrayList<UploadInfoBean>();
			// 创建一个执行任务的服务
			ExecutorService es = Executors.newFixedThreadPool(4);
			for (int i = 0; i < images.size(); i++) {
				
				//当前ImageBean
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
