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

public class BochuList extends Activity {
   	private ListView listdata3;
   	TabHost tab;
   	List<HashMap<String, Object>> lis;
   	private PhoneCall mm=new PhoneCall();
	
  @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	 setContentView(R.layout.bochunumber);
	  listdata3=(ListView) findViewById(R.id.callinlist3);
	  lis=mm.income(getApplicationContext(),
		      PhoneBlackData.db_table4, new String[]{"id", "number", "datatime"},"id desc","type='1'");
		         SimpleAdapter adpter=new SimpleAdapter(getApplicationContext(), lis, R.layout.callout_sginl,new String[]{"id","haoma","time"},new int[]{R.id.comein4,R.id.comein5,R.id.comein6});
         listdata3.setAdapter(adpter);
	
}
}
