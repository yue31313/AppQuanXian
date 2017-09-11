package com.phone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.example.phonesafe.PhoneBlackData;
import com.example.phonesafe.R;
import com.off.PhoneCall;

import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
public class BlcakListActivty extends Activity implements OnItemClickListener{
	ListView list1;
	Context context;
	List<HashMap<String, Object>> listdata1,listdata2,listdata3;
	List<HashMap<String, Object>>  ll=new ArrayList<HashMap<String,Object>>();
	PhoneCall dochu=new PhoneCall();
	int yy,zz,tt;
  @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.black_listdata);
	context=this;
	list1=(ListView) findViewById(R.id.heimingdan_list);
	listdata1=dochu.heimingdanlist(this, PhoneBlackData.db_table1, new String[]{"phone_no","id","phone_name","phone_o"});
	if(listdata1!=null)
	{   
		HashMap<String, Object> oo=new HashMap<String, Object>();
	      oo.put("phone_no","播出列表");
	        listdata1.add(0, oo);
	        ll.addAll(listdata1);
	        yy=ll.size();
	}
	listdata2=dochu.heimingdanlist(this, PhoneBlackData.db_table2,new String[]{"phone_no","id","phone_name","phone_o"});
	if(listdata2!=null){
		HashMap<String, Object> oo2=new HashMap<String, Object>();
	      oo2.put("phone_no","来电列表");
	        listdata2.add(0, oo2);
		  ll.addAll(listdata2);	
		  zz=ll.size();
	  }
	listdata3=dochu.heimingdanlist(this, PhoneBlackData.db_table3, new String[]{"phone_no","id","phone_name","phone_o"});
	if(listdata3!=null){
		HashMap<String, Object> oo3=new HashMap<String, Object>();
	      oo3.put("phone_no","监听列表");
	        listdata3.add(0, oo3);
		 ll.addAll(listdata3);
		 tt=ll.size();
	} 
	SimpleAdapter adpter=new SimpleAdapter(this, ll, R.layout.sginl_heimingdan, new String[]{"phone_no"},new int[]{R.id.sginl_heimingdan_number});
	   list1.setAdapter(adpter);
	   list1.setOnItemClickListener(this);
  }
  @Override
 public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	// TODO Auto-generated method stub
	  alterdilog(arg2);
}
  @SuppressLint("NewApi")
public void alterdilog(final int i){
      String shuju=ll.get(i).get("phone_no").toString();
      Toast.makeText(getApplicationContext(), shuju,1000).show();
      Toast.makeText(getApplicationContext(),"shujuwei"+shuju,1000).show();
      if(!shuju.equals("监听列表")&&!shuju.equals("播出列表")&&!shuju.equals("来电列表")){
	 LayoutInflater fac2=LayoutInflater.from(this);
  	 final View dialogView=fac2.inflate(R.layout.dialog_list_select,null);
  	LinearLayout linearLayoutMain = new LinearLayout(this);//自定义一个布局文件
	linearLayoutMain.setLayoutParams(new LayoutParams(
			LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

  	 final ListView listView=new ListView(this);
  	     listView.setFadingEdgeLength(0);
  	   List<HashMap<String, Object>> listdata=new ArrayList<HashMap<String,Object>>();
  		HashMap<String, Object> ob=new HashMap<String, Object>();
  		ob.put("leixing", "查询");
  		HashMap<String, Object> ob2=new HashMap<String, Object>();
  		ob2.put("leixing", "修改");
  		HashMap<String, Object> ob3=new HashMap<String, Object>();
  		ob3.put("leixing", "删除");
  		listdata.add(ob);
  	    listdata.add(ob2);    
  	    listdata.add(ob3);
  	    SimpleAdapter  dd=new SimpleAdapter(this, listdata, R.layout.dialog_signl_list, new String[]{"leixing"}, new int[]{R.id.dialog_text});
  	    listView.setAdapter(dd);
  	    linearLayoutMain.addView(listView);
  	    AlertDialog dig2=new AlertDialog.Builder(this).setTitle("请选择操作方式").setMessage("").setView(linearLayoutMain).create();
    	dig2.show();	
    	listView.setOnItemClickListener(new OnItemClickListener() {
    		int ziyuanid;
    		String tablename;
    		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
    			Toast.makeText(getApplicationContext(),i+"你选择了"+arg2,1000).show();
    			    	   
    			    	   if(arg2==0){
    			    		   LayoutInflater fac=LayoutInflater.from(context);
    			    		   View vv=fac.inflate(R.layout.dialog_dialog_select,null);
    			    		   TextView tt1= (TextView) vv.findViewById(R.id.dialog_dialog_text1);
    			    		   TextView tt2= (TextView) vv.findViewById(R.id.dialog_dialog_text2);
    			    		   TextView tt3= (TextView) vv.findViewById(R.id.dialog_dialog_text3);
    			    		   tt1.setText(ll.get(i).get("phone_no").toString());
    			    		   tt2.setText(ll.get(i).get("phone_name")!=null?ll.get(i).get("phone_name").toString():null);
    			    		   tt3.setText(ll.get(i).get("phone_o")!=null?ll.get(i).get("phone_o").toString():null);
    			    	       AlertDialog dig3=new AlertDialog.Builder(context).setTitle("资料").setView(vv).setNegativeButton("返回", new OnClickListener() {
								@Override
								public void onClick(DialogInterface arg0, int arg1) {
									// TODO Auto-generated method stub
								}
							}).create();	
    			    		    dig3.show();
    			    	   }else if(arg2==1){
    			    		LayoutInflater  updatinflater=LayoutInflater.from(context);
    			    		View updateview=updatinflater.inflate(R.layout.dialog_dialog_update, null);
    			    	    final EditText tt4= (EditText) updateview.findViewById(R.id.dialog_update_text1);
    			    	    final  EditText tt5= (EditText) updateview.findViewById(R.id.dialog_update_text2);
    			    	    final EditText tt6= (EditText) updateview.findViewById(R.id.dialog_update_text3);
  			    	        tt5.setText(ll.get(i).get("phone_no").toString());
			    		    tt4.setText(ll.get(i).get("phone_name")!=null?ll.get(i).get("phone_name").toString():"");
			    		    tt6.setText(ll.get(i).get("phone_o")!=null?ll.get(i).get("phone_o").toString():"");
			    		    AlertDialog dig4=new AlertDialog.Builder(context).setTitle("编辑").setView(updateview).setPositiveButton("保存",new OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
								int numberbyid=Integer.valueOf(ll.get(i).get("id").toString());
								String username=tt4.getText().toString().trim();
								String number=tt5.getText().toString().trim();
								String address=tt6.getText().toString().trim();
								Toast.makeText(context, "shudddd"+username, 1000).show();
								Toast.makeText(context, "shudddd"+number, 1000).show();
								Toast.makeText(context, "shudddd"+address, 1000).show();
								ContentValues cv=new ContentValues();
								cv.put("phone_no",number);
								cv.put("phone_name", username);
								cv.put("phone_o", address);
								if(i<yy){
	    			    			tablename=PhoneBlackData.db_table1;
	    			    		}else if(i>zz){
	    			    			tablename=PhoneBlackData.db_table3;
	    			    		}else{
	    			    			tablename=PhoneBlackData.db_table2;
	    			    		}
							int panduan=dochu.UpdateById(tablename, numberbyid, cv, context);
							if(panduan>0){
								Toast.makeText(context, "保存成功", 1000).show();
							    recreate();
							}else{
								Toast.makeText(context, "保存失败", 1000).show();	
							}
								
							}
						}).setNegativeButton("取消", new OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								
							}
						}).create();
    			    		 dig4.show();  
    			    	   }
    			    	    else if(arg2==2){
    			    		   if(i<yy){
    			    		     tablename=PhoneBlackData.db_table1;
    			    			 
    			    		   }else if(i>zz){
    			    			   tablename=PhoneBlackData.db_table3;
    			    		   }else{
    			    			  tablename=PhoneBlackData.db_table2; 
    			    		   }
    			    		   ziyuanid=Integer.valueOf((ll.get(i).get("id")).toString()).intValue() ;
    			    		   int kk= dochu.deletbyid(tablename, ziyuanid, context);
    			    		   if(kk>0){Toast.makeText(context, "删除成功", 1000).show();
    			    		     recreate();
    			    		   }else{
    			    			   
    			    			   Toast.makeText(context, "删除异常", 1000).show() ;
    			    		   }
    			    	   }
    			       
    			   
    			    
    			  
    		}
    		
		});
	  
      }}
}
