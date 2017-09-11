package com.phone.shadu;
import com.example.phonesafe.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
public class psrmissionMain extends Activity {
	Button bu,butt;
	LinearLayout l;
	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quan_xian_activity_main);
		
		bu= (Button) findViewById(R.id.button1);
		l = (LinearLayout)findViewById(R.id.layout);
		tv=(TextView)findViewById(R.id.text);
		bu.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				l.removeAllViews();
				tv.setText("----------");
				
				
				l.addView(getAppP());
				
				
				
			}
		});
	
	}

	
	/**
	 * 获取应用程序权限
	 */
	protected View getAppP() {
		// TODO Auto-generated method stub
		View v = null;
		
		try {
		    PackageManager pm = psrmissionMain.this.getPackageManager();
	        PackageInfo appinfo = pm.getPackageInfo(psrmissionMain.this.getPackageName(), 0);       
	        String apppkgname = appinfo.packageName;
		
	        AppSecurityPermissions asp = new AppSecurityPermissions(psrmissionMain.this,
	        		apppkgname);
	       v =  asp.getPermissionsView();
	        
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return v;
	}
	protected View getAppP(String packagename,Context context) {
		// TODO Auto-generated method stub
		View v = null;
		
		try {
		    PackageManager pm = context.getPackageManager();
	        PackageInfo appinfo = pm.getPackageInfo(packagename, 0);       
	        String apppkgname = appinfo.packageName;
		
	        AppSecurityPermissions asp = new AppSecurityPermissions(context,
	        		apppkgname);
	       v =  asp.getPermissionsView();
	        
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return v;
		
		
		
	}
	

}
