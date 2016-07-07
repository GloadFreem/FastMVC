package com.jinzht.mobile.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinzht.tools.Config;
import com.jinzht.web.entity.Rewardsystem;
import com.jinzht.web.entity.Users;
import com.jinzht.web.manager.RewardManager;
import com.jinzht.web.manager.UserManager;

@Controller
public class RewardSystemController extends BaseController {

	@Autowired
	private RewardManager rewardManger;
	@Autowired
	private UserManager userManager;

	@RequestMapping("/requestUserGoldCount")
	@ResponseBody
	/***
	 * 用户金条总数获取
	 * @return Map 返回值
	 */
	public Map requestUserGoldCount(HttpSession session) {
		this.result = new HashMap();
		// 获取用户
		Users user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_STATUS_OFFLINE;
		} else {
			Integer count  = this.rewardManger.findUserRewardCount(user);

			//封装返回数据
			Map map = new HashMap();
			map.put("count", count);
			
			this.status = 200;
			this.result.put("data", map);
			this.message = "";
		}

		return getResult();
	}
	@RequestMapping("/requestUserGoldGetInfo")
	@ResponseBody
	/***
	 * 获取用户金条奖励信息
	 * @return Map 返回值
	 */
	public Map requestUserGoldGetInfo(HttpSession session) {
		this.result = new HashMap();
		// 获取用户
		Users user = this.findUserInSession(session);
		
		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_STATUS_OFFLINE;
		} else {
			Map map = this.rewardManger.findRewardRecordNoRead(user);
			
			this.status = 200;
			this.result.put("data", map);
			this.message = "";
		}
		
		return getResult();
	}
	@RequestMapping("/requestUserGoldTradeList")
	@ResponseBody
	/***
	 * 金条交易流水
	 * @return Map 返回值
	 */
	public Map requestUserGoldTradeList(
			@RequestParam(value="page",required = false) Integer page,
			HttpSession session) {
		this.result = new HashMap();
		// 获取用户
		Users user = this.findUserInSession(session);
		
		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_STATUS_OFFLINE;
		} else {
			List list = this.rewardManger.findRewardRecordList(user,page);
			
			this.status = 200;
			if(list!=null && list.size()>0)
			{
				this.result.put("data", list);
			}else
			{
				this.result.put("data", new ArrayList());
			}
			this.message = "";
		}
		
		return getResult();
	}
	
	@RequestMapping("/requestGoldAccount")
	@ResponseBody
	/***
	 * 金条账户
	 * @param page 当前页
	 * @param session
	 * @return
	 */
	public Map requestGoldAccount(
			@RequestParam(value = "page", required = false) Integer page,
			HttpSession session) {
		this.result = new HashMap();

		this.status = 200;
		this.result.put("data", "");
		this.message = Config.STRING_LOGING_STATUS_ONLINE;

		// 获取用户
		Users user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_STATUS_OFFLINE;
		} else {
			// 获取列表
			Rewardsystem system = this.rewardManger.findRewardByUser(user);
			if(system!=null)
			{
				// 返回信息
				this.status = 200;
				this.result.put("data", system);
			}else{
				// 返回信息
				this.status = 400;
				this.result.put("data", "");
			}

			this.message = "";
		}

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
