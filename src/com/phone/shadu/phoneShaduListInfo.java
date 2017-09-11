package com.phone.shadu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.baidu.platform.comapi.map.s;
import com.example.phonesafe.R;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class phoneShaduListInfo extends Activity implements OnItemClickListener{
	ListView listview;
	PackageManager pm;
	int str;
	 List<applicationInfo> APP;
	List<applicationInfo> infodata=new ArrayList<applicationInfo>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.phone_shadu_info);
	Intent intent=getIntent();
	 str=intent.getIntExtra("data", 0);
	listview=(ListView) findViewById(R.id.phone_shadu_info);
	infodata=getapplictioninfo();
	APPAdapter adapter=new APPAdapter(infodata, this);
	 listview.setAdapter(adapter); 
	 listview.setOnItemClickListener(this);
    } 
     public List<applicationInfo> getapplictioninfo(){
	   pm=this.getPackageManager();
	   List<ApplicationInfo> appinfo=new ArrayList<ApplicationInfo>();
	   appinfo=pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
	   Collections.sort(appinfo,new ApplicationInfo.DisplayNameComparator(pm));
	  //保存应用程序信息的数组
	    APP=new ArrayList<applicationInfo>();
	    Toast.makeText(this, "leixing"+str, 1000).show();
	   switch (str) {
	   case 0://查看所有软件
		   APP.clear();
		for(int i=0;i<appinfo.size();i++){
			if(((appinfo.get(i)).flags&ApplicationInfo.FLAG_SYSTEM)!=1){
			APP.add(getAPPInfo(appinfo.get(i)));
			}
		}
		break;
	    case 1:
	    	//查看系统软件
	    	 APP.clear();
	    	for(int i=0;i<appinfo.size();i++){
				if(((appinfo.get(i)).flags&ApplicationInfo.FLAG_SYSTEM)!=0){
				APP.add(getAPPInfo(appinfo.get(i)));}
			}
		break;
		case 2: //第三方软件
			 APP.clear();
             for(int i=0;i<appinfo.size();i++){
				
				if(((appinfo.get(i)).flags&ApplicationInfo.FLAG_EXTERNAL_STORAGE)<=0){
				APP.add(getAPPInfo(appinfo.get(i)));
				}
			}
	    	break;
			
	      case 3://sd卡上
	    	  APP.clear();
	    	for(int i=0;i<appinfo.size();i++){
				
				if(((appinfo.get(i)).flags&ApplicationInfo.FLAG_EXTERNAL_STORAGE)!=0){
				APP.add(getAPPInfo(appinfo.get(i)));
				}
			}
	    	break;
	    default:
		break;
	}
	   return APP;
   }
     private applicationInfo getAPPInfo(ApplicationInfo appp){
	   applicationInfo info=new applicationInfo();
	   info.setAppLabel((String)appp.loadLabel(pm));
	   info.setAppIcon(appp.loadIcon(pm));
	   info.setApp(appp.permission);
	   info.setPackname(appp.packageName);
	   return info;
    }
    
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		String pack=infodata.get(arg2).getPackname();
		Toast.makeText(this, "包名为"+pack, 1000).show();
	
	}
     
}
