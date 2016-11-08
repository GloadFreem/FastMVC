package com.jinzht.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.jinzht.web.entity.Webcontenttype;
import com.message.Enity.Msg;
import com.message.Enity.MsgDetail;
import com.message.Enity.MsgImages;
import com.message.Enity.Webrule;

/**
 * 处理所有网站 筛选条件
 * 
 * @author EasonYang
 *
 */
public class Condition {

	public final static String LAG = "Message System:";
	

	/**
	 * JSON 解析列表数据
	 * @param webrule
	 * @return
	 */
	public final static List<Msg> getContentFromJson(Webrule webrule) {
		List<Msg> messages = new ArrayList<Msg>();
		// 获取 返回jsonStr
		String jsonStr = HttpUtil.getHtmlString(webrule.getUrl());
		if(jsonStr.equals("")){
			return messages;
		}
//		System.out.println(LAG + "jsonStr:" + jsonStr);
		// 截取字符串
		jsonStr = SplitStr(jsonStr, webrule.getPContentDel(), false);
//		System.out.println(LAG + "jsonStr:" + jsonStr);
		// Json 数据解析
		Object[] contentlist = JSONArray.fromObject(jsonStr).toArray();
		if (contentlist.length > 0) {
			for (int i = 0; i < contentlist.length; i++) {
//				try {
					Msg msg = new Msg();
					JSONObject jsonObject = JSONObject
							.fromObject(contentlist[i]);
					String title = jsonObject.getString(webrule.getPTitle());
					String time = jsonObject.getString(webrule.getPTime());
					String imgurl = jsonObject.getString(webrule.getPImg());
					String docId = jsonObject.getString(webrule.getPDocid());
					String source = jsonObject.getString(webrule.getPSource());
					String detailUrl = webrule.getDetailUrl().replace("{id}",
							docId);
					String detail = getDetailFromJson(detailUrl, webrule);
					if(jsonStr.equals("")){
						return messages;
					}
//					System.out.println(LAG + "detail:"+ detail);
					try {
						msg.setPublicDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(HttpUtil.timeUtil(time)));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//images
					Set<MsgImages> msgImageses = new HashSet<MsgImages>();
					MsgImages msgimage = new MsgImages();
					msgimage.setUrl(imgurl);
					msgimage.setMsg(msg);
					msgImageses.add(msgimage);
					msg.setMsgImageses(msgImageses);
					//type_id
				   Webcontenttype webcontenttype = new Webcontenttype();
				   webcontenttype.setTypeId(2);
					msg.setWebcontenttype(webcontenttype);
					//oringl
					msg.setOringl(source);
//					msg.setCreateTime(Calendar.getInstance().getTime());
					//title
					msg.setTitle(title);
					//details
					Set<MsgDetail> msgDetails = new HashSet<MsgDetail>();
					MsgDetail msgDetail = new MsgDetail();
					msgDetail.setContent(detail);
					msgDetails.add(msgDetail);
					msgDetail.setMsg(msg);
					msg.setMsgDetails(msgDetails);
					
			
//					if(imgurl!=null&&!imgurl.equals("")){
//						msg.setIsImg("2");
//					}else{
//						msg.setIsImg("1");
//					}
					//当详情获取成功后才存储这条资讯！
					if(!detail.equals("")&&detail!=null){
//						msg.setDetail(detail);
//						msg.setFlag(1+"");
						messages.add(msg);
					}
					
//				} catch (Exception e) {
////					System.out.println("线程"+LAG + "error:"
////							+ e.getMessage().toString());
//				}
			}
		}
		return messages;
	}

	/**
	 * 截取JsonStr
	 * @param jsonStr
	 * @param rule
	 * @return
	 */
	private static String SplitStr(String jsonStr, String rule, Boolean isdebug) {
		if (isdebug) {
//			System.out.println(LAG + "rule:" + rule);
		}
		String[] rulelist = rule.split(",");
		for (int i = 0; i < rulelist.length - 2; i++) {
			jsonStr = jsonStr.trim() + "?";
		}
		for (String item : rulelist) {
			if (isdebug) {
//				System.out.println(LAG + "item:" + item);
			}
			jsonStr = jsonStr.substring(jsonStr.indexOf(item),
					jsonStr.length() - 1);
			if (isdebug) {
//				System.out.println(LAG + "item:" + jsonStr);
			}
		}
		return jsonStr;
	}

	/**
	 * JSON 解析详情数据
	 * @param url 详情地址
	 * @param webrule
	 * @return
	 */
	public final static String getDetailFromJson(String url, Webrule webrule) {
		String detaiJsonStr = HttpUtil.getHtmlString(url);
		
//		System.out.println("线程"+webrule.getId()+LAG+url);
		String body = "";
		String title = "";
		String time = "";
		
		try {
			// TODO Auto-generated method stub
			detaiJsonStr = SplitStr(detaiJsonStr, webrule.getPDetailDel(), true);
//			System.out.println(LAG + detaiJsonStr);
			JSONObject jsonObject = JSONObject.fromObject(detaiJsonStr);
			String webruleContent = webrule.getPDetailContent();
			if(webruleContent.contains(";")){
				String[] webrulelist = webruleContent.split(";");
			
					title ="<h1>"+ jsonObject.getString(webrulelist[0])+"</h1>";
					time ="<p>"+  jsonObject.getString(webrulelist[1])+ jsonObject.getString(webrulelist[2])+"</p><br>";
					body =  jsonObject.getString(webrulelist[3]);
			}else{
				body = jsonObject.getString(webrule.getPDetailContent());
			}
			// System.out.println(LAG + body);
			String replaceImg = webrule.getPDetailImgReplace();
			if (!replaceImg.equals("")) {
				String[] replacelist = replaceImg.split(",");
				String imglistStr = jsonObject.getString(replacelist[0]);
				Object[] imglist = JSONArray.fromObject(imglistStr).toArray();
				if (imglist.length > 0) {
					for (Object imgBean : imglist) {
						// System.out.println(LAG + imglist);
						String ref = JSONObject.fromObject(imgBean).getString(
								replacelist[1]);
						// System.out.println(LAG + "ref:"+ref);
						String src = JSONObject.fromObject(imgBean).getString(
								replacelist[2]);
						// System.out.println(LAG + "src:"+src);
						String imgHtml = "<img alt=\"\" src=\"" + src + "\">";
						// System.out.println(LAG + "imgHtml:"+imgHtml);
						body = body.replace(ref, imgHtml);
					}
				}
			}
			// 去除相关图集
			if (body.contains("【")) {
				body = body.substring(0, body.indexOf("【"));
			}
			body = title +time +body;
		} catch (Exception e) {
//			 System.out.println(LAG +"error:"+ e.getMessage().toString());
//			 System.out.println(LAG +"error:"+ url);
		}
		return body;
	}

	/**
	 * HTML 解析列表数据
	 * @param webrule
	 * @return
	 */
	public final static List<Msg> getContentFromHtml(Webrule webrule) {
		List<Msg> messages = new ArrayList<Msg>();
		// 获取 返回htmlStr

		String htmlStr = HttpUtil.getHtmlString(webrule.getUrl());
				// Json 数据解析
		if(htmlStr.equals("")){
			return messages;
		}
		Elements links = new Elements();
		Document doc = Jsoup.parseBodyFragment(htmlStr);
		links = doc.select(webrule.getPContent());
		
		for (Element link : links) {
			String detail_id = link.select(webrule.getPDetailId())
					.first().attr("href");
//			System.out.println("detail_id =" + detail_id);
			String name = link.select(webrule.getPTitle()).first()
					.text();
//			System.out.println("name =" + name);
			String source = link.select(webrule.getPSource()).first()
					.text();
//			System.out.println("source =" + source);
			String imgurl = link.select(webrule.getPImg()).first()
					.attr("src");
			String time = link.select(webrule.getPTime()).first()
					.text();
//			System.out.println("imgurl =" + imgurl);
//			System.out.println("time =" + time);
			//images
//			Set<MsgImages> msgImageses = new HashSet<MsgImages>();
//			MsgImages msgimage = new MsgImages();
//			msgimage.setUrl(imgurl);
//			msgimage.setMsg(msg);
//			msgImageses.add(msgimage);
//			msg.setMsgImageses(msgImageses);
//			//type_id
//		   Webcontenttype webcontenttype = new Webcontenttype();
//		   webcontenttype.setTypeId(2);
//			msg.setWebcontenttype(webcontenttype);
//			//oringl
//			msg.setOringl(source);
////			msg.setCreateTime(Calendar.getInstance().getTime());
//			//title
//			msg.setTitle(title);
//			//details
//			Set<MsgDetail> msgDetails = new HashSet<MsgDetail>();
//			MsgDetail msgDetail = new MsgDetail();
//			msgDetail.setContent(detail);
//			msgDetails.add(msgDetail);
//			msgDetail.setMsg(msg);
//			msg.setMsgDetails(msgDetails);
			
			Msg msg = new Msg();
				msg.setTitle(name);
//				msg.setImgurl(imgurl);
				Set<MsgImages> msgImageses = new HashSet<MsgImages>();
				MsgImages msgimage = new MsgImages();
				msgimage.setUrl(imgurl);
				msgimage.setMsg(msg);
				msgImageses.add(msgimage);
				msg.setMsgImageses(msgImageses);
				try {
					msg.setPublicDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(HttpUtil.timeUtil(time)));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				msg.setOringl(source);
				//type_id
				   Webcontenttype webcontenttype = new Webcontenttype();
				   webcontenttype.setTypeId(2);
					msg.setWebcontenttype(webcontenttype);
				String detailUrl = webrule.getDetailUrl().replace("{id}", detail_id);
				String detailContent = getDetailFromHtml(detailUrl,webrule);
				Set<MsgDetail> msgDetails = new HashSet<MsgDetail>();
				MsgDetail msgDetail = new MsgDetail();
				msgDetail.setContent(detailContent);
				msgDetails.add(msgDetail);
				msgDetail.setMsg(msg);
				msg.setMsgDetails(msgDetails);
				if(!detailContent.equals("")){
					messages.add(msg);
				}
		}
		return messages;
	}
	
	
	private static String getDetailFromHtml(String detailUrl, Webrule webrule) {
		Element  content = null ;
		String emptyStr = "";
		try {
			
//		System.out.println("线程"+webrule.getId()+LAG+detailUrl);
		String detailHtmlStr = HttpUtil.getHtmlString(detailUrl);
		Document document = Jsoup.parse(detailHtmlStr);
		content = document
				.select(webrule.getPDetailContent())
				.first();
		String delDiv = webrule.getPDetailContentDel();
//		System.out.println(delDiv);
		if(!delDiv.equals("")&&delDiv.contains(";")){
			String  dellist[] = delDiv.split(";");
			for(String delStr:dellist){
//				System.out.println(delStr);
				content.select(delStr).remove();
			}
		}
		return content.toString();
			
		} catch (Exception e) {
			// TODO: handle exception
			return emptyStr;
		}
			
	}
	//
	// /**
	// * 网易：解析网页---获获取资讯对象列表
	// *
	// * @param url
	// * @param condition
	// * @return
	// */
	// public final static List<Message> getMessageBeanFromWangYi(String url,
	// String condition) {
	// List<Message> messages = new ArrayList<Message>();
	// String httpStr = HttpUtil.getHtmlString(url);
	// System.out.println(LAG + httpStr.length());
	// List<String> messageDomList = new ArrayList<String>();
	// List<String> list = new ArrayList<String>();
	// Document document = Jsoup.parse(httpStr);
	// Element bodyDom = document.select("body").first();
	// System.out.println(LAG + bodyDom.toString().length());
	// System.out.println(LAG + bodyDom.toString());
	// // Element messageDoms=bodyDom.select("swipe-content").first();
	// // System.out.println(LAG+messageDoms.toString().length());
	// // System.out.println(LAG+messageDoms.toString());
	// // for (Element msgDom : messageDoms) {
	// // Message message =new Message();
	// // String msgDetailUrl = msgDom.select("a").first().attr("href");
	// // String msgTitle = msgDom.select("div.m_article_title").text();
	// // String msgTimeStr = msgDom.select("div.m_article_desc_l").text();
	// // String msgTime = getCreateTime(msgTimeStr);
	// // String msgFrom = condition;
	// // String msgImgUrl =
	// // msgDom.select("div.m_article_img").select("img").attr("src");
	// //
	// //
	// //
	// // System.out.println(LAG+msgDetailUrl);
	// // System.out.println(LAG+msgTitle);
	// // System.out.println(LAG+msgTimeStr);
	// // System.out.println(LAG+msgImgUrl);
	// // System.out.println(LAG+msgFrom);
	// //
	// // }
	// // Node childNode=e.childNode(1);
	// // String href=url+childNode.attr("href");
	// // String ht=getHtml(href);
	// // Document doc=Jsoup.parse(ht);
	// // Element el=doc.getElementsByClass("center_right").get(0);
	// //
	// // Node n1=el.childNode(1);
	// //
	// // Node n2=n1.childNode(7);
	// // Node n3=n2.childNode(1);
	// //
	// // String imgUrl=n3.attr("src");
	// // list.add(imgUrl);
	// //
	// return messages;
	// }

	//
	// /**
	// * 网易 ：解析接口---详情
	// *
	// * @param detailHtmlStr
	// * @return
	// */
	// private static String getWangyiMessageDetailStr(String detailUrl) {
	// String detaiJsonStr = HttpUtil.getHtmlString(detailUrl);
	// // TODO Auto-generated method stub
	// detaiJsonStr = detaiJsonStr.substring(detaiJsonStr.indexOf("("),
	// detaiJsonStr.length()-1);
	// detaiJsonStr = detaiJsonStr.substring(detaiJsonStr.indexOf(":")+1,
	// detaiJsonStr.length()-1);
	// // System.out.println(LAG + detaiJsonStr);
	//
	// JSONObject jsonObject = JSONObject.fromObject(detaiJsonStr);
	// String body = jsonObject.getString("body");
	// // System.out.println(LAG + body);
	// String imglistStr = jsonObject.getString("img");
	// Object[] imglist = JSONArray.fromObject(imglistStr).toArray();
	// if(imglist.length>0){
	// for(Object imgBean: imglist){
	// System.out.println(LAG + imglist);
	// String ref = JSONObject.fromObject(imgBean).getString("ref");
	// System.out.println(LAG + "ref:"+ref);
	// String src = JSONObject.fromObject(imgBean).getString("src");
	// System.out.println(LAG + "src:"+src);
	// String imgHtml = "<img alt=\"\" src=\""+src+"\">";
	// System.out.println(LAG + "imgHtml:"+imgHtml);
	// body = body.replace(ref, imgHtml);
	// }
	// }
	// //去除相关图集
	// if(body.contains("【")){
	// body = body.substring(0,body.indexOf("【"));
	// }
	//
	// //
	// // Document document = Jsoup.parse(bean.getBody());
	// // //选取详情dom
	// // Element contentDom = document.select("div.content").first();
	// // String str = document.attr("href");
	// // //删除广告dom
	// // contentDom.select("pre").remove();
	// // System.out.println(LAG + contentDom.toString().length());
	// return body;
	// }


	// /**
	// *网易 接口--获取资讯对象列表
	// *
	// * @param url
	// * @param condition
	// * @return
	// */
	// public final static List<Message> getMessageBeanFromWangYi(String url) {
	// List<Message> messages = new ArrayList<Message>();
	// String httpStr = HttpUtil.getHtmlString(url);
	// httpStr = httpStr.substring(httpStr.indexOf("["), httpStr.length() - 2);
	// System.out.println(LAG + httpStr.length());
	// System.out.println(LAG + httpStr);
	// Gson gson = new Gson();
	// java.lang.reflect.Type type = new TypeToken<List<WangYiBean>>() {
	// }.getType();
	// List<WangYiBean> beans = gson.fromJson(httpStr, type);
	// for (WangYiBean bean : beans) {
	// Message message = new Message();
	// message.setMIsImg(bean.getHasImg() + "");
	// message.setMImg(bean.getImgsrc());
	// message.setMDate(bean.getPtime());
	// message.setMFrom(bean.getSource());
	// message.setMTitle(bean.getTitle());
	// message.setMCreateDate(HttpUtil.getDateTime());
	// String detailurl = Config.SOURCES_WANNYI_DETAIL.replace("{docid}",
	// bean.getDocid());
	//
	// //
	// if (bean.getUrl().endsWith(".html")) {
	// // http://3g.163.com/news/16/1010/13/C315T56P00014SEH.html
	// // http://3g.163.com/news/16/1010/13/C315T56P00014SEH_0_0.html
	// // detailurl = detailurl.substring(0, detailurl.length() - 5)
	// // + "_0_0.html";
	// String detailStr = getWangyiMessageDetailStr(detailurl);
	// message.setMDetails(detailStr);
	// message.setMDesc(detailurl);
	// }
	// message.setMTest("财经");
	// if(!bean.getImgsrc().equals("")){
	// messages.add(message);
	// }
	// }
	//
	// return messages;
	// }
}
