package com.yinji.sms.dao;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.yinji.sms.bean.AlarmBean;

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

	/**
	 * 同过sql查询结果集
	 * 
	 * @param sql
	 *            查询语句
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> query(String sql) {
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 更新流水号
	 * 
	 * @param msgId
	 *            短信表序号
	 * @param waterNo
	 *            流水号
	 * @throws Exception
	 */
	public void updateWaterNo(String msgId, String waterNo) throws Exception {
		String sql = "UPDATE MsgQueueCache SET WaterNo = '" + waterNo + "' WHERE MsgId = '"
				+ msgId + "'";
		int row = jdbcTemplate.update(sql);
		if (row == 0) {
			throw new Exception("update waterNo fail");
		}
	}

	/**
	 * 短信发送成功后更新状态和时间
	 * 
	 * @param msgId
	 *            序号
	 * @param beforStatus
	 *            更新前状态
	 * @param afterStatus
	 *            更新后状态
	 * @throws Exception
	 */
	public void updateStatuAndTime(String msgId, int beforStatus,
			int afterStatus) throws Exception {
		String sql = "UPDATE MsgQueueCache SET Status = " + afterStatus + ",UpdateTime = SYSTIMESTAMP WHERE MsgId = '" + msgId + "' AND Status = " + beforStatus;
 		int row = jdbcTemplate.update(sql);
 		if (row == 0) {
			throw new Exception("update waterNo fail");
		}
	}

	/**
	 * 短信发送失败更新状态
	 * 
	 * @param msgId
	 *            序号
	 * @param beforStatus
	 *            更新前状态
	 * @param afterStatus
	 *            更新后状态
	 * @throws Exception
	 */
	public void updateStatus(String msgId, int beforStatus, int afterStatus)
			throws Exception {
		String sql = "UPDATE MsgQueueCache SET Status = " + afterStatus + " WHERE MsgId = '"
				+ msgId + "' AND Status = " + beforStatus;
 		int row = jdbcTemplate.update(sql);
 		if (row == 0) {
			throw new Exception("update waterNo fail");
		}
	}

	/**
	 * 插入告警表(Alarm)
	 * 
	 * @param alarm
	 *            Alarm
	 * @throws Exception 
	 */
	public void insertAlarm(AlarmBean alarm) throws Exception {
		String sql = "INSERT INTO Alarm(NoticeId,MonitorID,Content,AlarmType,Status,CreateTime) VALUES("
				+ "SEQ_ALARM.NEXTVAL,"
				+ "'"
				+ alarm.getMonitorID()
				+ "','"
				+ alarm.getContent()
				+ "','"
				+ alarm.getAlarmType()
				+ "',"
				+ alarm.getStatus() + ",systimestamp)";
		int row = jdbcTemplate.update(sql);
		if (row == 0) {
			throw new Exception("insert alarm fail");
		}
	}
	
}
