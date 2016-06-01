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
}
