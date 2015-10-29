package com.qiniu.server;

import java.util.UUID;
import com.qiniu.constant.Constant;
import com.qiniu.http.Response;
import com.qiniu.util.Auth;
import com.qiniu.util.Token;

/**
 * @author Roger
 * @date 2015年10月28日
 * @remark 七牛云存储简单Demo
 */
public class QiNiuMainDemo {

	public static void main(String[] mains) {
		// 1.初始化
		// 通过AK、SK进行签名认证
		// 密钥配置
		Auth auth = Auth.create(Constant.ACCESS_KEY, Constant.SECRET_KEY);
		
		QiNiuMainDemo.upload(auth);
		
 	}
	
	public static void download(Auth auth){
		QiNiuDownloadDemo demo = new QiNiuDownloadDemo();
		String str = demo.download(auth, "card");
		System.out.println(str);
	}

	public static void bucket(Auth auth) {
		QiNiuBucketDemo demo = new QiNiuBucketDemo();
		String[] buckets = demo.getBuckets(auth);

		for (String bucket : buckets) {
			demo.getFileList(auth, bucket, null);
		}
	}

	public static void upload(Auth auth) {
		QiNiuUploadDemo upload = new QiNiuUploadDemo();
		String filePath = "F:\\IMG_0307.JPG";
		String key = UUID.randomUUID().toString();
		String token = Token.getUploadToken(auth, "lj5635906");
		try {
			Response response = upload.breakpointUpload(filePath, key, token);
			System.out.println(response.statusCode);
			System.out.println(response.address);
			System.out.println(response.url());
			System.out.println(response.reqId);
			System.out.println(response.bodyString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
