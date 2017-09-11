package com.phone.shadu;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import com.example.phonesafe.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
@SuppressLint("NewApi")
public class APPMListActivity extends Activity implements
Runnable,OnClickListener,OnCheckedChangeListener{
   public ActivityManager am;
   public RadioGroup radiagroup;
   Context context;
   RadioButton radio;
   ActivityManager.MemoryInfo men;
   ListView listdata;
   TextView yishiyong,shengyu;
   APPMListAdapter adapter;
   int xianzai=0;
   int zonggong=0;
   ProgressBar p1,p2;
   ImageButton bt;
   Bundle saved;
   List<Memory> shuju=new ArrayList<Memory>();
	Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {	
			am.getMemoryInfo(men);
			int mm=(int) (men.availMem>>10);
			int tt=(int) (men.totalMem>>10);
			p1.setProgress(mm);
			p2.setProgress(tt-mm);
			yishiyong.setText("已使用了"+(tt-mm)+"kb");
			shengyu.setText("剩余"+mm+"kb内存可使用");
			if(msg.obj!=null&&msg.what==000){
				Memory oo=(Memory) msg.obj;
				shuju.add(oo);
			}
			if(msg.obj!=null&&msg.what==111){
				Memory oo=(Memory) msg.obj;
				if(shuju.contains(oo)){
				 shuju.remove(oo);
//				 Toast.makeText(context, "对象id"+oo.getId(), 1000).show();
					
				}
				
			}
			 adapter=new APPMListAdapter(context, 
					 shuju, R.layout.phone_neicunyouhua_signl);
			 adapter.notifyDataSetChanged();
			listdata.setAdapter(adapter);
//			Toast.makeText(context, "对象为"+msg.obj, Toast.LENGTH_LONG).show();
		};
	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.layout_app_mlis);
    	context=this;
    	saved=savedInstanceState;
    	listdata=(ListView) findViewById(R.id.neicunyouhua_list);
          p1=(ProgressBar) findViewById(R.id.jiandu1);
          yishiyong=(TextView) findViewById(R.id.yishiyong);
          shengyu=(TextView) findViewById(R.id.shengyu);
          p1.setProgress(0);
          p2=(ProgressBar) findViewById(R.id.jindu2);
    	  p2.setProgress(0);
    	  bt=(ImageButton) findViewById(R.id.yijianqingli);
    	  bt.setOnClickListener(this);
    	  radiagroup=(RadioGroup) findViewById(R.id.danxuanzu);
    	  radiagroup.setOnCheckedChangeListener(this);
    	//获取可用内存
    	 am=(ActivityManager) getSystemService(ACTIVITY_SERVICE);
    	 men=new ActivityManager.MemoryInfo();//内存数据
    	 am.getMemoryInfo(men);//获取最新的内存信息（可用）
    	 long memoryData=men.availMem;
    	 long total=men.totalMem;//获取总共
    	 xianzai=(int) (memoryData>>20);//位移20位
    	 zonggong=(int) (total>>20);
    	 p1.setMax(zonggong);
    	 p2.setMax(zonggong);
    	TextView tt=(TextView) findViewById(R.id.app_mlist_t);
    	CMD cmd=new CMD();
    	String str = null;
		String []  c = {"/system/bin/cat","/proc/meminfo"};
		try {
			 str = cmd.run(c, "/system/bin");
			System.out.println("----------------"+str);
			String[] ss=str.split("\n");
			for(int i=0;i<ss.length;i++){
//			Toast.makeText(context, "内存信息"+ss[i], 1000).show();		
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    	//获取内存信息
    	Thread t=new Thread(this);
    	t.start();
       }
	@Override
	public void run() {
		List<RunningServiceInfo> serviceInfos=am.getRunningServices(100);
		Iterator<RunningServiceInfo> l=serviceInfos.iterator();
		while(l.hasNext()){
			RunningServiceInfo run=l.next();
			int pid=run.pid;
			String name=run.process;
			String service=run.service+"";//包名信息
			Memory object=new Memory();
			object.setId(pid);
			object.setName(name);
			object.setService(service);
			object.setIscheck(false);
			Message msg=new Message();
			msg.what=000;
			msg.obj=object;
			handler.sendMessage(msg);	
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
	 	}
	}
	
	
	
	@Override
	public void onClick(View arg0) {
		Toast.makeText(context, "shifoujinru", 1).show();
		List<Memory> ll=adapter.getlistdata();
		final List<Memory> rmobject=new ArrayList<Memory>();
		if(ll!=null&&ll.size()>0){
			for (Memory memory : ll) {
				if(memory.isIscheck()){
					int pid=memory.getId();
					android.os.Process.killProcess(pid);
				    rmobject.add(memory);
				}
			}		
		    }
		Thread kk=new Thread(new Runnable() {			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				if(rmobject!=null&&rmobject.size()>0){
//					shuju.removeAll(rmobject);
//					handler.sendMessage(nn);
					
				 for(int j=0;j<rmobject.size();j++){	
					 Message nn=new Message();
					try {
						if(shuju.contains(rmobject.get(j))){
//						shuju.remove(rmobject.get(j));
							nn.what=111;
							nn.obj=rmobject.get(j);
						   handler.sendMessage(nn);
						  Thread.sleep(1000);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}		
			}}
		});
		
			kk.start();
	
//		shuju.removeAll(rmobject);
//		handler.sendMessage(nn);
		 if(radiagroup.getCheckedRadioButtonId()>0){
	       RadioButton ttt=(RadioButton) APPMListActivity.this.findViewById(radiagroup.getCheckedRadioButtonId());
		 }
	   }
	 @Override
	 public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		int radiabuttionid=group.getCheckedRadioButtonId();
		 radio=(RadioButton) APPMListActivity.this.findViewById(radiabuttionid);
		List<Memory> ll=adapter.getlistdata();
		List<Memory> rmobject=new ArrayList<Memory>();
		Message mess=new Message();
		if(radio.getText().toString().equals("全选")){
			if(ll!=null&&ll.size()>0){
				for (Memory memory : ll) {
					  memory.setIscheck(true);
					}
				}	
			  handler.sendMessage(mess);
			}
		 else if(radio.getText().toString().equals("反选")){
			if(ll!=null&&ll.size()>0){
				for (Memory memory : ll) {
					if(memory.isIscheck()){
					      memory.setIscheck(false);
					}else{
						  memory.setIscheck(true);
					}
				}		
			}
			handler.sendMessage(mess);
		 }
	}
}
