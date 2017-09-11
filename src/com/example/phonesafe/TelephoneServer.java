package com.example.phonesafe;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.format.Time;
import android.widget.Toast;

public class TelephoneServer extends Service {
  public File wenjian;
  public File mRecordFile;
  int count=0;
  long starttime;
  PhoneBlackData dao=new PhoneBlackData();
  ContentValues contentvalue=new ContentValues();
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		TelephonyManager manager=(TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		manager.listen(new MyPhoneListener(),PhoneStateListener.LISTEN_CALL_STATE);	
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	}
 private class MyPhoneListener extends PhoneStateListener{
	 private String num;//记录电话号码
	 private MediaRecorder mRecorder;
	 @Override
	public void onCallStateChanged(int state, String incomingNumber) {
		switch (state) {		
		case TelephonyManager.CALL_STATE_RINGING:
			num=incomingNumber;	
			count++;
//			Toast.makeText(getApplicationContext(), "服务器来电是否输入"+num, 1000).show();
			break;
        case TelephonyManager.CALL_STATE_OFFHOOK:
        	count++;
        	dao.createdata(getApplicationContext());
        	Cursor curs=dao.query(PhoneBlackData.db_table3, num);
//        	Toast.makeText(getApplicationContext(), "来电的yy"+num, 1000).show();
//    		Toast.makeText(getApplicationContext(), "通话类型"+count, 1000).show();
        	int yy=0;
        	if(curs!=null){
        	if(curs.moveToFirst()){
        		yy++;
            	}
        	}
        	curs.close();
        	dao.close();
        	if(yy>0){
    	if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
    		 wenjian=Environment.getExternalStorageDirectory();
    		
    	}else {
			  Toast.makeText(getApplicationContext(), "没有", 1000).show();
		}  
    	try {
    		String nn=""+System.currentTimeMillis();
    		mRecordFile=File.createTempFile(nn,".amr",wenjian);
		} catch (Exception e) {
			e.printStackTrace();
		}	
    	mRecorder=new MediaRecorder();
    	mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
    	mRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
    	mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
    	mRecorder.setOutputFile(mRecordFile.getAbsolutePath());  	   	
    	   try {
			   mRecorder.prepare();
		} catch (Exception e) {
			e.printStackTrace();
		}  	   
    	   try {
    		   Toast.makeText(getApplicationContext(), "mmmmm"+mRecorder, 1000).show();
    		   mRecorder.start();
    		   starttime=System.currentTimeMillis();
    		   count=3;
    		   Toast.makeText(getApplicationContext(), "该电话被监听", 1000).show();
		 }catch (Exception e) {
			Toast.makeText(getApplicationContext(), "出现录音机打开异常", 1000).show();
		 } 	
    	   }
        	else{
			 Toast.makeText(getApplicationContext(), "没有记录通话", 1000).show();
		 }
    	   break;
           case TelephonyManager.CALL_STATE_IDLE:
        	   count++;
       		Toast.makeText(getApplicationContext(), "挂断类型类型"+count, 1000).show();
        	   try {
        		   if(mRecorder!=null){
        			   mRecorder.stop();
            		   mRecorder.release();
            		   Toast.makeText(getApplicationContext(), "监听结束", 1000).show();
            		   mRecorder=null;		   
            	   }
			} catch (Exception e) {
				// TODO: handle exception
				Toast.makeText(getApplicationContext(), "出现了双录音机打开", 1000).show();
			}  
    	   Toast.makeText(getApplicationContext(),"count"+count, 1000).show();
    	   if(count<=3){ 
    	      dao.createdata(getApplicationContext());
    	      dao.createtable(PhoneBlackData.createab4);
		       long time=System.currentTimeMillis();
		       Date data=new Date(time);
		       SimpleDateFormat formate=new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		      String today= formate.format(data);
		      contentvalue.put("number", num);
		      contentvalue.put("type", count);
		      contentvalue.put("datatime",today);					
		      dao.add(PhoneBlackData.db_table4, contentvalue);
	          dao.close();   
    	   }else if(count==4){
    		   dao.createdata(getApplicationContext());
    		   dao.createtable(PhoneBlackData.createab5);
    		   long time=System.currentTimeMillis();
		       Date data=new Date(time);
		       SimpleDateFormat formate=new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		      String today= formate.format(data);
		      contentvalue.put("number", num);
		      contentvalue.put("url", mRecordFile.getAbsolutePath());
		      contentvalue.put("totaltime",new SimpleDateFormat("mm:ss").format(new Date(time-starttime)));
		      contentvalue.put("datatime",today);					
		      dao.add(PhoneBlackData.db_table5, contentvalue);
    	   }
    	   count=0;
             break;
		 default:
			break;
		}
	}
	 
	 
 }
}
