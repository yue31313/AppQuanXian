package com.phone.SMScheck;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;
public class SMSR extends BroadcastReceiver {
	String phone_NO;
	String sms_data;
	String sms_time;
	//�������ݿ������
	SMS baochun = new SMS();
	SMSBlack bb=new SMSBlack();
	public static int MODE = Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE;
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		//this.abortBroadcast();
		bb.OpenCreat(arg0);
		Cursor cur=bb.QureyCount(arg0);
		cur.moveToFirst();
			Bundle bundle = arg1.getExtras();//��ȡintent�е�����
			SmsMessage sms = null;
			if (bundle != null) {
			Object[] pdus = (Object[]) bundle.get("pdus");//��ȡbundle���������
			for (Object obj : pdus) {
			//�������н���������ȡ�����뵽message��
			sms = SmsMessage.createFromPdu((byte[]) obj);
			//���Ͷ��ŵ��ֻ����룻
			phone_NO = sms.getDisplayOriginatingAddress();
			//��������
			sms_data = sms.getDisplayMessageBody();
			//���ŷ��͵�ʱ��
			Date date = new Date(sms.getTimestampMillis());//�õ����Ͷ��ž���ʱ��
		    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//Ϊʵ�����ø�ʽ
			sms_time = format.format(date);	
			if(cur!=null){
				baochun.OpenCreat(arg0);
			    baochun.CreatTable();
			    int curindex=0;
				do{
					if(cur.getString(cur.getColumnIndex("obligate")).equals("0")){
					if(cur.getString(cur.getColumnIndex("phoneno")).equals(phone_NO))
					{
					    baochun.AddData(phone_NO, phone_NO, sms_data, sms_time,"2");//���ض���
					    curindex=10;
					    cur.moveToLast();
					    this.abortBroadcast();
					}
					}
					else if(cur.getString(cur.getColumnIndex("obligate")).equals("1")){ 
						String no=cur.getString(cur.getColumnIndex("sendphoneno"));
						if(no.equals(phone_NO)){
							Toast.makeText(arg0, "dd"+phone_NO,1).show();
							String nno=cur.getString(cur.getColumnIndex("phoneno"));
					  send(arg0,nno, sms_data);//ת��
					  curindex=10;
					  cur.moveToLast();
					}
					}
					else if(cur.getString(cur.getColumnIndex("obligate")).equals("2")){
						String no=cur.getString(cur.getColumnIndex("jiantingno"));
						if(no.equals(phone_NO)){
					    baochun.AddData(phone_NO, phone_NO, sms_data, sms_time,"1");//��
					    curindex=10;
					    cur.moveToLast();
					    this.abortBroadcast();
					  }		
					}
				     }
				  while(cur.moveToNext());
				if(curindex!=10){
					baochun.AddData(phone_NO, phone_NO, sms_data, sms_time,"0");//��������
				}
				baochun.Close();
			}
    	}}}
	
	public void send(Context c,String s,String str)
	{
	     final String sss=s;
				Toast.makeText(c, "ת���ɹ�", 1).show();
				  baochun.AddData( phone_NO, sss, sms_data, sms_time,"3" );
		  SmsManager smsManager=SmsManager.getDefault(); 
	       PendingIntent intent=PendingIntent.getBroadcast(c, 0,new Intent(), 0); 
	       //������Ϣ 
	     smsManager.sendTextMessage(phone_NO, null, s, intent, null); 
	     

	}
}
