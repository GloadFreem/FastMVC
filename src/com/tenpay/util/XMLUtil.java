package com.tenpay.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.io.ByteArrayInputStream;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.weini.alipay.config.AlipayConfig;
import com.weini.alipay.util.AlipayCore;
import com.weini.alipay.util.MD5Util;

/**
 * xml工具类
 * 
 * @author miklchen
 * 
 */
public class XMLUtil {

	/**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * 
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static Map doXMLParse(String strxml) throws JDOMException,
			IOException {
		strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

		if (null == strxml || "".equals(strxml)) {
			return null;
		}

		Map m = new HashMap();

		InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if (children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = XMLUtil.getChildrenText(children);
			}

			m.put(k, v);
		}

		// 关闭流
		in.close();

		return m;
	}

	/**
	 * 获取子结点的xml
	 * 
	 * @param children
	 * @return String
	 */
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if (!children.isEmpty()) {
			Iterator it = children.iterator();
			while (it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if (!list.isEmpty()) {
					sb.append(XMLUtil.getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}

		return sb.toString();
	}

	/**
	 * 获取xml编码字符集
	 * 
	 * @param strxml
	 * @return
	 * @throws IOException
	 * @throws JDOMException
	 */
	public static String getXMLEncoding(String strxml) throws JDOMException,
			IOException {
		InputStream in = HttpClientUtil.String2Inputstream(strxml);
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		in.close();
		return (String) doc.getProperty("encoding");
	}

	/**
	 * 根据Map参数生成xml格式字符串 注意:只针对微信预支付请求方法
	 * 
	 * @param map
	 * @return xml字符串
	 * @throws IOException
	 * @throws XMLException
	 */
	public static String getXMLWithMap(Map xmlMap, String sign) throws UnsupportedEncodingException {
		// 参数中加入sign
		xmlMap.put("sign", sign);
		// 去除key,生成发送xml格式字符串
		xmlMap.remove("key");
		List<String> keys = new ArrayList<String>(xmlMap.keySet());
		StringBuilder xml = new StringBuilder(
				"<?xml version='1.0' encoding='utf-8' standalone='yes' ?><xml>");
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = (String) xmlMap.get(key);
			xml.append("<" + key + ">");
			xml.append(value);
			xml.append("</" + key + ">");
		}
		xml.append("</xml>");
		return xml.toString();
//			return new String(xml.toString().getBytes("iso8859-1"),"UTF-8");
	}

	/**
	 * 解析微信服务器返回带有prepayid参数xml字符串
	 * 
	 * @param xmlstr
	 * @return preparid
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	public static String getPrepayIdWithXML(String xmlStr) throws JDOMException, IOException {
		Map prepayMap = XMLUtil.doXMLParse(xmlStr);
		// 获取prepayId
		String return_code = prepayMap.get("return_code").toString();
		String prepayid = "";
		if (return_code.equalsIgnoreCase("SUCCESS")) {
			String result_code = prepayMap.get("result_code").toString();
			// 生成返回数据签名
			String temp = AlipayCore.createWXLinkString(prepayMap);
			temp += "&key=" + AlipayConfig.PARTNER_ID;
			String send_sign = prepayMap.get("sign").toString();

			String signStr = MD5Util.MD5(temp);
			// 验证签名是否正确
			if (send_sign.equalsIgnoreCase(signStr)) {
				if (result_code.equalsIgnoreCase("SUCCESS")) {
					// 验证业务处理状态
					prepayid = prepayMap.get("prepay_id").toString();
					return prepayid;
				}
			}

		}
		return null;
	}

}
