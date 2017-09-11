package com.phoneshow;

import java.util.HashMap;
import java.util.List;
import com.example.phonesafe.PhoneBlackData;
import com.example.phonesafe.R;
import com.off.PhoneCall;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
  public class ComeinNumber extends Activity {
  TextView t1,t2,t3;
   PhoneCall ziyuan=new PhoneCall();
   String table=PhoneBlackData.db_table4;
   String shuxu="id desc";
   String[] data={"id","number","datatime"};
   String type="type='3'";
   List<HashMap<String ,Object>> listdata;
   ListView lis;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.callin_number);
	t1=(TextView) findViewById(R.id.comein1);
	t2=(TextView) findViewById(R.id.comein2);
	t3=(TextView) findViewById(R.id.comein3);
	lis=(ListView) findViewById(R.id.callinlist);
    listdata=ziyuan.income(this, table, data, shuxu,type);
     if(listdata!=null){
    	SimpleAdapter adpter=new SimpleAdapter(this, listdata, R.layout.callin_sginl,new String[]{"id","haoma","time"},new int[]{R.id.comein1,R.id.comein2,R.id.comein3});
    	lis.setAdapter(adpter);   	
    }else{
    	Toast.makeText(this, "Ã»ÓÐ¼ÇÂ¼", 1000).show();
    	
    }
	
	
	
}
	
	
}
