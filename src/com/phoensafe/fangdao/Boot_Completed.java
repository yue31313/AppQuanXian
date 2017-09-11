package com.phoensafe.fangdao;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

public class Boot_Completed extends BroadcastReceiver {
	 TelephonyManager tm;
	 SharedPreferences share;
	 SharedPreferences.Editor ed;
	 SmsManager smsmage=SmsManager.getDefault();
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		 TelephonyManager tm=(TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
	     share=context.getSharedPreferences("phone_fangdao",Context.MODE_PRIVATE+Context.MODE_WORLD_READABLE);
	     String yiqian=share.getString("benji", "");
	     String benji=tm.getLine1Number();
	     if(yiqian!=null){
	     if(!yiqian.trim().equals("")){
	    	 if(!yiqian.equals(benji)){
	    		 String ss=share.getString("phone_bangding", "");
	    		 if(ss!=null)
	    		smsmage.sendTextMessage(ss, null, "¸ü»»µÄºÅÂëÎª"+benji, null, null); 
	    	 }}
	    	 
	     }else{
	    	 ed=share.edit();
	    	 ed.putString("benji", benji);
	    	 ed.commit();
	    	 
	     }
	
	}

}
