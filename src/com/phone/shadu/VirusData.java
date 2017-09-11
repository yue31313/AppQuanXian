package com.phone.shadu;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
public class VirusData {
	
	/**
	 * 病毒数据库操作类
	 */

	//数据库名
	private static final String DB_NAME = "virus.db";
	//表名
	private static final String DB_TABLE = "peopleinfo";
	//数据库版本
	private static final int DB_VERSION = 1;
		
	//字段
	public static final String KEY_ID = "_id";
	public static final String KEY_NAME = "virus_pkg_name";//病毒包名（）
	public static final String KEY_AGE = "virus_apk_name";//病毒文件md5
	public static final String KEY_HEIGHT = "virus_meg";//病毒描述信息
	public static final String KEY_VISON = "virus_vison";//病毒版本
	public static final String KEY_BEIZHU = "virus_beizhu";//病毒备注信息

	//创建表的语句 
	public final static String CREATTAB = "CREATE TABLE "
				+DB_TABLE+ " ("+ KEY_ID 
				+ " INTEGER PRIMARY KEY,"
				+ KEY_NAME +" TEXT,"
				+ KEY_AGE +" TEXT,"
				+ KEY_HEIGHT +" TEXT,"
				+ KEY_VISON +" TEXT,"
				+ KEY_BEIZHU +" TEXT)";
	
	/**
	 * 数据库对象
	 */
	private SQLiteDatabase virus_db;
	
	private VData dbOpenHelper;
	
	public Context context;
	
	//构造方法
	public VirusData(Context context)
	{
		this.context = context;
	}
	
	/**
	 * 打开数据库
	 * @throws SQLiteException
	 */
	public void open() throws SQLiteException {
		
		//		virus_db = context.openOrCreateDatabase(arg0, arg1, arg2)
		
		dbOpenHelper = new VData(context, DB_NAME, null, 
				DB_VERSION,CREATTAB,DB_TABLE);

		try {
			virus_db = dbOpenHelper.getWritableDatabase();
        }catch (SQLiteException ex) {
        	virus_db = dbOpenHelper.getReadableDatabase();
	        }
	}

	
	public void close()
	{
		if(virus_db!=null)
			{
			virus_db.close();
		}
	}
	/**
	 * 添加数据
	 * @param KEY_NAME  病毒包名（）
	 * @param KEY_AGE  病毒文件md5
	 * @param KEY_HEIGHT 病毒描述信息
	 * @param KEY_VISON  病毒版本
	 * @param KEY_BEIZHU 病毒备注信息
	 */
	
	public void add(String KEY_NAM,String KEY_AG,String KEY_HEIGH,
			String KEY_VISO,String KEY_BEIZH)
	{
		
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, KEY_NAM);
		cv.put(KEY_AGE, KEY_AG);
		cv.put(KEY_HEIGHT, KEY_HEIGH);
		cv.put(KEY_VISON, KEY_VISO);
		cv.put(KEY_BEIZHU, KEY_BEIZH);
		
		virus_db.insert(DB_TABLE, KEY_ID, cv);
		
		
	}
	
	public Cursor getAppData()
	{
		return virus_db.query(DB_TABLE, null, null, null, null, null, null);
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
