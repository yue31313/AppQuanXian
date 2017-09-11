package com.phone.shadu;

import java.util.HashMap;
import java.util.List;

import com.example.phonesafe.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class APPMListAdapter extends BaseAdapter {
private Context mContext;
private List<Memory> mdatalist;
private int mResource;
private LayoutInflater mInflater;
public APPMListAdapter(Context context,List<Memory> datalist,int resource) {
	mResource=resource;
	mContext=context;
	mdatalist=datalist;
	mInflater=  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mdatalist.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mdatalist.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}
	@Override
	public View getView(final int arg0, View arg1, ViewGroup arg2) {
          if(arg1==null){
        	  arg1=mInflater.inflate(mResource, arg2, false);
          }
     TextView name=(TextView) arg1.findViewById(R.id.neicuntext1);
     TextView message=(TextView) arg1.findViewById(R.id.neicuntext2);
     final CheckBox ckbitem=(CheckBox) arg1.findViewById(R.id.checkbox_id);
      Memory object=mdatalist.get(arg0);
      name.setText(object.getName());
      message.setText(object.getService());
      ckbitem.setChecked(object.isIscheck());
      ckbitem.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg2) {
			// TODO Auto-generated method stub
		mdatalist.get(arg0).setIscheck(ckbitem.isChecked());
		}
	});
	  object=null;
     return arg1;
	}
 public List<Memory> getlistdata(){
	 
	 return mdatalist;
 }
}
