package com.phone.SMScheck;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.example.phonesafe.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
public class Sms_main extends Activity implements OnItemClickListener{
	private GridView gridview;
	private LayoutInflater ly;
	private Context context;
	private List<HashMap<String, Object>> lljj;
	String[] names=new String[]{"�鿴���ж���","�鿴�ֻ�����","�鿴���ض���","�鿴���ض��ż�¼",
	"�鿴ת����¼","�鿴���غ����ת������","�������غ���","����ת������","��Ӽ�������","����"};
	int[] imageid=new int[]{R.drawable.ic_send_sms,R.drawable.ic_visit_sms_data,R.drawable.protection_notify_sms,R.drawable.short_cut_trash_messgae,R.drawable.top_share_0,R.drawable.sms_send_add_contact_pressed,R.drawable.tm_profile_popup_ico_add,R.drawable.tm_profile_popup_ico_recomm,R.drawable.tm_profile_popup_ico_add,R.drawable.short_cut_phone_anti};
   @Override
   protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	 setContentView(R.layout.wangge_layout);
	gridview=(GridView)findViewById(R.id.gridid);
	lljj=new ArrayList<HashMap<String,Object>>();
	context=this;
	for(int i=0;i<names.length;i++){
	HashMap<String, Object> object=new HashMap<String, Object>();
	object.put("name",names[i]);
	object.put("imageid",imageid[i]);
	lljj.add(object);
	    }
   SmsAdapter ad=new SmsAdapter(lljj, this);
    gridview.setAdapter(ad);
    gridview.setOnItemClickListener(this);
}
@Override
public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	// TODO Auto-generated method stub
	Intent intent;
	switch (arg2) {
	case 0://�鿴���еĶ���
		//���ж���
		 intent = new Intent(this,SMSListActivity.class);
		intent.putExtra("filter", 0); 
		startActivity(intent);
		break;
		case 1:
			//�鿴�ֻ�����
			intent = new Intent(this,SMSListActivity.class);
			intent.putExtra("filter", 1); 
			startActivity(intent);
			break;
    	case 2:
		//�鿴���ض���
		intent = new Intent(this,SMSListActivity.class);
		intent.putExtra("filter", 2); 
		startActivity(intent);
		break;
	    case 3:
		//�鿴���ض��ż�¼
		intent = new Intent(this,SMSListActivity.class);
		intent.putExtra("filter", 3); 
		startActivity(intent);
		break;
	 case 4:
		//�鿴ת�����ż�¼
		intent = new Intent(this,SMSListActivity.class);
		intent.putExtra("filter", 4); 
		startActivity(intent);
		break;
	 case 5:
		//�鿴���غ����ת�����룬������
		intent = new Intent(this,SMSNOListActivity.class);
		startActivity(intent);
		break;
	 case 6:
		//�������غ���"
		LayoutInflater factory = LayoutInflater.from(this);
		final View DialogView = factory.inflate(R.layout.layout_dialog_pho, null);
		final EditText dialE = (EditText)DialogView.findViewById(R.id.dialog_pho_edit);
		TextView dd=(TextView) DialogView.findViewById(R.id.dialog_pho_name);
		   dd.setText("���صĺ���");
		AlertDialog dig = new AlertDialog.Builder(this)
			 .setTitle("�������غ���")
			 .setView(DialogView)
			 .setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					String dialNuberm = dialE.getText().toString();
					SMSBlack blackList = new SMSBlack();
					blackList.OpenCreat(context);
					blackList.CreatTable();
					try {
						if(!dialNuberm.trim().equals("")){
						blackList.AddData(dialNuberm,"0", "0","0");
                        Toast.makeText(context, "��ӳɹ�", 1).show();}
					} catch (Exception e) {
						  e.printStackTrace();
						Toast.makeText(context, "���ʧ��", 1).show();
					   }
					finally{
		            blackList.Close();
					dialog.cancel();
					}
				}
			} )
			.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.cancel();
						}
					} )
					.create();
	        dig.show();
		break;
	case 7:
		//"����ת������"
		LayoutInflater factory1 = LayoutInflater.from(this);
		final View DialogView1 = factory1.inflate(R.layout.zhuanfaedit, null);
		final EditText dialE1 = (EditText)DialogView1.findViewById(R.id.zhuanfa_t1);
		final EditText dialE2 = (EditText)DialogView1.findViewById(R.id.zhuanfa_t2);
		AlertDialog dig1 = new AlertDialog.Builder(this)
			.setTitle("����ת������")
			.setView(DialogView1)
			.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					String dialNuberm = dialE1.getText().toString();
					SMSBlack blackList = new SMSBlack();
					blackList.OpenCreat(context);
					blackList.CreatTable();
					try {
						String num1=dialE1.getText().toString();
						String num2=dialE2.getText().toString();
						if(!num1.trim().equals("")&&!num2.trim().equals("")){
						blackList.AddData(num1,num2,"1","0");
						Toast.makeText(context, "��ӳɹ�", 1).show();}
						else{
							Toast.makeText(context, "���ʧ��",1).show(); 
						}
					} catch (Exception e) {
						e.printStackTrace();
                        Toast.makeText(context, "���ʧ��",1).show(); 
					}
					finally{
						blackList.Close();
						dialog.cancel();	
					}
				}
			} )
			.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.cancel();
						}
					} )
					.create();
	        dig1.show();
		break;
		case 8:
			//���ü�������"
			LayoutInflater factory2 = LayoutInflater.from(this);
			final View DialogView2 = factory2.inflate(R.layout.layout_dialog_pho, null);
			final EditText yinmino = (EditText)DialogView2.findViewById(R.id.dialog_pho_edit);
			AlertDialog yinmi = new AlertDialog.Builder(this)
				 .setTitle("�������غ���")
				 .setView(DialogView2)
				 .setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						String dialNuberm = yinmino.getText().toString();
						SMSBlack blackList = new SMSBlack();
						blackList.OpenCreat(context);
						blackList.CreatTable();
						try {
							if(!dialNuberm.trim().equals("")){
							blackList.AddData("0","0","2",dialNuberm);
							Toast.makeText(context, "��ӳɹ�", 1).show();
							} 
						} catch (Exception e) {
							  e.printStackTrace();
							Toast.makeText(context, "���ʧ��", 1).show();
						   }
						finally{
			            blackList.Close();
						dialog.cancel();
						}
					}
				} )
				.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								dialog.cancel();
							}
						} )
						.create();
		        yinmi.show();
		        break;
	case 9:
		Toast.makeText(context, "�û���Ҫʲô����", 1000).show();
	default:
		break;
	}
}
}
