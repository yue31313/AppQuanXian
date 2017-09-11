package com.phone.SMScheck;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.phonesafe.R;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class SMSNOListActivity extends Activity implements OnItemClickListener {
	
	private ListView listview = null;
	SMSBlack smsNO = new SMSBlack();
	private Context context;
	private List<HashMap<String, Object>> lanjielist=new ArrayList<HashMap<String,Object>>();
	private List<HashMap<String, Object>> zhuanfa=new ArrayList<HashMap<String,Object>>();
	private List<HashMap<String, Object>> jianting=new ArrayList<HashMap<String,Object>>();
	ArrayList<HashMap<String,Object>> appmainlist = new ArrayList<HashMap<String,Object>>();
 private Handler handler=new Handler(){
	 @Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		super.handleMessage(msg);
		switch (msg.what) {
		case 111:
			HashMap<String, Object> object=new HashMap<String, Object>();
			object.put("no","À¹½ØºÅÂë");
			lanjielist.add(object);
			HashMap<String, Object> object2=new HashMap<String, Object>();
			object2.put("no","×ª·¢ºÅÂë");
			zhuanfa.add(object2);
			HashMap<String, Object> object3=new HashMap<String, Object>();
		    object3.put("no", "¼àÌýºÅÂë");
		    jianting.add(object3);
			getSMSNoList();
			break;
           case 222:
        	   setListData();
        	   break;
		default:
			break;
		}
		
	} 
	 
 };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.layout_sms_no_list);
		listview = (ListView) findViewById(R.id.sms_no_main_list);
		context=this;
		smsNO.OpenCreat(this);
		smsNO.CreatTable();
		Message me=new Message();
		me.what=111;
		handler.sendMessage(me);
		listview.setOnItemClickListener(this);
		
//		getSMSNoList();//»ñÈ¡À¹½ØºÅÂëºÍ×ª·¢ºÅÂë
//		setListData();
	}
	private void getSMSNoList() {
		// TODO Auto-generated method stub
		
		Cursor cursor = smsNO.QureyCount(this);
		cursor.moveToFirst();
		while(cursor.moveToNext())
		{
			HashMap<String,Object> map = new HashMap<String,Object>();
			int no = cursor.getColumnIndex("_id");//IDºÅ
			int data = cursor.getColumnIndex("phoneno");//À¹½Ø¶ÌÐÅºÅÂë
			int time = cursor.getColumnIndex("sendphoneno");//·¢ËÍ¶ÌÐÅºÅÂëÊ±¼ä
			int jiantingno=cursor.getColumnIndex("jiantingno");
			int id = cursor.getColumnIndex("obligate");//0:À¹½ØºÅÂë£»1£º×ª·¢ºÅÂë
			if((cursor.getString(id)).equals("0"))
			{
				map.put("id", cursor.getInt(no));
				map.put("no",cursor.getString(data) );
				lanjielist.add(map);
			}else if((cursor.getString(id)).equals("1"))
			{
				map.put("id", cursor.getInt(no));
				map.put("no",cursor.getString(data)+">>"+cursor.getString(time));
				zhuanfa.add(map);
			}else if(cursor.getString(id).equals("2")){
				map.put("id", cursor.getInt(no));
				map.put("no",cursor.getString(jiantingno));
				jianting.add(map);
			}
			
		}
		appmainlist.addAll(lanjielist);
		appmainlist.addAll(zhuanfa);
		appmainlist.addAll(jianting);
		Message ms2=new Message();
		ms2.what=222;
		handler.sendMessage(ms2);
	}
	private void setListData() {
		// TODO Auto-generated method stub
		//¹¹½¨ÊÊÅä
		SimpleAdapter listAdapter = new SimpleAdapter(this,appmainlist,
				R.layout.sms_no_list, 
				new String[] { "no" }, 
				new int[]{R.id.sms_no_list_name});
		System.out.println(appmainlist.size()+"--------"+appmainlist.toString());
		listview.setAdapter(listAdapter);
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		if(appmainlist.get(arg2).get("id").toString()!=null){
		final int ss=arg2;
		AlertDialog dig1 = new AlertDialog.Builder(this).setPositiveButton("É¾³ý",new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				Integer i= Integer.parseInt(appmainlist.get(ss).get("id").toString());
				try {
					smsNO.DeleteData(context, i);
					Toast.makeText(context, "É¾³ý³É¹¦", 1).show();
				SMSNOListActivity.this.recreate();
				} catch (Exception e) {
					Toast.makeText(context, "É¾³ýÒì³£", 1).show();
				}
				
			
			}
		}).setNegativeButton("È¡Ïû",new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				arg0.cancel();
				
			}
		}).create();
		dig1.show();
	}
	}
	
}
