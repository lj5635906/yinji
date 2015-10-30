package com.yinji.sms.bean;

import java.sql.Timestamp;

/**
 * @author : Roger
 * @date : 2015-10-30
 * @desc : 告警表(Alarm)
 */
public class AlarmBean {
	
	public AlarmBean(){}

	public AlarmBean(String monitorID, String content, String alarmType,
			int status) {
		super();
		this.monitorID = monitorID;
		this.content = content;
		this.alarmType = alarmType;
		this.status = status;
 	}


	/**
	 * 序号
	 */
	private String noticeId;
	
	/**
	 * 设备ID
	 */
	private String monitorID;
	
	/**
	 * 告警内容
	 */
	private String content;
	
	/**
	 * 告警类型
	 */
	private String alarmType;
	
	/**
	 * 状态
	 */
	private int status;
	
	/**
	 * 时间戳
	 */
	private Timestamp createTime;

	public String getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	public String getMonitorID() {
		return monitorID;
	}

	public void setMonitorID(String monitorID) {
		this.monitorID = monitorID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
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
}
