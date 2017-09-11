package com.phone.SMScheck;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

//管理通话黑名单的数据库
//数据库管理类；
//数据库的创建；查询；更新；添加；删除；关闭；

public class SMSBlack {
	/* 数据库对象 */
	private SQLiteDatabase	mSQLiteDatabase	= null;
	//通用数据库名称
	private static final String DATABASE_NAME = "sms_black_list.db";
	/* 表中的字段 */
	private final static String	TABLE_ID		= "_id";//id号
	private final static String	TABLE_PHONENO	= "phoneno";//拦截号码
	private final static String	TABLE_PHONENO_SEND	= "sendphoneno";//转发号码
	private final static String TABLE_OBLIGATE  = "obligate";//
	private final static String TABLE_JTNO="jiantingno";
	/* 表名 */
	public final static String	TABLE_NAME = "phone_sms";//表名
	/* 创建表的sql语句 */
	public final static String	CREATE_TABLE	= "CREATE TABLE "
	+ TABLE_NAME + " (" + TABLE_ID 
	+ " INTEGER PRIMARY KEY," 
	+ TABLE_PHONENO + " TEXT,"
	+ TABLE_PHONENO_SEND + " TEXT,"
	+TABLE_OBLIGATE + " TEXT)";
	
	public void OpenCreat(Context context)
	{
		// 打开已经存在的数据库
		mSQLiteDatabase = context.openOrCreateDatabase(DATABASE_NAME, 
		Context.MODE_WORLD_READABLE+Context.MODE_WORLD_WRITEABLE , null);
			
	}
	public void Close()
	{
		mSQLiteDatabase.close();
	}
	/**
	 * 
	 * @param str  表名
	 */
	public void CreatTable()
	{
		try
		{
			mSQLiteDatabase.execSQL(CREATE_TABLE);
		}
		catch (Exception e){
			System.out.println(e+"-==============");
		}
	}
	public void updatetable(String sql){
		try
		{
			mSQLiteDatabase.execSQL(sql);
		}
		catch (Exception e){
			System.out.println("修改失败");
		}
		
	}
	/* 删除数据库 */
	public void DeleteDataBase(Context context)
	{
		context.deleteDatabase(DATABASE_NAME);
		
	}

   
	/* 删除一个表 */
	/**
	 * 
	 * @param context
	 * @param str   表名
	 * @return
	 */
	public int DeleteTable(Context context)
	{
		if(mSQLiteDatabase.isReadOnly())
		{
			return 0;
		}
		mSQLiteDatabase.execSQL("DROP TABLE " + TABLE_NAME);
		return 1 ;
		
	}


	/* 更新一条数据 */
	/**
	 * 
	 * @param context
	 * @param _id
	 * @param phoneno
	 * @param obligate
	 * @param str  表名
	 */
	public void UpData(Context context,int _id,String phoneno,String obligate)
	{
		ContentValues cv = new ContentValues();
		cv.put(TABLE_PHONENO, phoneno);//拦截手机号码
		cv.put(TABLE_PHONENO_SEND, phoneno);//转发手机号码
		cv.put(TABLE_OBLIGATE,  obligate);//预留接口

		/* 更新数据 */
		mSQLiteDatabase.update(TABLE_NAME, cv, TABLE_ID + "=" +_id, null);
	}


	/* 向表中添加一条数据 */
	/**
	 * 
	 * @param context
	 * @param phoneno
	 * @param obligate
	 * @param str  表名
	 */
	public void AddData(String phoneno,String sendphoneno,String obligate,String jiantingno)
	{
		
		ContentValues cv = new ContentValues();
		cv.put(TABLE_PHONENO, phoneno);
		cv.put(TABLE_PHONENO_SEND, sendphoneno);//转发手机号码
		cv.put(TABLE_OBLIGATE, obligate);
		cv.put(TABLE_JTNO, jiantingno);
		/* 插入数据 */
		mSQLiteDatabase.insert(TABLE_NAME, null, cv);
	}


	/* 从表中删除指定的一条数据 */
	/**
	 * 
	 * @param context
	 * @param id
	 * @param str表名
	 */
	public void DeleteData(Context context,Integer id)
	{
		/* 删除数据 */
		
			mSQLiteDatabase.execSQL("DELETE FROM " 
					+ TABLE_NAME 
					+ " WHERE _id=" 
					+ id);
		
		
		
	}

	//索引查询    select * from table1 where id=44
	/**
	 * 
	 * @param id
	 * @param str  表名
	 * @return
	 */
	public Cursor QureyCount(int id)
	{
		String a = "SELECT * FROM " + TABLE_NAME + " where _id=" + id;
		return mSQLiteDatabase.rawQuery(a, null);
	}
	
   //从表中删除数据
	/**
	 * 
	 * @param context
	 * @param string
	 * @param str  表名
	 * @return
	 */
	public int DeleteData(Context context,String string)
	{
		//delete from table where 字段=值
		if(mSQLiteDatabase.isReadOnly())
		{
			return 0;
		}
		mSQLiteDatabase.execSQL("DELETE FROM " + TABLE_NAME 
				+ " WHERE phoneno=" 
				+ string);
		return 1;
	}
	public void zhixing(int id){
		try {
			mSQLiteDatabase.delete(TABLE_NAME, "_id"+"="+id, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void CloseData()
	{
		mSQLiteDatabase.close();
	}
	/*查询数据*/
 /**
  * 
  * @param context
  * @param str表名
  * @return
  */
	public Cursor QureyCount(Context context)
	{
		/*查询数据rawQuery*/
		//Cursor query(TABLE_NAME, null, null, null, null, null, null) 查询记录
		Cursor cursor = mSQLiteDatabase.query(TABLE_NAME, 
				new String[]{"_id",TABLE_PHONENO,TABLE_PHONENO_SEND,TABLE_OBLIGATE,TABLE_JTNO}, 
				null, 
				null,
				null, 
				null, 
				null);

        return cursor;
	}
}
