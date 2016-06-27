package com.jinzht.mobile.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinzht.tools.Config;
import com.jinzht.tools.Tools;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Notice;
import com.jinzht.web.entity.Preloadingpage;
import com.jinzht.web.entity.Customservice;
import com.jinzht.web.entity.Project;
import com.jinzht.web.entity.Systemmessage;
import com.jinzht.web.entity.Traderecord;
import com.jinzht.web.entity.Tradestatus;
import com.jinzht.web.entity.Tradetype;
import com.jinzht.web.entity.Users;
import com.jinzht.web.entity.Versioncontroll;
import com.jinzht.web.hibernate.HibernateSessionFactory;
import com.jinzht.web.manager.ProjectManager;
import com.jinzht.web.manager.SystemManager;
import com.jinzht.web.manager.TradeManager;
import com.jinzht.web.manager.UserManager;
import com.jinzht.web.test.User;

@Controller
public class TradeController extends BaseController {

	@Autowired
	private TradeManager TradeManger;
	@Autowired
	private UserManager userManager;
	@Autowired
	private ProjectManager projectManager;

	@RequestMapping("/requestTradeList")
	@ResponseBody
	/***
	 * 交易流水
	 * @param page 当前页
	 * @param session
	 * @return
	 */
	public Map requestTradeList(
			@RequestParam(value = "page", required = false) Integer page,
			HttpSession session) {
		this.result = new HashMap();
		this.status = 200;
		this.result.put("data", "");

		// ---------------------------开始获取数据-------------------------------------------//
		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {
			List list = this.TradeManger.findTradeRecordByUser(user, page);
			
			

			if (list != null && list.size() > 0) {
				List result = new ArrayList();
				Map map  = null;
				for(int i = 0;i<list.size();i++)
				{
					Traderecord record = (Traderecord) list.get(i);
					//投资项目
					if(record!=null)
					{
						if(record.getExt()!=null)
						{
							Integer projectId = Integer.parseInt(record.getExt());
							//项目
							Project project = this.projectManager.findProjectById(projectId);
							if(project!=null)
							{
								map = new HashMap();
								
								map.put("name", project.getAbbrevName());
								map.put("img", project.getStartPageImage());
								map.put("record", record);
								
								result.add(map);
								
								record.setExt(null);
							}
						}else{
							map = new HashMap();
							map.put("name", "");
							map.put("img", "");
							map.put("record", record);
							
							result.add(map);
						}
					
					}
				}
				this.result.put("data", result);
			}else{
				this.result.put("data", new ArrayList());
			}
		}

		// ---------------------------开始获取数据-------------------------------------------//

		this.message = "";
		return getResult();
	}
	@RequestMapping("/requestWithDraw")
	@ResponseBody
	/***
	 * 发起提现
	 * @param amount 金额
	 * @param tradeCode 订单编号
	 * @param session
	 * @return
	 */
	public Map requestWithDraw(
			@RequestParam(value = "amount", required = false) float amount,
			@RequestParam(value = "tradeCode", required = false) String tradeCode,
			HttpSession session) {
		this.result = new HashMap();
		this.status = 200;
		this.result.put("data", "");
		
		// ---------------------------开始获取数据-------------------------------------------//
		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);
		
		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {
			//生成交易记录
			Traderecord record = new Traderecord();
			//交易类型
			Tradetype type = new Tradetype();
			type.setTradeTypeId(3);
			//设置交易类型
			record.setTradetype(type);
			//设置交易用户
			record.setUsers(user);
			//设置订单编号
			record.setTradeCode(tradeCode);
			//设置交易金额
			record.setAmount(amount);
			//设置交易时间 
			record.setTradeDate(new Date());
			//设置交易状态
			Tradestatus status = new Tradestatus();
			status.setStatusId(0);
			//设置交易状态
			record.setTradestatus(status);
			
			//保存交易记录
			this.TradeManger.addTradeRecord(user, record);
			
			
			//封装返回数据
			this.message = Config.STRING_TRADE__ADD_SUCCESS;
		}
		
		// ---------------------------开始获取数据-------------------------------------------//
		
		return getResult();
	}
	@RequestMapping("/requestAccountCharge")
	@ResponseBody
	/***
	 * 账户充值 
	 * @param amount 金额
	 * @param tradeCode 订单编号
	 * @param session
	 * @return
	 */
	public Map requestAccountCharge(
			@RequestParam(value = "amount", required = false) float amount,
			@RequestParam(value = "tradeCode", required = false) String tradeCode,
			HttpSession session) {
		this.result = new HashMap();
		this.status = 200;
		this.result.put("data", "");
		
		// ---------------------------开始获取数据-------------------------------------------//
		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);
		
		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {
			//生成交易记录
			Traderecord record = new Traderecord();
			//交易类型
			Tradetype type = new Tradetype();
			type.setTradeTypeId(1);
			//设置交易类型
			record.setTradetype(type);
			//设置交易用户
			record.setUsers(user);
			//设置订单编号
			record.setTradeCode(tradeCode);
			//设置交易金额
			record.setAmount(amount);
			//设置交易时间 
			record.setTradeDate(new Date());
			//设置交易状态
			Tradestatus status = new Tradestatus();
			status.setStatusId(0);
			//设置交易状态
			record.setTradestatus(status);
			
			//保存交易记录
			this.TradeManger.addTradeRecord(user, record);
			
			
			//封装返回数据
			this.message = Config.STRING_TRADE__ADD_SUCCESS;
		}
		
		// ---------------------------开始获取数据-------------------------------------------//
		
		return getResult();
	}

	/***
	 * 从当前session获取用户对象
	 * 
	 * @param session
	 * @return
	 */
	private Users findUserInSession(HttpSession session) {
		Users user = null;
		if (session.getAttribute("userId") != null) {
			Integer userId = (Integer) session.getAttribute("userId");
			user = this.userManager.findUserById(userId);
		}

		return user;
	}
}
