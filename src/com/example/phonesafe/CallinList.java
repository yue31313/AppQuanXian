package com.example.phonesafe;

import java.util.HashMap;
import java.util.List;

import com.off.PhoneCall;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;

public class CallinList extends Activity {
   	private ListView listdata;
   	TabHost tab;
   	List<HashMap<String, Object>> lis3;
   	private PhoneCall mm=new PhoneCall();
	
  @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.callin_number);
	listdata=(ListView) findViewById(R.id.callinlist);
	 lis3=mm.income(getApplicationContext(),
             PhoneBlackData.db_table4, new String[]{"id", "number", "datatime"},"id desc","type='3'");
             SimpleAdapter adpter3=new SimpleAdapter(getApplicationContext(), lis3, R.layout.callin_sginl,new String[]{"id","haoma","time"},new int[]{R.id.comein1,R.id.comein2,R.id.comein3});
             listdata.setAdapter(adpter3);
	
}
}
