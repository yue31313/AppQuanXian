package com.phone.shadu;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.Thread.State;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.example.phonesafe.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
public class addVirus extends Activity implements Runnable{
private Context context;
public VirusData virusData;
public Cursor cc;
String[] viruspackag;
Thread sd;
List<HashMap<String, Object>> virusdataziyuan=new ArrayList<HashMap<String,Object>>();
int jj=0;
int baocount;
ArrayList<applicationInfo> appdata;
public ProgressBar progressBar,progressBar1;
public TextView textView,textView1;
TextToSpeech speaktext;
ListView listdata;

List<HashMap<String, Object>>  listdd=new ArrayList<HashMap<String,Object>>();
 public Handler handler4=new Handler(){
	 public void handleMessage(Message msg) {
	    	if(msg.what==1){
		   synchronized (sd) {
		   try {
			sd.notify();
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}
		Toast.makeText(context, "恢复", 1000).show();}
	    	if(msg.what==2){
	    		
	    		synchronized (sd){
	    			
	    			if(sd.isAlive()){
	    	try {
	    		if(sd.getState()==State.TIMED_WAITING){
	    			sd.wait();
	    		}else{
	    			sd.notify();
	    		}
				
			} catch (Exception e) {
				// TODO: handle exception
			}		
	    			}
				}
	    		
	    	}
		 
	 };
	 
 };
 public Handler handler3=new Handler(){
	@Override
	public void handleMessage(Message msg) {
		//if(msg.what==9){
		virusadpter adpter=new virusadpter(context, listdd);
		listdata.setAdapter(adpter);
	          final HashMap<String, Object> data=virusdataziyuan.get(msg.what);
		 AlertDialog dialog=new AlertDialog.Builder(context).setTitle("病毒库").setCancelable(false).setMessage(data.get("message").toString()).setPositiveButton("卸载",new DialogInterface.OnClickListener() {	
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
			Toast.makeText(context, "baoming"+data.get("packagevirus").toString(), 1000).show();
			Intent mm=new Intent(Intent.ACTION_DELETE);
			Uri uri=Uri.fromParts("package", data.get("packagevirus").toString(), null);
			mm.setData(uri);
			context.startActivity(mm);
			
			
			}
		}).setNegativeButton("取消",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				synchronized (sd) {
				sd.notify();
				}
				arg0.cancel();
				
			}
		}).create();
		dialog.show();
//		speaktext=new TextToSpeech(context, new OnInitListener() {		
//			@Override
//			public void onInit(int status) {
//		    if(status==TextToSpeech.SUCCESS){
//		    	int result=speaktext.setLanguage(Locale.CHINESE);
//		    	if(result!=TextToSpeech.LANG_COUNTRY_VAR_AVAILABLE&&result!=TextToSpeech.LANG_AVAILABLE){
//		    		Toast.makeText(context, "不支持", 1000).show(); 		
//		    	}else{
//		    		Toast.makeText(context, "支持", 1000).show();
//		    		speaktext.speak("发现病毒请注意查杀", TextToSpeech.QUEUE_ADD, null);
//		    	}
//		    }
//			}
//		});
		//}
	};
};
 public Handler handler2=new Handler(){
	@Override
	public void handleMessage(Message msg) {
		double shuju=((msg.what*100)/appdata.size());
		textView1.setText(shuju+"%");	
		textView.setText(appdata.get(msg.what-1).getPackname());		
	};	
};
public Handler handler=new Handler(){
@Override
public void handleMessage(android.os.Message msg) {
		super.handleMessage(msg);	
		switch (msg.what) {
		case 0:progressBar1.setProgress(25);		
			break;
        case 1:
        	progressBar1.setProgress(50);
			break;
        case 2:
        	progressBar1.setProgress(75);
	break;
        case 3:
        	progressBar1.setProgress(100);
	break;
		default:
			break;
		}
		
	};
	
};
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.phone_virus_kill_main);

	context=this;
	virusData=new VirusData(this);
	virusData.open();
	cc=virusData.getAppData();
	viruspackag=new String[cc.getCount()];
//	virusData.add("android", "liujian", "亲，我是一个病毒，就喜欢耗费你的流量，为您排除流量用不完的影响，你说我够意思么", "1", "强烈性攻击");
//	virusData.add("com.android.phone","liujian", "亲，打开的你的网络，让我也上一上网吧，thank you！", "1", "强烈性攻击");
//	virusData.add("com.android.mms", "liujian", "我是鬼，是你的恶魔，有本事你删了我，我折磨死你", "1", "强烈性攻击");
//	virusData.add("com.android.smoketest","liujian", "亲，包邮吧，游戏网卡免费送，等你哟", "1", "强烈性攻击");
	if(cc!=null){
		cc.moveToPosition(-1);
		while(cc.moveToNext()){
			HashMap<String, Object> object=new HashMap<String, Object>();
			object.put("packagevirus", cc.getString(cc.getColumnIndex("virus_pkg_name")));
			object.put("message", cc.getString(cc.getColumnIndex("virus_meg")));
			virusdataziyuan.add(object);
//			viruspackag[jj]=
//					cc.getString(cc.getColumnIndex("virus_pkg_name"));	
//			jj++;

		}
		cc.moveToLast();
		cc.close();
		virusData.close();
	}
	progressBar=(ProgressBar) findViewById(R.id.progressBar1);
	progressBar1=(ProgressBar) findViewById(R.id.progressBar2);
	progressBar1.setProgress(0);
	progressBar1.setMax(100);
	textView=(TextView) findViewById(R.id.app_virus_packagename);
	textView1=(TextView) findViewById(R.id.app_vrius_refer);
	listdata=(ListView) findViewById(R.id.app_virus_kill_progess);	
	View view=findViewById(R.id.chumo);
	 sd=new Thread(this);
	sd.start();	
}
	/**
	 * 对文件全文生成MD5摘要
	 * 
	 * @param file
	 *            要加密的文件
	 * @return MD5摘要码
	 */

	public char hexdigits[] = { '0', '1', '2', '3', 

'4', '5', '6', '7', '8',
			'9', 'a', 'b', 'c', 'd', 'e', 'f' 

};

	public String getMD5(File file) {

		FileInputStream fis = null;
		try {
			MessageDigest md = 

 MessageDigest.getInstance("MD5");
			fis = new FileInputStream(file);
			byte[] buffer = new byte[2048];
			int length = -1;
			long s = 

 System.currentTimeMillis();
			while ((length = fis.read

 (buffer)) != -1) {
				md.update(buffer, 0, 

   length);
			}
			byte[] b = md.digest();
			return byteToHexString(b);
			// 16位加密
			// return buf.toString().substring(8, 24);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			try {
				fis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	/** */
	/**
	 * 把byte[]数组转换成十六进制字符串表示形式
	 * @param tmp    要转换的byte[]
	 * @return 十六进制字符串表示形式
	 */
	private String byteToHexString(byte[] tmp) {
		String s;
		char str[] = new char[16 * 2]; // 每个字

		// 所以表示成 16 进制需要 32 个字符
		int k = 0; // 表示转换结果中对应的字符位置
		for (int i = 0; i < 16; i++) { // 从第一
			// 转换成 16 进制字符的转换
			byte byte0 = tmp[i]; // 取第 i 个

			str[k++] = hexdigits[byte0 >>>4 & 0xf]; 
			// 取字节中高 4 位的数字转换, 
			// >>> 为逻辑右移，将符号位一起右

			str[k++] = hexdigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
		}
		s = new String(str); // 换后的结果转换为字

		return s;
   }
   @Override
   public void run(){
	//获取了所有应用程序的信息
	PackageManager pm=context.getPackageManager();
	List<ApplicationInfo> appinfo=new ArrayList<ApplicationInfo>();
	appinfo=pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
	 appdata=new ArrayList<applicationInfo>();
	for(int i=0;i<appinfo.size();i++){
		applicationInfo info=new applicationInfo();
		String appname=(String) appinfo.get(i).loadLabel(pm);
		String appPkg=appinfo.get(i).packageName;
		Drawable appdraw=appinfo.get(i).loadIcon(pm);
		info.setApp(appname);
		info.setAppIcon(appdraw);
		info.setPackname(appPkg);
		appdata.add(info);
	}
	//与病毒数据库对比
	  //取数据库中的数据
		for(int i1= 0;i1<appdata.size();i1++)
		{
			String appPkg = appdata.get(i1).getPackname();//包名
			if(virusdataziyuan!=null&&virusdataziyuan.size()>0){
				for(int j=0;j<virusdataziyuan.size();j++){
					String bdpackage=virusdataziyuan.get(j).get("packagevirus").toString();
					if(appPkg.equals(bdpackage)){
						HashMap<String, Object> object=new HashMap<String, Object>();
						applicationInfo info = appdata.get(i1);	
						object.put("text1",info.getApp());
						object.put("text2",appdata.get(i1).getAppIcon());
						object.put("text3",appdata.get(i1).getPackname());
						listdd.add(object);
						Message kk=new Message();
						kk.what=j;
					    handler3.sendMessage(kk);	
					    synchronized (sd) {
					    try {
							sd.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}}
					}	
					
				}
				
			}

//			//取数据库中的数据
//			int j = c.getColumnIndex("virus_pkg_name");//包名在数据库中位置
//			try {
//				if(c!=null){
//					Toast.makeText(context, ""+c, 1000).show();
//					c.moveToPosition(-1);
//					while(c.moveToNext())
//					  {
//						String virusdataapppgk =c.getString(j);
//						if(appPkg.equals(virusdataapppgk))
//						{
//							Toast.makeText(context, "是病毒", 1000).show();
////							Message bingdu=new Message();
////							bingdu.what=i1;
////							Toast.makeText(this, "找到病毒"+virusdataapppgk, 1000).show();
////		                    System.out.println(virusdataapppgk+"是病毒");
//						}else
//						{
//							 System.out.println(virusdataapppgk+"是病毒");	
//						}
//					}
//					c.moveToLast();
//					}else{
//						 System.out.println("没有病毒数据");	
//						 Toast.makeText(this, "找到病毒", 1000).show();
//					}				
//					
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//				Toast.makeText(this, "找到病毒", 1000).show();
//				System.out.println("出现异常");
//			}
			try {
				baocount++;
				Message m1=new Message();
				m1.what=baocount;
				System.out.println("ddddd"+baocount);
				handler2.sendMessage(m1);
				for(int i=0;i<4;i++){
					Message m=new Message();
					m.what=i;
					handler.sendMessage(m);
					Thread.sleep(100);
				}		
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
       synchronized (sd) {
    	 try {
    		 sd.stop();
		} catch (Exception e) {
			// TODO: handle exception
		}
	 
      }
	}
@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Message mmm=new Message();
		mmm.what=1;
		handler4.sendMessage(mmm);

	}
@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Toast.makeText(context, "restart", 1000).show();
		
	}
@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Toast.makeText(context, "start", 1000).show();
	}
   @Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Toast.makeText(context, "pause", 1000).show();
	} 
   
     @Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
    	 if(event.getAction()==MotionEvent.ACTION_DOWN){
    		 Message jj=new Message();
        	 jj.what=2;
        	 handler4.sendMessage(jj);
    		 Toast.makeText(getApplicationContext(), "-------------"+event.getAction(), 1000).show();
    	 }
 	return false;
	}
     
}

