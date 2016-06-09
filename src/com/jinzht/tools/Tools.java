package com.jinzht.tools;

import javax.servlet.http.HttpSession;

import com.jinzht.web.entity.Users;

public class Tools {
	
	public static Integer findUserBySession(HttpSession session)
	{
		Users user = null;
		if (session.getAttribute("userId") != null) {
			Integer userId = (Integer)session
					.getAttribute("userId");
			return userId;
		}
		return null;
	}
	
	public static String generateInviteCode(Integer id,boolean isWebchatLogin){
		String code = "";
		String flag="T";
		if(!isWebchatLogin){
			flag = "F";
		}
		
		String temp = String.valueOf(id);
		int length  = temp.length();
		
		//填充中间位数
		for(int i = length;i<=Config.INTEGER_SYSTEM_CODE_GENERATE_MAX_VALUE;i++){
			code +="0";
		}
		
		//按照生成统一生成
		return String.format(Config.STRING_SYSTEM_CODE_GENERATE_FORMAT, code,id,flag);
	}
	
	
}
