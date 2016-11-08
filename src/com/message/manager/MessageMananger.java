package com.message.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;

import com.jinzht.tools.Condition;
import com.jinzht.tools.Config;
import com.jinzht.web.entity.WebcontenttypeDAO;
import com.message.Enity.Msg;
import com.message.Enity.MsgDAO;
import com.message.Enity.MsgDetail;
import com.message.Enity.MsgDetailDAO;
import com.message.Enity.MsgImagesDAO;
import com.message.Enity.Webrule;
import com.message.Enity.WebruleDAO;
public class MessageMananger {

	public final static String LAG = ":Message System:";


	public WebruleDAO wDao;
	public WebcontenttypeDAO contentTypeDAO;
	public MsgDAO msgDao;
	public MsgDetailDAO msgDetailDAO;
	private MsgImagesDAO msgImagesDao;
	

	public MsgDetailDAO getMsgDetailDAO() {
		return msgDetailDAO;
	}
	@Autowired
	public void setMsgDetailDAO(MsgDetailDAO msgDetailDAO) {
		this.msgDetailDAO = msgDetailDAO;
	}

	public WebcontenttypeDAO getContentTypeDAO() {
		return contentTypeDAO;
	}

	@Autowired
	public void setContentTypeDAO(WebcontenttypeDAO contentTypeDAO) {
		this.contentTypeDAO = contentTypeDAO;
	}



	public WebruleDAO getwDao() {
		return wDao;
	}

	@Autowired
	public void setwDao(WebruleDAO wDao) {
		this.wDao = wDao;
	}

	public MsgDAO getMsgDao() {
		return msgDao;
	}

	@Autowired
	public void setMsgDao(MsgDAO msgDao) {
		this.msgDao = msgDao;
	}

	public List getAllMessagersList() {
		List list = this.msgDao.findAll();
		return list;
	}

	/**
	 * 开始执行抓取任务线程
	 * 
	 * @param urllist
	 * @return
	 */
	public void startInsertService() {
		final List<Webrule> webrulelist = wDao.findAll();
		// 线程池
		ExecutorService threadPool = Executors
				.newFixedThreadPool(Config.MAX_THREAD_NUM);
		if (webrulelist.size() > 0) {
			for (int i = 0; i < webrulelist.size(); i++) {
				final int taskID = i;
				// 开启线程
				threadPool.execute(new Runnable() {
					public void run() {
						System.out.println(LAG + "----------------执行线程（"
								+ taskID + ")");
						// while(true){
						System.out.println(LAG + "----------------爬虫地址："
								+ webrulelist.get(taskID).getUrl());
						startCapture(webrulelist.get(taskID));
					}
				});

			}
		}
		 threadPool.shutdown();// 任务执行完毕，关闭线程池
	}

	/**
	 * 处理不同网站解析入口
	 * 
	 * @param urllist
	 */
	protected void startCapture(Webrule webrule) {
		// TODO Auto-generated method stub
		// String itemUrl[] = urlStr.split(";");
		String url = webrule.getUrl();
		// int sources = Integer.valueOf( itemUrl[1]);
		System.out.println("线程" + webrule.getId() + LAG + "----------解析网站入口");
		// if(sources==Config.SOURCES_WANGYI){
		// //网易新闻
		// System.out.println(LAG+"----------开始网易新闻解析");
		captureMsg(webrule);
		// }else if(sources==Config.SOURCES_XINLANG){
		// //新浪新闻
		// // captureMsgFromWangyi(url);
		// }

	}

	/**
	 * 抓取资讯
	 * 
	 * @param url
	 */
	private void captureMsg(Webrule webrule) {
		List<Msg> msglist = new ArrayList<Msg>();
		if (webrule.getType().equals("0")) {
			// JSOn
			msglist = Condition.getContentFromJson(webrule);

		} else if (webrule.getType().equals("2")) {
			// HTML
			msglist = Condition.getContentFromHtml(webrule);
		}
		System.out.println("线程" + webrule.getId() + "抓取：" + msglist.size());
		// 存储
		saveAll(msglist);
	}

	//
	// public List<Message> getMessageBeansFromUrl(String url) {
	// List<Message> messages = new ArrayList<Message>();
	// messages = Condition.getMessageBeanFromWangYi(url);
	// return messages;
	// }

	public void saveAll(List<Msg> transientInstances) {
		for (Msg transientInstance : transientInstances) {
			List<Msg> list = msgDao.findByTitle(transientInstance
					.getTitle());
			// System.out.println(LAG + list.size());
			if (list != null && list.size() == 0) {
				msgDao.save(transientInstance);
			}
		}
	}

	public List<Msg> findAll() {
		return msgDao.findAll();

	}

	public List<Msg> findMsgList(int pageId, int pageNum,String likeWords) {
		List<Msg> msglist = new ArrayList<Msg>();
//		List<Msg> contentlist = new ArrayList<Msg>();
//		List<MsgDetail> detallist = new ArrayList<MsgDetail>();
		List<Msg>titlelist = msgDao.findListBySearch(pageId, pageNum,likeWords);
		
//		List<MsgDetail> detallist = msgDetailDAO.findListBySearch(pageId, pageNum,likeWords);
//		for (Msg message : msglist) {
//			Msg msg = new Msg();
//			msg.setId(message.getId() + "");
//			msg.setTitle(message.getTitle());
//			msg.setOringl(message.getSource());
//			String sqlDateStr = message.getMTime().toString();
//			msg.setPublicDate(sqlDateStr.substring(0, sqlDateStr.length() - 2));
//			List<String> imgList = new ArrayList<String>();
//			if (message.getIsImg().equals("1")) {
//				msg.setWebcontenttype(contentTypeDAO.findById("1"));
//			} else if (message.getIsImg().equals("2")) {
//				msg.setWebcontenttype(contentTypeDAO.findById("2"));
//				imgList.add(message.getImgurl());
//			} else if (message.getIsImg().equals("3")) {
//				msg.setWebcontenttype(contentTypeDAO.findById("3"));
//				imgList.add(message.getImgurl());
//			} else {
//				msg.setWebcontenttype(contentTypeDAO.findById("4"));
//				imgList.add(message.getImgurl());
//				imgList.add(message.getImgurl());
//				imgList.add(message.getImgurl());
//			}
//			msg.setImages(imgList);
//			msglist.add(msg);
//		}
		msglist = titlelist;
		return msglist;

	}
	public void deleteLastWeekData() {
		
		
		
	}
	public MsgImagesDAO getMsgImagesDao() {
		return msgImagesDao;
	}
	@Autowired
	public void setMsgImagesDao(MsgImagesDAO msgImagesDao) {
		this.msgImagesDao = msgImagesDao;
	}


}
