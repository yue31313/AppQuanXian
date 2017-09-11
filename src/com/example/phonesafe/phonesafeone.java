package com.example.phonesafe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.phoensafe.fangdao.PhoneSafeActivity;
import com.phone.BlcakListActivty;
import com.phoneshow.ComeinNumber;
import com.phoneshow.dophoneTab;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
public class phonesafeone extends Activity implements OnItemClickListener {
	ImageView t1,t2;
	TextView s1;
	Context context;
	ListView list;
	List<HashMap<String, Object>> listdata=new ArrayList<HashMap<String,Object>>();
   int[] aa=new int[]{R.drawable.tm_profile_popup_ico_call,R.drawable.tm_profile_popup_ico_recomm,R.drawable.tm_profile_popup_ico_set,R.drawable.top_share_0,R.drawable.tm_profile_popup_ico_chat,R.drawable.top_share_0_night,R.drawable.ic_get_telephone_number};
   int[] bb=new int[]{R.drawable.short_cut_active_defense,R.drawable.short_cut_active_defense,R.drawable.short_cut_active_defense,R.drawable.short_cut_active_defense,R.drawable.short_cut_active_defense,R.drawable.short_cut_active_defense,R.drawable.short_cut_active_defense};
   String[] name=new String[]{"添加拨打黑名单","添加来电黑名单","添加监听号码","查看被监听的电话","查看被拦截的电话","监听黑名单管理","黑名单，监听列表管理"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_phone);
		t1=(ImageView) findViewById(R.id.diyi);
		s1=(TextView) findViewById(R.id.dier);
		t2=(ImageView) findViewById(R.id.disan);
		list=(ListView) findViewById(R.id.phonelist);
		for(int i=0;i<aa.length;i++){
			HashMap<String, Object> ob=new HashMap<String, Object>();
			ob.put("ct1", aa[i]);
			ob.put("c2", name[i]);
			ob.put("c3", bb[i]);
			listdata.add(ob);		
		  }
	SimpleAdapter adt=new SimpleAdapter(this, listdata, R.layout.signl_phone, new String[]{"ct1", "c2","c3"},new int[]{R.id.diyi,R.id.dier,R.id.disan});
	list.setAdapter(adt);
	list.setOnItemClickListener(this);
	context=this;
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		 switch (arg2) {
		    case 0:
		    	LayoutInflater fac=LayoutInflater.from(this);
		    	final View dialogView=fac.inflate(R.layout.layout_phone,null);
		    	final EditText ddd=(EditText) dialogView.findViewById(R.id.layout_phone_dialog_edot);
		    	AlertDialog dig=new AlertDialog.Builder(this).setTitle("添加信息").setMessage("添加来电黑名单").setView(dialogView).setPositiveButton("确定",new OnClickListener() {				
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						
						String ed=ddd.getText().toString();
						Toast.makeText(context, "进入了"+ed, 1000).show();
						PhoneBlackData data=new PhoneBlackData();
						data.createdata(context);
						data.createtable(data.createab);
						data.add(ed,null,null,data.db_table1);
						data.close();
						arg0.cancel();		
					}
				}).setNegativeButton("取消",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						arg0.cancel();
						
					}
				}).create();
		    	dig.show();
			break;
			case 1:		
		    	LayoutInflater fac2=LayoutInflater.from(this);
		    	final View dialogView2=fac2.inflate(R.layout.layout_phone,null);
		    	final EditText ddd2=(EditText) dialogView2.findViewById(R.id.layout_phone_dialog_edot);
		    	AlertDialog dig2=new AlertDialog.Builder(this).setTitle("添加信息").setMessage("添加来电白名单").setView(dialogView2).setPositiveButton("确定",new OnClickListener() {				
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						String ed=ddd2.getText().toString();
						PhoneBlackData data=new PhoneBlackData();
						data.createdata(context);
						data.createtable(data.createab2);
						data.add(ed,null , null,data.db_table2);
						data.close();
						arg0.cancel();		
					}
				}).setNegativeButton("取消",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						arg0.cancel();
						
					}
				}).create();
		    	dig2.show();		
			break;
			case 2:
				LayoutInflater fac3=LayoutInflater.from(this);
		    	final View dialogView3=fac3.inflate(R.layout.layout_phone,null);
		    	final EditText ddd3=(EditText) dialogView3.findViewById(R.id.layout_phone_dialog_edot);
		    	AlertDialog dig3=new AlertDialog.Builder(this).setTitle("添加信息").setMessage("添加监听号码").setView(dialogView3).setPositiveButton("确定",new OnClickListener() {				
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						String ed=ddd3.getText().toString();
						PhoneBlackData data=new PhoneBlackData();
						data.createdata(context);
						data.createtable(data.createab3);
						data.add(ed,null , null,data.db_table3);
						data.close();
						arg0.cancel();		
					}
				}).setNegativeButton("取消",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						arg0.cancel();
						
					}
				}).create();
		    	dig3.show();	
				break;
			case 3: 
				     Intent xxx=new Intent(getApplicationContext(), PhoneLisonActivity.class);
				     context.startActivity(xxx);
		    break;
			case 4:Intent  xx=new Intent(getApplicationContext(),phoneLanjieActivity.class);
			    context.startActivity(xx);
			  break;
			case 5:
			  Intent  mm=new Intent(getApplicationContext(),phonenumlisenter.class);
			  context.startActivity(mm);
			  break;
			  case 6:
				  Intent  mm2=new Intent(getApplicationContext(),BlcakListActivty.class);
				  context.startActivity(mm2);
				  break;
		default:
			break;
		}
		
		
	}
}
