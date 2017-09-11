package com.phone.SMScheck;
public class SMSData {
	private String message;    //短信内容
	private String phoneNo ;  //发生短信号码
	private String sendtime ;     //短信接收时间
	private String smsInt ;  //短信是否是隐秘短信
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

