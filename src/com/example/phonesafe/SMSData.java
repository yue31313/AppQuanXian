package com.example.phonesafe;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

  public class SMSData extends SQLiteOpenHelper{
	private static final String db_name="phone_black.db";
	//数据库的表
	private static final String db_table1="phone";//拨打电话的
	private static final String db_table2="phone_incall";//来电的
	//数据库字段
	public final static String key_id="id";
	public final static String key_sms_name="phone_name";
	public final static String key_sms_no="phone_no";
	public final static String key_sms_o="phone_o";
	public SMSData(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	public final static String db_createab="create table"+db_name+"" +
			"("+key_id+"integer primary key,"+key_sms_no+" text,"+key_sms_name+" text,"+key_sms_o+" text)";
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		arg0.execSQL(db_createab);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		   arg0.execSQL("DROP TABLE IF EXISTS " + db_createab);
           onCreate(arg0);

	}

}
