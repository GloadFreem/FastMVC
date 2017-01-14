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
		ExecutorService threadPool = Executors.newFixedThreadPool(1);
		// 开启线程
		threadPool.execute(new Runnable() {
			public void run() {
				boolean isStart = false;
				while (!isStart) {
				
					int num = sanManager.getCompanyDAO().getCountByType("0")
							+ sanManager.getCompanyDAO().getCountByType("1");
					if( num== 0){
						System.out.println("数据库剩余数量:0    开始更新数据");
						isStart = true;
						sanManager.startPushService(true);
					}else{
						isStart = false;
						System.out.println("数据库剩余数量:"+num);
					}
					try {
						Thread.sleep(1 * 60 * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		// 任务执行完毕，关闭线程池
		threadPool.shutdown();
	}


}
