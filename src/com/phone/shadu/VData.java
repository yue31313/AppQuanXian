package com.phone.shadu;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * �������ݿ������
 * @author Administrator
 *
 */
public class VData extends SQLiteOpenHelper{
	public Context context;
	public String CREATTAB;
	public String tab;
	/**
	 * ���췽��
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
	 * ���ݿⴴ��
	 */
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		arg0.execSQL(CREATTAB);
		
		
	}

	/**
	 * ���ݿ����
	 */
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		arg0.execSQL("DROP TABLE IF EXISTS " + tab);
        onCreate(arg0);

	}
	
	
	
	


}
