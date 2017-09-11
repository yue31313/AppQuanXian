package com.phone.shadu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.phonesafe.R;
import android.widget.ProgressBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class phoneShaduList extends Activity implements OnItemClickListener,Runnable {
	ImageView t1,t2;
	TextView s1;
	Context context;
	ListView list;
	TextView text1,text2,text3;
	public ProgressBar progressBar;
	List<HashMap<String, Object>> listdata=new ArrayList<HashMap<String,Object>>();
   int[] aa=new int[]{R.drawable.short_cut_phone_exam,R.drawable.short_cut_app_market,R.drawable.short_cut_phone_accelerate,R.drawable.short_cut_anti_scan,R.drawable.short_cut_program_manager,R.drawable.shortcut_app_icon};
   int[] bb=new int[]{R.drawable.shield_app_icon_warn,R.drawable.shield_app_icon_warn,R.drawable.shield_app_icon_warn,R.drawable.shield_app_icon_warn,R.drawable.shield_app_icon_warn,R.drawable.shield_app_icon_warn};
   String[] name=new String[]{"�鿴��װ���","��װ���","������ɱ","�����Ʋ�ɱ","���Ȩ��ɨ��","�ֻ��ڴ��Ż�"};
 //����б����е�����
 		public	ArrayList<HashMap<String,Object>>  smsListViewData = new ArrayList<HashMap<String,Object>>();
 		
 		public Handler handler = new Handler(){

 			@Override
 			public void handleMessage(Message msg) {
 				// TODO Auto-generated method stub
 				super.handleMessage(msg);
 				switch (msg.what) {
 				case 0:
 					progressBar.setProgress(0);
 					break;
 				case 1:
 					progressBar.setProgress(25);
 					break;
 				case 2:
 					progressBar.setProgress(50);
 					break;
 				case 3:
 					progressBar.setProgress(75);
 					break;
 				case 4:
 					progressBar.setProgress(100);
 					break;

 				default:
 					break;
 				}
 				
 			}
 			
 		};
   
   @Override
  protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	 setContentView(R.layout.phone_shadu_list);
		list=(ListView) findViewById(R.id.phone_shadu_list);
		text1=(TextView) findViewById(R.id.x);
		text2=(TextView) findViewById(R.id.x1);
		text3=(TextView) findViewById(R.id.x2);
		progressBar = (ProgressBar)findViewById(R.id.progressBar1);
		progressBar.setMax(100);
		progressBar.setProgress(0);	
		for(int i=0;i<aa.length;i++){
			HashMap<String, Object> ob=new HashMap<String, Object>();
			ob.put("ct1", aa[i]);
			ob.put("c2", name[i]);
			ob.put("c3", bb[i]);
			listdata.add(ob);		
		  }
		SimpleAdapter adt=new SimpleAdapter(this, listdata, R.layout.phone_shadu_signl, new String[]{"ct1", "c2","c3"},new int[]{R.id.diyi,R.id.dier,R.id.disan});
	list.setAdapter(adt);
	//��list�ؼ����м���
	list.setOnItemClickListener(this);
	context=this;	
	//   ����
	
	//��ȡӦ�ó�����Ϣ
	PackageManager pm = this.getPackageManager();
	List<ApplicationInfo> appinfo = new ArrayList<ApplicationInfo>();//Ӧ�ó�������
	appinfo = pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);//��ȡ��װӦ�ó�����Ϣ
//����Ӧ�ó�������
	text1.setText("һ��"+appinfo.size()+"�����");
	//ϵͳ���
	int j = 0;
	for(int i=0;i<appinfo.size();i++)
	{
	if(((appinfo.get(i)).flags&ApplicationInfo.FLAG_SYSTEM)!=0)//�����Ƿ���ϵͳ����
	{
		j++;
	}
	}
	
	text2.setText("��"+j+"��ϵͳ���");
	//�������
	int k = 0;
	for(int i=0;i<appinfo.size();i++)
	{
		if(((appinfo.get(i)).flags&ApplicationInfo.FLAG_SYSTEM)<=0)//�����Ƿ����������
		{
			k++;
		}
		
	}
	text3.setText("��"+k+"���������");
	Thread thread=new Thread(this);
	thread.start();
	
}
 @Override
 public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	// TODO Auto-generated method stub
  switch (arg2) {
case 0:
	 List<HashMap<String, Object>> alterlist=new ArrayList<HashMap<String,Object>>();
	 HashMap<String, Object> object1=new HashMap<String, Object>();
	  object1.put("shuju", "�鿴�������");
	  alterlist.add(object1);
	  HashMap<String, Object> object2=new HashMap<String, Object>();
	  object2.put("shuju", "�鿴ϵͳ���");
	  alterlist.add(object2);
	  HashMap<String, Object> object3=new HashMap<String, Object>();
	  object3.put("shuju", "�鿴���������");
	  alterlist.add(object3);
	  HashMap<String, Object> object4=new HashMap<String, Object>();
	  object4.put("shuju", "�鿴sd�����");
	  alterlist.add(object4);
    LayoutInflater la=LayoutInflater.from(context);
    View alterview=la.inflate(R.layout.phone_shadu_list_alterdialog_data, null);
    ListView listdata=(ListView) alterview.findViewById(R.id.phone_shadu_list_data);
    SimpleAdapter adapter=new SimpleAdapter(context, alterlist, R.layout.phone_app_list_dialog_list_data, new String[]{"shuju"}, new int[]{R.id.phone_shadu_list_dialog_list_data_t});
    listdata.setAdapter(adapter);
    AlertDialog dig=new AlertDialog.Builder(context).setTitle("�鿴���").setMessage("").setView(alterview).create();
    dig.show();
    listdata.setOnItemClickListener(new OnItemClickListener() {
    	
    	@Override
    	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
    			long arg3) {
    		// TODO Auto-generated method stub
    		Intent mm=new Intent(context, phoneShaduListInfo.class);
    		Toast.makeText(context, "leixing1"+arg2, 1000).show();
    		mm.putExtra("data", arg2);
    		context.startActivity(mm);		
    	}
	});
	break;
   case 1://��װ���
	   break;
   case 2://������ɱ
	   Intent mm2=new Intent(context, addVirus.class);
	   context.startActivity(mm2);
	   break;
	   case 3://�����Ʋ�ɱ
		   Intent mm4=new Intent(context, psrmissionMain.class);
		   context.startActivity(mm4);
		   break;
   case 4://���Ȩ��ɨ��
	  Intent mm3=new Intent(context, APPPermissionActivity.class);
	    context.startActivity(mm3);
    break;
   case 5://�ֻ��ڴ��Ż�
	   Intent mm5=new Intent(context, APPMListActivity.class);
	   context.startActivity(mm5);
	   
    default:
	break;
}			  
}
@Override
public void run() {
	for(int i=0;i<5;i++)
	{
		Message msg = new Message();
		msg.what = i;
		handler.sendMessage(msg);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


}
