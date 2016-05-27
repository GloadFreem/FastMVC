package com.jinzht.tools;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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

	public static boolean send(Map session) {
		try {
			if (msgType == MessageType.VerifyCode) {
				int code = (int) (Math.random() * 9000+1000);
				session.put("code", code);
				content = String.format(Config.SMS_VERIFY_CODE, code);
			} else {
				content+= Config.SMS_VERIFY_STRING;
				content = java.net.URLEncoder.encode(content, "utf-8");
			}
			String PostData = String.format("sname=%s&spwd=%s&scorpid=&sprdid=%s&sdst=%s&smsg=%s",Config.SMS_ACCOUNT, Config.SMS_PASSWORD, Config.SMS_USERID,telePhone, content);
			String ret = Send.SMS(PostData,"http://cf.51welink.com/submitdata/Service.asmx/g_Submit");
			System.out.println(ret);
			Document document = DocumentHelper.parseText(ret);
			Element root = document.getRootElement();
			Element element = root.element("State");
			if (Integer.parseInt(element.getText()) == 0) {
				return true;
			} else {
				return false;
			}
		} catch (DocumentException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
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

}
