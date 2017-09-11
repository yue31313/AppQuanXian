package com.example.phonesafe;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class sginl_hmd extends Activity implements OnClickListener{
	TextView t1;
	EditText t2,t3;
	Button t4;
	int zhujian;
	ContentValues jj=new ContentValues();
    @Override
   protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.sginl_hmd);
	   t1=(TextView) findViewById(R.id.haoma1);
	   t2=(EditText) findViewById(R.id.haoma2);
	   t3=(EditText) findViewById(R.id.haoma3);
	   t4=(Button) findViewById(R.id.haoma4);
	   Bundle kk=getIntent().getExtras();
	   if(kk!=null){
		  String idd=kk.getString("id");
		  String name=kk.getString("name");
		  String no=kk.getString("no");
		  String neirong=kk.getString("neirong");
		  Toast.makeText(this, "内容为"+neirong,1000).show();
		  t1.setText(no);
		  t2.setText(name);
		  t3.setText(neirong);
		  zhujian=Integer.valueOf(idd).intValue();
		Toast.makeText(this, zhujian+"duo"+no, 1000).show();  
	    }
	   else{
		   Toast.makeText(this, "没有数据", 1000).show();  
	   }
	  t4.setOnClickListener(this);	  
         }
     @Override
      public void onClick(View arg0) {
	  jj.put(PhoneBlackData.key_sms_no, t1.getText().toString());
	  jj.put(PhoneBlackData.key_sms_name, t2.getText()==null?null:t2.getText().toString());
	  jj.put(PhoneBlackData.key_sms_o,t3.getText()==null?null:t3.getText().toString());
	  PhoneBlackData da=new PhoneBlackData();
	  da.createdata(this);
	 int boo=da.update(zhujian, PhoneBlackData.db_table3, jj);
	 if(boo>0){
		 Toast.makeText(this, "修改成功", 1000).show(); 
		 
	     }else{
		 Toast.makeText(this, "没有修改成功", 1000).show();   
	   }
	 da.close();
	 this.startActivity(new Intent(this, phonenumlisenter.class));
}
  
}
