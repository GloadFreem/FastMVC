package com.jinzht.web.database.move;

import java.util.HashMap;
import java.util.Map;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import javax.json.JsonBuilderFactory;

import com.jinzht.tools.Config;

public class DatabaseMove {
	private String tableName; //对应要迁移数据库表名
	private Integer page; //分页
	private String requestUrl; //请求地址
	
	public static void main(String[] args)
	{
		DatabaseMove move = new DatabaseMove();
		move.setPage(10);
		move.setRequestUrl("databasemove");
		move.setTableName("Project");
		
		//开始请求
		move.goRequestData();
	}

	public Map goRequestData()
	{
		Map map = new HashMap();
		String postData = String.format("key=%s&page=%d", this.tableName,this.page);
		String result = DatabaseTools.sendRequest(postData, requestUrl);
		
		System.out.println("返回数据:"+result);
		//开始解析
//		JsonObject jsonObject =new JsonObjectBuilder();
		String json1 = DatabaseTools.jsonTest();
		String json2 = DatabaseTools.jsonTest2(result);
		
		System.out.println("返回数据:"+json1);
		System.out.println("返回数据:"+json2);
		return map;
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
}
