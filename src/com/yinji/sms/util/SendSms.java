package com.yinji.sms.util;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class SendSms {
	private String mobile = null; // 接收端手机号
	private String content = null; // 短信内容
	private String hexPass = null;
	private String userName = "jiaozi";
	private String password = "1qaz2wsx";
	char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
			'B', 'C', 'D', 'E', 'F' };

	public SendSms(String mobile, String content) {
		this.mobile = mobile;
		this.content = content;
	}

	public int SendMsg() {
		hexPass = password + userName;
		System.out.println(hexPass);
		byte[] buf = hexPass.getBytes();

		// 获得MD5摘要算法的 MessageDigest 对象
		MessageDigest mdInst;
		try {
			mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(buf);
			// 获得密文
			byte[] mdstr = mdInst.digest();
			// 转换成16进制字符串
			hexPass = FormatTransfer.BinaryToHexString(mdstr);
			System.out.println("hexpass:" + hexPass);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}

		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://api.sms.cn/mtutf8/");
		post.addRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=utf-8");
		NameValuePair[] data = { new NameValuePair("uid", userName),
				new NameValuePair("pwd", hexPass),
				new NameValuePair("mobile", mobile),
				new NameValuePair("content", content) };
		post.setRequestBody(data);
		
		try {
			client.executeMethod(post);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Header[] headers = post.getResponseHeaders();
		int statusCode = post.getStatusCode();
		System.out.println("statusCode:" + statusCode);
		for (Header h : headers) {
			System.out.println(h.toString());
		}
		String result;
		try {
			result = new String(post.getResponseBodyAsString()
					.getBytes("utf-8"));
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		post.releaseConnection();
		return 0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SendSms sms = new SendSms("18181996435", "test1");
		sms.SendMsg();
	}
}
