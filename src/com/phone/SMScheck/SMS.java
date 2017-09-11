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
//������յĶ��ŵ����ݿ�
//���ݿ�����ࣻ
//���ݿ�Ĵ�������ѯ�����£���ӣ�ɾ�����رգ�
public class SMS {
	//m_SQLiteDatabase = m_DBOpenHelper.getWritableDatabase();
	/* ���ݿ���� */
	private SQLiteDatabase	mSQLiteDatabase	= null;
	//ͨ�����ݿ�����
	private static final String DATABASE_NAME = "sms_receive.db";
	//���ݿ�汾
	private static final int DB_VERSION = 1;
	/* ���� */
	private final static String	TABLE_NAME		= "sms_r";//�㲥���յĶ���
	private final static String	TABLE_NAME1		= "sms_c";
	/* ���е��ֶ� */
	private final static String	TABLE_ID		= "_id";//id��
	private final static String	TABLE_PHONENO	= "phoneno";//�������ֻ�����
	private final static String TABLE_RPHONENO = "rphoneno";//�������ֻ�����
	private final static String	TABLE_CONTENT		= "content";//��������
	private final static String TABLE_SENDTIME  = "sendtime";//����ʱ��
	private final static String TABLE_OBLIGATE  = "obligate";//Ԥ���ӿ�
	/* �������sql��� */
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
		// ���Ѿ����ڵ����ݿ�
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
			/* �����ݿ�mSQLiteDatabase�д���һ���� */
			mSQLiteDatabase.execSQL(CREATE_TABLE);
		}
		catch (Exception e)
		{
			//Log.v("Exception e", e.toString());
		}
	}
    
	/* ɾ�����ݿ� */
	public void DeleteDataBase(Context context)
	{
		context.deleteDatabase(DATABASE_NAME);
		
	}

   
	/* ɾ��һ���� */
	public int DeleteTable(Context context)
	{
		if(mSQLiteDatabase.isReadOnly())
		{
			return 0;
		}
		mSQLiteDatabase.execSQL("DROP TABLE " + TABLE_NAME);
		return 1 ;
		
	}


	/* ����һ������ */
	public void UpData(Context context,int _id,String phoneno,String rphoneno,String content,String sendtime ,String obligate)
	{
		ContentValues cv = new ContentValues();
		cv.put(TABLE_PHONENO, phoneno);//�������ֻ�����
		cv.put(TABLE_RPHONENO, rphoneno);//�������ֻ�����
		cv.put(TABLE_CONTENT, content);//��������
		cv.put(TABLE_SENDTIME, sendtime);//����ʱ��
		cv.put(TABLE_OBLIGATE,  obligate);//Ԥ���ӿ�
		

		/* �������� */
		mSQLiteDatabase.update(TABLE_NAME, cv, TABLE_ID + "=" +_id, null);
		
		
	}


	/* ��������һ������ */
	public void AddData(String phoneno,String rphoneno,String content,String sendtime,String obligate)
	{
		ContentValues cv = new ContentValues();
		cv.put(TABLE_PHONENO, phoneno);
		cv.put(TABLE_RPHONENO, rphoneno);
		cv.put(TABLE_CONTENT, content);
		cv.put(TABLE_SENDTIME, sendtime);
		cv.put(TABLE_OBLIGATE, obligate);
		/* �������� */
		mSQLiteDatabase.insert(TABLE_NAME, null, cv);
	//	mSQLiteDatabase.insert(TABLE_NAME, null, cv);
		
	}


	/* �ӱ���ɾ��ָ����һ������ */
	public void DeleteData(Context context,int id)
	{

		/* ɾ������ */
		mSQLiteDatabase.execSQL("DELETE FROM " + TABLE_NAME + " WHERE _id=" + Integer.toString(id));

		
	}

	//������ѯ    select * from table1 where id=44
	
	public Cursor QureyCount(int id)
	{
		String a = "SELECT * FROM " + TABLE_NAME + " where _id=" + id;
		return mSQLiteDatabase.rawQuery(a, null);
	}
	
   //�ӱ���ɾ������
	public int DeleteData(Context context,String string)
	{
		//delete from table where �ֶ�=ֵ
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
	/*��ѯ����*/
	public Cursor QureyCount(Context context)
	{
		/*��ѯ����rawQuery*/
		//Cursor query(TABLE_NAME, null, null, null, null, null, null) ��ѯ��¼
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
