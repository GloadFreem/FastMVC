package com.jinzht.web.database.move;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class DatabaseTools {
	public static String sendRequest(String postData, String postUrl) {
        try {
        	System.out.println("请求地址:"+postUrl);
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Length", "" + postData.length());
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(postData);
            out.flush();
            out.close();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("connect failed!");
                return "";
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            String result = "";
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return "";
    }
	
	public static String jsonTest() throws JSONException{  
	    JSONObject json=new JSONObject();  
	    JSONArray jsonMembers = new JSONArray();  
	    JSONObject member1 = new JSONObject();  
	    member1.put("loginname", "zhangfan");  
	    member1.put("password", "userpass");  
	    member1.put("email","10371443@qq.com");  
	    member1.put("sign_date", "2007-06-12");  
	    jsonMembers.add(member1);  
	  
	    JSONObject member2 = new JSONObject();  
	    member2.put("loginname", "zf");  
	    member2.put("password", "userpass");  
	    member2.put("email","8223939@qq.com");  
	    member2.put("sign_date", "2008-07-16");  
	    jsonMembers.add(member2);  
	    json.put("users", jsonMembers);  
	  
	    return json.toString();  
	}  
	
	public static String jsonTest2(String jsonString) throws JSONException{  
	    JSONObject json= JSONObject.fromObject(jsonString); 
	    String message = json.getString("msg");
	    
//	    JSONArray jsonArray=json.getJSONArray("users");  
	   
	     return message;  
	}  
}
