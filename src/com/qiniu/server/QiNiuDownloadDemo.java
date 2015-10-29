package com.qiniu.server;

import com.qiniu.constant.Constant;
import com.qiniu.util.Auth;

/**
 * @author Roger
 * @date 2015年10月28日
 * @remark 七牛云存储下载Demo
 */
public class QiNiuDownloadDemo {

	/**
	 * 返回下载链接
	 * 
	 * @param auth
	 *            Auth
	 * @param key
	 *            资源名称
	 * @return URL
	 */
	public String download(Auth auth, String key) {
		// 默认有效时长：3600秒,即链接过期时间
		return auth.privateDownloadUrl(getUrl(key));
	}

	/**
	 * 返回下载链接
	 * 
	 * @param auth
	 *            Auth
	 * @param key
	 *            资源名称
	 * @param expires
	 *            链接过期时间(以秒为单位)
	 * @return URL
	 */
	public String download(Auth auth, String key, long expires) {
		return auth.privateDownloadUrl(getUrl(key), expires);
	}

	/**
	 * 拼装链接
	 * 
	 * @param key
	 *            资源名称
	 * @return URL
	 */
	private String getUrl(String key) {
		String url = Constant.DOMAIN_NAME + "/" + key;
		return url;
	}

}
