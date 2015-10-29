package com.yinji.image.bean;

/**
 * @author : Roger
 * @date : 2015-10-29
 * @desc : 云存储上传信息
 */
public class UploadInfoBean {

	/**
	 * 库存图片id
	 */
	private String imageId;
	/**
	 * 返回码
	 */
	private int code;
	
	/**
	 * 云存储中的资源名
	 */
	private String key;

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
