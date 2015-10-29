package com.qiniu.constant;

/**
 * @author Roger
 * @date 2015年10月28日
 * @remark
 */
public class Constant {

	/**
	 * Access Key
	 */
	public static final String ACCESS_KEY = "uUmwyxZQSnTtgVZTy80PYM6gCQgF2Q7OKwgJkAyf";

	/**
	 * Secret Key
	 */
	public static final String SECRET_KEY = "q8iA5VvtIS2mFLetbiQW6hMhV-3zni2SB9GilWr-";

	/**
	 * 设置断点文件保存的位置： pathFile变量表示断点记录文件所在 “文件夹” 的路径
	 */
	public static final String BREAK_POINT_UPLOAD_FILEPATH = "F:\\";

	/**
	 * 七牛域名
	 */
	public static final String DOMAIN_NAME = "http://7xnux8.com1.z0.glb.clouddn.com";

	/**
	 * 空间名称
	 */
	public static final String BUCKET = "lj5635906";
	
	/**
	 * 上传失败自定义码
	 */
	public static final int UPLOAD_ERROR = 444;
	
	/**
	 * @author Roger
	 * @date 2015年10月29日
	 * @remark HTTP状态码
	 */
	public interface HttpCode {
		/**
		 * 操作执行成功。
		 */
		int code_200 = 200;

		/**
		 * 部分操作执行成功。
		 */
		String code_298 = "298";

		/**
		 * 请求报文格式错误。（包括上传时，上传表单格式错误；URL触发图片处理时，处理参数错误；资源管理操作或触发持久化处理（pfop）
		 * 操作请求格式错误）
		 */
		String code_400 = "400";

		/**
		 * 认证授权失败。（包括密钥信息不正确；数字签名错误；授权已超时）
		 */
		String code_401 = "401";

		/**
		 * 资源不存在。（包括空间资源不存在；镜像源资源不存在）
		 */
		String code_404 = "404";
		/**
		 * 请求方式错误。（主要指非预期的请求方式）
		 */
		String code_405 = "405";

		/**
		 * 上传的数据 CRC32 校验错误。
		 */
		String code_406 = "406";

		/**
		 * 用户账号被冻结
		 */
		String code_419 = "419";

		/**
		 * 镜像回源失败。（主要指镜像源服务器出现异常）
		 */
		String code_478 = "478";

		/**
		 * 服务端不可用。
		 */
		String code_503 = "503";

		/**
		 * 服务端操作超时。
		 */
		String code_504 = "504";

		/**
		 * 上传成功但是回调失败。（包括业务服务器异常；七牛服务器异常；服务器间网络异常）
		 */
		String code_579 = "579";

		/**
		 * 服务端操作失败。
		 */
		String code_599 = "599";

		/**
		 * 资源内容被修改。
		 */
		String code_608 = "608";

		/**
		 * 指定资源不存在或已被删除
		 */
		String code_612 = "612";

		/**
		 * 目标资源已存在。
		 */
		String code_614 = "614";

		/**
		 * 已创建的空间数量达到上限，无法创建新空间。
		 */
		String code_630 = "630";

		/**
		 * 指定空间不存在。
		 */
		String code_631 = "631";

		/**
		 * 调用列举资源（list）接口时，指定非法的marker参数。
		 */
		String code_640 = "640";

		/**
		 * 在断点续上传过程中，后续上传接收地址不正确或ctx信息已过期。
		 */
		String code_701 = "701";

	}

}
