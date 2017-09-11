package com.phone.SMScheck;
import java.util.HashMap;
import java.util.Map;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.SimpleCursorAdapter;
//保存接收的短信的数据库
//数据库管理类；
//数据库的创建；查询；更新；添加；删除；关闭；
public class SMS {
	//m_SQLiteDatabase = m_DBOpenHelper.getWritableDatabase();
	/* 数据库对象 */
	private SQLiteDatabase	mSQLiteDatabase	= null;
	//通用数据库名称
	private static final String DATABASE_NAME = "sms_receive.db";
	//数据库版本
	private static final int DB_VERSION = 1;
	/* 表名 */
	private final static String	TABLE_NAME		= "sms_r";//广播接收的短信
	private final static String	TABLE_NAME1		= "sms_c";
	/* 表中的字段 */
	private final static String	TABLE_ID		= "_id";//id号
	private final static String	TABLE_PHONENO	= "phoneno";//发送者手机号码
	private final static String TABLE_RPHONENO = "rphoneno";//接收者手机号码
	private final static String	TABLE_CONTENT		= "content";//短信内容
	private final static String TABLE_SENDTIME  = "sendtime";//接收时间
	private final static String TABLE_OBLIGATE  = "obligate";//预留接口
	/* 创建表的sql语句 */
	public final static String	CREATE_TABLE	= "CREATE TABLE "
	+ TABLE_NAME + " (" + TABLE_ID 
	+ " INTEGER PRIMARY KEY," 
	+ TABLE_PHONENO + " TEXT,"
	+ TABLE_RPHONENO + " TEXT,"
	+ TABLE_CONTENT + " TEXT,"
	+ TABLE_SENDTIME + " TEXT,"
	+TABLE_OBLIGATE + " TEXT)";
	
	public void OpenCreat(Context context)
	{
		// 打开已经存在的数据库
		mSQLiteDatabase = context.openOrCreateDatabase(DATABASE_NAME,
				Context.MODE_WORLD_READABLE , null);
	}
	public void Close()
	{
		mSQLiteDatabase.close();
	}
	public void CreatTable()
	{
		try
		{
			/* 在数据库mSQLiteDatabase中创建一个表 */
			mSQLiteDatabase.execSQL(CREATE_TABLE);
		}
		catch (Exception e)
		{
			//Log.v("Exception e", e.toString());
		}
	}
    
	/* 删除数据库 */
	public void DeleteDataBase(Context context)
	{
		context.deleteDatabase(DATABASE_NAME);
		
	}

   
	/* 删除一个表 */
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
	public void UpData(Context context,int _id,String phoneno,String rphoneno,String content,String sendtime ,String obligate)
	{
		ContentValues cv = new ContentValues();
		cv.put(TABLE_PHONENO, phoneno);//发送者手机号码
		cv.put(TABLE_RPHONENO, rphoneno);//接收者手机号码
		cv.put(TABLE_CONTENT, content);//短信内容
		cv.put(TABLE_SENDTIME, sendtime);//接收时间
		cv.put(TABLE_OBLIGATE,  obligate);//预留接口
		

		/* 更新数据 */
		mSQLiteDatabase.update(TABLE_NAME, cv, TABLE_ID + "=" +_id, null);
		
		
	}


	/* 向表中添加一条数据 */
	public void AddData(String phoneno,String rphoneno,String content,String sendtime,String obligate)
	{
		ContentValues cv = new ContentValues();
		cv.put(TABLE_PHONENO, phoneno);
		cv.put(TABLE_RPHONENO, rphoneno);
		cv.put(TABLE_CONTENT, content);
		cv.put(TABLE_SENDTIME, sendtime);
		cv.put(TABLE_OBLIGATE, obligate);
		/* 插入数据 */
		mSQLiteDatabase.insert(TABLE_NAME, null, cv);
	//	mSQLiteDatabase.insert(TABLE_NAME, null, cv);
		
	}


	/* 从表中删除指定的一条数据 */
	public void DeleteData(Context context,int id)
	{

		/* 删除数据 */
		mSQLiteDatabase.execSQL("DELETE FROM " + TABLE_NAME + " WHERE _id=" + Integer.toString(id));

		
	}

	//索引查询    select * from table1 where id=44
	
	public Cursor QureyCount(int id)
	{
		String a = "SELECT * FROM " + TABLE_NAME + " where _id=" + id;
		return mSQLiteDatabase.rawQuery(a, null);
	}
	
   //从表中删除数据
	public int DeleteData(Context context,String string)
	{
		//delete from table where 字段=值
		if(mSQLiteDatabase.isReadOnly())
		{
			return 0;
		}
		String a = "DELETE FROM " + TABLE_NAME + " WHERE phoneno=" + string;
		mSQLiteDatabase.execSQL("DELETE FROM " + TABLE_NAME + " WHERE phoneno=" + string);
		return 1;
	}
	
	public void CloseData()
	{
		mSQLiteDatabase.close();
	}
	/*查询数据*/
	public Cursor QureyCount(Context context)
	{
		/*查询数据rawQuery*/
		//Cursor query(TABLE_NAME, null, null, null, null, null, null) 查询记录
		Cursor cursor = mSQLiteDatabase.query(TABLE_NAME, 
				new String[]{"_id","phoneno","rphoneno","content","sendtime","obligate"}, 
				null, 
				null,
				null, 
				null, 
				"sendtime desc");
        return cursor;
	}
}
