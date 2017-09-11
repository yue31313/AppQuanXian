package com.phoensafe.fangdao;
import com.example.phonesafe.PhoneBlackData;
import com.example.phonesafe.R;
import com.phoensafe.fangdao.jizhan.SItude;
import com.phoensafe.fangdao.jizhan.Scell;
import com.phoensafe.fangdao.jizhan.Scellinformation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
  public class PhoneSafeActivity extends Activity implements android.view.View.OnClickListener{
	SharedPreferences share;
	SharedPreferences.Editor editor;
	FangDao fangdao;
	Scellinformation mm;
	LayoutInflater layout;
	private Context context;
	
   @Override
   protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	 super.onCreate(savedInstanceState);
	 setContentView(R.layout.phone_fangdao); 
	 Button btn=(Button) findViewById(R.id.phone_fangdao_index);
	 btn.setOnClickListener(this);
	 context=this;
	 layout=(LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
	 share=getSharedPreferences("phone_fangdao", Context.MODE_PRIVATE+Context.MODE_WORLD_WRITEABLE+Context.MODE_WORLD_READABLE);
	 Toast.makeText(this, "jinru", 1000).show();
	 fangdao=new FangDao(this);
  /**通过运营商获取手机号码
  */
  	 TelephonyManager tm=(TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
	  int i=this.getResources().getConfiguration().mcc;  //获取运营商的相关信息
	  String imsi=String.valueOf(i);
	  String number=tm.getLine1Number();

}
@Override
public void onClick(View arg0) {
	// TODO Auto-generated method stub
	View vv=layout.from(context).inflate(R.layout.phone_fangdao_main,null);
	final EditText number=(EditText) vv.findViewById(R.id.bangding_number);
	AlertDialog dig=new AlertDialog.Builder(this).setTitle("绑定号码").setView(vv).setPositiveButton("确定",new OnClickListener() {				
		@Override
		public void onClick(DialogInterface arg0, int arg1) {
			String ed=number.getText().toString();
			if(!ed.trim().equals("")){
				 editor=share.edit();
				 editor.putString("phone_bangding", ed);
				Boolean flg= editor.commit();
				if(flg){
					Toast.makeText(context, "添加成功",1).show();
				}else{
					Toast.makeText(context, "添加失败",1).show();
				}
			}
//			Toast.makeText(context, "进入了"+ed, 1000).show();
//			PhoneBlackData data=new PhoneBlackData();
//			data.createdata(context);
//			data.createtable(data.createab);
//			data.add(ed,null,null,data.db_table1);
//			data.close();
//			arg0.cancel();		
		}
	}).setNegativeButton("取消",new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface arg0, int arg1) {
			// TODO Auto-generated method stub
			arg0.cancel();
			
		}
	}).create();
	dig.show();
	
}

}
