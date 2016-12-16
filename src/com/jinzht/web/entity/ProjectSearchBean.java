package com.jinzht.web.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.fabric.xmlrpc.base.Array;

public class ProjectSearchBean {
	
	
	private String cName;
	private String cKey;
	private List<MapResult> cData = new ArrayList<MapResult>();
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getcKey() {
		return cKey;
	}
	public void setcKey(String cKey) {
		this.cKey = cKey;
	}
	public List<MapResult> getcData() {
		return cData;
	}
	public void setcData(List<MapResult> cData) {
		this.cData = cData;
	}
	
	
	public static class MapResult {
		private int itemKey;
		private String value;
		public int getItemKey() {
			return itemKey;
		}
		public void setItemKey(int itemKey) {
			this.itemKey = itemKey;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		
		
		

	}
	
	
	

}
