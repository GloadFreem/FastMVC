package com.jinzht.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class YeePayUtil {
	public static String sign(String req) {
		String ret = "";

		HttpClient client = new DefaultHttpClient();
		String path = Config.STRING_YEEPAY_VERIFY_ADDRESS + "sign" + "?req="
				+ req;
		HttpPost post = new HttpPost(path);

		StringEntity entity;
		try {
			HttpResponse response = client.execute(post);

			InputStream inStream = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inStream, "utf-8"));
			StringBuilder strber = new StringBuilder();

			String line = null;
			while ((line = reader.readLine()) != null)
				strber.append(line);
			inStream.close();

			System.out.println("服务器端响应的数据：" + strber.toString());
			ret = strber.toString();
			ret.substring(0, ret.length()-1);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

		return ret;
	}
	public static String verify(String req,String sign) {
		String ret = "";
		
		HttpClient client = new DefaultHttpClient();
		String path = Config.STRING_YEEPAY_VERIFY_ADDRESS + "verify" + "?req="
				+ req+"&sign="+sign;
		HttpPost post = new HttpPost(path);
		
		StringEntity entity;
		try {
			HttpResponse response = client.execute(post);
			
			InputStream inStream = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inStream, "utf-8"));
			StringBuilder strber = new StringBuilder();
			
			String line = null;
			while ((line = reader.readLine()) != null)
				strber.append(line);
			inStream.close();
			
			System.out.println("服务器端响应的数据：" + strber.toString());
			ret = strber.toString();
			ret.substring(0, ret.length()-1);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		return ret;
	}

	public static void main(String[] args) {
		String req = "<request platformNo=\"10040011137\"><platformUserNo>张三</platformUserNo></request>";
		req = URLEncoder.encode(req);
		System.out.println(req);
		String sign = YeePayUtil.sign( req);
		
		sign = URLEncoder.encode(sign);
		String result = YeePayUtil.verify(req, sign);
	}
}
