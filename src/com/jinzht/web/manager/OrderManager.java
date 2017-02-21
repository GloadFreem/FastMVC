package com.jinzht.web.manager;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpException;
import org.jdom.JDOMException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jinzht.tools.Config;
import com.jinzht.tools.DateUtils;
import com.jinzht.web.dao.AudiorecordDAO;
import com.jinzht.web.dao.BusinessplanDAO;
import com.jinzht.web.dao.CityDAO;
import com.jinzht.web.dao.CollectionDAO;
import com.jinzht.web.dao.ContentpriseDAO;
import com.jinzht.web.dao.FinancestatusDAO;
import com.jinzht.web.dao.FinancialstandingDAO;
import com.jinzht.web.dao.FinancingcaseDAO;
import com.jinzht.web.dao.FinancingexitDAO;
import com.jinzht.web.dao.IdentiytypeDAO;
import com.jinzht.web.dao.IndustoryareaDAO;
import com.jinzht.web.dao.InvestmentrecordDAO;
import com.jinzht.web.dao.LoginfailrecordDAO;
import com.jinzht.web.dao.MemberDAO;
import com.jinzht.web.dao.ProjectDAO;
import com.jinzht.web.dao.ProjectcommentDAO;
import com.jinzht.web.dao.ProjectcommitrecordDAO;
import com.jinzht.web.dao.ProvinceDAO;
import com.jinzht.web.dao.PubliccontentDAO;
import com.jinzht.web.dao.RoadshowDAO;
import com.jinzht.web.dao.RoadshowplanDAO;
import com.jinzht.web.dao.SceneDAO;
import com.jinzht.web.dao.ScenecommentDAO;
import com.jinzht.web.dao.ShareDAO;
import com.jinzht.web.dao.TeamDAO;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.BusinessOrder;
import com.jinzht.web.entity.Businessplan;
import com.jinzht.web.entity.City;
import com.jinzht.web.entity.Collection;
import com.jinzht.web.entity.Comment;
import com.jinzht.web.entity.Contentprise;
import com.jinzht.web.entity.Controlreport;
import com.jinzht.web.entity.Financestatus;
import com.jinzht.web.entity.Financialstanding;
import com.jinzht.web.entity.Financingcase;
import com.jinzht.web.entity.Financingexit;
import com.jinzht.web.entity.Identiytype;
import com.jinzht.web.entity.Industoryarea;
import com.jinzht.web.entity.Investmentrecord;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.Project;
import com.jinzht.web.entity.ProjectAddress;
import com.jinzht.web.entity.ProjectAddressDAO;
import com.jinzht.web.entity.ProjectHotSearch;
import com.jinzht.web.entity.ProjectHotSearchDAO;
import com.jinzht.web.entity.ProjectRange;
import com.jinzht.web.entity.ProjectRangeDAO;
import com.jinzht.web.entity.ProjectSearchBean;
import com.jinzht.web.entity.ProjectSearchBean.MapResult;
import com.jinzht.web.entity.ProjectType;
import com.jinzht.web.entity.ProjectTypeDAO;
import com.jinzht.web.entity.Projectcomment;
import com.jinzht.web.entity.Projectcommitrecord;
import com.jinzht.web.entity.Publiccontent;
import com.jinzht.web.entity.Roadshow;
import com.jinzht.web.entity.Roadshowplan;
import com.jinzht.web.entity.Scene;
import com.jinzht.web.entity.Scenecomment;
import com.jinzht.web.entity.Users;
import com.tenpay.util.CommonUtil;
import com.tenpay.util.WXUtil;
import com.tenpay.util.XMLUtil;
import com.weini.alipay.config.AlipayConfig;
import com.weini.alipay.util.AlipayCore;
import com.weini.alipay.util.MD5Util;

public class OrderManager {
	/**
	 * 支付组装接口
	 * 
	 * @param TOrder
	 * @return
	 * @throws JSONException
	 * @throws IOException
	 * @throws HttpException
	 * @throws JDOMException
	 */
	public Map orderPayDescription(String timeLetf, int payType,
			String osType, String openId, BusinessOrder order)
			throws JSONException, HttpException, IOException, JDOMException {

		// 日志测试Log4
		// ================支付宝支付业务开始=============================
		Map<String, String> map = new HashMap();
		String retmsg = "";
		// 返回字符串
		String orderDesc = "";
		if (payType == 1) {

			// 创建传参
			JSONObject jsonObject = new JSONObject();

			String code = "jinzht_pro_" + new Date().getTime();

			// 支付宝支付参数封装
			String service_url = Config.STRING_SYSTEM_ADDRESS
					+ AlipayConfig.notify_wx_url;
			jsonObject.put(AlipayConfig.seller_id_str, AlipayConfig.partner);// 卖家支付宝账号
			jsonObject.put(AlipayConfig.out_trade_no_str, code); // 商家订单编号
			jsonObject.put(AlipayConfig.subject_str, AlipayConfig.subject); // 商品名称
			jsonObject.put(AlipayConfig.body_str, AlipayConfig.body); // 商品描述
			jsonObject.put("product_code", "QUICK_MSECURITY_PAY"); // 商品描述
			jsonObject.put("goods_type", "1"); // 商品描述

			jsonObject.put(AlipayConfig.total_fee_str, String.valueOf("100")); // 商品价格

			// 转换格式
			ObjectMapper mapper = new ObjectMapper();

			String json = mapper.writeValueAsString(jsonObject);
			// json = URLEncoder.encode(json, "utf-8");

			map.put("biz_content", json); // 商品描述
			map.put("timestamp",
					DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss")); // 商品描述
			map.put("format", "json"); // 商品描述
			map.put("app_id", AlipayConfig.app_id); // 商品描述
			map.put("method", "alipay.trade.app.pay"); // 商品描述
			map.put("version", "1.0"); // 商品描述
			map.put(AlipayConfig.notify_url_str, service_url);// 回调链接
			map.put(AlipayConfig._input_charset_str, AlipayConfig.input_charset); // 参数编码字符集
			map.put(AlipayConfig.sign_type_str, AlipayConfig.sign_type); // 签名类型

			// ----------------------------开始签名--------------------------------
			// 开始签名
			// String orderSign = AlipayCore.createLinkString(map);
			// String sign = RSA.sign(orderSign, AlipayConfig.private_key,
			// AlipayConfig.input_charset);
			//

			String sign;
			try {
				sign = AlipaySignature.rsaSign(map, AlipayConfig.private_key,
						"utf-8");
				sign = URLEncoder.encode(sign);

				// sign = sign.replace("+", "%2B");
				// sign = sign.replace("/", "%2F");
				// sign = sign.replace("=", "");

				// ----------------------------签名结束--------------------------------
				// /* Map checkMap = map;
				// checkMap.put("sign", sign);
				// if(AlipaySignature.rsaCheckV2(checkMap,
				// AlipayConfig.ali_public_key, "utf-8"))
				// {
				// System.out.println("验签成功！");
				// }*/
				// ----------------------------签名验证结束--------------------------------

				// ----------------------------重新组装参数--------------------------------
				map.put(AlipayConfig.sign_str, sign);
				// ----------------------------重新组装参数结束--------------------------------
				// ================支付宝支付业务结束=============================
				orderDesc = AlipayCore.createLinkString(map);
			} catch (AlipayApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (payType == 2) {
			System.out.print("微信支付业务开始");
			// ================微信支付业务开始=============================

			// 设置package订单参数
			// 生成随即唯一标识
			String noncestr = WXUtil.getNonceStr();

			// 设置获取prepayid支付参数
			// ---------------获取订单号 开始------------------------
			String out_trade_no = order.getOrderCode();
			// String out_trade_no = "jinzht_pro_" + new Date().getTime();
			// ---------------获取订单号 结束------------------------

			// ---------------设置订单预支付参数开始------------------------
			Double price = order.getTotalFee();

			String priceStr = String.valueOf(price * 100);

			priceStr = priceStr.replace(".0", "");

			// 请求参数封装
			map.put(AlipayConfig.APP_ID_STR, AlipayConfig.APP_ID);
			map.put(AlipayConfig.MCH_ID_STR, AlipayConfig.MCH_ID);
			String service_url = Config.STRING_SYSTEM_ADDRESS
					+ AlipayConfig.notify_wx_url;
			map.put(AlipayConfig.NONCE_STR, noncestr);
			map.put(AlipayConfig.NOTIFY_URL_STR, service_url);
			map.put(AlipayConfig.OUT_TRADE_NO_STR, out_trade_no);
			map.put(AlipayConfig.SPBILL_CREATE_IP_STR, AlipayConfig.SERVER_IP);
			map.put("total_fee", priceStr);
			map.put(AlipayConfig.TRADE_TYPE_STR, "APP");

			map.put(AlipayConfig.BODY_STR, new String("商学院参课"));
			// ---------------设置订单预支付参数结束------------------------

			// ---------------生成加密字符串（MD5）开始------------------------
			// 生成MD5签名字符串
			String orderSign = AlipayCore.createWXLinkString(map);
			orderSign += "&key=" + AlipayConfig.PARTNER_ID;
			// MD5签名
			// String sign = MD5Util.MD5(orderSign);
			String sign = MD5Util.MD5Encode(orderSign, "UTF-8").toUpperCase();
			// ---------------生成加密字符串（MD5）结束------------------------

			// ---------------生成预支付请求xml格式字符串------------------------
			String xmlStr = XMLUtil.getXMLWithMap(map, sign);
			// ---------------生成预支付请求xml格式字符串结束------------------------

			// ---------------请求业务逻辑获取预支付------------------------
			// String responseString = new
			// TenpayHttpClient().getPrepayId(xmlStr);
			String responseString = CommonUtil.httpsRequest(
					AlipayConfig.API_CHECK, "POST", xmlStr);
			// 解析服务器返回的xml格式字符串
			String prepayid = XMLUtil.getPrepayIdWithXML(responseString);

			// 返回给客户端的参数
			if (null != prepayid && !"".equals(prepayid)) {
				// 获取 prepayid之后进行第二次签名
				String package_str;
				// 设置支付参数
				// 设置package
				package_str = "Sign=WXPay";
				// 第二次签名参数列表
				map = new HashMap();
				String timestamp = WXUtil.getTimeStamp();
//				String timestamp = "1483932100";
				
				noncestr = MD5Util.MD5(timestamp);
				map.put(AlipayConfig.APP_ID_STR, AlipayConfig.APP_ID);
				map.put("package", package_str);
				map.put("partnerid", AlipayConfig.MCH_ID);
				map.put("timestamp", timestamp);
				map.put("prepayid", prepayid);
				map.put("noncestr", noncestr);
				String signStr = AlipayCore.createWXLinkString(map);
				signStr += "&key=" + AlipayConfig.PARTNER_ID;
				// 生成签名
				String signSecond = MD5Util.MD5(signStr);
				// 加入参数
				map.put("sign", signSecond);
				
				
				map.remove("package");
				
				map.put("pack", package_str);
				orderDesc = AlipayCore.createLinkString(map);
			} else {
				map = null;
				retmsg = "错误：获取prepayId失败";
			}
			// ================微信支付业务结束=============================

		} else if (payType == 3) {
			System.out.print("微信公众号支付业务开始");
			// ================微信支付业务开始=============================
			// 设置package订单参数
			// 生成随即唯一标识
			String noncestr = WXUtil.getNonceStr();

			// 设置获取prepayid支付参数
			// ---------------获取订单号 开始------------------------
			// String out_trade_no = order.getOrderCode();
			String out_trade_no = "jinzht_pro_" + new Date().getTime();
			// ---------------获取订单号 结束------------------------

			// ---------------设置订单预支付参数开始------------------------
			double price =order.getTotalFee();

			String priceStr = String.valueOf(price * 100);

			priceStr = priceStr.replace(".0", "");

			// 请求参数封装
			map.put(AlipayConfig.APP_ID_STR, AlipayConfig.APP_ID_SERVER);
			map.put(AlipayConfig.MCH_ID_STR, AlipayConfig.MCH_ID_SERVER);

			String service_url = Config.STRING_SYSTEM_ADDRESS
					+ AlipayConfig.notify_wx_url;
			map.put(AlipayConfig.NONCE_STR, noncestr);
			map.put(AlipayConfig.NOTIFY_URL_STR, service_url);
			map.put(AlipayConfig.OUT_TRADE_NO_STR, out_trade_no);
			map.put(AlipayConfig.SPBILL_CREATE_IP_STR, "115.28.110.243");
			map.put("total_fee", priceStr);
			map.put(AlipayConfig.TRADE_TYPE_STR, AlipayConfig.TRADE_TYPE_JSAPI);
			map.put("openid", openId);

			map.put(AlipayConfig.BODY_STR, new String("商学院"));
			// ---------------设置订单预支付参数结束------------------------

			// ---------------生成加密字符串（MD5）开始------------------------
			// 生成MD5签名字符串
			String orderSign = AlipayCore.createWXLinkString(map);
			orderSign += "&key=" + AlipayConfig.PARTNER_ID;
			// MD5签名
			// String sign = MD5Util.MD5(orderSign);
			String sign = MD5Util.MD5Encode(orderSign, "UTF-8").toUpperCase();
			// ---------------生成加密字符串（MD5）结束------------------------

			// ---------------生成预支付请求xml格式字符串------------------------
			String xmlStr = XMLUtil.getXMLWithMap(map, sign);
			// ---------------生成预支付请求xml格式字符串结束------------------------

			// ---------------请求业务逻辑获取预支付------------------------
			// String responseString = new
			// TenpayHttpClient().getPrepayId(xmlStr);
			String responseString = CommonUtil.httpsRequest(
					AlipayConfig.API_CHECK, "POST", xmlStr);
			// 解析服务器返回的xml格式字符串
			String prepayid = XMLUtil.getPrepayIdWithXML(responseString);

			// 返回给客户端的参数
			if (null != prepayid && !"".equals(prepayid)) {
				// 获取 prepayid之后进行第二次签名
				String package_str;
				// 设置支付参数
				// 设置package
				package_str = "prepay_id=" + prepayid;
				// 第二次签名参数列表
				map = new HashMap();
				String timestamp = WXUtil.getTimeStamp();
				noncestr = MD5Util.MD5(timestamp);
				map.put("appId", AlipayConfig.APP_ID_SERVER);
				map.put("package", package_str);
				map.put("signType", "MD5");
				map.put("timeStamp", timestamp);
				map.put("nonceStr", noncestr);

				String signStr = AlipayCore.createWXLinkString(map);
				signStr += "&key=" + AlipayConfig.PARTNER_ID;
				// 生成签名
				String signSecond = MD5Util.MD5(signStr);
				// 加入参数
				map.put("sign", signSecond);
				orderDesc = AlipayCore.createLinkString(map);
			} else {
				map = null;
				retmsg = "错误：获取prepayId失败";
			}
			// ================微信支付业务结束=============================
		}

		return map;
	}

}
