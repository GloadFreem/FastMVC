package com.jinzht.web.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Users;

public class UserManager {
	
//	private static UsersDAO userDao = (UsersDAO)context.getBean("UsersDAO");
	private UsersDAO userDao;
	public List<Users> findAllUsers()
	{ 	
		return getUserDao().findAll();
	}
	public UsersDAO getUserDao() {
		return userDao;
	}
	
	@Autowired
	public void setUserDao(UsersDAO userDao) {
		this.userDao = userDao;
	}
}
