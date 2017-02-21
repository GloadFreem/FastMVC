package com.jinzht.mobile.web;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinzht.stock.entity.SanCompany;
import com.jinzht.stock.entity.SanShareholder;
import com.jinzht.stock.manager.SanManager;
import com.jinzht.tools.Config;
import com.jinzht.web.entity.ProjectSearchBean;

@Controller
public class SanCompanyController extends BaseController {

	@Autowired
	private SanManager sanManager;
	
	/**
	 *  http://localhost:8080/jinzht/startCompanyService.action?pwd==113
	 * @param type
	 * @return
	 */
	@RequestMapping("/startCompanyService.action")
	@ResponseBody
	public Map startCompanyService(
			@RequestParam(value = "pwd", required = true) String pwd) {
		this.result = new HashMap();
		if(pwd.equals("create")){
			sanManager.startPushService(true);
			this.status = 200;
			this.message = "创建数据库";
		}else if(pwd.equals("add")){
			sanManager.startPushService(false);
			this.status = 201;
			this.message = "新增数据库";
		}else if(pwd.equals("refresh")){
			sanManager.refreshDataService();
			this.status = 202;
			this.message = "更新数据库";
		}
		else if(pwd.equals("clear")){
			sanManager.deleteService();
			this.status = 203;
		}
		this.result.put("data", "");
		return getResult();

	}
	
	/***
	 * 获取三板公司列表
	 * @param page 当前页
	 * @return
	 */
	@RequestMapping("/requestSanCompanyList.action")
	@ResponseBody
	public Map requestSanCompanyList(
			@RequestParam(value = "page", required = true) Integer page) {
		this.result = new HashMap();
		List list = this.sanManager.findListByPage(page);
		if (list != null && list.size() > 0) {
			this.status = 200;
			this.message = "";
			this.result.put("data", list);
		} else {
			this.status = 201;
			this.result.put("data", new ArrayList());
			this.message = Config.STRING_FEELING_NO_DATA;
		}

		return getResult();
	}
	
	/***
	 * 获取三板公司详情
	 * @param page 当前页
	 * @return
	 */
	@RequestMapping("/requestSanCompanyDetail.action")
	@ResponseBody
	public Map requestSanCompanyDetail(
			@RequestParam(value = "id", required = true) Integer id) {
		this.result = new HashMap();
		SanCompany company = this.sanManager.getCompanyDAO().findById(id);
		if (company != null ) {
			this.status = 200;
			this.message = "";
			//数据处理
			company = ChangeData(company);
			this.result.put("data", company);
		} else {
			this.status = 201;
			this.message = "暂无数据";
			this.result.put("data", "");
		}

		return getResult();
	}
	
	
	/**
	 * 处理数据
	 * @param company
	 * @return
	 */
	private SanCompany ChangeData(SanCompany company) {
		
		Set<SanShareholder> holders = company.getSanShareholders();
		for(SanShareholder s:holders){
			String   stock= s.getStock();
			String  pecent = s.getPercent();
			pecent = changeNum(Double.valueOf(pecent));
			stock = changeNumPoint(stock);
			s.setStock(stock);
			s.setPercent(pecent);
		}
		company.setSanShareholders(holders);
		return company;
	}

	private String changeNumPoint(String stock) {
		if(stock==null||stock.equals("")){
			stock = "0.00";	
		}else {
			double   stockNum = Double.valueOf(stock);
			//
			stockNum = stockNum*100;		
			 NumberFormat nf = NumberFormat.getNumberInstance();
		        // 保留两位小数
		        nf.setMaximumFractionDigits(2); 
			stock =  nf.format(stockNum);
			if(stock.contains(".")&&stock.indexOf(".")==stock.length()-2){
				stock = stock +"0";
			}
		}
		return stock;
	}

	/***
	 * 获取公司数据统计
	 * @return
	 */
	@RequestMapping("/requestSanCompanyStatistics.action")
	@ResponseBody
	public Map requestSanCompanyStatistics() {
		this.result = new HashMap();
		int baselist = this.sanManager.getCompanyDAO().getCountByType("0");
		int newlist = this.sanManager.getCompanyDAO().getCountByType("1");
		String baseNum = changeNum(baselist);
		String newNum = changeNum(newlist);
		String allNum = changeNum((baselist +newlist));
	          Map map = new HashMap();
			this.status = 200;
			this.message = "";
			map.put("allSize", allNum);
			map.put("newSize", newNum);
			map.put("baseSize", baseNum);
			this.result.put("data", map);
		return getResult();
	}
	
	/**
	 * 转化
	 * @param size
	 * @return
	 */
	private String changeNum(int size) {
		String num = size+"";
		String test  ="";
		int length = num.length();
		if(length>3){
			NumberFormat nf = new DecimalFormat(",###");
			   test = nf.format(size);
		}else{
			test = num;
		}
		return test;
	}
	
	
	/**
	 * 转化
	 * @param size
	 * @return
	 */
	private String changeNum(Double size) {
		String num = size+"";
		String test  ="";
		int length = num.length();
		if(length>3){
			NumberFormat nf = new DecimalFormat(",###");
			   test = nf.format(size);
		}else{
			test = num;
		}
		return test;
	}

	/***
	 * 获取公司数据统计
	 * @return
	 */
	@RequestMapping("/searchSanCompanyList.action")
	@ResponseBody
	public Map searchSanCompanyList(
			@RequestParam(value = "search", required = true) String search,
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "page", required = true) Integer page) {
		this.result = new HashMap();
		List list = this.sanManager.findListBySearchPage(search,type,page);
		if (list != null && list.size() > 0) {
			this.status = 200;
			this.message = "";
			this.result.put("data", list);
		} else {
			this.status = 201;
			this.result.put("data", new ArrayList());
			this.message = Config.STRING_FEELING_NO_DATA;
		}

		return getResult();
	}
	
	
	

}
