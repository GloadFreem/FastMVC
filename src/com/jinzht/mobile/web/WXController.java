package com.jinzht.mobile.web;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.type.IdentifierType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.jinzht.tools.Config;
import com.jinzht.tools.FileUtil;
import com.jinzht.tools.MailUtil;
import com.jinzht.tools.MessageType;
import com.jinzht.tools.MsgUtil;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.City;
import com.jinzht.web.entity.Identiytype;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.MessageBean;
import com.jinzht.web.entity.Rewardsystem;
import com.jinzht.web.entity.Rewardtrade;
import com.jinzht.web.entity.Rewardtradetype;
import com.jinzht.web.entity.Systemcode;
import com.jinzht.web.entity.Users;
import com.jinzht.web.filter.LogInterceptor;
import com.jinzht.web.hibernate.HibernateSessionFactory;
import com.jinzht.web.manager.AuthenticManager;
import com.jinzht.web.manager.ImManager;
import com.jinzht.web.manager.InvestorManager;
import com.jinzht.web.manager.RewardManager;
import com.jinzht.web.manager.UserManager;
import com.jinzht.web.test.User;
import com.sun.jmx.snmp.Timestamp;
import com.tenpay.util.Sha1Util;

@Controller
public class WXController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(WXController.class);
	@Autowired
	private UserManager userManger;
	@Autowired
	private AuthenticManager authenticManager;
	@Autowired
	private InvestorManager investorManager;
	@Autowired
	private RewardManager rewardManger;
	@Autowired
	private ImManager imManager;

	@RequestMapping("/wechatcheck")
	@ResponseBody
	/***
	 * 发送验证码
	 * @param session HttpSession
	 * @return
	 */
	public void wechatcheck(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="signature",required=false)String signature,
			@RequestParam(value="timestamp",required=false)String timestamp,
			@RequestParam(value="nonce",required=false)String nonce,
			@RequestParam(value="echostr",required=false)String echostr,
			HttpSession session) {
		System.out.println("微信签名:"+signature);
		System.out.println("微信时间戳:"+timestamp);
		System.out.println("微信随机字符串:"+nonce);
		
		//微信公众平台后台设定token值
		String token = "wx1234";
		
		//字典排序
		ArrayList list = new ArrayList();
		list.add(signature);
		list.add(nonce);
		list.add(timestamp);
		
		//排序
		Collections.sort(list, new SpellComparator());
		String temp = "";
		for(int i = 0 ;i<list.size();i++)
		{
			temp += list.get(i);
		}
		System.out.println("排序后的字符串:"+temp);
		
		//将三个参数字符串拼接成一个字符串进行sha1加密
		String digest = new Sha1Util().getSha1(temp);
		
		//加密后
		System.out.println("加密后的字符串:"+digest);
		
		if(digest.equals(signature))
		{
			try{
				PrintWriter writer = response.getWriter();
				writer.print(echostr);
				writer.flush();
				writer.close();
				System.out.println("成功! ");
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}else{
			System.out.println("校验失败!");
		}
		
		
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
			user = this.userManger.findUserById(userId);
		}

		return user;
	}

	/**
	 * 汉字拼音排序比较器
	 */
	static class SpellComparator implements Comparator {
		public int compare(Object o1, Object o2) {
			try {
				// 取得比较对象的汉字编码，并将其转换成字符串
				String s1 = new String(o1.toString().getBytes("GB2312"),
						"ISO-8859-1");
				String s2 = new String(o2.toString().getBytes("GB2312"),
						"ISO-8859-1");
				// 运用String类的 compareTo（）方法对两对象进行比较
				return s1.compareTo(s2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
	}
}
