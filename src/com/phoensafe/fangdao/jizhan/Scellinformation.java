package com.phoensafe.fangdao.jizhan;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.ContentResolver;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;

public class Scellinformation {
	public Context context;
	public Scellinformation(Context context) {
		// TODO Auto-generated constructor stub
		this.context=context;
	}
/**
 * 获得基站的信息
 * @return
 * @throws Exception
 */
public Scell getCellInfo() throws Exception{
	Scell cell=new Scell();
	TelephonyManager mTelNet = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
	GsmCellLocation location = (GsmCellLocation) mTelNet.getCellLocation();
	if (location == null)
		throw new Exception("获取基站信息失败");

	String operator = mTelNet.getNetworkOperator();
	int mcc = Integer.parseInt(operator.substring(0, 3));
	int mnc = Integer.parseInt(operator.substring(3));
	int cid = location.getCid();
	int lac = location.getLac();
     
	/** 将获得的数据放到结构体中 */
	cell.setMCC(mcc);
	cell.setMNC(mnc);
	cell.setLAC(lac);
	cell.setCID(cid);
	return cell;

	
}


/**
 * 获得经纬度信息
 * @throws Exception 
 */
public SItude getItude(Scell cell) throws Exception{
	SItude itude = new SItude();
	HttpClient client = new DefaultHttpClient();
	/** 采用POST方法 */
	HttpPost post = new HttpPost("http://www.google.com/loc/json");
	try {
		/** 构造POST的JSON数据 */
		JSONObject holder = new JSONObject();
		holder.put("version", "1.1.0");
		holder.put("host", "maps.google.com");
		holder.put("address_language", "zh_CN");
		holder.put("request_address", true);
		holder.put("radio_type", "gsm");
		holder.put("carrier", "HTC");
		JSONObject tower = new JSONObject();
		tower.put("mobile_country_code", cell.getMCC());
		tower.put("mobile_network_code", cell.getMNC());
		tower.put("cell_id", cell.getCID());
		tower.put("location_area_code", cell.getLAC());
		JSONArray towerarray = new JSONArray();
		towerarray.put(tower);
		holder.put("cell_towers", towerarray);
		StringEntity query = new StringEntity(holder.toString());
		post.setEntity(query);
		/** 发出POST数据并获取返回数据 */
		HttpResponse response = client.execute(post);
		HttpEntity entity = response.getEntity();
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(entity.getContent()));
		StringBuffer strBuff = new StringBuffer();
		String result = null;
		while ((result = buffReader.readLine()) != null) {
			strBuff.append(result);
		}

		/** 解析返回的JSON数据获得经纬度 */
		JSONObject json = new JSONObject(strBuff.toString());
		JSONObject subjosn = new JSONObject(json.getString("location"));
		itude.setLatitude(subjosn.getString("latitude"));
		itude.setLongitude(subjosn.getString("longitude"));
	} catch (Exception e) {
		throw new Exception("获取经纬度出现错误:"+e.getMessage());
	} finally{
		post.abort();
		client = null;
	}
	
	return itude;

}
}
