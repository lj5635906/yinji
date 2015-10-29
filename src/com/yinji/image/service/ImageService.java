package com.yinji.image.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qiniu.constant.Constant.HttpCode;
import com.yinji.image.bean.ImageBean;
import com.yinji.image.bean.UploadInfoBean;
import com.yinji.image.dao.ImageDao;

/**
 * @author : Roger
 * @date : 2015-10-29
 * @desc : 图片Service
 */
@Service(value = "imageService")
public class ImageService {

	@Resource(name = "imageDao")
	private ImageDao imageDao;

	/**
	 * 查询没有上传到云存储的图片路径
	 * 
	 * @return 路径集合
	 */
	public List<ImageBean> getImages() {
		List<Map<String, Object>> list = imageDao.getList();
		// 路径集合
		List<ImageBean> paths = null;
		if (null != list && list.size() > 0) {

			ImageBean image = null;
			paths = new ArrayList<ImageBean>();
			Map<String, Object> path = null;

			for (int i = 0; i < list.size(); i++) {
				path = list.get(i);
				if (null != path && path.size() > 0) {
					image = new ImageBean();
					image.setPath(String.valueOf(path.get("ImagePath")));
					image.setImageId(String.valueOf(path.get("imageId")));
					paths.add(image);
				}
			}
		}
		return paths;
	}

	/**
	 * 修改图片上传标示
	 * 
	 * @param uploadInfos
	 *            当前批次上传信息
	 * @throws Exception
	 */
	@Transactional
	public void update(List<UploadInfoBean> uploads) throws Exception {
		if (null == uploads || uploads.size() == 0) {
			return;
		}
		// 存放所有修改sql
		List<String> sqls = new ArrayList<String>();
 		UploadInfoBean bean = null;
		StringBuffer sql = new StringBuffer("");
		for (int i = 0; i < uploads.size(); i++) {
			bean = uploads.get(i);
			if (null != bean) {
				if (HttpCode.code_200 == bean.getCode()) {
					sql = new StringBuffer("UPDATE SET DataStatus = 1,");
					sql.append("ImageCloudPath = '");
					sql.append(bean.getKey()+"' ");
					sql.append("WHERE ImageId = '"+bean.getImageId()+"'");
					sqls.add(sql.toString());
				}
			}
		}

		imageDao.batchUpdate(sqls);
	}

}
