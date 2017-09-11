package com.example.phonesafe;
import android.R.integer;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
public class PhoneBlackData {
private static final String db_name="phone_black.db";
//数据库的表
public static final String db_table1="phone_call";//拨打电话的
public static final String db_table2="phone_incall";//来电的
public static final String db_table3="phone_lisenter";//监听的
public static final String db_table4="dophonenumber";//处理未接，已结，播出电话记录的表
public static final String db_table5="jiantingamr";
//数据库字段
public final static String key_id="id";
public final static String key_sms_name="phone_name";
public final static String key_sms_no="phone_no";
public final static String key_sms_o="phone_o";
//创建表的语句
public final static String createab="create table "+db_table1+"("+key_id+" integer primary key,"+key_sms_no+" text,"+key_sms_name+" text,"+key_sms_o+" text)";
public final static String  createab2="create table "+db_table2+"("+key_id+" integer primary key,"+key_sms_no+" text,"+key_sms_name+" text,"+key_sms_o+" text)";
public final static String createab3="create table phone_lisenter("+key_id+" integer primary key,"+key_sms_no+" text,"+key_sms_name+" text,"+key_sms_o+" text)";
public final static String createab4="create table "+db_table4+"(id integer primary key,number text,type text,datatime text)";//设置type 1.2.3 1：未接，2：已结，3：拨号
public final static String createab5="create table jiantingamr (id integer primary key,number text,url text,totaltime text,datatime text)";
private SQLiteDatabase phone=null;
/**
 * 创建打开数据库
 * 组件对象
 */
public void createdata(Context context){
	phone=context.openOrCreateDatabase(db_name, Context.MODE_WORLD_READABLE+Context.MODE_WORLD_WRITEABLE, null);
}

    public void createtable(String string) {
    	
    	try {
    		 phone.execSQL(string);
      		 
	    	} catch (Exception e) {
			// TODO: handle exception
					}  	
	    }
    public void close(){
    	phone.close();
    	
    }
    /**
     * 实现增删改
     */
    public void add(String phone1,String phone2,String phone3,String tablename){
    	ContentValues cv=new ContentValues();
    	cv.put(key_sms_no,phone1);
    	cv.put(key_sms_name,phone2);
    	cv.put(key_sms_o,phone3);
    	phone.insertOrThrow(tablename, null, cv);
    }
    public void add(String table,ContentValues cv){
    	try {
    		phone.insert(table, null, cv);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
    }
   public void update(int i,String phone1,String phone2,String phone3){
	ContentValues cv=new ContentValues();
   	cv.put(key_sms_no,phone1);
   	cv.put(key_sms_name,phone2);
   	cv.put(key_sms_o, phone3);
   	phone.update(db_table1, cv, key_id+"="+i,null);
   }
   public int update(int i,String table,ContentValues ctx){
	   int k=-1;
	   try {
		  phone.update(table, ctx, key_id+"="+i,null);
		
		  k=1;
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("shuchuyichang");
		  e.printStackTrace();
		k=0;
	}
	   return k;
   }
   
   public Cursor query(String table,String shuju){
	   Cursor curs=null;
	   try {
		    curs=phone.query(table,new String[]{"phone_no","id"}, "phone_no="+shuju, null, null, null, null);
	  }catch(NullPointerException e) {
		// TODO: handle exception
		 e.printStackTrace();
	  }
	return curs;
	      
   }
   public Cursor querytable(String table,String[] data,String sl,String[] selectionargs,String groupby,String having,String shunxu){
	   Cursor css=null; 
	   try {
	    	  css=phone.query(table,data, sl, selectionargs,groupby, having, shunxu);
		} catch (Exception e) {
			// TODO: handle exception
		}
	 
	   return css;
	   
   }
   public Cursor queryall(String table,String[] data){
	   Cursor css=null; 
	   try {
	    	  css=phone.query(table,data, null, null, null, null, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
	 
	   return css;
   }
     public void deletdata(Context context){
    	 try {
    		 context.deleteDatabase(db_name);

		} catch (Exception e) {
			// TODO: handle exception
		}
	        }
      public void deletetabname(String name){
	     if(phone.isReadOnly()){}
	     else{
	    	 
	    	 String str="drop table "+db_table1;
	    	 phone.execSQL(str);
	    	 
	     }   
     }
    public void delete(String str, String data){
	     String sql="delete from "+db_table1+" where "+str+" ="+'"'+data+'"';
	     phone.execSQL(sql);
	   
     }
    public void deletebyid(String table,Integer shu){
    	try {
    		phone.delete(table, "id="+shu,null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	
    }
    
    /**
     * 
     */
    
    
}
