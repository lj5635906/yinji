package com.yinji.sms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.yinji.sms.bean.AlarmBean;
import com.yinji.sms.bean.SMSBean;
import com.yinji.sms.dao.SMSDao;

/**
 * @author : Roger
 * @date : 2015-10-29
 * @desc : 短信Service
 */
@Service(value = "smsService")
public class SMSService {

	@Resource(name = "smsDao")
	private SMSDao smsDao;

	/**
	 * 获取序列号
	 * 
	 * @return long
	 */
	public Long getSeq() {
		return smsDao.getSeq();
	}

	/**
	 * 创建12位的流水号
	 * 
	 * @return
	 */
	public String createWaterNo() {
		// 获取序列号
		Long seq = getSeq();
		return StringUtils.leftPad(String.valueOf(seq), 12, "0");
	}

	/**
	 * 通过状态查询发送短信结果集
	 * 
	 * @param status
	 *            状态
	 * @return List<SMSBean>
	 */
	public List<SMSBean> querySMS(int status) {
		// 查询sql
		StringBuffer sql = null;
		if(0 == status){
			sql =new StringBuffer("SELECT m.MsgId MsgId,m.MonitorID MonitorID,m.MonitorCode MonitorCode,m.MsgType MsgType,m.Content Content,m.CreateTime CreateTime,m.WaterNo WaterNo,s.MobileCode MobileCode FROM MsgQueueCache m ");
			sql.append("left join SYSUR_CathdMonitor s on m.monitorid = s.monitorid ");
			// 第一次发送
			sql.append("WHERE Status = "+status);
		}else{
			sql =new StringBuffer("SELECT m.MsgId MsgId,m.MonitorID MonitorID,m.MonitorCode MonitorCode,m.MsgType MsgType,m.Content Content,m.CreateTime CreateTime,m.WaterNo WaterNo,s.MobileCode MobileCode FROM MsgQueueCache m ");
			sql.append("left join SYSUR_CathdMonitor s on m.monitorid = s.monitorid ");
			sql.append("WHERE Status = "+status);
			sql.append(" AND CEIL((");
			sql.append("to_date(TO_CHAR(systimestamp , 'yyyy-mm-dd hh24:mi:ss'),'YYYY-MM-DD HH24:mi:ss')");
			sql.append(" - ");
			sql.append("to_date(TO_CHAR(UpdateTime , 'yyyy-mm-dd hh24:mi:ss'),'YYYY-MM-DD HH24:mi:ss')");
			sql.append(") * 24 * 60 * 60)");
			sql.append(" > 300 ");
		}
		// 第一次发送结果集
		List<Map<String, Object>> lm = smsDao.query(sql.toString());
 		if (null == lm || lm.size() == 0) {
			return null;
		}

		List<SMSBean> beans = new ArrayList<SMSBean>();
		SMSBean bean = null;
		for (int i = 0; i < lm.size(); i++) {
			Map<String, Object> map = lm.get(i);
			if (null != map && map.size() > 0) {
				bean = new SMSBean();
				bean.setContent(String.valueOf(map.get("Content")));
//				bean.setCreateTime(Timestamp.valueOf(String
//						.valueOf("CreateTime")));
				bean.setMonitorCode(String.valueOf(map.get("MonitorCode")));
				bean.setMonitorId(String.valueOf(map.get("MonitorID")));
				bean.setMsgId(String.valueOf(map.get("MsgId")));
				bean.setMsgType(String.valueOf(map.get("MsgType")));
				bean.setStatus(status);
				bean.setWaterNo(String.valueOf(map.get("WaterNo")));
				bean.setMobileCode(String.valueOf(map.get("MobileCode")));
				beans.add(bean);
			}
		}
		return beans;
	}

	/**
	 * 更新短信表流水
	 * 
	 * @param msgId
	 *            序号
	 * @param waterNo
	 *            流水号
	 * @throws Exception
	 */
	@Transactional
	public void updateWaterNo(String msgId, String waterNo) throws Exception {
		smsDao.updateWaterNo(msgId, waterNo);
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
		smsDao.updateStatuAndTime(msgId, beforStatus, afterStatus);
	}
	
	/**
	 * 	短信发送失败更新状态
	 * @param msgId
	 * 		序号
	 * @param beforStatus
	 * 		更新前状态
	 * @param afterStatus
	 * 		更新后状态
	 * @throws Exception
	 */
	public void updateStatus(String msgId, int beforStatus,
			int afterStatus) throws Exception {
		smsDao.updateStatus(msgId, beforStatus, afterStatus);
	}
	
	/**
	 * 插入告警表(Alarm)
	 * 
	 * @param alarm
	 *            Alarm
	 * @throws Exception 
	 */
	public void insertAlarm(AlarmBean alarm) throws Exception {
		smsDao.insertAlarm(alarm);
	}
	
	/**
	 * 获取参数信息
	 * @return
	 * 	Map<String,String>
	 */
	public Map<String,String> queryProperty(){
		String sql = "SELECT ParamName,ParamValue FROM Property";
		
		List<Map<String, Object>> lm = smsDao.query(sql.toString());
		if (null == lm || lm.size() == 0) {
			return null;
		}

		Map<String,String> propertis = new HashMap<String,String>();
		
 		for (int i = 0; i < lm.size(); i++) {
			Map<String, Object> map = lm.get(i);
			if (null != map && map.size() > 0) {
				propertis.put(String.valueOf(map.get("ParamName")), String.valueOf(map.get("ParamValue")));
			}
		}
		
 		return propertis;
	}

}
