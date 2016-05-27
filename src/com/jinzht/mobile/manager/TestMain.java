package com.jinzht.mobile.manager;

import java.util.List;

import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Users;

public class TestMain {
	public static void main(String[] args) {
		UsersDAO userDao = new UsersDAO();
		List<Users> users = userDao.findAll();
		for (int i = 0; i < users.size(); i++) {
			Users user = users.get(i);
			System.out.println(user.getTelephone());
		}

	}
}
