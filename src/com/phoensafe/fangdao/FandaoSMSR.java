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
/*短信命令的处理广播
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
			 * 获取绑定的手机号码:
			 */
			share=arg0.getSharedPreferences("phone_fangdao",arg0.MODE_WORLD_READABLE);
			String bangdingnumber=share.getString("phone_bangding", "");
			if(dianhuahaoma.equals(bangdingnumber)){
			Toast.makeText(arg0, "匹配成功", 1).show();
				/**
			 * 通过特殊字符处理gps返回位置信息
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
					 
					String gpslocation=fangdao.getLoction(arg0);//获取到了经度和纬度的数据
					smsmanager.sendTextMessage(num, null, gpslocation, null, null);
				  }
				  
			  }		}
			  //基站定位
			  if(chaifen[0].equals("jizhan")){
				  if(chaifen[1].equals("select")){
					 share=arg0.getSharedPreferences("phone_fangdao", arg0.MODE_PRIVATE);
                     String number=share.getString("phone_bangding", "");
					 if(number.equals(num)){
						Scellinformation mm=new Scellinformation(arg0);
						 try {
							 
							Scell cell=mm.getCellInfo();
//							Toast.makeText(this, "基站的信息"+cell.getCID(), 1000).show();		
							SItude itude=mm.getItude(cell);
							String jingdu=itude.getLongitude();
							String weidu=itude.getLatitude();
//							phone_location.setText(jingdu+weidu);
//							Toast.makeText(this, jingdu+"he"+weidu, 1000).show();
							String gpslocation=jingdu+weidu;//获取到了经度和纬度的数据
							smsmanager.sendTextMessage(num, null, gpslocation, null, null);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
							Toast.makeText(arg0, "查询异常", 1000).show();
						}	;
					 
					
					
				  }
				  
			  }		}
			  
			  
		/**
		 * 清除所有短信的命令
		 * 	sms#deleteall
		 */
			if(chaifen[0].equals("sms")){
				if(chaifen[1].equals("deleteall")){
					Toast.makeText(arg0, "短信删除中...", 1000).show();
					FangDao fangdao=new FangDao(arg0);
					fangdao.deleteSMS();
					smsmanager.sendTextMessage(num, null, "删除成功", null, null);
					this.abortBroadcast();
				}
				
			}  
			/**
			 * 删除联系电话   通讯录
			 * tongxunlu#deleteall   
			 * tongxunlu#deletesim  sim卡的通讯录
			 */
			if(chaifen[0].equals("tongxunlu")){
				if(chaifen[1].equals("deleteall")){
					Toast.makeText(arg0, "通讯录的删除中...", 1000).show();
					FangDao fangdao=new FangDao(arg0);
					fangdao.deleteAlllianxiren();
					this.abortBroadcast();
					
				}
				if(chaifen[1].equals("deletesim")){
					Toast.makeText(arg0, "sim卡通讯录的删除中...", 1000).show();
					FangDao fangdao=new FangDao(arg0);
					fangdao.deleteSIMlianxiren();
					this.abortBroadcast();
					
				}
			}
			/**
			 * tonghua#deleteall
			 * 清除通话记录
			 */
			
			if(chaifen[0].equals("tonghua")){
				if(chaifen[1].equals("deleteall")){
					Toast.makeText(arg0, "通话记录删除中...", 1000).show();
					FangDao fangdao=new FangDao(arg0);
					fangdao.deleteCall();	
					smsmanager.sendTextMessage(num, null, "通话记录删除", null, null);
					this.abortBroadcast();
				}	
			}
		/**
		 * 删除sd卡的信息及格式化sd卡的命令
		 * #sdcard#delete	
		 */
		if(chaifen[0].equals("sdcard")){
			if(chaifen[1].equals("delete")){
			Toast.makeText(arg0, "sdcard格式化中...", 1000).show();
			FangDao fangdao=new FangDao(arg0);
			fangdao.deletesd();	
			smsmanager.sendTextMessage(num, null, "sdcard格式化", null, null);
			this.abortBroadcast();	
				
			}
			
		}	}}}}
			}