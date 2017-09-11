package com.phoensafe.fangdao;
import java.io.File;

import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.telephony.gsm.SmsManager;
import android.telephony.gsm.SmsMessage;
import android.widget.Toast;
public class FangDao {
public LocationManager locationmanager;
public double jingdu;
public double weidu;
public String latLongString;
 public Context context;
public FangDao(Context ctx) {
	
  context=ctx;
 }
public String getLoction(Context context){
		locationmanager=(LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria=new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		String provider=locationmanager.getBestProvider(criteria, true);
		//gps����
		Location location=locationmanager.getLastKnownLocation(provider);
		latLongString="����Ϊ��"+location.getLongitude()+"γ��Ϊ��"+location.getLatitude();
		latLongString= updateWithNewLocation(location);
		locationmanager.requestLocationUpdates(provider, 2000, 10, locationlistener);
		return latLongString;
	   }
	    private String updateWithNewLocation(Location location){
		String weizhi=null;
		if(location!=null){
			jingdu=location.getLongitude();
			 weidu=location.getLatitude();
		latLongString="����Ϊ��"+jingdu+"\nγ��Ϊ ��"+weidu; 
		}else{
			latLongString="�޷���ȡλ����Ϣ";
			
		}
		SharedPreferences sh=context.getSharedPreferences("phone_fangdao", context.MODE_WORLD_READABLE);
		String sendsmsNo=sh.getString("phone_haoma", "");
		SmsManager smsManager=SmsManager.getDefault();
		PendingIntent intent=PendingIntent.getBroadcast(context, 0, new Intent(), 0);
		//������Ϣ
		  smsManager.sendTextMessage(sendsmsNo, null, latLongString, intent, null);
		return weizhi;
	  }
	   private final LocationListener locationlistener=new LocationListener() {
		@Override
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			// TODO Auto-generated method stub
		}
		@Override
		public void onProviderEnabled(String arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub
			updateWithNewLocation(null);
		}
		@Override
		public void onLocationChanged(Location arg0) {
			// TODO Auto-generated method stub
			   updateWithNewLocation(arg0); 
		}
	 };
     /**
     * ������ж���
     */
	 public void deleteSMS(){
		//���Ҷ��ŵ�URL
		String smsURL="content://sms/";
		ContentResolver contentresolver=context.getContentResolver();
		contentresolver.delete(Uri.parse(smsURL), null, null); //������еĶ���
	} 
	 
	 /**
	  * ɾ��ͨ����url
	  */
	 public void deleteCall(){
		 String thURL="content://call_log/calls";
		 ContentResolver contentresolver=context.getContentResolver();
		 try {
			 contentresolver.delete(Uri.parse(thURL), null, null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		  
		 
	 }
	 /**
	  * ������е�ͨѶ¼
	  */
	public void deleteAlllianxiren(){
	String smsURL="content://com.android.contacts/raw_contacts";
	ContentResolver contentresolver=context.getContentResolver();
	 contentresolver.delete(Uri.parse(smsURL), null, null); 
		 
	 }
	/**
	 * ���sim����ϵ��
	 */
	public void deleteSIMlianxiren(){
		String simURL="content://icc/adn";
	ContentResolver contentresolver=context.getContentResolver();
	try {
		contentresolver.delete(Uri.parse(simURL), null, null);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
		
	} 
	/**
	 * ��ʽ��sd��    ɾ��sd��
	 * 
	 */
	public void deleteFileSD(){
		if((Environment.getExternalStorageDirectory().toString())
				.equals(Environment.MEDIA_MOUNTED)){
			  deletesd();
			
		}else{
			Toast.makeText(context, "SD��������", Toast.LENGTH_LONG).show();
		}
	}
	public  void deletesd(){
		String url=Environment.getExternalStorageDirectory().toString();
		
		try {
			File fid=new File(url);
			File[] fids=fid.listFiles();
		     if(fids!=null&&fids.length>0){
		 		for(int i=0;i<fids.length;i++){
		 			if(fids[i].isDirectory()){
		 				wipeDirectory(fids[i].toString());
		 			}else{
		 				fids[i].delete();
		 			}
		 		}}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void wipeDirectory(String name){
		try {
		 File direfile=new File(name);
		 File[] filenames=direfile.listFiles();
		 if(filenames!=null&&filenames.length>0){
			 for(File temFile:filenames){
				 if(temFile.isDirectory()){
					 wipeDirectory(temFile.toString());
					 
				 }else{
					 temFile.delete();
				 }
			 }
		 }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
