package com.phone.SMScheck;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.phonesafe.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SmsAdapter extends BaseAdapter {
 private Context context;
 private List<HashMap<String, Object>> listdata;
 private LayoutInflater ly;
	public SmsAdapter(List<HashMap<String, Object>> listdata,Context context) {
     this.context=context;
     this.listdata=listdata;
     ly=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listdata.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return listdata.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		View view=null;
     if(arg1==null||arg1.getTag()==null){
//    	 view=getview(listdata.get(arg0).get("name").toString(),Integer.parseInt(listdata.get(arg0).get("imageid").toString()));
        view=ly.inflate(R.layout.sms_show,null);
        TextView name=(TextView) view.findViewById(R.id.sms_name);
        name.setText(listdata.get(arg0).get("name").toString());
        ImageView imag=(ImageView) view.findViewById(R.id.sms_image);
        imag.setImageResource(Integer.parseInt(listdata.get(arg0).get("imageid").toString()));
     }
		return view;
	}
//    private View getview(String name,int imageid){
//    	LinearLayout nn=new LinearLayout(context);
//  	    nn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//  	    nn.setOrientation(0);
//  	    TextView t1=new TextView(context);
//  	    t1.setWidth(LayoutParams.WRAP_CONTENT);
//  	    t1.setHeight(LayoutParams.WRAP_CONTENT);
//  	    t1.setText(name);
//        nn.addView(t1, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//  	    ImageView imgview=new ImageView(context);
//  	    imgview.setImageResource(imageid);
//  	    nn.addView(imgview, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));  
//     return nn;
//    }
	
	
}
