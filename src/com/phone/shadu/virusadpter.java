package com.phone.shadu;
import java.util.HashMap;
import java.util.List;
import com.example.phonesafe.R;
import com.phone.shadu.APPAdapter.ViewHolder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class virusadpter extends BaseAdapter {
	private Context context;
	private List<HashMap<String, Object>>  listdd;
	private LayoutInflater layout;
    public virusadpter(Context context,List<HashMap<String, Object>>  listdd) {
     this.context=context; 
     this.listdd=listdd;
     layout=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listdd.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return listdd.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		View view=null;
		holderview viewGroup=null;
		if(arg1==null||arg1.getTag()==null){
			view=layout.inflate(R.layout.vrius_add_sginl,null);
			viewGroup=new holderview(view);
			view.setTag(viewGroup);
			
		}else{
			view=arg1;
			viewGroup=(holderview) arg1.getTag();
		}
	 //把列表中数据添加到列表视图中
		HashMap<String, Object> object=new HashMap<String, Object>();
		object=listdd.get(arg0);
		viewGroup.appimage.setImageDrawable((Drawable) object.get("text2"));
		viewGroup.appname.setText( object.get("text1").toString());
		viewGroup.apppackage.setText(object.get("text3").toString());
		viewGroup.btn.setOnClickListener(new onclick(object.get("text3").toString()));
		return view;
		
	
	}
  class holderview {

	   TextView appname;
	   TextView apppackage;
	   ImageView appimage;
	   Button btn;
	   public holderview(View view) {
			appname=(TextView) view.findViewById(R.id.virus_text1_appname);
			apppackage=(TextView) view.findViewById(R.id.virus_text3_apppackage);
			appimage=(ImageView) view.findViewById(R.id.virus_text2_appicon);
			btn=(Button) view.findViewById(R.id.virus_text4_appbtn);
		}
		  
	   
	  
  }
  
  class onclick implements OnClickListener{
	  private String packagename;
   public onclick(String packagename) {
	   this.packagename=packagename;
  }
  @Override
  public void onClick(View arg0) {
	// TODO Auto-generated method stub
	  
	  if(packagename!=null){
	  Uri uri=Uri.fromParts("package", packagename,null);
	  Intent intent=new Intent(Intent.ACTION_DELETE);
	  intent.setData(uri);
	  context.startActivity(intent);}
	
}

  }
}
