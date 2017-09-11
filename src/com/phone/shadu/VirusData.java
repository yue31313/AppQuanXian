package com.phone.shadu;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
public class VirusData {
	
	/**
	 * �������ݿ������
	 */

	//���ݿ���
	private static final String DB_NAME = "virus.db";
	//����
	private static final String DB_TABLE = "peopleinfo";
	//���ݿ�汾
	private static final int DB_VERSION = 1;
		
	//�ֶ�
	public static final String KEY_ID = "_id";
	public static final String KEY_NAME = "virus_pkg_name";//������������
	public static final String KEY_AGE = "virus_apk_name";//�����ļ�md5
	public static final String KEY_HEIGHT = "virus_meg";//����������Ϣ
	public static final String KEY_VISON = "virus_vison";//�����汾
	public static final String KEY_BEIZHU = "virus_beizhu";//������ע��Ϣ

	//���������� 
	public final static String CREATTAB = "CREATE TABLE "
				+DB_TABLE+ " ("+ KEY_ID 
				+ " INTEGER PRIMARY KEY,"
				+ KEY_NAME +" TEXT,"
				+ KEY_AGE +" TEXT,"
				+ KEY_HEIGHT +" TEXT,"
				+ KEY_VISON +" TEXT,"
				+ KEY_BEIZHU +" TEXT)";
	
	/**
	 * ���ݿ����
	 */
	private SQLiteDatabase virus_db;
	
	private VData dbOpenHelper;
	
	public Context context;
	
	//���췽��
	public VirusData(Context context)
	{
		this.context = context;
	}
	
	/**
	 * �����ݿ�
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
	 * �������
	 * @param KEY_NAME  ������������
	 * @param KEY_AGE  �����ļ�md5
	 * @param KEY_HEIGHT ����������Ϣ
	 * @param KEY_VISON  �����汾
	 * @param KEY_BEIZHU ������ע��Ϣ
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
