package com.example.phonesafe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
  public class PhoneLisonActivity extends Activity implements OnItemClickListener {
  public  List<HashMap<String, Object>> listdata;
	ListView list_lison;
	public Context context;
	private Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
		if(msg.what>=0){
			Intent intent=new Intent();
			  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			  intent.setAction(android.content.Intent.ACTION_VIEW);
			  String uri=listdata.get(msg.what).get("lujing").toString();  
			  String type=uri.substring(uri.lastIndexOf(".")+1, uri.length());
			  intent.setDataAndType(Uri.parse(uri), "amr");
			  startActivity(intent);
		}
		};
		
	};
	 PhoneBlackData data=new PhoneBlackData();
   @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	data.createdata(this);
	setContentView(R.layout.layout_phone_lison);
	list_lison=(ListView) findViewById(R.id.list_lison_data);
	listdata= getData();
	 SimpleAdapter ad=new SimpleAdapter(this,listdata, R.layout.phone_bjthm,new String[]{"number","url","totaltimes","sstime"},new int[]{R.id.jt_hmd,R.id.jt_url,R.id.jt_zonggongtime,R.id.jt_kaishitime});
	 list_lison.setAdapter(ad);
	 list_lison.setOnItemClickListener(this);
    }
   private List<HashMap<String, Object>> getData(){
	   List<HashMap<String, Object>> da=new ArrayList<HashMap<String,Object>>();
	   data.createtable(PhoneBlackData.createab5);
	   Cursor cur=data.queryall(PhoneBlackData.db_table5,new String[]{"id","number","url","totaltime","datatime"});
	   cur.moveToFirst();
	   if(cur!=null){
		  int id= cur.getColumnIndex("id");
		   int phonenum=cur.getColumnIndex("number");
		   int url=cur.getColumnIndex("url");
		   int time=cur.getColumnIndex("totaltime");
		   int stime=cur.getColumnIndex("datatime");
		   while(cur.moveToNext()){
		   HashMap<String, Object> object=new  HashMap<String, Object>();
		   object.put("id", cur.getInt(id));
		   object.put("number", cur.getString(phonenum));
		   object.put("url","Â¼Òô");
		   object.put("lujing", cur.getString(url));
		   object.put("totaltimes", cur.getString(time));
		   object.put("sstime", cur.getString(stime));
		   da.add(object);}
		   }
	   return da;
   }
@Override
public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	Message mess=new Message();
	mess.what=arg2;
	handler.sendMessage(mess);
}
   }
