package com.phone.shadu;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * 病毒数据库管理类
 * @author Administrator
 *
 */
public class VData extends SQLiteOpenHelper{
	public Context context;
	public String CREATTAB;
	public String tab;
	/**
	 * 构造方法
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	public VData(Context context, String name, CursorFactory factory,
			int version,String string,String tab) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
		this.CREATTAB = string;
		this.tab = tab;
	}
	/**
	 * 数据库创建
	 */
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		arg0.execSQL(CREATTAB);
		
		
	}

	/**
	 * 数据库更新
	 */
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		arg0.execSQL("DROP TABLE IF EXISTS " + tab);
        onCreate(arg0);

	}
	
	
	
	


}
