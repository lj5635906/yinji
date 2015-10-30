package com.yinji.sms.bean;

import java.sql.Timestamp;

/**
 * @author : Roger
 * @date : 2015-10-30
 * @desc : 短信信息bean
 */
public class SMSBean {

	/**
	 * 序号
	 */
	private String msgId;

	/**
	 * 设备ID
	 */
	private String monitorId;

	/**
	 * 设备编号
	 */
	private String monitorCode;

	/**
	 * 指令类型
	 */
	private String msgType;

	/**
	 * 指令内容
	 */
	private String content;

	/**
	 * 状态
	 */
	private int status;

	/**
	 * 消息创建时间
	 */
	private Timestamp createTime;

	/**
	 * 流水号
	 */
	private String waterNo;

	/**
	 * 消息更新时间
	 */
	private Timestamp updateTime;

	/**
	 * 短信号
	 */
	private String mobileCode;

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMonitorId() {
		return monitorId;
	}

	public void setMonitorId(String monitorId) {
		this.monitorId = monitorId;
	}

	public String getMonitorCode() {
		return monitorCode;
	}

	public void setMonitorCode(String monitorCode) {
		this.monitorCode = monitorCode;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getWaterNo() {
		return waterNo;
	}

	public void setWaterNo(String waterNo) {
		this.waterNo = waterNo;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getMobileCode() {
		return mobileCode;
	}

	public void setMobileCode(String mobileCode) {
		this.mobileCode = mobileCode;
	}

}
