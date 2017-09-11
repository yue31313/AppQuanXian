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
	public static String inCallPhone;//����ĺ���
	Cursor cs;
	String num;
	ContentValues con=new ContentValues();
	com.android.internal.telephony.ITelephony RR;
//    public static MediaRecorder mediarecorder;//¼��������
//    public  File mRecordFile;//¼���ļ�
//    public  File RecorderFile; 
	    public AudioManager phoAudioManager;//��ý�����
    /**
    * ����ͨ���㲥
    * �����������onreceive�����Ĵ���
    */
	@Override
	public void onReceive(Context arg0, Intent arg1) {
//  ����ͨ��
// ����㲥
//�绰״̬�ı�
// this.abortBroadcast();
   /**
     * �����绰
    */
         if(arg1.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")){
        	 int tt=0;
           phone=arg1.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
           PhoneBlackData mm=new PhoneBlackData();
           mm.createdata(arg0);
           cs=mm.query(PhoneBlackData.db_table1,phone);       
            if(cs.moveToFirst()){
        	    Toast.makeText(arg0, phone+"���������", 1000).show();
        	    this.setResultData(null);	
        	    tt=5;
        	    cs.moveToNext();
             }else{
         } 
           cs.close();
         //��Ӳ�����¼
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
    * �绰״̬      
    * 
    */  
        	 phoAudioManager=(AudioManager) arg0.getSystemService(Context.AUDIO_SERVICE);//ʵ������ý��
        	 TelephonyManager phonemanager=(TelephonyManager)arg0.getSystemService(Service.TELEPHONY_SERVICE);
        	 //ʵ����ITelephony   
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
	 * �жϵ绰����ʲô״̬		        	 
	 */       
        switch (phonemanager.getCallState()) {    	//����״̬ 
            case TelephonyManager.CALL_STATE_RINGING://����ĺ���     			          		
	    	inCallPhone=arg1.getStringExtra("incoming_number");	
	   /**ʵ��ͨ����¼��ʾ
	    */ 
	    	 arg0.startService(new Intent(arg0,TelephoneServer.class));
			Toast.makeText(arg0, "�������"+inCallPhone, Toast.LENGTH_LONG).show();		    		
	         //�����ֻ�����Ϊ����
		      phoAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);	
				try {	
					PhoneBlackData mm=new PhoneBlackData();	
					mm.createdata(arg0);
					Cursor nn=mm.query(PhoneBlackData.db_table2,inCallPhone);
			    if(nn.moveToFirst()){
					Toast.makeText(arg0, "Ϊ���������"+inCallPhone,1000).show();
                   //������ؼ�¼   6
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
						Toast.makeText(arg0, "��������"+inCallPhone, 1000).show();		
					 }	
					  mm.close();
					  nn.close();
				}catch (Exception e) {
					  e.printStackTrace();
				}
				    //�ָ��ֻ�����
				    phoAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);				
				    Toast.makeText(arg0, "����",Toast.LENGTH_LONG).show();
				
				     break;						
//            case TelephonyManager.CALL_STATE_OFFHOOK://��ͨ�绰        	   
//        	   /**
//        	    * �ж�sd���Ƿ����
//        	    */
//        	   if(Environment.getExternalStorageState().equals((Environment.MEDIA_MOUNTED))){
//        		      RecorderFile=Environment.getExternalStorageDirectory();//��ȡsd��·��     		   
//        		   
//        	   }else{
//        		       Toast.makeText(arg0, "û��sd��", 1000).show();
//        		       		   
//        	    }
//		      try {
//					mRecordFile=File.createTempFile("RecorderFil",".amr",RecorderFile);
//				 } catch (IOException e1) {
//					// TODO Auto-generated catch block
//					 e1.printStackTrace();
//				 }
//        	   //�ж��Ƿ�ȫͨ��-ͨ��¼������λ
//        	   /**  ¼�������ܱ��ظ���
//        	    * �ٴδ�¼����������򿪹�¼���������׳��쳣��¼�����Ѿ�����
//        	    *  ��������¼�����Ƿ񱻴򿪣�gprs�Ƿ񱻴�
//        	    */
//        	      mediarecorder=new MediaRecorder();//ʵ����¼��������
//        	      mediarecorder.setAudioSource(MediaRecorder.AudioSource.MIC);//������˷�
//        	      mediarecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);//�����ʽ
//        	      mediarecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);//���¼�����뷽ʽ
//			      mediarecorder.setOutputFile(mRecordFile.getAbsolutePath());//¼���ļ�·��	
//        	  try {
//					mediarecorder.prepare();                 //׼��¼��
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
//        	    	 //��ʼ¼��
//        	    	 css.moveToLast();
//        	      }else{
//        	    	Toast.makeText(arg0, "�õ绰û�б�����", 1000).show();
//        	      }
//        	       mm.close();
//        	       css.close();
//           	   try {
//        		   mediarecorder.start();
//		    	  } catch (Exception e) {
//				// TODO: handle exception
//				   Toast.makeText(arg0, "���ͨ����������", 1000).show();
//				   
//			    } 	   
//             	Toast.makeText(arg0, "ͨ��",Toast.LENGTH_LONG).show();
//        	      break;
//            case TelephonyManager.CALL_STATE_IDLE://�Ҷϵ绰
//            	Toast.makeText(arg0, "�Ҷ�",Toast.LENGTH_LONG).show();
//            try {
//            	if(mediarecorder!=null){
//            	mediarecorder.stop();
//            	mediarecorder.release();
//            	Toast.makeText(arg0, "�����˹Ҷϵ����", 1000).show();}
//			} catch (Exception e) {
//				// TODO: handle exception
//				Toast.makeText(arg0, "�����˹Ҷϵ��쳣", 1000).show();
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
