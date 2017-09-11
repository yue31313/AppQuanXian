package com.example.phonesafe;

import java.util.HashMap;
import java.util.List;

import com.off.PhoneCall;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TextView;

public class WeijieList extends Activity {
   	private ListView listdata2;
   	TabHost tab;
   	List<HashMap<String, Object>> lis2;
   	private PhoneCall mm=new PhoneCall();
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.weijienumber);
	   listdata2=(ListView) findViewById(R.id.callinlist2);
	   lis2=mm.income(getApplicationContext(),
               PhoneBlackData.db_table4, new String[]{"id", "number", "datatime"},"id desc","type='2'");
               SimpleAdapter adpter2=new SimpleAdapter(getApplicationContext(), lis2, R.layout.weijie_sginl,new String[]{"id","haoma","time"},new int[]{R.id.comein7,R.id.comein8,R.id.comein9});
               listdata2.setAdapter(adpter2);
	
	
}
}
