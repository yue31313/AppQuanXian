package com.phone.shadu;

import java.util.HashMap;
import java.util.List;

import com.example.phonesafe.R;
import com.phone.shadu.virusadpter.holderview;
import com.phone.shadu.virusadpter.onclick;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class APPpermission extends BaseAdapter {
	private Context context;
	private List<HashMap<String, Object>>  listdd;
	private LayoutInflater layout;
	public APPpermission(Context context,List<HashMap<String, Object>>  listdd) {
		 this.context=context; 
	     this.listdd=listdd;
	     layout=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listdd.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return listdd.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		View view=null;
		holderview viewGroup=null;
		if(arg1==null||arg1.getTag()==null){
			view=layout.inflate(R.layout.phone_appperminsion_app_sginl,null);
			viewGroup=new holderview(view);
			view.setTag(viewGroup);
			
		}else{
			view=arg1;
			viewGroup=(holderview) arg1.getTag();
		}
	 //把列表中数据添加到列表视图中
		HashMap<String, Object> object=new HashMap<String, Object>();
		object=listdd.get(arg0);
		viewGroup.appimage.setImageDrawable((Drawable) object.get("text2"));
		viewGroup.appname.setText( object.get("text1").toString());
		viewGroup.apppackage.setText(object.get("text3").toString());
		viewGroup.btn.setOnClickListener(new onclick(object.get("text3").toString()));
		return view;
	}
  class holderview {
	   TextView appname;
	   TextView apppackage;
	   ImageView appimage;
	   Button btn;
	   public holderview(View view) {
			appname=(TextView) view.findViewById(R.id.permission_text1);
			apppackage=(TextView) view.findViewById(R.id.permission_text3);
			appimage=(ImageView) view.findViewById(R.id.permission_text2);
			btn=(Button) view.findViewById(R.id.permission_text_btn);
		}
  }
  
   public class onclick implements OnClickListener{
   private String packagename;
   public onclick(String packagename) {
	   this.packagename=packagename;
  }
  @Override
  public void onClick(View arg0) {
	// TODO Auto-generated method stub
	  Toast.makeText(context, "已经进入了监听事件"+packagename, 1000).show();
	  getAppPermission(packagename);
	 
	 
	
}
  protected void getAppPermission(String packagename) {
	  Toast.makeText(context, "已经进入了监听事件getapppermission", 1000).show();
	  ScrollView view =(ScrollView) layout.inflate(R.layout.phone_app_permission_show,null);
//	  ScrollView v = (ScrollView)view.findViewById(R.id.gundong);
	  //  TextView show=(TextView) view.findViewById(R.id.app_permission_show);
//		try {
//			PackageManager pm=context.getPackageManager();
//			PackageInfo appinfo=pm.getPackageInfo(packagename, 0);
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
////		   show.append(i+"------------"+str);
//			   show.append(i+"------------"+pgi.loadLabel(pm).toString());
//			   show.append(i+"------------"+perinfo.loadDescription(pm).toString()+"\n");
//			   show.append(i+"------------"+perinfo.loadLabel(pm).toString()+"\n");
//			   show.append("----------------"+"\n");
//		   }	   
////		   Toast.makeText(context, "描述"+show.getText().toString(), 1000).show();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		//if(view!=null||view.toString()!=null){
			psrmissionMain mm=new psrmissionMain();
		   View v=  mm.getAppP(packagename, context);
		  view.addView(v);
	        AlertDialog dialog=new AlertDialog.Builder(context).setTitle("权限详细信息").
	    		 setView(view)
	    		 .setNegativeButton("返回", new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface arg0, int arg1) {
			// TODO Auto-generated method stub
			arg0.cancel();
		}
	}).create();
	 dialog.show();
	 }
//	}
  class shitu extends ScrollView{

	public shitu(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	 
	  
  }
  }}