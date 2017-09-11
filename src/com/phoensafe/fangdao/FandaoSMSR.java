package com.phoensafe.fangdao;

import com.phoensafe.fangdao.jizhan.SItude;
import com.phoensafe.fangdao.jizhan.Scell;
import com.phoensafe.fangdao.jizhan.Scellinformation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.telephony.gsm.SmsMessage;
import android.widget.Toast;

public class FandaoSMSR extends BroadcastReceiver {
	SharedPreferences share;
	SharedPreferences.Editor editor;
	public String dianhuahaoma;
	SmsManager smsmanager=SmsManager.getDefault();
/*��������Ĵ���㲥
 */
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		Toast.makeText(arg0, "guangbo", 1000).show();
		Bundle bundle=arg1.getExtras();
		if(bundle!=null){
			String num=null;
			String neirong=null;
			Object[] objects=(Object[]) bundle.get("pdus");
			for(Object oo:objects){
				SmsMessage mm=SmsMessage.createFromPdu((byte[]) oo);
				 num=mm.getDisplayOriginatingAddress();
				 neirong=mm.getDisplayMessageBody();
				 dianhuahaoma=num;
			}	
			/**
			 * ��ȡ�󶨵��ֻ�����:
			 */
			share=arg0.getSharedPreferences("phone_fangdao",arg0.MODE_WORLD_READABLE);
			String bangdingnumber=share.getString("phone_bangding", "");
			if(dianhuahaoma.equals(bangdingnumber)){
			Toast.makeText(arg0, "ƥ��ɹ�", 1).show();
				/**
			 * ͨ�������ַ�����gps����λ����Ϣ
			 * gps#select
			 * 
			 */
			 if(neirong!=null){
			 String[] chaifen=neirong.split("#");
			  if(chaifen[0].equals("gps")){
				  if(chaifen[1].equals("select")){
					 share=arg0.getSharedPreferences("phone_fangdao", arg0.MODE_PRIVATE);
                     String number=share.getString("phone_bangding", "");
					 if(number.equals(num)){
					 editor.putString("phone_haoma", num);
					 FangDao fangdao=new FangDao(arg0);
					 
					String gpslocation=fangdao.getLoction(arg0);//��ȡ���˾��Ⱥ�γ�ȵ�����
					smsmanager.sendTextMessage(num, null, gpslocation, null, null);
				  }
				  
			  }		}
			  //��վ��λ
			  if(chaifen[0].equals("jizhan")){
				  if(chaifen[1].equals("select")){
					 share=arg0.getSharedPreferences("phone_fangdao", arg0.MODE_PRIVATE);
                     String number=share.getString("phone_bangding", "");
					 if(number.equals(num)){
						Scellinformation mm=new Scellinformation(arg0);
						 try {
							 
							Scell cell=mm.getCellInfo();
//							Toast.makeText(this, "��վ����Ϣ"+cell.getCID(), 1000).show();		
							SItude itude=mm.getItude(cell);
							String jingdu=itude.getLongitude();
							String weidu=itude.getLatitude();
//							phone_location.setText(jingdu+weidu);
//							Toast.makeText(this, jingdu+"he"+weidu, 1000).show();
							String gpslocation=jingdu+weidu;//��ȡ���˾��Ⱥ�γ�ȵ�����
							smsmanager.sendTextMessage(num, null, gpslocation, null, null);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
							Toast.makeText(arg0, "��ѯ�쳣", 1000).show();
						}	;
					 
					
					
				  }
				  
			  }		}
			  
			  
		/**
		 * ������ж��ŵ�����
		 * 	sms#deleteall
		 */
			if(chaifen[0].equals("sms")){
				if(chaifen[1].equals("deleteall")){
					Toast.makeText(arg0, "����ɾ����...", 1000).show();
					FangDao fangdao=new FangDao(arg0);
					fangdao.deleteSMS();
					smsmanager.sendTextMessage(num, null, "ɾ���ɹ�", null, null);
					this.abortBroadcast();
				}
				
			}  
			/**
			 * ɾ����ϵ�绰   ͨѶ¼
			 * tongxunlu#deleteall   
			 * tongxunlu#deletesim  sim����ͨѶ¼
			 */
			if(chaifen[0].equals("tongxunlu")){
				if(chaifen[1].equals("deleteall")){
					Toast.makeText(arg0, "ͨѶ¼��ɾ����...", 1000).show();
					FangDao fangdao=new FangDao(arg0);
					fangdao.deleteAlllianxiren();
					this.abortBroadcast();
					
				}
				if(chaifen[1].equals("deletesim")){
					Toast.makeText(arg0, "sim��ͨѶ¼��ɾ����...", 1000).show();
					FangDao fangdao=new FangDao(arg0);
					fangdao.deleteSIMlianxiren();
					this.abortBroadcast();
					
				}
			}
			/**
			 * tonghua#deleteall
			 * ���ͨ����¼
			 */
			
			if(chaifen[0].equals("tonghua")){
				if(chaifen[1].equals("deleteall")){
					Toast.makeText(arg0, "ͨ����¼ɾ����...", 1000).show();
					FangDao fangdao=new FangDao(arg0);
					fangdao.deleteCall();	
					smsmanager.sendTextMessage(num, null, "ͨ����¼ɾ��", null, null);
					this.abortBroadcast();
				}	
			}
		/**
		 * ɾ��sd������Ϣ����ʽ��sd��������
		 * #sdcard#delete	
		 */
		if(chaifen[0].equals("sdcard")){
			if(chaifen[1].equals("delete")){
			Toast.makeText(arg0, "sdcard��ʽ����...", 1000).show();
			FangDao fangdao=new FangDao(arg0);
			fangdao.deletesd();	
			smsmanager.sendTextMessage(num, null, "sdcard��ʽ��", null, null);
			this.abortBroadcast();	
				
			}
			
		}	}}}}
			}