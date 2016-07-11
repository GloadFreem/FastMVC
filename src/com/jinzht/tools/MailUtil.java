package com.jinzht.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailUtil {

	/**
	 * 发件人邮箱服务器
	 */
	private String emailHost;
	/**
	 * 发件人邮箱
	 */
	private String emailFrom;

	/**
	 * 发件人用户名
	 */
	private String emailUserName;

	/**
	 * 发件人密码
	 */
	private String emailPassword;

	/**
	 * 收件人邮箱，多个邮箱以“;”分隔
	 */
	private String toEmails;
	/**
	 * 邮件主题
	 */
	private String subject;
	/**
	 * 邮件内容
	 */
	private String content;
	/**
	 * 邮件中的图片，为空时无图片。map中的key为图片ID，value为图片地址
	 */
	private Map<String, String> pictures;
	/**
	 * 邮件中的附件，为空时无附件。map中的key为附件ID，value为附件地址
	 */
	private Map<String, String> attachments;
	
	public MailUtil()
	{
		this.emailHost = Config.STRING_MAIL_SERVER_NAME;
		this.emailFrom = Config.STRING_MAIL_SERVER_USER;
	}

	/**
	 * 
	 * @author geloin
	 * @date 2012-5-9 上午10:49:01
	 * @return the emailHost
	 */
	public String getEmailHost() {
		emailHost = Tools.objToStr(emailHost);
		if (emailHost.equals("")) {
			emailHost = Config.STRING_MAIL_SERVER_NAME;
		}
		return emailHost;
	}

	/**
	 * 
	 * @author geloin
	 * @date 2012-5-9 上午10:49:01
	 * @param emailHost
	 *            the emailHost to set
	 */
	public void setEmailHost(String emailHost) {
		this.emailHost = emailHost;
	}

	/**
	 * 
	 * @author geloin
	 * @date 2012-5-9 上午10:49:01
	 * @return the emailFrom
	 */
	public String getEmailFrom() {
		emailFrom = Tools.objToStr(emailFrom);
		if (emailFrom.equals("")) {
			emailFrom = Config.STRING_MAIL_SERVER_USER;
		}
		return emailFrom;
	}

	/**
	 * 
	 * @author geloin
	 * @date 2012-5-9 上午10:49:01
	 * @param emailFrom
	 *            the emailFrom to set
	 */
	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	/**
	 * 
	 * @author geloin
	 * @date 2012-5-9 上午10:49:01
	 * @return the emailUserName
	 */
	public String getEmailUserName() {
		emailUserName = Tools.objToStr(emailUserName);
		if (emailUserName.equals("")) {
			emailUserName = Config.STRING_MAIL_SERVER_USER;
		}
		return emailUserName;
	}

	/**
	 * 
	 * @author geloin
	 * @date 2012-5-9 上午10:49:01
	 * @param emailUserName
	 *            the emailUserName to set
	 */
	public void setEmailUserName(String emailUserName) {
		this.emailUserName = emailUserName;
	}

	/**
	 * 
	 * @author geloin
	 * @date 2012-5-9 上午10:49:01
	 * @return the emailPassword
	 */
	public String getEmailPassword() {
		emailPassword = Tools.objToStr(emailPassword);
		if (emailPassword.equals("")) {
			emailPassword = Config.STRING_MAIL_SERVER_PASSWD;
		}
		return emailPassword;
	}

	/**
	 * 
	 * @author geloin
	 * @date 2012-5-9 上午10:49:01
	 * @param emailPassword
	 *            the emailPassword to set
	 */
	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}

	/**
	 * 
	 * @author geloin
	 * @date 2012-5-9 上午10:49:01
	 * @return the toEmails
	 */
	public String getToEmails() {
		return Tools.objToStr(toEmails);
	}

	/**
	 * 
	 * @author geloin
	 * @date 2012-5-9 上午10:49:01
	 * @param toEmails
	 *            the toEmails to set
	 */
	public void setToEmails(String toEmails) {
		this.toEmails = toEmails;
	}

	/**
	 * 
	 * @author geloin
	 * @date 2012-5-9 上午10:49:01
	 * @return the subject
	 */
	public String getSubject() {
		subject = Tools.objToStr(subject);
		if (subject.equals("")) {
			subject = "无主题";
		}
		return Tools.objToStr(subject);
	}

	/**
	 * 
	 * @author geloin
	 * @date 2012-5-9 上午10:49:01
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 
	 * @author geloin
	 * @date 2012-5-9 上午10:49:01
	 * @return the content
	 */
	public String getContent() {
		return Tools.objToStr(content);
	}

	/**
	 * 
	 * @author geloin
	 * @date 2012-5-9 上午10:49:01
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 
	 * @author geloin
	 * @date 2012-5-9 上午10:49:01
	 * @return the pictures
	 */
	public Map<String, String> getPictures() {
		return pictures;
	}

	/**
	 * 
	 * @author geloin
	 * @date 2012-5-9 上午10:49:01
	 * @param pictures
	 *            the pictures to set
	 */
	public void setPictures(Map<String, String> pictures) {
		this.pictures = pictures;
	}

	/**
	 * 
	 * @author geloin
	 * @date 2012-5-9 上午10:49:01
	 * @return the attachments
	 */
	public Map<String, String> getAttachments() {
		return attachments;
	}

	/**
	 * 
	 * @author geloin
	 * @date 2012-5-9 上午10:49:01
	 * @param attachments
	 *            the attachments to set
	 */
	public void setAttachments(Map<String, String> attachments) {
		this.attachments = attachments;
	}

	/**
	 * 发送邮件
	 * 
	 * @author geloin
	 * @date 2012-5-9 上午11:18:21
	 * @throws Exception
	 */
	public void sendEmail() throws Exception {

		if (this.getEmailHost().equals("") || this.getEmailFrom().equals("")
				|| this.getEmailUserName().equals("")
				|| this.getEmailPassword().equals("")) {
			throw new RuntimeException("发件人信息不完全，请确认发件人信息！");
		}

		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();

		// 设定mail server
		senderImpl.setHost(emailHost);

		// 建立邮件消息
		MimeMessage mailMessage = senderImpl.createMimeMessage();

		MimeMessageHelper messageHelper = null;
		messageHelper = new MimeMessageHelper(mailMessage, true, "UTF-8");
		// 设置发件人邮箱
		messageHelper.setFrom(emailFrom);

		// 设置收件人邮箱
		String[] toEmailArray = toEmails.split(";");
		List<String> toEmailList = new ArrayList<String>();
		if (null == toEmailArray || toEmailArray.length <= 0) {
			throw new RuntimeException("收件人邮箱不得为空！");
		} else {
			for (String s : toEmailArray) {
				s = Tools.objToStr(s);
				if (!s.equals("")) {
					toEmailList.add(s);
				}
			}
			if (null == toEmailList || toEmailList.size() <= 0) {
				throw new RuntimeException("收件人邮箱不得为空！");
			} else {
				toEmailArray = new String[toEmailList.size()];
				for (int i = 0; i < toEmailList.size(); i++) {
					toEmailArray[i] = toEmailList.get(i);
				}
			}
		}
		messageHelper.setTo(toEmailArray);

		// 邮件主题
		messageHelper.setSubject(subject);

		// true 表示启动HTML格式的邮件
		messageHelper.setText(content, true);

		// 添加图片
		if (null != pictures) {
			for (Iterator<Map.Entry<String, String>> it = pictures.entrySet()
					.iterator(); it.hasNext();) {
				Map.Entry<String, String> entry = it.next();
				String cid = entry.getKey();
				String filePath = entry.getValue();
				if (null == cid || null == filePath) {
					throw new RuntimeException("请确认每张图片的ID和图片地址是否齐全！");
				}

				File file = new File(filePath);
				if (!file.exists()) {
					throw new RuntimeException("图片" + filePath + "不存在！");
				}

				FileSystemResource img = new FileSystemResource(file);
				messageHelper.addInline(cid, img);
			}
		}

		// 添加附件
		if (null != attachments) {
			for (Iterator<Map.Entry<String, String>> it = attachments
					.entrySet().iterator(); it.hasNext();) {
				Map.Entry<String, String> entry = it.next();
				String cid = entry.getKey();
				String filePath = entry.getValue();
				if (null == cid || null == filePath) {
					throw new RuntimeException("请确认每个附件的ID和地址是否齐全！");
				}

				File file = new File(filePath);
				if (!file.exists()) {
					throw new RuntimeException("附件" + filePath + "不存在！");
				}

				FileSystemResource fileResource = new FileSystemResource(file);
				messageHelper.addAttachment(cid, fileResource);
			}
		}

		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
		prop.put("mail.smtp.timeout", "25000");
		// 添加验证
		MyAuthenticator auth = new MyAuthenticator(emailUserName, emailPassword);

		Session session = Session.getDefaultInstance(prop, auth);
		senderImpl.setSession(session);

		// 发送邮件
		senderImpl.send(mailMessage);
	}

	public static void main(String[] args) throws Exception {
		MailUtil mu = new MailUtil();
		sendUserRegist(mu,"18729342354");
//		 test2(mu);
//		 test3(mu);
//		 test4(mu);
		// test5(mu);
//		 test6(mu);
	}

	public static void sendUserRegist(MailUtil mu,String telephone) throws Exception {
		String toEmails = "chenshengzhu@jinzht.com;xuli@jinzht.com";
//		String toEmails = Config.STRING_MAIL_SERVER_USER;
		String subject = "用户注册--【金指投投融资】";
//		StringBuilder builder = new StringBuilder();
		String dateString = DateUtils.dateToString(new Date(),"YYYY-MM-DD HH:MM:SS");
		String content = String.format(Config.STRING_EMIAL_REGIST, telephone,dateString);
//		builder.append(content);
//		String content = builder.toString();

		mu.setToEmails(toEmails);
		mu.setSubject(subject);
		mu.setContent(content);

		mu.sendEmail();
	}
	
	public static void sendUserAuthenticQuick(MailUtil mu,String telephone,String name) throws Exception {
		String toEmails = "chenshengzhu@jinzht.com;xuli@jinzht.com";
//		String toEmails = Config.STRING_MAIL_SERVER_USER;
		String subject = "用户认证催单--【金指投投融资】";
//		StringBuilder builder = new StringBuilder();
		String content = String.format(Config.STRING_EMIAL_AUTHENTIC_QUICK, telephone,name);
//		builder.append(content);
//		String content = builder.toString();
		
		mu.setToEmails(toEmails);
		mu.setSubject(subject);
		mu.setContent(content);
		
		mu.sendEmail();
	}
	public static void sendUserInvest(MailUtil mu,String telephone,String name,String projectName,float count) throws Exception {
		String toEmails = "chenshengzhu@jinzht.com;xuli@jinzht.com";
//		String toEmails = Config.STRING_MAIL_SERVER_USER;
		String subject = "认投消息--【金指投投融资】";
//		StringBuilder builder = new StringBuilder();
		String dateString = DateUtils.dateToString(new Date(),"YYYY-MM-DD HH:MM:SS");
		String content = String.format(Config.STRING_EMIAL_INVEST,name, telephone,dateString,projectName,count);
//		builder.append(content);
//		String content = builder.toString();
		
		mu.setToEmails(toEmails);
		mu.setSubject(subject);
		mu.setContent(content);
		
		mu.sendEmail();
	}
	public static void sendUserAuthentic(MailUtil mu,String telephone) throws Exception {
		String toEmails = "chenshengzhu@jinzht.com;xuli@jinzht.com";
//		String toEmails = Config.STRING_MAIL_SERVER_USER;
		String subject = "用户认证消息--【金指投投融资】";
//		StringBuilder builder = new StringBuilder();
		String dateString = DateUtils.dateToString(new Date(),"YYYY-MM-DD HH:MM:SS");
		String content = String.format(Config.STRING_EMIAL_AUTHENTIC, telephone,dateString);
//		builder.append(content);
//		String content = builder.toString();
		
		mu.setToEmails(toEmails);
		mu.setSubject(subject);
		mu.setContent(content);
		mu.sendEmail();
	}
	public static void sendUserInvest(MailUtil mu,String telephone) throws Exception {
		String toEmails = "chenshengzhu@jinzht.com;xuli@jinzht.com";
//		String toEmails = Config.STRING_MAIL_SERVER_USER;
		String subject = "用户认投消息--【金指投投融资】";
//		StringBuilder builder = new StringBuilder();
		String dateString = DateUtils.dateToString(new Date(),"YYYY-MM-DD HH:MM:SS");
		String content = String.format(Config.STRING_EMIAL_AUTHENTIC, telephone,dateString);
//		builder.append(content);
//		String content = builder.toString();
		
		mu.setToEmails(toEmails);
		mu.setSubject(subject);
		mu.setContent(content);
		mu.sendEmail();
	}

	public static void test2(MailUtil mu) throws Exception {
//		String toEmails = "774599724@qq.com";
		String toEmails = Config.STRING_MAIL_SERVER_USER;
		String subject = "第二封，HTML邮件";
		StringBuilder builder = new StringBuilder();
		builder.append("<html><body>老婆：<br />我是你的老公吗？<br />是的，是很久了。<br /></body></html>");
		String content = builder.toString();

		mu.setToEmails(toEmails);
		mu.setSubject(subject);
		mu.setContent(content);

		mu.sendEmail();
	}

	public static void test3(MailUtil mu) throws Exception {
//		String toEmails = "774599724@qq.com";
		String toEmails = Config.STRING_MAIL_SERVER_USER;
		String subject = "第三封，图片邮件";

		Map<String, String> pictures = new HashMap<String, String>();
		pictures.put("d1", "C:/Users/Administrator/Desktop/f3d3572c11dfa9ecb0068b9265d0f703918fc10f.jpg");
		pictures.put("d2", "C:/Users/Administrator/Desktop/f3d3572c11dfa9ecb0068b9265d0f703918fc10f.jpg");
		pictures.put("d3", "C:/Users/Administrator/Desktop/f3d3572c11dfa9ecb0068b9265d0f703918fc10f.jpg");

		StringBuilder builder = new StringBuilder();
		builder.append("<html><body>看看下面的图，你会知道花儿为什么是这样红的：<br />");
		builder.append("<img src=\"cid:d1\" /><br />");
		builder.append("<img src=\"cid:d2\" /><br />");
		builder.append("<img src=\"cid:d3\" /><br />");
		builder.append("</body></html>");
		String content = builder.toString();

		mu.setToEmails(toEmails);
		mu.setSubject(subject);
		mu.setContent(content);
		mu.setPictures(pictures);

		mu.sendEmail();

	}

	public static void test4(MailUtil mu) throws Exception {
		String toEmails = "774599724@qq.com";
		String subject = "第四封，附件邮件";
		Map<String, String> attachments = new HashMap<String, String>();
		attachments.put("d1.jar", "D:/work/download/activation.jar");
		attachments.put("d2.doc",
				"C:/Documents and Settings/Administrator/桌面/Java设计模式.doc");
		StringBuilder builder = new StringBuilder();
		builder.append("<html><body>看看附件中的资料，你会知道世界为什么是平的。</body></html>");
		String content = builder.toString();

		mu.setToEmails(toEmails);
		mu.setSubject(subject);
		mu.setContent(content);
		mu.setAttachments(attachments);

		mu.sendEmail();
	}

	public static void test5(MailUtil mu) throws Exception {
		String toEmails = "774599724@qq.com";
		String subject = "第五封，综合邮件";

		Map<String, String> attachments = new HashMap<String, String>();
		attachments.put("d1.jar", "D:/work/download/activation.jar");
		attachments.put("d2.doc",
				"C:/Documents and Settings/Administrator/桌面/Java设计模式.doc");

		Map<String, String> pictures = new HashMap<String, String>();
		pictures.put("d1", "D:/work/download/d1.jpg");
		pictures.put("d2", "D:/work/download/测试图片2.jpg");
		pictures.put("d3", "D:/work/download/d3.jpg");

		StringBuilder builder = new StringBuilder();
		builder.append("<html><body>看看附件中的资料，你会知道世界为什么是平的。<br />");
		builder.append("看看下面的图，你会知道花儿为什么是这样红的：<br />");
		builder.append("<img src=\"cid:d1\" /><br />");
		builder.append("<img src=\"cid:d2\" /><br />");
		builder.append("<img src=\"cid:d3\" /><br />");
		builder.append("</body></html>");
		String content = builder.toString();

		mu.setToEmails(toEmails);
		mu.setSubject(subject);
		mu.setContent(content);
		mu.setPictures(pictures);
		mu.setAttachments(attachments);

		mu.sendEmail();
	}

	public static void test6(MailUtil mu) throws Exception {
//		String toEmails = "774599724@qq.com;geloin@163.com";
		String toEmails = Config.STRING_MAIL_SERVER_USER;
		String subject = "第五封，群发邮件";

		Map<String, String> attachments = new HashMap<String, String>();
//		attachments.put("d1.jar", "D:/work/download/activation.jar");
//		attachments.put("d2.doc",
//				"C:/Documents and Settings/Administrator/桌面/Java设计模式.doc");

		Map<String, String> pictures = new HashMap<String, String>();
		pictures.put("d1", "C:/Users/Administrator/Desktop/f3d3572c11dfa9ecb0068b9265d0f703918fc10f.jpg");
		pictures.put("d2", "C:/Users/Administrator/Desktop/f3d3572c11dfa9ecb0068b9265d0f703918fc10f.jpg");
		pictures.put("d3", "C:/Users/Administrator/Desktop/f3d3572c11dfa9ecb0068b9265d0f703918fc10f.jpg");

		StringBuilder builder = new StringBuilder();
		builder.append("<html><body>看看附件中的资料，你会知道世界为什么是平的。<br />");
		builder.append("看看下面的图，你会知道花儿为什么是这样红的：<br />");
		builder.append("<img src=\"cid:d1\" /><br />");
		builder.append("<img src=\"cid:d2\" /><br />");
		builder.append("<img src=\"cid:d3\" /><br />");
		builder.append("</body></html>");
		String content = builder.toString();

		mu.setToEmails(toEmails);
		mu.setSubject(subject);
		mu.setContent(content);
		mu.setPictures(pictures);
//		mu.setAttachments(attachments);

		mu.sendEmail();
	}

}
