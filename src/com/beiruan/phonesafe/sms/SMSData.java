package com.beiruan.phonesafe.sms;
public class SMSData {
	
	private String message;    //��������
	private String phoneNo ;  //�������ź���
	private String sendtime ;     //���Ž���ʱ��
	private String smsInt ;  //�����Ƿ������ض���

	
	public SMSData()
	{
		
	}
	
	public String getmessage() {
		return message;
	}
	public void setmessage(String message) {
		this.message = message;
	}
	public String getphoneNo() {
		return phoneNo;
	}
	public void setphoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getIntent() {
		return sendtime;
	}
	public void setsendtime(String sendtime) {
		this.sendtime = sendtime;
	}
	public String getis(){
		return smsInt ;
	}
	public void setis(String is){
		this.smsInt=is ;
	}
}

