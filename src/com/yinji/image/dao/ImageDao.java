package com.yinji.image.dao;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author : Roger
 * @date : 2015-10-29
 * @desc : 图片Dao
 */
@Repository(value = "imageDao")
public class ImageDao {

	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	/**
	 * 查询没有上传到云存储的图片路径
	 * 
	 * @return 路径集合
	 */
	public List<Map<String, Object>> getList() {
		String sql = "SELECT ImageId,ImagePath FROM UR_CathMonitorDataImage WHERE ImageCloudPath is null or trim(ImageCloudPath)=''";
 		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 	批量修改数据库
	 * @param sqls
	 * 		所有sql
	 * @throws Exception
	 */
	public void batchUpdate(List<String> sqls) throws Exception {
		if (null == sqls || sqls.size() == 0) {
			throw new Exception("batechUpdate sql is null");
		}
		this.jdbcTemplate.batchUpdate(sqls.toArray(new String[sqls.size()]));
	}
}
