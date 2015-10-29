package com.yinji.sms.quartz;

public class SMSQuartz {
	/**
	 * quartz调用的方法
	 */
	public void execute(){
		try {
			System.out.println("SMSQuartz开始作业调度了..");
 		} catch (Exception e) {
 			e.printStackTrace();
		} 
	}
}
