package com.phone.SMScheck;
import java.util.List;
import com.example.phonesafe.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
public class smsBaseAdapter extends BaseAdapter {
	private List<SMSData> SMSList = null;
	private LayoutInflater infater = null;
	public smsBaseAdapter(Context context,  List<SMSData> sms)//sms列表的数据
	{
		infater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		SMSList = sms ;
	}
	public int getCount() {
		return SMSList.size();
	}
	public Object getItem(int arg0) {
		return SMSList.get(arg0);
	}
	public long getItemId(int arg0) {
		return arg0;
	}
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		View view = null;
		ViewHolder holder =  null;
		if (arg1 == null || arg1.getTag() == null) {
			view = infater.inflate(R.layout.sms_list_view, null);
			holder = new ViewHolder(view);
			view.setTag(holder);
		} 
		else{
			view = arg1 ;
			holder = (ViewHolder) arg1.getTag() ;
		}
		SMSData sms = (SMSData) getItem(arg0);
		holder.message.setText(sms.getmessage());
		holder.phoneNo.setText(sms.getphoneNo());
		holder.sendtime.setText(sms.getIntent());
		holder.no.setText(sms.getis());
		holder.i.setImageResource(R.drawable.ic_launcher);
		return view;
	}
	class ViewHolder {
		TextView message;
		TextView phoneNo;
		TextView sendtime;
		TextView no;
		ImageView i;
		public ViewHolder(View view) {
			this.message = (TextView) view.findViewById(R.id.tvLabel);
			this.phoneNo = (TextView) view.findViewById(R.id.tvLabell);
			this.sendtime = (TextView) view.findViewById(R.id.tvLabel1);
			this.no = (TextView) view.findViewById(R.id.tvLabe);
			this.i = (ImageView) view.findViewById(R.id.imgApp);
		}
	}
}
