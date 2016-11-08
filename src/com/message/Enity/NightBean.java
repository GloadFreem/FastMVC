package com.message.Enity;

import org.springframework.beans.factory.annotation.Autowired;

import com.message.manager.MessageMananger;

public class NightBean {
	
	@Autowired
	private MessageMananger msgmanager;

	public void printNightMessage() {
		// TODO Auto-generated method stub
		
		 System.out.println("����һ��ɾ������:");  
		 msgmanager.deleteLastWeekData();
	}

}
