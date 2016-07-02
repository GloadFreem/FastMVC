package com.jinzht.tools;

import java.util.Date;

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
		case 3:
			url = Config.STRING_SHARE_APP_URL;
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

}
