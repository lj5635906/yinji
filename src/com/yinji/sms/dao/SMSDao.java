package com.yinji.sms.dao;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author : Roger
 * @date : 2015-10-29
 * @desc : 短信Dao
 */
@Repository(value = "smsDao")
public class SMSDao {

	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	/**
	 * 获取短信序列
	 * 
	 * @return Long
	 */
	public long getSeq() {
		String sql = "SELECT SEQ_SMS_ID.NEXTVAL FROM DUAL";
		return jdbcTemplate.queryForLong(sql);
	}

}
