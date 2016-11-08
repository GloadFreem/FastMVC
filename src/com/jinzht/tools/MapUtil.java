package com.jinzht.tools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUtil {

	public static Map mapData(String message, String status, List list){
		Map map = new HashMap();
		map.put("message", message);
		map.put("status", status);
		map.put("data", list);
		return map;
	}
}
