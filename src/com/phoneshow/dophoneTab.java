package com.phoneshow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.phonesafe.BochuList;
import com.example.phonesafe.CallinList;
import com.example.phonesafe.PhoneBlackData;
import com.example.phonesafe.R;
import com.example.phonesafe.WeijieList;
import com.off.PhoneCall;

import android.app.TabActivity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;

    public class dophoneTab extends TabActivity{
   	TabHost tab;
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 tab=getTabHost();
		 Intent intent=new Intent(this, BochuList.class);
		 Intent intent2=new Intent(this, CallinList.class);
		 Intent intent3=new Intent(this, WeijieList.class);
		 tab.addTab(tab.newTabSpec("tab3").setContent(intent).setIndicator("已拨来电"));
		 tab.addTab(tab.newTabSpec("tab1").setContent(intent2).setIndicator("已结来电"));
		 tab.addTab(tab.newTabSpec("tab2").setContent(intent3).setIndicator("未接来电"));
		 tab.setCurrentTab(0);

	}
	
	
	
	
			
		
		
	}
	
	

	
	
