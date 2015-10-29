package com.yinji.sms.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yinji.sms.dao.SMSDao;

/**
 * @author : Roger
 * @date : 2015-10-29
 * @desc : 短信Service
 */
@Service(value="smsService")
public class SMSService {

	@Resource(name="smsDao")
	private SMSDao smsDao;
	
	public Long getSeq(){
		return smsDao.getSeq();
	}
	
}
