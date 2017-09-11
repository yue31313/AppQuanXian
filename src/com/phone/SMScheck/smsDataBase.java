package com.phone.SMScheck;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.app.Activity;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
//对短信的数据库进行监听
//通过ContentObserver监听数据库的变化；
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
	            if (cursor.getCount() != 0){//SMS数据库不为空时；
	                cursor.moveToFirst();  
	                //监听短信数据库最新数据变化     
	                String id = cursor.getString(cursor.getColumnIndex("_id"));//接收号码
	                String address = cursor.getString(cursor.getColumnIndex("address"));//接收号码
	                String type = cursor.getString(cursor.getColumnIndex("type"));//SMS格式；
	                body = cursor.getString(cursor.getColumnIndex("body"));//SMS内容
                   //处理时间格式
	                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	                Date time=new Date(cursor.getLong(cursor.getColumnIndex("date")));
	                String dat = format.format(time);
	                //与数据库中的短信做对比，判断收到的短信是否是隐秘短信；
	                SMS smsdata = new SMS();
	        		smsdata.OpenCreat(a.getBaseContext());
	        		Cursor cursor = smsdata.QureyCount(a.getBaseContext());
	        		cursor.moveToFirst();
	        		//短信内容
	        		String smsData = cursor.getString(cursor.getColumnIndex("content"));
	        		int sms_id = cursor.getColumnIndex("_id");
	        		if(body.equals(smsData))
	        		{
	        			//正常短信
	        		}else
	        		{
	        			//隐秘短信
	        			smsdata.UpData(a.getBaseContext(), sms_id, address, null, body, dat, "1");
	        		}
	            } 

	            }
	   }      
}