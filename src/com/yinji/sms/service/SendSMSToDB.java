package com.yinji.sms.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author : Roger
 * @date : 2015-11-11
 * @desc : 将短信存储到数据库
 */
public class SendSMSToDB {

	/**
	 * 获取链接
	 * @return Connection
	 */
	private Connection getJDBC(){
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
 			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://192.168.20.25:3306/sms", "root", "root");
		} catch (SQLException e) {
 			e.printStackTrace();
		}
		return con;
	}
	
	public void saveSMS(String mobile,String message) throws Exception{
		Connection con = getJDBC();
		if(null == con){
			throw new Exception("不能获取MySQL链接");
		}
		Statement st = null;
		try {
			String sql = "insert into WBS_SMS_SendBuffer(MobileNo,SmsContent) values('"+mobile+"','"+message+"')";
			
			con.setAutoCommit(false);
			
			st = con.createStatement();
			
			int row = st.executeUpdate(sql);
			if(row > 0){
				con.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			con.rollback();
		}finally{
			st.close();
			con.close();
		}
	}
	
	public static void main(String[] args){
		
		SendSMSToDB send = new SendSMSToDB();
		try {
			send.saveSMS("18181996435", "这是测试用的...");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
