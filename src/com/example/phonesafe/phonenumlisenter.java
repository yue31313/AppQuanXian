package com.example.phonesafe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
 public class phonenumlisenter extends Activity {
  TextView t1;
  ListView listdate;
  List<HashMap<String, Object>> listall=new ArrayList<HashMap<String,Object>>();
  Intent mm;
  int i=-1;
 @Override
 protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	 super.onCreate(savedInstanceState);
	 setContentView(R.layout.jiantinglist);
	 t1=(TextView) findViewById(R.id.HH);
	 listdate=(ListView) findViewById(R.id.heimingdan);
	  this.registerForContextMenu(listdate);
      PhoneBlackData mm=new PhoneBlackData();
       mm.createdata(this);
        Cursor data= mm.queryall(PhoneBlackData.db_table3, new String[]{PhoneBlackData.key_sms_no,PhoneBlackData.key_id,PhoneBlackData.key_sms_name,PhoneBlackData.key_sms_o});
       Toast.makeText(this, "data"+data, 1000).show();
       if(data!=null){
    	     data.moveToPosition(-1);
    	      while(data.moveToNext()){
    		   HashMap<String, Object> object=new HashMap<String, Object>();
    		   object.put("haoma",data.getString(data.getColumnIndex(PhoneBlackData.key_sms_no)));
    		   object.put("id",data.getString(data.getColumnIndex(PhoneBlackData.key_id)));
    		   object.put("name",data.getString(data.getColumnIndex(PhoneBlackData.key_sms_name)));
    		   object.put("neirong",data.getString(data.getColumnIndex(PhoneBlackData.key_sms_o)));
    		   listall.add(object);	   
    	   }   		   
    	  data.close();   
          }else{
    		   Toast.makeText(this, "没有值", 1000).show();
             }      
	  SimpleAdapter adpter=new SimpleAdapter(this, listall, R.layout.sginl_jiantinglist, new String[]{"haoma"},new int[]{R.id.HH});
	  listdate.setAdapter(adpter);
  // listdate.setOnItemClickListener(this);
	  listdate.setOnCreateContextMenuListener(this);
        }
        @Override
    	public void onCreateContextMenu(ContextMenu menu, View v,
    			ContextMenuInfo menuInfo) {
   //    super.onCreateContextMenu(menu, v, menuInfo);
    	       menu.setHeaderTitle("请选择");
    		   menu.add(0, Menu.FIRST+1, 1,"编辑");
    		   menu.add(0, Menu.FIRST+2,2,"删除");
    		   menu.add(0, Menu.FIRST+3,3,"取消");
    		  AdapterView.AdapterContextMenuInfo info=(AdapterContextMenuInfo) menuInfo;
    		  i=info.position; 		
    	}
 @SuppressLint("NewApi")
 @Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
	if(item.getItemId()==Menu.FIRST+1){
		Toast.makeText(this, "你选中了", 1000).show();
	  	 mm=new Intent(getApplicationContext(), sginl_hmd.class);
 	    Bundle jj=new Bundle();
 	    jj.putString("id",listall.get(i).get("id").toString());
 	    jj.putString("name",listall.get(i).get("name")!=null?listall.get(i).get("name").toString():null);
 	    jj.putString("no", listall.get(i).get("haoma").toString());
 	    jj.putString("neirong",listall.get(i).get("neirong")!=null?listall.get(i).get("neirong").toString():null);
 	    mm.putExtras(jj);
 	    startActivity(mm);		
	  }else if(item.getItemId()==Menu.FIRST+2){	
		Toast.makeText(this, "你选择了删除", 1000).show();
		PhoneBlackData pp=new PhoneBlackData();
		pp.createdata(this);
		Integer shu=Integer.valueOf(listall.get(i).get("id").toString()).intValue();
		pp.deletebyid(PhoneBlackData.db_table3, shu);		
       	     this.recreate();
	  }
	    return super.onContextItemSelected(item);
	 
	 
	}
//    @Override
//    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//    	 mm=new Intent(getApplicationContext(), sginl_hmd.class);
//    	    Bundle jj=new Bundle();
//    	    jj.putString("id",listall.get(arg2).get("id").toString());
//    	    jj.putString("name",listall.get(arg2).get("name")!=null?listall.get(arg2).get("name").toString():null);
//    	    jj.putString("no", listall.get(arg2).get("haoma").toString());
//    	    jj.putString("neirong",listall.get(arg2).get("neirong")!=null?listall.get(arg2).get("neirong").toString():null);
//    	    mm.putExtras(jj);
//    	    startActivity(mm);	
    	    
//   	     LayoutInflater mm1=(LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
//         View contentview=mm1.inflate(R.layout.buju,null); 
//         PopupWindow  pao=new PopupWindow(contentview,ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//         pao.setFocusable(true);
//      
//}
}
