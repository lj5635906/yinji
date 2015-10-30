package com.yinji.image.service;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

import com.qiniu.constant.Constant;
import com.qiniu.http.Response;
import com.qiniu.server.QiNiuUploadDemo;
import com.qiniu.util.Auth;
import com.qiniu.util.Token;
import com.yinji.image.bean.ImageBean;
import com.yinji.image.bean.UploadInfoBean;

/**
 * @author : Roger
 * @date : 2015-10-29
 * @desc : 上传文件引擎
 */
public class ImageUploadEngine implements Callable<UploadInfoBean> {

	/**
	 * 单个上传资源路径
	 */
	private ImageBean image;
	
	/**
	 *  七牛云存储身份验证信息
	 */
	private Auth auth;

	/**
	 * @param image
	 *            图片信息
	 * @param auth
	 *            七牛云存储Auth
	 */
	public ImageUploadEngine(ImageBean image, Auth auth) {
		this.image = image;
		this.auth = auth;
	}

	static AtomicInteger ai = new AtomicInteger(1);
	
	@Override
	public UploadInfoBean call() throws Exception {
		
		UploadInfoBean bean = new UploadInfoBean();
		QiNiuUploadDemo upload = new QiNiuUploadDemo();
		// 获取上传token
		String token = Token.getUploadToken(auth, Constant.BUCKET);
		// 云存储上资源名
		String key = String.valueOf(UUID.randomUUID());
		// 响应信息
		Response response = null;
		try {
			System.out.println("上传 : "+ai.getAndIncrement()+" ; path : "+image.getPath()+" ; key : "+key);
			response = upload.breakpointUpload(image.getPath(), key, token);
			// 上传返回信息
			bean.setCode(response.statusCode);
			bean.setImageId(this.image.getImageId());
			bean.setKey(key);
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
			bean.setCode(Constant.UPLOAD_ERROR);
			bean.setImageId(this.image.getImageId());
		} finally {
			upload = null;
			token = null;
			key = null;
			response = null;
		}
		return null;
	}

	public ImageBean getImage() {
		return image;
	}

	public void setImage(ImageBean image) {
		this.image = image;
	}

	public Auth getAuth() {
		return auth;
	}

	public void setAuth(Auth auth) {
		this.auth = auth;
	}

}
