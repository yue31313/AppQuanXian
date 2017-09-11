package com.phone.shadu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.example.phonesafe.R;
import com.phone.shadu.applicationInfo;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class APPPermissionActivity extends Activity {
	Context context;
	ListView listdata;
	List<HashMap<String, Object>> liss;
//	TextView show;
//	Button btn;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.phone_permission_showapplist);
//    	setContentView(R.layout.phone_app_permission_show);
//    	show=(TextView) findViewById(R.id.app_permission_show);
//    	btn=(Button) findViewById(R.id.app_permission_btn);
//    	btn.setOnClickListener(new onclick());
    	context=this;
       listdata=(ListView) findViewById(R.id.phone_permission_appshowlist);
       showlistdata(); 	
    }   
//    protected void getAppPermission() {
//		try {
//			PackageManager pm=context.getPackageManager();
//			PackageInfo appinfo=pm.getPackageInfo(context.getPackageName(), 0);
//			//包名
//			String appName=appinfo.packageName;
//			PermissionGroupInfo pgi;
//			PackageInfo pkgInfo=pm.getPackageInfo(appName, PackageManager.GET_PERMISSIONS);
//		   String per[]=pkgInfo.requestedPermissions;
//		   for(int i=0;i<per.length;i++){
//			   //把应用程序权限显示到界面
//			   String str=per[i];
//			   PermissionInfo perinfo=pm.getPermissionInfo(str, 0);
//			   pgi=pm.getPermissionGroupInfo(perinfo.group, 0);
//			   show.append(i+"------------"+str);
//			   show.append(i+"------------"+pgi.loadLabel(pm).toString());
//			   show.append(i+"------------"+perinfo.loadDescription(pm).toString()+"\n");
//			   show.append(i+"------------"+perinfo.loadLabel(pm).toString()+"\n");
//			   show.append("----------------"+"\n");
//		   }	   
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//    class onclick implements OnClickListener{
//
//		@Override
//		public void onClick(View arg0) {
//			// TODO Auto-generated method stub
//			getAppPermission();
//		}
//    }
    public List<HashMap<String, Object>> getapplictioninfo(){
 	   PackageManager pm=this.getPackageManager();
 	   List<ApplicationInfo> appinfo=new ArrayList<ApplicationInfo>();
 	   appinfo=pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
 	   Collections.sort(appinfo,new ApplicationInfo.DisplayNameComparator(pm));
 	   liss=new ArrayList<HashMap<String,Object>>();
 		   for(int i=0;i<appinfo.size();i++){
 			   ApplicationInfo bb=appinfo.get(i);
 			HashMap<String, Object> object=new HashMap<String, Object>();
 			object.put("text1", bb.loadLabel(pm).toString());
 			object.put("text2",bb.loadIcon(pm));
 			object.put("text3", bb.packageName);	
 		  liss.add(object);	   
 		   }
 		  return liss;     
    }
      public void showlistdata(){
    	  List<HashMap<String, Object>>  data=getapplictioninfo();
    	  APPpermission adpter=new APPpermission(context, data);
    	  listdata.setAdapter(adpter);
    	  
//    	  SimpleAdapter adapter=new SimpleAdapter(context, data, R.layout.phone_appperminsion_app_sginl,new String[]{"text1","text3"},new int[]{R.id.permission_text1,R.id.permission_text3});
//    	 listdata.setAdapter(adapter);
    	
    }
    
}
