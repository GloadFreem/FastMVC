package com.jinzht.tools;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

@Entity
public class MsgUtil {
	private static String telePhone = null;
	private static String content = null;
	@ManyToOne
	private static MessageType msgType = MessageType.NormalMessage;

	public MsgUtil() {

	}

	public MsgUtil(String telePhone, String content, MessageType msgType) {
		this.setTelePhone(telePhone);
		this.setContent(content);
		this.setMsgType(msgType);
	}

	public static Integer  send() {
		Integer code = 0;
		try {
			if (msgType == MessageType.VerifyCode) {
				code = (int) (Math.random() * 9000+1000);
				content = String.format(Config.SMS_VERIFY_CODE, code);
			} else {
				content+= Config.SMS_VERIFY_STRING;
				System.out.println(content);
				content = java.net.URLEncoder.encode(content, "utf-8");
			}
			
			String PostData = String.format("sname=%s&spwd=%s&scorpid=&sprdid=%s&sdst=%s&smsg=%s",Config.SMS_ACCOUNT, Config.SMS_PASSWORD, Config.SMS_USERID,telePhone, content);
			String ret = Send.SMS(PostData,"http://cf.51welink.com/submitdata/Service.asmx/g_Submit");
			System.out.println(ret);
			Document document = DocumentHelper.parseText(ret);
			Element root = document.getRootElement();
			Element element = root.element("State");
			if (Integer.parseInt(element.getText()) == 0) {
				return code;
			} else {
				return 0;
			}
		} catch (DocumentException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public String getTelePhone() {
		return telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MessageType getMsgType() {
		return msgType;
	}

	public void setMsgType(MessageType msgType) {
		this.msgType = msgType;
	}

	public static void main(String[] args)
	{
		// 发送用户注册成功短信
		MsgUtil SMS = new MsgUtil();
		SMS.setTelePhone("13468655774");
		SMS.setMsgType(MessageType.NormalMessage);
		SMS.setContent(Config.STRING_SMS_AUTH_TRUE);
		// 发送短信
		MsgUtil.send();
	}
}
