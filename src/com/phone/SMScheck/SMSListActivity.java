package com.phone.SMScheck;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.phonesafe.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
public class SMSListActivity extends Activity {
	public static final int FILTER_ALL_SMS = 0; // 所有短信
	public static final int FILTER_SYSTEM_SMS = 1; // 手机系统电信
	public static final int FILTER_SECRET_SMS = 2; // 隐秘短信
	private ListView smslistview = null;
	private List<SMSData> mlistSMSInfo  = new ArrayList<SMSData>();
	private smsBaseAdapter smsList = null;
	private static List<SMSData> yinmi=new ArrayList<SMSData>();
	private int filter;
	public SMSData smsData = new SMSData();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sms_list);
		if(getIntent()!=null){
			filter = getIntent().getIntExtra("filter", 0) ;
		}
		intint();
	 }
	private void intint() {
		smslistview = (ListView) findViewById(R.id.sms_list_q);
		mlistSMSInfo = qusms();
		smsList = new smsBaseAdapter(this, mlistSMSInfo);
		smslistview.setAdapter(smsList);//调用ListView的适配器
	  }
	 private List<SMSData> qusms() {
		List<SMSData> SMSInfo=new ArrayList<SMSData>();
		switch (filter)
     		{
		case 0:
			SMSInfo = getAllSMS();
			break;
		case 1:
			//获取手机系统短信
			SMSInfo = getSystemSMS();
			break;
		case 2:
			//查看隐秘短信
			SMSInfo = getSecret();
			break;
		case 3:
			//查看拦截短信
			SMSInfo = getIntercept();
			break;
		case 4:
			//查看转发短信
			SMSInfo = getSendSMS();
			break;
	   	default:
			break;
		}
		return SMSInfo;
	}
	private List<SMSData> getSendSMS() {
		List<SMSData> SMSInfo = new ArrayList<SMSData>();
		SMS smsdata = new SMS();
		smsdata.OpenCreat(this);
		smsdata.CreatTable();
		Cursor cursor = smsdata.QureyCount(this);
		cursor.moveToFirst();
		while(cursor.moveToNext())
		{
			SMSData sms = new SMSData();
			int no = cursor.getColumnIndex("rphoneno");//发送短信的号码
			int desno = cursor.getColumnIndex("phoneno");//geishui
			int data = cursor.getColumnIndex("content");//短信内容
			int time = cursor.getColumnIndex("sendtime");//短信发送时间
			int id = cursor.getColumnIndex("obligate");
			if((cursor.getString(id)).equals("3"))
			{   
				sms.setmessage(cursor.getString(desno));
				sms.setphoneNo(cursor.getString(data));
				sms.setsendtime(cursor.getString(time));
				sms.setis("发送至:"+cursor.getString(no));
				SMSInfo.add(sms);
			} 
			
		}
		return SMSInfo;
	}
	private List<SMSData> getIntercept() {
		List<SMSData> SMSInfo = new ArrayList<SMSData>();
		SMS smsdata = new SMS();
		smsdata.OpenCreat(this);
		smsdata.CreatTable();
		Cursor cursor = smsdata.QureyCount(this);
		cursor.moveToFirst();
		
			do
		{
			SMSData sms = new SMSData();
			int no = cursor.getColumnIndex("phoneno");//发送短信的号码
			int data = cursor.getColumnIndex("content");//短信内容
			int time = cursor.getColumnIndex("sendtime");//短信发送时间
			int id = cursor.getColumnIndex("obligate");
			if((cursor.getString(id)).equals("2"))
			{
				sms.setmessage(cursor.getString(data));
				sms.setphoneNo(cursor.getString(no));
				sms.setsendtime(cursor.getString(time));
				sms.setis("被拦截短信");
				SMSInfo.add(sms);
			}
		}while(cursor.moveToNext());
		return SMSInfo;
    	}
	private List<SMSData> getSecret() {
		List<SMSData> SMSInfo = new ArrayList<SMSData>();
		List<SMSData> SMSall = new ArrayList<SMSData>();
		SMS smsdata = new SMS();
		List<SMSData> xitong=getSystemSMS();
		smsdata.OpenCreat(this);
		smsdata.CreatTable();
		Cursor cursor = smsdata.QureyCount(this);
		cursor.moveToFirst();
		do
		{
			SMSData sms = new SMSData();
			int no = cursor.getColumnIndex("phoneno");//发送短信的号码
			int data = cursor.getColumnIndex("content");//短信内容
			int time = cursor.getColumnIndex("sendtime");//短信发送时间
			int id = cursor.getColumnIndex("obligate");
			 if(!(cursor.getString(id)).equals("2"))
			{
				 String num=cursor.getString(no);
				 String content=cursor.getString(data);
				 String shijian=cursor.getString(time);
					sms.setmessage(content);
					sms.setphoneNo(num);
					sms.setsendtime(shijian);
					sms.setis("隐秘短信");
					SMSall.add(sms);
				 
				 for (SMSData smsData2 : xitong) {
				
					if(smsData2.getphoneNo().equals(num)&&smsData2.getmessage().equals(content)
						){
					    SMSInfo.add(sms);
						break;
					}
				}
			}
		}while(cursor.moveToNext());
		SMSall.removeAll(SMSInfo);
		return SMSall;
	}

	private List<SMSData> getSystemSMS() {
		// TODO Auto-generated method stub
		List<SMSData> SMSInfo = new ArrayList<SMSData>();
		String[] projection = new String[] { "_id", "address", "person", "body", "date", "type" }; 
		Cursor  cursor = this.getContentResolver().query(Uri.parse("content://sms"), 
			    projection, null, null, "date desc");
          {
          if (cursor.getCount() != 0){//SMS数据库不为空时；
              cursor.moveToFirst();  
              //监听短信数据库最新数据变化
             while(cursor.moveToNext())
             {
              String id = cursor.getString(cursor.getColumnIndex("_id"));//接收号码
              String address = cursor.getString(cursor.getColumnIndex("address"));//接收号码
              String type = cursor.getString(cursor.getColumnIndex("type"));//SMS格式；
              String body = cursor.getString(cursor.getColumnIndex("body"));//SMS内容
             //处理时间格式
              SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
              Date time=new Date(cursor.getLong(cursor.getColumnIndex("date")));
              String dat = format.format(time);
              SMSData sms = new SMSData();
              sms.setmessage(body);
              sms.setphoneNo(address);
              sms.setsendtime(dat);
              sms.setis("系统短信");
              SMSInfo.add(sms);
             }     }
          }
  		return SMSInfo;
	}
	private List<SMSData> getAllSMS() {
		// TODO Auto-generated method stub
		List<SMSData> SMSInfo = new ArrayList<SMSData>();
		SMS smsdata = new SMS();
		smsdata.OpenCreat(this);
		smsdata.CreatTable();
		Cursor cursor = smsdata.QureyCount(this);
		cursor.moveToFirst();
		do
		{
			SMSData sms = new SMSData();
			int no = cursor.getColumnIndex("phoneno");//发送短信的号码
			int data = cursor.getColumnIndex("content");//短信内容
			int time = cursor.getColumnIndex("sendtime");//短信发送时间
			int id = cursor.getColumnIndex("obligate");
			
			if((cursor.getString(id)).equals("0"))
			{   sms.setmessage(cursor.getString(data));
			    sms.setphoneNo(cursor.getString(no));
			    sms.setsendtime(cursor.getString(time));
				sms.setis("正常短信");
				SMSInfo.add(sms);
			}
		}
		while(cursor.moveToNext());
		SMSInfo.addAll(this.getSystemSMS());
		return SMSInfo;
	}
	}
	
