package com.yinji.sms.util;

import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import com.yinji.sms.service.SMSService;

/**
 * @author : Roger
 * @date : 2015-10-29
 * @desc : 生成12位的表主键
 */
public class KeyProduceCenter {
	
	@Resource(name = "smsService")
	private SMSService smsService;
	
	/**
	 * 	创建短信表12位的主键
	 * @return String
	 */
	public synchronized String creatKeyId(){
		// 获取序列号
		Long seq = smsService.getSeq();
		return StringUtils.leftPad(String.valueOf(seq), 12, "0");
	}
}
