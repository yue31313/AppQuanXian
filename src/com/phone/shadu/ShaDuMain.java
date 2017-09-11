package com.phone.shadu;
import com.example.phonesafe.R;
import com.example.phonesafe.phonesafeone;
import com.phoensafe.fangdao.PhoneSafeActivity;
import com.phone.SMScheck.Sms_main;
import com.phoneshow.dophoneTab;
import com.yarin.android.FileManager.FileManager;
import android.app.ActionBar.Tab;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.gesture.GestureOverlayView;
import android.view.GestureDetector.OnGestureListener;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TabHost;
import android.widget.Toast;
public class ShaDuMain extends TabActivity implements OnGestureListener{
  GestureDetector detector;
  int i=0;
  TabHost tab;
	@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	 tab=getTabHost();
	Resources res=getResources();
	TabHost.TabSpec spec;
	spec=tab.newTabSpec("tab_1").setIndicator("杀毒",res.getDrawable(R.drawable.shield_item_icon_open_audio)).setContent(new Intent(this,phoneShaduList.class));
	tab.addTab(spec);
	spec=tab.newTabSpec("tab_2").setIndicator("通话",res.getDrawable(R.drawable.shield_item_icon_open_audio)).setContent(new Intent(this,phonesafeone.class));
	tab.addTab(spec);
	spec=tab.newTabSpec("tab_5").setIndicator("防盗",res.getDrawable(R.drawable.shield_item_icon_open_audio)).setContent(new Intent(this,PhoneSafeActivity.class));
	tab.addTab(spec);
	spec=tab.newTabSpec("tab_3").setIndicator("电话管理",res.getDrawable(R.drawable.shield_item_icon_call_phone)).setContent(new Intent(this,dophoneTab.class));
	tab.addTab(spec);
	spec=tab.newTabSpec("tab_3").setIndicator("文件管理",res.getDrawable(R.drawable.icon)).setContent(new Intent(this,FileManager.class));
	tab.addTab(spec);
	spec=tab.newTabSpec("tab_4").setIndicator("短信",res.getDrawable(R.drawable.shield_item_icon_access_sms)).setContent(new Intent(this,Sms_main.class));
	tab.addTab(spec);
	tab.setCurrentTab(1);
	detector=new GestureDetector(this, this);
	
}
	@Override
		public boolean onTouchEvent(MotionEvent event) {
			// TODO Auto-generated method stub
			return detector.onTouchEvent(event);
		}
	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		if(arg0.getX()-arg1.getX()>=20){
			i=tab.getCurrentTab();
			if(i<=2){
			tab.setCurrentTab(i++);
			Toast.makeText(getApplicationContext(), "向左 ", 1000).show();
			return true;
			
			}
		}
		if(arg1.getX()-arg0.getX()>=20){
			i=tab.getCurrentTab();
			if(i>=1){
			tab.setCurrentTab(i--);
			Toast.makeText(getApplicationContext(), "xiangyou", 1000).show();
			return true;
			}	
		}
		
		return false;
	}
	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), "changan", 1000).show();
		
	}
	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}


}
