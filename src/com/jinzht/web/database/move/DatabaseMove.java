package com.jinzht.web.database.move;

import java.util.HashMap;
import java.util.Map;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonBuilderFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.jinzht.tools.Config;

public class DatabaseMove {
	private String tableName; //对应要迁移数据库表名
	private Integer page; //分页
	private String requestUrl; //请求地址
	private Integer size;
	
	public static void main(String[] args)
	{
//		DatabaseMove move = new DatabaseMove();
//		move.setPage(10);
//		move.setRequestUrl("databasemove");
//		move.setTableName("Project");
//		//开始请求
//		move.goRequestData();
		
		String number = "0.07";
		Double value = Double.parseDouble(number);
		Double total = value * 10000.00;
		
		System.out.println(total);
		System.out.println(String.valueOf(total));
	}

	public JSONArray goRequestData()
	{
		String postData = String.format("key=%s&page=%d&size=%d", this.tableName,this.page,this.size);
		String result = DatabaseTools.sendRequest(postData, requestUrl);
		
		System.out.println("返回数据:"+result);
		
		JSONObject json= JSONObject.fromObject(result);
		JSONArray array = json.getJSONArray("data");
		
		return array;
	}
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	public String getRequestUrl() {
		return requestUrl;
	}
	public void setRequestUrl(String requestUrl) {
		String config = Config.STRING_PYTHON_SYSTEM_ADDRESS+requestUrl+"/?format=json";
		this.requestUrl = config;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
}
