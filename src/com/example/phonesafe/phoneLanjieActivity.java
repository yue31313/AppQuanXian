package com.example.phonesafe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
public class phoneLanjieActivity extends Activity implements OnItemClickListener{
	ListView list1;
	Context context;
	List<HashMap<String, Object>> listdata1=new ArrayList<HashMap<String,Object>>(),listdata2=new ArrayList<HashMap<String,Object>>();
	List<HashMap<String, Object>>  ll=new ArrayList<HashMap<String,Object>>();
	PhoneBlackData dochu=new PhoneBlackData();
	int yy,zz,tt;
	LayoutInflater layoutinflater;
  @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.phone_lanjielist);
	context=this;
	layoutinflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	dochu.createdata(context);
	dochu.createtable(PhoneBlackData.db_table4);
	list1=(ListView) findViewById(R.id.lanjie_list);
	Cursor cur=dochu.queryall(PhoneBlackData.db_table4, new String[]{"id","number","type","datatime"});
	cur.moveToFirst();
	if(cur!=null){
		while(cur.moveToNext()){
			//播出黑名单
			if(cur.getString(cur.getColumnIndex("type")).equals("5")){
				HashMap<String, Object> object=new HashMap<String, Object>();
				object.put("id", cur.getInt(cur.getColumnIndex("id")));
				object.put("number", cur.getString(cur.getColumnIndex("number")));
				object.put("datatime",cur.getString(cur.getColumnIndex("datatime")));
				listdata1.add(object);
			}else if(cur.getString(cur.getColumnIndex("type")).equals("6")){
			//来电拦截
				HashMap<String, Object> object=new HashMap<String, Object>();
				object.put("id", cur.getInt(cur.getColumnIndex("id")));
				object.put("number", cur.getString(cur.getColumnIndex("number")));
				object.put("datatime",cur.getString(cur.getColumnIndex("datatime")));
				listdata2.add(object);
			}
		}
		if(listdata1!=null)
		{   
			HashMap<String, Object> oo=new HashMap<String, Object>();
		      oo.put("number","播出拦截列表");
		        listdata1.add(0, oo);
		        ll.addAll(listdata1);
		        yy=ll.size();
		}
		if(listdata2!=null){
			HashMap<String, Object> oo2=new HashMap<String, Object>();
		      oo2.put("number","来电拦截列表");
		        listdata2.add(0, oo2);
			  ll.addAll(listdata2);	
			  zz=ll.size();
		  }
	};
	SimpleAdapter adpter=new SimpleAdapter(this, ll, R.layout.sginl_lanjie, new String[]{"number","datatime"},new int[]{R.id.sginl_lanjie_number,R.id.sginl_lanjie_time});
	   list1.setAdapter(adpter);
	   list1.setOnItemClickListener(this);
  }
  @Override
 public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	  int id=-1;
	  if(arg2!=yy){
		 id=(Integer) ll.get(arg2).get("id");
		 showdialog(id, PhoneBlackData.db_table4);
     	}
}
  private void showdialog(final int co,final String tablename){
	  AlertDialog bb=new AlertDialog.Builder(context).setTitle("操作").setPositiveButton("删除",new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
			   try {
				   dochu.deletebyid(tablename,co);
                    Toast.makeText(getApplicationContext(), "删除成功", 1000).show();
                   phoneLanjieActivity.this.recreate();
			} catch (Exception e) {
				Toast.makeText(getApplicationContext(), "删除失败", 1000).show();// TODO: handle exception
			}					
			}
		}).setNegativeButton("取消", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				arg0.cancel();
			}
		}).create();	
     bb.show();	  
  }
  
}
