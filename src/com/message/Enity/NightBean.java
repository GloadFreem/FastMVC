package com.message.Enity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;

import com.jinzht.stock.manager.SanManager;
import com.message.manager.MessageMananger;

public class NightBean {
	@Autowired
	private SanManager sanManager;

	public void printNightMessage() {

		System.out.println("数据库开始删除");
		sanManager.deleteService();
	
		sanManager.startPushService(true);

	}

}
