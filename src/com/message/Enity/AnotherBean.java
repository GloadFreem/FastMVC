package com.message.Enity;

import org.springframework.beans.factory.annotation.Autowired;

import com.jinzht.tools.HttpUtil;
import com.message.manager.MessageMananger;


public class AnotherBean {
	
	@Autowired
	private MessageMananger msgmanager;
	

	public void printAnotherMessage() {

		System.out.println("---开启定时任务:"+HttpUtil.getDateTime());
		msgmanager.startInsertService();
	
	}



}
