package com.qiniu.util;

/**
 * @author Roger
 * @date 2015年10月28日
 * @remark 上传Token
 */
public class Token {

	/**
	 * 生成上传token,简单上传，使用默认策略
	 * 
	 * @param auth
	 *            Auth
	 * @param bucket
	 *            空间名
	 * @return Token
	 */
	public static String getUploadToken(Auth auth, String bucket) {
		return auth.uploadToken(bucket);
	}

	/**
	 * 生成上传token,覆盖上传
	 * 
	 * @param auth
	 *            Auth
	 * @param bucket
	 *            空间名
	 * @param key
	 *            key，可为 null
	 * @return Token
	 */
	public String getUploadToken(Auth auth, String bucket, String key) {
		return auth.uploadToken(bucket, key);
	}

	/**
	 * 生成上传token,设置指定上传策略
	 * 
	 * @param auth
	 *            Auth
	 * @param bucket
	 *            空间名
	 * @param key
	 *            key，可为 null
	 * @param expires
	 *            有效时长，单位秒。默认3600s
	 * @param policy
	 *            上传策略的其它参数，如 new StringMap().put("endUser",
	 *            "uid").putNotEmpty("returnBody", "")。
	 * @return Token
	 */
	public String getUploadToken(Auth auth, String bucket, String key,
			long expires, StringMap policy) {
		return auth.uploadToken(bucket, key, expires, policy);
	}

	/**
	 * 生成上传token,设置预处理、去除非限定的策略字段
	 * 
	 * @param auth
	 *            Auth
	 * @param bucket
	 *            空间名
	 * @param key
	 *            key，可为 null
	 * @param expires
	 *            有效时长，单位秒。默认3600s
	 * @param policy
	 *            上传策略的其它参数，如 new StringMap().put("endUser",
	 *            "uid").putNotEmpty("returnBody", "")。
	 * @param strict
	 *            是否去除非限定的策略字段，默认true
	 * @return Token
	 */
	public String getUploadToken(Auth auth, String bucket, String key,
			long expires, StringMap policy, boolean strict) {
		return auth.uploadToken(bucket, key, expires, policy, strict);
	}
}
