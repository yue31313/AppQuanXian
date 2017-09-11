package com.phone.SMScheck;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

//����ͨ�������������ݿ�
//���ݿ�����ࣻ
//���ݿ�Ĵ�������ѯ�����£���ӣ�ɾ�����رգ�

public class SMSBlack {
	/* ���ݿ���� */
	private SQLiteDatabase	mSQLiteDatabase	= null;
	//ͨ�����ݿ�����
	private static final String DATABASE_NAME = "sms_black_list.db";
	/* ���е��ֶ� */
	private final static String	TABLE_ID		= "_id";//id��
	private final static String	TABLE_PHONENO	= "phoneno";//���غ���
	private final static String	TABLE_PHONENO_SEND	= "sendphoneno";//ת������
	private final static String TABLE_OBLIGATE  = "obligate";//
	private final static String TABLE_JTNO="jiantingno";
	/* ���� */
	public final static String	TABLE_NAME = "phone_sms";//����
	/* �������sql��� */
	public final static String	CREATE_TABLE	= "CREATE TABLE "
	+ TABLE_NAME + " (" + TABLE_ID 
	+ " INTEGER PRIMARY KEY," 
	+ TABLE_PHONENO + " TEXT,"
	+ TABLE_PHONENO_SEND + " TEXT,"
	+TABLE_OBLIGATE + " TEXT)";
	
	public void OpenCreat(Context context)
	{
		// ���Ѿ����ڵ����ݿ�
		mSQLiteDatabase = context.openOrCreateDatabase(DATABASE_NAME, 
		Context.MODE_WORLD_READABLE+Context.MODE_WORLD_WRITEABLE , null);
			
	}
	public void Close()
	{
		mSQLiteDatabase.close();
	}
	/**
	 * 
	 * @param str  ����
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
			System.out.println("�޸�ʧ��");
		}
		
	}
	/* ɾ�����ݿ� */
	public void DeleteDataBase(Context context)
	{
		context.deleteDatabase(DATABASE_NAME);
		
	}

   
	/* ɾ��һ���� */
	/**
	 * 
	 * @param context
	 * @param str   ����
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


	/* ����һ������ */
	/**
	 * 
	 * @param context
	 * @param _id
	 * @param phoneno
	 * @param obligate
	 * @param str  ����
	 */
	public void UpData(Context context,int _id,String phoneno,String obligate)
	{
		ContentValues cv = new ContentValues();
		cv.put(TABLE_PHONENO, phoneno);//�����ֻ�����
		cv.put(TABLE_PHONENO_SEND, phoneno);//ת���ֻ�����
		cv.put(TABLE_OBLIGATE,  obligate);//Ԥ���ӿ�

		/* �������� */
		mSQLiteDatabase.update(TABLE_NAME, cv, TABLE_ID + "=" +_id, null);
	}


	/* ��������һ������ */
	/**
	 * 
	 * @param context
	 * @param phoneno
	 * @param obligate
	 * @param str  ����
	 */
	public void AddData(String phoneno,String sendphoneno,String obligate,String jiantingno)
	{
		
		ContentValues cv = new ContentValues();
		cv.put(TABLE_PHONENO, phoneno);
		cv.put(TABLE_PHONENO_SEND, sendphoneno);//ת���ֻ�����
		cv.put(TABLE_OBLIGATE, obligate);
		cv.put(TABLE_JTNO, jiantingno);
		/* �������� */
		mSQLiteDatabase.insert(TABLE_NAME, null, cv);
	}


	/* �ӱ���ɾ��ָ����һ������ */
	/**
	 * 
	 * @param context
	 * @param id
	 * @param str����
	 */
	public void DeleteData(Context context,Integer id)
	{
		/* ɾ������ */
		
			mSQLiteDatabase.execSQL("DELETE FROM " 
					+ TABLE_NAME 
					+ " WHERE _id=" 
					+ id);
		
		
		
	}

	//������ѯ    select * from table1 where id=44
	/**
	 * 
	 * @param id
	 * @param str  ����
	 * @return
	 */
	public Cursor QureyCount(int id)
	{
		String a = "SELECT * FROM " + TABLE_NAME + " where _id=" + id;
		return mSQLiteDatabase.rawQuery(a, null);
	}
	
   //�ӱ���ɾ������
	/**
	 * 
	 * @param context
	 * @param string
	 * @param str  ����
	 * @return
	 */
	public int DeleteData(Context context,String string)
	{
		//delete from table where �ֶ�=ֵ
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
	/*��ѯ����*/
 /**
  * 
  * @param context
  * @param str����
  * @return
  */
	public Cursor QureyCount(Context context)
	{
		/*��ѯ����rawQuery*/
		//Cursor query(TABLE_NAME, null, null, null, null, null, null) ��ѯ��¼
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
