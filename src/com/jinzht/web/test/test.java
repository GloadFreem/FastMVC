package com.jinzht.web.test;

import com.jinzht.tools.EncryptUtil;

public class test {
	public static void main(String[] args)
	{
		byte[] s = EncryptUtil.encrypt("123", "111");
		System.out.println(s);
		
		byte[] d = EncryptUtil.decrypt(s, "111");
		System.out.println(d);
	}

}
