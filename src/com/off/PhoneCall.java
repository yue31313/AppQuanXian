package com.off;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.phonesafe.PhoneBlackData;
 public class PhoneCall {
 PhoneBlackData data=new PhoneBlackData();
 List<HashMap<String, Object>> listdata=new ArrayList<HashMap<String,Object>>();
 public List<HashMap<String, Object>> income(Context ctx,String table,String[] phone,String shunxu,String where){
	    data.createdata(ctx);
	  Cursor cu=data.querytable(table, phone,where, null, null, null, shunxu);
	   if(cu!=null){
		  cu.moveToPosition(-1);
	   while(cu.moveToNext()){
		  HashMap<String, Object> ob=new HashMap<String, Object>();
		  ob.put("id", cu.getString(cu.getColumnIndex("id")));
		  ob.put("haoma",cu.getString(cu.getColumnIndex("number")));
		  ob.put("time", cu.getString(cu.getColumnIndex("datatime")));
		  listdata.add(ob);
	    }
	   cu.close();
	   data.close();
	  return listdata;
	  }else{
		  return null;
		  
	  }
	 
	
 }

   public List<HashMap<String, Object>> heimingdanlist(Context context,String table,String[] shuju){
	   List<HashMap<String, Object>> listdata2=new ArrayList<HashMap<String,Object>>();
	   data.createdata(context);
	   Cursor cursor=data.queryall(table,shuju);
	   if(cursor!=null){
		   cursor.moveToPosition(-1);
		   while(cursor.moveToNext()){
			 HashMap<String, Object> object=new HashMap<String, Object>();
			 for(String oo:shuju){
				 object.put(oo,cursor.getString(cursor.getColumnIndex(oo)));
				 
			 }
			   listdata2.add(object);
		   }
		   
	   }
	      cursor.close();
	      data.close();
	   return listdata2;
   }
 
   
   public int deletbyid(String table,int id,Context context){
	   data.createdata(context);
	   int i=0;
	   try {
		   data.deletebyid(table, id);
		   i=1;
	  } catch (Exception e) {
		// TODO: handle exception
		  e.printStackTrace();
	}finally{
		if(data!=null){data.close();}
		
	}   
	   return i;
	   
   }
   public int UpdateById(String table,int id,ContentValues cv,Context context){
	   data.createdata(context);
	   int i=0;
	   try {
		 i= data.update(id, table, cv);
	 } catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	   return i;
	   
   }
   
}
