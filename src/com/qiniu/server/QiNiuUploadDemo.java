package com.qiniu.server;

import java.io.File;

import com.qiniu.common.QiniuException;
import com.qiniu.constant.Constant;
import com.qiniu.http.Response;
import com.qiniu.storage.Recorder;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.StringMap;

/**
 * @author Roger
 * @date 2015年10月28日
 * @remark 七牛云存储上传Demo
 */
public class QiNiuUploadDemo {

	/**
	 * 七牛上传控制器
	 */
	static UploadManager uploadManager = new UploadManager();
	/**
	 * 七牛上传控制器(断点续传)
	 */
	static UploadManager uploadManagerRecord = null;
	/**
	 * 设置断点文件保存的位置
	 */
	static Recorder recorder = null;

	/**
	 * 上传文件
	 * 
	 * @param filePath
	 *            文件路径
	 * @param key
	 *            上传后资源名称
	 * @param token
	 *            Token
	 * @return Response
	 * @throws QiniuException
	 */
	public Response upload(String filePath, String key, String token)
			throws QiniuException {
		return uploadManager.put(filePath, key, token);
	}

	/**
	 * 上传文件
	 * 
	 * @param data
	 *            上传资源
	 * @param key
	 *            上传后资源名称
	 * @param token
	 *            Token
	 * @return Response
	 * @throws QiniuException
	 */
	public Response upload(byte[] data, String key, String token)
			throws QiniuException {
		return uploadManager.put(data, key, token);
	}

	/**
	 * 上传文件
	 * 
	 * @param file
	 *            文件File
	 * @param key
	 *            上传后资源名称
	 * @param token
	 *            Token
	 * @return Response
	 * @throws QiniuException
	 */
	public Response upload(File file, String key, String token)
			throws QiniuException {
		return uploadManager.put(file, key, token);
	}

	/**
	 * 上传文件
	 * 
	 * @param data
	 *            上传资源
	 * @param key
	 *            上传后资源名称
	 * @param token
	 *            Token
	 * @param params
	 *            指定自定义变量
	 * @param mime
	 *            指定mimetype
	 * @param checkCrc
	 *            使用crc32检查文件完整性
	 * @return Response
	 * @throws QiniuException
	 */
	public Response upload(byte[] data, String key, String token,
			StringMap params, String mime, boolean checkCrc)
			throws QiniuException {
		return uploadManager.put(data, key, token, params, mime, checkCrc);
	}

	/**
	 * 上传文件
	 * 
	 * @param filePath
	 *            资源路径
	 * @param key
	 *            上传后资源名称
	 * @param token
	 *            Token
	 * @param params
	 *            指定自定义变量
	 * @param mime
	 *            指定mimetype
	 * @param checkCrc
	 *            使用crc32检查文件完整性
	 * @return Response
	 * @throws QiniuException
	 */
	public Response upload(String filePath, String key, String token,
			StringMap params, String mime, boolean checkCrc)
			throws QiniuException {
		return uploadManager.put(filePath, key, token, params, mime, checkCrc);
	}

	/**
	 * 上传文件
	 * 
	 * @param file
	 *            资源File
	 * @param key
	 *            上传后资源名称
	 * @param token
	 *            Token
	 * @param params
	 *            指定自定义变量
	 * @param mime
	 *            指定mimetype
	 * @param checkCrc
	 *            使用crc32检查文件完整性
	 * @return Response
	 * @throws QiniuException
	 */
	public Response upload(File file, String key, String token,
			StringMap params, String mime, boolean checkCrc)
			throws QiniuException {
		return uploadManager.put(file, key, token, params, mime, checkCrc);
	}

	/**
	 * 上传文件，支持断点续传
	 * 
	 * @param filePath
	 *            上传资源路径
	 * @param key
	 *            上传后资源名称
	 * @param token
	 *            Token
	 * @return Response
	 * @throws Exception
	 */
	public Response breakpointUpload(String filePath, String key, String token)
			throws Exception {
		// 默认实现，设置断点文件保存的位置： pathFile变量表示断点记录文件所在 “文件夹” 的路径或其表示的File对象
		if (null == recorder) {
			recorder = new FileRecorder(Constant.BREAK_POINT_UPLOAD_FILEPATH);
		}
		// 重用 uploadManager。一般地，只需要创建一个 uploadManager 对象
		if (null == uploadManagerRecord) {
			uploadManagerRecord = new UploadManager(recorder);
		}
		return uploadManagerRecord.put(filePath, key, token);
	}

}
