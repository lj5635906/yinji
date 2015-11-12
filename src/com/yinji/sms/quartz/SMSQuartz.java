package com.yinji.sms.quartz;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.yinji.sms.bean.AlarmBean;
import com.yinji.sms.bean.SMSBean;
import com.yinji.sms.service.SMSService;
import com.yinji.sms.util.SendSms;

/**
 * @author : Roger
 * @date : 2015-10-30
 * @desc : 短信定时任务
 */
public class SMSQuartz {

	@Resource(name = "smsService")
	private SMSService smsService;

	/**
	 * 任务执行方法
	 */
	public void execute() {
		System.out.println("SMSQuartz开始作业调度了..");

		// --------------------第一次发送短信--------------------------//
		fristSend();
		// --------------------第一次发送短信--------------------------//

		// --------------------第二次发送短信--------------------------//
		secondSend();
		// --------------------第二次发送短信--------------------------//

		// --------------------第三次发送短信--------------------------//
		thirdSend();
		// --------------------第三次发送短信--------------------------//
		
		// --------------------发送短信失败----------------------------//
		sendFail();
		// --------------------发送短信失败----------------------------//

	}

	/**
	 * 发送短信失败
	 */
	public void sendFail() {
		// 发送短信失败集合
		List<SMSBean> sendFail = smsService.querySMS(3);
		SMSBean bean = null;
		if (null != sendFail && sendFail.size() > 0) {
			for (int i = 0; i < sendFail.size(); i++) {
				bean = sendFail.get(i);
				try {
					// 更新状态
					smsService.updateStatus(bean.getMsgId(), 3, 200);

					// 写告警表
					smsService.insertAlarm(new AlarmBean(bean.getMonitorId(),
							bean.getMsgType(), "D5", 0));
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		}
	}

	/**
	 * 第三次发送短信
	 */
	public void thirdSend() {
		// 第三次发送短信集合
		List<SMSBean> thirdSend = smsService.querySMS(2);
		SMSBean bean = null;
		if (null != thirdSend && thirdSend.size() > 0) {
			for (int i = 0; i < thirdSend.size(); i++) {
				bean = thirdSend.get(i);
				try {
					// 拼装发送报文
					sendSMS(bean);
					// 更新状态和时间
					smsService.updateStatuAndTime(bean.getMsgId(), 2, 3);
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		}
	}

	/**
	 * 第二次发送
	 */
	public void secondSend() {
		// 第二次发送短信集合
		List<SMSBean> secondSend = smsService.querySMS(1);
		SMSBean bean = null;
		if (null != secondSend && secondSend.size() > 0) {
			for (int i = 0; i < secondSend.size(); i++) {
				bean = secondSend.get(i);
				try {
					// 拼装发送报文
					sendSMS(bean);
					// 更新状态和时间
					smsService.updateStatuAndTime(bean.getMsgId(), 1, 2);
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		}
	}

	/**
	 * 第一次发送短信
	 */
	public void fristSend() {
		// 第一次发送短信集合
		List<SMSBean> fristSend = smsService.querySMS(0);

		String waterNo = null;
		SMSBean bean = null;

		if (null != fristSend && fristSend.size() > 0) {
			for (int i = 0; i < fristSend.size(); i++) {
				bean = fristSend.get(i);
				try {
					waterNo = bean.getWaterNo();
					if (null == waterNo || "".equals(waterNo) || "null".equals(waterNo)) {
						// 获取流水号
						waterNo = smsService.createWaterNo();
						// 更新流水号
						smsService.updateWaterNo(bean.getMsgId(), waterNo);
						bean.setWaterNo(waterNo);
					}
					// 拼装发送报文
					sendSMS(bean);
					// 更新状态和时间
					smsService.updateStatuAndTime(bean.getMsgId(), 0, 1);
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		}
	}

	/**
	 * 拼装报文，发送短信
	 */
	public void sendSMS(SMSBean bean) {
		String mobile = bean.getMobileCode();
		if(null == mobile){
			return ;
		}
		
		// 获取参数信息
		Map<String, String> properties = smsService.queryProperty();
		// 起始标记
		String start = "F2F2";
		// 数据网管IP地址
		String ipaddr = properties.get("GateWayIp");
		// 数据网管端口
		String port = properties.get("GateWayPort");
		// 设备标识
		String monitorCode = bean.getMonitorCode();
		// 流水号
		String waterNo = bean.getWaterNo();
		// 功能
		String function = bean.getMsgType();
		// 数据参数
		String content = bean.getContent();
		
		String message = start + ipaddr + port
				+ monitorCode + waterNo + function + content;
		System.out.println("短信内容 : "+message);
		SendSms sms = new SendSms("13880469946", message);
//		SendSms sms = new SendSms(mobile, message);
		sms.SendMsg();
	}
}
