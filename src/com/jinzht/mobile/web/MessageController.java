package com.jinzht.mobile.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinzht.tools.Config;
import com.jinzht.tools.MapUtil;
import com.message.Enity.Msg;
import com.message.Enity.MsgBean;
import com.message.Enity.MsgDetail;
import com.message.Enity.MsgImages;
import com.message.Enity.Original;
import com.message.Enity.OriginalBean;
import com.message.Enity.OriginalDetail;
import com.message.Enity.OriginalImg;
import com.message.Enity.Webcontent;
import com.message.manager.MainManager;
import com.message.manager.MessageMananger;

/**
 * 
 * @author EasonYang
 *
 */
@Controller
public class MessageController {
	@Autowired
	private MessageMananger messageManager;
	@Autowired
	private MainManager mainManager;



	/**
	 * JSP 返回 http://localhost:8080/MyProject/messageSystem/search
	 * 
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String getMain(ModelMap model) {

		List<Msg> alllist = messageManager.findAll();
		System.out.print("controller:"+""+alllist.size());

		model.put("messagelist", alllist);
		return "MessageHtml";
	}

	/**
	 * JSP 带参数
	 * http://localhost:8080/MyProject/messageSystem/searchID.action?id=XXX
	 * 
	 * @return
	 */
	@RequestMapping(value = "/searchID")
	public String deleteBean(@RequestParam("id") Integer id, ModelMap model) {
		Msg msg = messageManager.getMsgDao().findById(id);
		model.put("Message", msg);

		return "MessageDetailHtml";
	}

	/**
	 * JSP 返回 http://localhost:8080/MyProject/messageSystem/insert
	 * 
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String addMessageFromNet(ModelMap model) {

		String urllist[] = Config.webUrlList;
		// 开始执行操作
		System.out.println("----------------开始执行操作");
		messageManager.startInsertService();

		// 查询
		List<Msg> alllist = messageManager.findAll();
		model.put("messagelist", alllist);
		return "MessageHtml";
	}

	/**
	 * 获取资讯列表 JSON 数据返回
	 * http://localhost:8080/MyProject/messageSystem/requestThinkTankList.action
	 * http://localhost:8080/MyProject/messageSystem/requestThinkTankList.action?key=jinzht_server_security&partner=sdfwefwf&page=1
	 * http://192.168.5.182:8080/MyProject/messageSystem/requestThinkTankList.action?key=jinzht_server_security&partner=sdfwefwf&page=1
	 * @param key
	 * @param partner
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/requestThinkTankList")
	@ResponseBody
	public Object getMessageList(@RequestParam("key") String key,
			@RequestParam("partner") String partner,
			@RequestParam("page") Integer page) {
		// JSON类型返回
		String requestKey = key;
		String requestPartner = partner;
		Integer requestPageId = page;
		Map dataMap = new HashMap();
		//pageNum
		int pageNum = Config.Page_Data_Size;
		//参数检查
		if (requestKey.equals("jinzht_server_security")) {
			List<Msg> list = messageManager.findMsgList(requestPageId,pageNum,null);
			List<MsgBean> resultList = new ArrayList<MsgBean>();
			if(list.size()==0){
				dataMap.put("data", resultList);
				dataMap.put("message", "this has no more data");
				dataMap.put("status", Config.HTTP_RESULT_LAST);
			}else{
				for(Msg msg:list){
					MsgBean msgBean = new MsgBean();
					Webcontent webcontenttype = new Webcontent();
					msgBean.setId(msg.getInfoId()+"");
					msgBean.setTitle(msg.getTitle());
					msgBean.setPublicDate(msg.getPublicDate().toString().substring(0,msg.getPublicDate().toString().length()-2));
					msgBean.setOringl(msg.getOringl());
					webcontenttype.setTypeId(msg.getWebcontenttype().getTypeId()+"");
					webcontenttype.setName(msg.getWebcontenttype().getName());
					msgBean.setWebcontentType(webcontenttype);
//					System.out.print(msg.getWebcontenttype().getName());
					Set<MsgImages> imgSet = msg.getMsgImageses();
					String[] images =  new String[imgSet.size()];
					List<MsgImages> imglist = new ArrayList<MsgImages>();
					for(MsgImages mimg: imgSet){
						imglist.add(mimg);
					}
					for(int i=0;i<imglist.size();i++){
						images[i] = imglist.get(i).getUrl();
					}
					msgBean.setImages(images);
					resultList.add(msgBean);
				}
				dataMap.put("data", resultList);
				dataMap.put("message", "success");
				dataMap.put("status", Config.HTTP_RESULT_OK);	
			}	
		}else{
			dataMap.put("data", "[]");
			dataMap.put("message", "error:request param error!");
			dataMap.put("status", Config.HTTP_RESULT_ERROR);
		}
		return dataMap;
	}
	
	
	/**
	 * 获取资讯列表 JSON 数据返回
	 * http://localhost:8080/MyProject/messageSystem/requestSearchThinkTank.action
	 * http://localhost:8080/MyProject/messageSystem/requestSearchThinkTank.action?key=jinzht_server_security&partner=sdfwefwf&page=0&keyWord=美金
	 * http://192.168.5.182:8080/MyProject/messageSystem/requestSearchThinkTank.action?key=jinzht_server_security&partner=sdfwefwf&page=0&keyWord=%E7%BE%8E%E9%87%91
	 * @param key
	 * @param partner
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/requestSearchThinkTank")
	@ResponseBody
	public Object SearchMessage(@RequestParam("key") String key,
			@RequestParam("partner") String partner,
			@RequestParam("page") Integer page,@RequestParam("keyWord") String keyWord) {
		// JSON类型返回
		String requestKey = key;
		String requestPartner = partner;
		Integer requestPageId = page;
		String requestKeyWord = keyWord;
		Map dataMap = new HashMap();
		//pageNum
		int pageNum = 7;
		//参数检查
		if (requestKey.equals("jinzht_server_security")) {
			
			List<Msg> list = messageManager.findMsgList(requestPageId,pageNum,requestKeyWord);
			List<MsgBean> resultList = new ArrayList<MsgBean>();
			if(list.size()==0){
				if(requestPageId==0){
					dataMap.put("data", resultList);
					dataMap.put("message", "success");
					dataMap.put("status", Config.HTTP_RESULT_OK);
				}else{
					dataMap.put("data", resultList);
					dataMap.put("message", " no more data ");
					dataMap.put("status", Config.HTTP_RESULT_LAST);
				}	
		
			}else{
				for(Msg msg:list){
					MsgBean msgBean = new MsgBean();
					Webcontent webcontenttype = new Webcontent();
					msgBean.setId(msg.getInfoId()+"");
					msgBean.setTitle(msg.getTitle());
					msgBean.setPublicDate(msg.getPublicDate().toString().substring(0,msg.getPublicDate().toString().length()-2));
					msgBean.setOringl(msg.getOringl());
					webcontenttype.setTypeId(msg.getWebcontenttype().getTypeId()+"");
					webcontenttype.setName(msg.getWebcontenttype().getName());
					msgBean.setWebcontentType(webcontenttype);
//					System.out.print(msg.getWebcontenttype().getName());
					Set<MsgImages> imgSet = msg.getMsgImageses();
					String[] images =  new String[imgSet.size()];
					List<MsgImages> imglist = new ArrayList<MsgImages>();
					for(MsgImages mimg: imgSet){
						imglist.add(mimg);
					}
					for(int i=0;i<imglist.size();i++){
						images[i] = imglist.get(i).getUrl();
					}
					msgBean.setImages(images);
					resultList.add(msgBean);
				}
				dataMap.put("data", resultList);
				dataMap.put("message", "success");
				dataMap.put("status", Config.HTTP_RESULT_OK);	
			}	
		}else{
			dataMap.put("data", "[]");
			dataMap.put("message", "error:request param error!");
			dataMap.put("status", Config.HTTP_RESULT_ERROR);
		}
		return dataMap;
	}
	

	/**
	 * 鑾峰彇璧勮鍒楄〃 JSON 鏁版嵁杩斿洖   web缃戠珯涓撶敤
	 * http://localhost:8080/MyProject/messageSystem/requestWebThinkTankList.action
	 * http://localhost:8080/MyProject/messageSystem/requestWebThinkTankList.action?key=jinzht_server_security&partner=sdfwefwf&page=1
	 * http://192.168.5.182:8080/MyProject/messageSystem/requestWebThinkTankList.action?key=jinzht_server_security&partner=sdfwefwf&page=1
	 * @param key
	 * @param partner
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/requestWebThinkTankList")
	@ResponseBody
	public Object getMessageWebList(@RequestParam("key") String key,
			@RequestParam("partner") String partner,
			@RequestParam("page") Integer page) {
		// JSON绫诲瀷杩斿洖
		String requestKey = key;
		String requestPartner = partner;
		Integer requestPageId = page;
		Map dataMap = new HashMap();
		//pageNum
		int pageNum = Config.Page_Data_Size;
		//鍙傛暟妫�煡
		if (requestKey.equals("jinzht_server_security")) {
			List<Msg> list = messageManager.findMsgList(requestPageId,pageNum,null);
			List<MsgBean> resultList = new ArrayList<MsgBean>();
			if(list.size()==0){
				dataMap.put("data", resultList);
				dataMap.put("message", "this has no more data");
				dataMap.put("status", Config.HTTP_RESULT_LAST);
			}else{
				for(Msg msg:list){
					MsgBean msgBean = new MsgBean();
					Webcontent webcontenttype = new Webcontent();
					msgBean.setId(msg.getInfoId()+"");
					msgBean.setTitle(msg.getTitle());
					msgBean.setPublicDate(msg.getPublicDate().toString().substring(0,msg.getPublicDate().toString().length()-2));
					msgBean.setOringl(msg.getOringl());
					webcontenttype.setTypeId(msg.getWebcontenttype().getTypeId()+"");
					webcontenttype.setName(msg.getWebcontenttype().getName());
					msgBean.setWebcontentType(webcontenttype);
//					System.out.print(msg.getWebcontenttype().getName());
					Set<MsgImages> imgSet = msg.getMsgImageses();
					String[] images =  new String[imgSet.size()];
					List<MsgImages> imglist = new ArrayList<MsgImages>();
					for(MsgImages mimg: imgSet){
						imglist.add(mimg);
					}
					for(int i=0;i<imglist.size();i++){
						images[i] = imglist.get(i).getUrl();
					}
					msgBean.setImages(images);
					//desc
					Set<MsgDetail> detalSet = msg.getMsgDetails();
					List<MsgDetail> detalList = new ArrayList<MsgDetail>();
					for(MsgDetail mimg: detalSet){
						detalList.add(mimg);
					}
					String msgDesc = detalList.get(0).getContent();
					Elements links = new Elements();
					Document doc = Jsoup.parse(msgDesc);
					links = doc.getAllElements();
					String descStr = links.text();
					if(descStr.indexOf(msg.getOringl())!=-1&&!msg.getOringl().equals("")){
						descStr = 	descStr.substring(descStr.indexOf(msg.getOringl())+msg.getOringl().length(), descStr.length()-1);
					}
					if(descStr.length()>200){
						descStr = 	descStr.substring(0, 199);
					}
					msgBean.setDesc(descStr);
					resultList.add(msgBean);
				}
				dataMap.put("data", resultList);
				dataMap.put("message", "success");
				dataMap.put("status", Config.HTTP_RESULT_OK);	
			}	
		}else{
			dataMap.put("data", "[]");
			dataMap.put("message", "error:request param error!");
			dataMap.put("status", Config.HTTP_RESULT_ERROR);
		}
		return dataMap;
	}
	

	/**
	 * requestWebViewPointList  web锛氳祫璁姤鍛�	 * http://localhost:8080/MyProject/messageSystem/requestWebViewPointList.action?key=jinzht_server_security&partner=requestViewPointList&page=0
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/requestWebViewPointList.action")
	@ResponseBody
	public Map searchViewPointWeb(HttpServletRequest request) {
		// 閹恒儲鏁归崣鍌涙殶
		String key = request.getParameter("key");
		String partner = request.getParameter("partner");
		String page = request.getParameter("page");
		int pageIndex = Integer.parseInt(page.trim());
		
		List<OriginalBean> oBeans = new ArrayList<OriginalBean>();
		
//		List allData = this.mainManager.obtainAllOriginal();
//		if(allData.size() < 2*pageIndex){
//			return MapUtil.mapData("鏁版嵁鍔犺浇瀹屾瘯", "201", null);
//		}
		if (key.equals("jinzht_server_security")
				) {
			List<Original> list = mainManager.obtainOriginal(pageIndex, 10);
			for(Original original:list){
				OriginalBean bean = new OriginalBean();
				bean.setTitle(original.getTitle());
				bean.setInfoId(original.getInfoId());
				bean.setOringl(original.getOringl());
				bean.setPublicDate(original.getPublicDate());
				bean.setWebcontenttype(original.getWebcontenttype());
				//imgurl
				Set<OriginalImg>  imgUrls = original.getOriginalImgs();
				List<String> urllist = new ArrayList<String>();
				for(OriginalImg img:imgUrls){
					urllist.add(img.getUrl());
				}
				if(urllist.size()>0){
					bean.setImgurl(urllist.get(0));
				}else{
					bean.setImgurl("");
				}
				//desc
				Set<OriginalDetail>  detaillist = original.getOriginalDetails();
				List<String> detailStrlsit = new ArrayList<String>();
				for(OriginalDetail detal:detaillist){
					detailStrlsit.add(detal.getContent());
				}
				String desc = "";
				String descStr = "";
				if(detailStrlsit.size()>0){
					 desc = detailStrlsit.get(0);
					 Elements links = new Elements();
						Document doc = Jsoup.parse(desc);
						links = doc.getAllElements();
						descStr = links.text();
						if(descStr.indexOf(original.getTitle())!=-1&&!original.getTitle().equals("")){
							descStr = 	descStr.substring(descStr.indexOf(original.getTitle())+original.getTitle().length(), descStr.length()-1);
						}
						if(descStr.length()>200){
							descStr = 	descStr.substring(0, 199);
						}
				}

				bean.setDesc(descStr);
				oBeans.add(bean);
			}
			if(oBeans.size()==0){
				if(page.equals("0")){
					return MapUtil.mapData("success", "200", oBeans);
				}else{
					return MapUtil.mapData("no more data", "201", oBeans);
				}	
			}else{
				return MapUtil.mapData("success", "200", oBeans);
			}
		} else {
			return MapUtil.mapData("鏁版嵁鍔犺浇澶辫触", "400", null);
		}
	}

	
	
	/**
	*  获取资讯详情页面  JSP数据返回
	*  http://192.168.5.182:8080/MyProject/messageSystem/requestThinkTankDetail.action?id=117
	* @return
	*/
	@RequestMapping(value = "/requestThinkTankDetail")
	public String getThinkTankDetail(@RequestParam("id") Integer id,ModelMap model ){
	    Msg msg = messageManager.getMsgDao().findById(id);
	    Set<MsgDetail> msgDetail=msg.getMsgDetails();
	    String detail = "";
	    for(MsgDetail det:msgDetail){
	    	detail+=det.getContent();
	    }
		model.put("Message", detail);
		return "ThinkTankDetail";
	}
	
	/**
	 * http://localhost:8080/MyProject/messageSystem/requestViewPointList.action?key=jinzht_server_security&partner=requestViewPointList&page=0
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/requestViewPointList.action")
	@ResponseBody
	public Map originaljson(HttpServletRequest request) {
		// 鎺ユ敹鍙傛暟
		String key = request.getParameter("key");
		String partner = request.getParameter("partner");
		String page = request.getParameter("page");
		int pageIndex = Integer.parseInt(page.trim());
		
		List allData = this.mainManager.obtainAllOriginal();
		if(allData.size() < 2*pageIndex){
			return MapUtil.mapData("数据加载完毕", "201", null);
		}

		if (key.equals("jinzht_server_security")
				) {
			List<Original> list = mainManager.obtainOriginal(pageIndex, 10);
			if(list!=null && list.size()>0)
			{
				for(Original o : list)
				{
					o.setOriginalDetails(null);
				}
				return MapUtil.mapData("success", "200", list);
			}else{
				return MapUtil.mapData("暂无数据", "201", list);
			}
		} else {
			return MapUtil.mapData("数据加载失败", "400", null);
		}
	}

	/**
	 * http://localhost:8080/MyProject/messageSystem/requestSearchViewPointList.action?key=jinzht_server_security&partner=requestViewPointList&page=0
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/requestSearchViewPointList.action")
	@ResponseBody
	public Map searchViewPoint(HttpServletRequest request) {
		// 鎺ユ敹鍙傛暟
		String key = request.getParameter("key");
		String partner = request.getParameter("partner");
		String keyWords = request.getParameter("keyWord");
		String page = request.getParameter("page");
		int pageIndex = Integer.parseInt(page.trim());
		
//		List allData = this.mainManager.obtainAllOriginal();
//		if(allData.size() < 2*pageIndex){
//			return MapUtil.mapData("数据加载完毕", "201", null);
//		}
		if (key.equals("jinzht_server_security")
				) {
			List list = mainManager.obtainSearchOriginal(pageIndex, 10,
					keyWords);
			if(list.size()==0){
				if(page.equals("0")){
					return MapUtil.mapData("success", "200", list);
				}else{
					return MapUtil.mapData("no more data ！", "201", list);
				}	
			}else{
				return MapUtil.mapData("success", "200", list);
			}
		} else {
			return MapUtil.mapData("数据加载失败", "400", null);
		}
	}

	/**
	 * http://localhost:8080/MyProject/messageSystem/requestViewPointDetail.action?infoId=3
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/requestViewPointDetail")
	public String originalDetailPage(ModelMap map, HttpServletRequest request) {
		String infoId = request.getParameter("infoId");
		Integer infoId_integer = Integer.parseInt(infoId.trim());
		Original original = (Original) this.mainManager
				.obtainOriginalByInfoId(infoId_integer);
		Set<OriginalDetail> originalDetails = original.getOriginalDetails();
		for (OriginalDetail value : originalDetails) {
			map.put("originalDetail", value);
		}
		return "OriginalDetailPage";
	}
	
	
	
	/**
	*  获取活动详情
	*  http://localhost:8080/MyProject/messageSystem/sportDetail.action
	* @return
	*/
	@RequestMapping(value = "/sportDetail")
	public String getSportDetail(ModelMap model ){

		return "SportDetail";
	}
	
}
