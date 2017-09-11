package com.phone.SMScheck;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.app.Activity;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
//�Զ��ŵ����ݿ���м���
//ͨ��ContentObserver�������ݿ�ı仯��
public class smsDataBase extends ContentObserver {
	private Cursor cursor = null;   
	 public String str;
	 public int i;
	 public Activity a;
	 public String body;
	 public smsDataBase(Activity c,Handler handler,String url) 
	 {   
      super(handler);   
      str = url;
      a = c;
    }
@Override  
	public void onChange(boolean selfChange) {   
	           // TODO Auto-generated method stub   
	            super.onChange(selfChange);   
	            cursor = a.managedQuery(Uri.parse(str), null, null, null, null); 
	            {
	            if (cursor.getCount() != 0){//SMS���ݿⲻΪ��ʱ��
	                cursor.moveToFirst();  
	                //�����������ݿ��������ݱ仯     
	                String id = cursor.getString(cursor.getColumnIndex("_id"));//���պ���
	                String address = cursor.getString(cursor.getColumnIndex("address"));//���պ���
	                String type = cursor.getString(cursor.getColumnIndex("type"));//SMS��ʽ��
	                body = cursor.getString(cursor.getColumnIndex("body"));//SMS����
                   //����ʱ���ʽ
	                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	                Date time=new Date(cursor.getLong(cursor.getColumnIndex("date")));
	                String dat = format.format(time);
	                //�����ݿ��еĶ������Աȣ��ж��յ��Ķ����Ƿ������ض��ţ�
	                SMS smsdata = new SMS();
	        		smsdata.OpenCreat(a.getBaseContext());
	        		Cursor cursor = smsdata.QureyCount(a.getBaseContext());
	        		cursor.moveToFirst();
	        		//��������
	        		String smsData = cursor.getString(cursor.getColumnIndex("content"));
	        		int sms_id = cursor.getColumnIndex("_id");
	        		if(body.equals(smsData))
	        		{
	        			//��������
	        		}else
	        		{
	        			//���ض���
	        			smsdata.UpData(a.getBaseContext(), sms_id, address, null, body, dat, "1");
	        		}
	            } 

	            }
	   }      
}