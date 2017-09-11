package com.phone.shadu;

import java.util.ArrayList;
import java.util.List;

import com.example.phonesafe.R;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class APPAdapter extends BaseAdapter {
 public LayoutInflater layoutInflater=null;	
 public Context context;
 public String packagename;
 phoneShaduListInfo mm;
 public List<applicationInfo> applistviewdata=new ArrayList<applicationInfo>();
	public APPAdapter(List<applicationInfo> applistviewdata,Context context) {
		// TODO Auto-generated constructor stub
		this.applistviewdata=applistviewdata;
		this.context=context;
		layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return applistviewdata.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return applistviewdata.get(arg0);
	}
	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View view=null;
		ViewHolder viewGroup=null;
		if(arg1==null||arg1.getTag()==null){
			view=layoutInflater.inflate(R.layout.phone_shadu_app_sginl,null);
			viewGroup=new ViewHolder(view);
			view.setTag(viewGroup);
			
		}else{
			view=arg1;
			viewGroup=(ViewHolder) arg1.getTag();
		}
	 //把列表中数据添加到列表视图中
		applicationInfo app=new applicationInfo();
		app=(applicationInfo) getItem(arg0);
		viewGroup.appImage.setImageDrawable(app.getAppIcon());
		viewGroup.appLabel.setText(app.getAppLabel());
		viewGroup.appPkgName.setText(app.getPackname());
		viewGroup.button.setOnClickListener(new onclik(arg0));
		//获取包名
		return view;
	   }
      class ViewHolder{
	  ImageView appImage;
	  TextView appLabel;
	  TextView appPkgName;
	   Button button;
	  public ViewHolder(View v) {
		  this.appImage=(ImageView) v.findViewById(R.id.app_info_list_data_appimage);
		  this.appLabel=(TextView) v.findViewById(R.id.app_info_list_data_appname);
		  this.button=(Button) v.findViewById(R.id.app_info_list_data_xiezai);
		  this.appPkgName=(TextView) v.findViewById(R.id.app_info_list_data_apppkgname);
	  }
  }	
	class onclik implements OnClickListener{
		private int o;
        public onclik(int postion) {
			o=postion;
		}
        public void onClick(View arg0) {
        	packagename=applistviewdata.get(o).getPackname();
    		Toast.makeText(context, "包名为"+packagename, Toast.LENGTH_LONG).show();
    		if(packagename!=null){
    			Uri uri=Uri.fromParts("package", packagename, null);
    	    	Intent intent=new Intent(Intent.ACTION_DELETE);
    	    	intent.setData(uri);
    	    	context.startActivity(intent);		
    		}else{
    			Toast.makeText(context, "没有该包", Toast.LENGTH_LONG).show();
    			
    		}	
    	}
		
	}
      
}

