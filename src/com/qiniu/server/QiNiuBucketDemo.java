package com.qiniu.server;

import com.qiniu.common.QiniuException;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;

/**
 * @author Roger
 * @date 2015年10月28日
 * @remark 七牛目录管理
 */
public class QiNiuBucketDemo {

	private BucketManager bucketManager;

	/**
	 * 初始化空间管理器
	 * 
	 * @param auth
	 *            Auth
	 */
	private void initBucketManager(Auth auth) {
		bucketManager = new BucketManager(auth);
	}

	/**
	 * 获取空间列表
	 * 
	 * @param auth
	 *            Auth
	 */
	public String[] getBuckets(Auth auth) {
		// 初始化空间管理器
		initBucketManager(auth);
		try {
 			// 获取空间列表
			return bucketManager.buckets();
		} catch (QiniuException e) {
			e.printStackTrace();
			return null;
		}
	}
	

	/**
	 * 获取某空间下所有资源信息
	 * 
	 * @param auth
	 *            Auth
	 * @param bucket
	 *            空间名
	 * @param prefix
	 *            前缀.可以为null
	 * @return FileInfo[]
	 */
	public FileInfo[] getFileList(Auth auth, String bucket, String prefix) {
		// 初始化空间管理器
		initBucketManager(auth);
		// 获取空间资源
		BucketManager.FileListIterator it = bucketManager
				.createFileListIterator(bucket, prefix, 100, null);
		while (it.hasNext()) {
			return it.next();
		}
		return null;
	}

	/**
	 * 查看空间(bucket)下资源名为key的信息
	 * 
	 * @param auth
	 *            Auth
	 * @param bucket
	 *            空间名
	 * @param key
	 *            资源名称
	 * @return FileInfo
	 */
	public FileInfo getFileInfo(Auth auth, String bucket, String key) {
		// 初始化空间管理器
		initBucketManager(auth);
		try {
			return bucketManager.stat(bucket, key);
		} catch (QiniuException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 复制资源
	 * 
	 * @param auth
	 *            Auth
	 * @param fromBucket
	 *            源空间
	 * @param fromKey
	 *            源资源名
	 * @param targetBuckey
	 *            目标空间
	 * @param targetKey
	 *            目标资源名
	 */
	public void copy(Auth auth, String fromBucket, String fromKey,
			String targetBuckey, String targetKey) {
		// 初始化空间管理器
		initBucketManager(auth);
		try {
			bucketManager.copy(fromBucket, fromKey, targetBuckey, targetKey);
		} catch (QiniuException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 重命名
	 * 
	 * @param auth
	 *            Auth
	 * @param bucket
	 *            空间名
	 * @param oldKey
	 *            资源原名称
	 * @param newKey
	 *            资源新名称
	 */
	public void rename(Auth auth, String bucket, String oldKey, String newKey) {
		// 初始化空间管理器
		initBucketManager(auth);
		try {
			bucketManager.rename(bucket, oldKey, newKey);
		} catch (QiniuException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 移动资源
	 * 
	 * @param auth
	 *            Auth
	 * @param fromBucket
	 *            源空间
	 * @param fromKey
	 *            源资源名
	 * @param targetBuckey
	 *            目标空间
	 * @param targetKey
	 *            目标资源名
	 */
	public void move(Auth auth, String fromBucket, String fromKey,
			String targetBuckey, String targetKey) {
		// 初始化空间管理器
		initBucketManager(auth);
		try {
			bucketManager.move(fromBucket, fromKey, targetBuckey, targetKey);
		} catch (QiniuException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 移除资源
	 * 
	 * @param auth
	 *            Auth
	 * @param bucket
	 *            空间名
	 * @param key
	 *            资源名
	 */
	public void delete(Auth auth, String bucket, String key) {
		// 初始化空间管理器
		initBucketManager(auth);
		try {
			bucketManager.delete(bucket, key);
		} catch (QiniuException e) {
			e.printStackTrace();
		}
	}
}
