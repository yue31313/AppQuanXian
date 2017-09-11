package com.example.phonesafe;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.android.internal.telephony.ITelephony;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaRecorder;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.widget.Toast;
public class phonebroad extends BroadcastReceiver {
	public String phone;
	public static String inCallPhone;//来电的号码
	Cursor cs;
	String num;
	ContentValues con=new ContentValues();
	com.android.internal.telephony.ITelephony RR;
//    public static MediaRecorder mediarecorder;//录音机对象
//    public  File mRecordFile;//录音文件
//    public  File RecorderFile; 
	    public AudioManager phoAudioManager;//多媒体对象
    /**
    * 监听通话广播
    * 拨打，来电放在onreceive方法的处理
    */
	@Override
	public void onReceive(Context arg0, Intent arg1) {
//  拦截通话
// 来电广播
//电话状态改变
// this.abortBroadcast();
   /**
     * 播出电话
    */
         if(arg1.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")){
        	 int tt=0;
           phone=arg1.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
           PhoneBlackData mm=new PhoneBlackData();
           mm.createdata(arg0);
           cs=mm.query(PhoneBlackData.db_table1,phone);       
            if(cs.moveToFirst()){
        	    Toast.makeText(arg0, phone+"打出黑名单", 1000).show();
        	    this.setResultData(null);	
        	    tt=5;
        	    cs.moveToNext();
             }else{
         } 
           cs.close();
         //添加播出记录
          Date data=new Date(System.currentTimeMillis());
          String time=new SimpleDateFormat("yy-MM-dd-hh-mm-ss").format(data);
          con.put("number",phone);
          con.put("type",1);
          con.put("datatime", time);
           mm.add(PhoneBlackData.db_table4,con); 
           if(tt==5){
               con.put("number",phone);
               con.put("type",5);
               con.put("datatime", time);;
        	   mm.add(PhoneBlackData.db_table4, con);
           }
           mm.close();  
         }    
         else{
   /**
    * 电话状态      
    * 
    */  
        	 phoAudioManager=(AudioManager) arg0.getSystemService(Context.AUDIO_SERVICE);//实例化多媒体
        	 TelephonyManager phonemanager=(TelephonyManager)arg0.getSystemService(Service.TELEPHONY_SERVICE);
        	 //实例化ITelephony   
        	  Method mathod;
			    try {
				mathod = TelephonyManager.class.getDeclaredMethod("getITelephony",(Class[])null);
	            mathod.setAccessible(true);
				 try {
					RR= (ITelephony) mathod.invoke(phonemanager, (Object[])null);
				} catch (Exception e) {
					e.printStackTrace();
				} 

			  } catch (Exception e1) {
				e1.printStackTrace();
			 }
	/**
	 * 判断电话处于什么状态		        	 
	 */       
        switch (phonemanager.getCallState()) {    	//来电状态 
            case TelephonyManager.CALL_STATE_RINGING://来电的号码     			          		
	    	inCallPhone=arg1.getStringExtra("incoming_number");	
	   /**实现通话记录显示
	    */ 
	    	 arg0.startService(new Intent(arg0,TelephoneServer.class));
			Toast.makeText(arg0, "来电号码"+inCallPhone, Toast.LENGTH_LONG).show();		    		
	         //设置手机铃声为静音
		      phoAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);	
				try {	
					PhoneBlackData mm=new PhoneBlackData();	
					mm.createdata(arg0);
					Cursor nn=mm.query(PhoneBlackData.db_table2,inCallPhone);
			    if(nn.moveToFirst()){
					Toast.makeText(arg0, "为来电黑名单"+inCallPhone,1000).show();
                   //添加拦截记录   6
					mm.createtable(PhoneBlackData.db_table4);
                   Date data=new Date(System.currentTimeMillis());
                   String time=new SimpleDateFormat("yy-MM-dd-hh-mm-ss").format(data);
                   con.put("number",inCallPhone);
                   con.put("type",6);
                   con.put("datatime", time);
                    mm.add(PhoneBlackData.db_table4,con);
					RR.endCall();
					nn.moveToLast();
				}else{
						Toast.makeText(arg0, "来电正常"+inCallPhone, 1000).show();		
					 }	
					  mm.close();
					  nn.close();
				}catch (Exception e) {
					  e.printStackTrace();
				}
				    //恢复手机铃声
				    phoAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);				
				    Toast.makeText(arg0, "来电",Toast.LENGTH_LONG).show();
				
				     break;						
//            case TelephonyManager.CALL_STATE_OFFHOOK://接通电话        	   
//        	   /**
//        	    * 判断sd卡是否存在
//        	    */
//        	   if(Environment.getExternalStorageState().equals((Environment.MEDIA_MOUNTED))){
//        		      RecorderFile=Environment.getExternalStorageDirectory();//获取sd卡路径     		   
//        		   
//        	   }else{
//        		       Toast.makeText(arg0, "没有sd卡", 1000).show();
//        		       		   
//        	    }
//		      try {
//					mRecordFile=File.createTempFile("RecorderFil",".amr",RecorderFile);
//				 } catch (IOException e1) {
//					// TODO Auto-generated catch block
//					 e1.printStackTrace();
//				 }
//        	   //判断是否安全通话-通话录音，定位
//        	   /**  录音机不能被重复打开
//        	    * 再次打开录音机，如果打开过录音机，会抛出异常，录音机已经被打开
//        	    *  启动服务录音机是否被打开，gprs是否被打开
//        	    */
//        	      mediarecorder=new MediaRecorder();//实例化录音机对象
//        	      mediarecorder.setAudioSource(MediaRecorder.AudioSource.MIC);//设置麦克风
//        	      mediarecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);//输出格式
//        	      mediarecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);//输出录音编码方式
//			      mediarecorder.setOutputFile(mRecordFile.getAbsolutePath());//录音文件路径	
//        	  try {
//					mediarecorder.prepare();                 //准备录音
//				  } catch (IllegalStateException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				   } catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				   }
//        	  
//        	       PhoneBlackData mm=new PhoneBlackData();
//        	        mm.createdata(arg0);         	      
//        	        Toast.makeText(arg0, "laidian:"+inCallPhone+": ", 1000).show();
//        	        Cursor css=mm.query(PhoneBlackData.db_table3,inCallPhone);
//        	        Toast.makeText(arg0, "css"+css,1000).show();
//        	    if(css.moveToFirst()){
//        	    	 mediarecorder.start();   
//        	    	 //开始录音
//        	    	 css.moveToLast();
//        	      }else{
//        	    	Toast.makeText(arg0, "该电话没有被监听", 1000).show();
//        	      }
//        	       mm.close();
//        	       css.close();
//           	   try {
//        		   mediarecorder.start();
//		    	  } catch (Exception e) {
//				// TODO: handle exception
//				   Toast.makeText(arg0, "你的通话被监听了", 1000).show();
//				   
//			    } 	   
//             	Toast.makeText(arg0, "通话",Toast.LENGTH_LONG).show();
//        	      break;
//            case TelephonyManager.CALL_STATE_IDLE://挂断电话
//            	Toast.makeText(arg0, "挂断",Toast.LENGTH_LONG).show();
//            try {
//            	if(mediarecorder!=null){
//            	mediarecorder.stop();
//            	mediarecorder.release();
//            	Toast.makeText(arg0, "进入了挂断的最后", 1000).show();}
//			} catch (Exception e) {
//				// TODO: handle exception
//				Toast.makeText(arg0, "出现了挂断的异常", 1000).show();
//				e.printStackTrace();
//			}
//            	break;
		   default:
				break;
			}
        	 }	
        	
        }
         
         
         
        
	}

//}
