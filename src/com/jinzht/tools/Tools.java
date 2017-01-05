package com.jinzht.tools;

import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpSession;

import com.jinzht.web.entity.Share;
import com.jinzht.web.entity.Sharetype;
import com.jinzht.web.entity.Users;

public class Tools {

	public static Integer findUserBySession(HttpSession session) {
		Users user = null;
		if (session.getAttribute("userId") != null) {
			Integer userId = (Integer) session.getAttribute("userId");
			return userId;
		}
		return null;
	}

	public static String generateInviteCode(Integer id, boolean isWebchatLogin) {
		String code = "";
		String flag = "T";
		if (!isWebchatLogin) {
			flag = "F";
		}

		String temp = String.valueOf(id);
		int length = temp.length();

		// 填充中间位数
		for (int i = length; i <= Config.INTEGER_SYSTEM_CODE_GENERATE_MAX_VALUE; i++) {
			code += "0";
		}

		// 按照生成统一生成
		return String.format(Config.STRING_SYSTEM_CODE_GENERATE_FORMAT, code,
				id, flag);
	}

	public static Share generateShareContent(Integer contentId,Integer type) {
		// 生成分享链接
		Share share = new Share();
		Sharetype shareType = new Sharetype();
		shareType.setShareTypeId(type);
		share.setSharetype(shareType);
		share.setShareDate(new Date());
		share.setContentId(contentId);
		share.setContent("");
		share.setImage("http://ww2.sinaimg.cn/crop.0.0.1242.1242.1024/b07408d6jw8ey23urbdhkj20yi0yi770.jpg");

		String url = ""; // 生成分享链接
		switch (type) {
		case 1:
			url = Tools.generateWebUrl(Config.STRING_SYSTEM_SHARE_PROJECT_DETAIL);
			break;
		case 2:
			url = Tools.generateWebUrl(Config.STRING_SYSTEM_SHARE_FEELING_DETAIL);
			break;
		case 3:
			url = Config.STRING_SHARE_APP_URL;
			break;
		case 5:
			url = Tools.generateWebUrl(Config.STRING_SYSTEM_SHARE_FEELING_DETAIL);
			break;
		default:
			url = String.format("%s%d/%d", Config.STRING_SYSTEM_ADDRESS, type,
					contentId);
			break;
		}

		share.setUrl(url);
		
		return share;
	}
	
	public static String objToStr(String obj)
	{
		if(obj!=null && obj!="")
		{
			return obj.trim();
		}
		return "";
		
	}
	
	/***
	 * 生成请求连接
	 * @param actionUrl
	 * @return
	 */
	public static String generateWebUrl(String actionUrl)
	{
		return String.format(Config.STRING_SYSTEM_ADDRESS+"%s.action", actionUrl);
	}
	
	/***
	 * 根据内容id生成网页查看链接
	 * @param contentId
	 * @return
	 */
	public static String  generateWebRecordUrl(Integer contentId)
	{
		String path = String.format("%swebUrlLooker.action?contentId=%d",
				Config.STRING_SYSTEM_ADDRESS, contentId);
		return path;
	}
	
	
	/***
	 * 按照密码生成规则生成密码
	 * @param password
	 * @return
	 */
	public static String generatePassword(String password,String telephone)
	{
		String str = String.format(Config.STRING_PASWWORD_RULE, password,telephone);
		
		return MD5.GetMD5Code(str);
	}
	
	public static String getRandomString(int length) { //length表示生成字符串的长度
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	 }   

}
