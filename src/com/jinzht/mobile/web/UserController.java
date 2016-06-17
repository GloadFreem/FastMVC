package com.jinzht.mobile.web;

import java.util.ArrayList;
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
import com.jinzht.tools.MessageType;
import com.jinzht.tools.MsgUtil;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.City;
import com.jinzht.web.entity.Identiytype;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.MessageBean;
import com.jinzht.web.entity.Rewardsystem;
import com.jinzht.web.entity.Users;
import com.jinzht.web.hibernate.HibernateSessionFactory;
import com.jinzht.web.manager.AuthenticManager;
import com.jinzht.web.manager.UserManager;
import com.jinzht.web.test.User;

@Controller
@SessionAttributes("code")
public class UserController extends BaseController {

	@Autowired
	private UserManager userManger;
	@Autowired
	private AuthenticManager authenticManager;

	@RequestMapping(value = "createUser")
	public String createUser(@ModelAttribute("user") Users user) {
		String path = System.getProperty("jinzht.root");
		System.out.println(path);
		List<Users> users = userManger.findAllUsers();
		for (int i = 0; i < users.size(); i++) {
			Users u = users.get(i);
			System.out.println(u.getTelephone());
		}
		return "index";
	}

	@RequestMapping("/valid")
	public String handleValid1(@Valid @ModelAttribute("user") User user,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			System.out.println("失败!" + bindingResult.getErrorCount()
					+ bindingResult.getFieldError().getDefaultMessage());
		} else {
			System.out.println("成功!");
		}
		return "index";
	}

	@RequestMapping("/jsonResult")
	@ResponseBody
	public Map jsonResult(ModelMap mm) {
		this.result = new HashMap();
		Users userItem = new Users();
		userItem.setTelephone("198545545");
		userItem.setPassword("jhsdhfjgh");

		this.status = 200;
		this.result.put("data", userItem);
		this.message = Config.STRING_LOGING_SUCCESS;
		return getResult();
	}

	@RequestMapping("/verifyCode")
	@ResponseBody
	/***
	 * 发送验证码
	 * @param message MessageBean数据校验bean
	 * @param bindingResult 校验绑定结果
	 * @param session HttpSession
	 * @return
	 */
	public Map verifyCode(
			@Valid @ModelAttribute("messagebean") MessageBean message,
			BindingResult bindingResult,Model model, HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");
		if (bindingResult.hasErrors()) {
			this.status = 400;
			this.message = bindingResult.getFieldError().getDefaultMessage();
		} else {
			String content = Config.STRING_SMS_REGISTE;
			MsgUtil SMS = new MsgUtil(message.getTelephone(), content,
					MessageType.VerifyCode);
			
			//发送验证码
			Integer code = MsgUtil.send();
			
			if (code != 0) {
				this.status = 200;
				this.message = Config.SMS_HAVE_SEND_STRING;
				//将code加入到session会话
				
				session.setAttribute("code", code);
				//model.addAttribute("code", code);
			} else {
				this.status = 400;
				this.message = Config.SMS_FAIL_SEND_STRING;
			}
			
			if(session.getAttribute("code")!=null){
				System.out.println("获取到验证码:"+session.getAttribute("code"));
			}
		}
		return getResult();
	}

	@RequestMapping("/registUser")
	@ResponseBody
	/***
	 * 发送验证码
	 * @param message MessageBean数据校验bean
	 * @param bindingResult 校验绑定结果
	 * @param session HttpSession
	 * @return
	 */
	public Map registUser(
			@RequestParam(value = "verifyCode", required = false) String verifyCode,
			@Valid @ModelAttribute("user") Users userInstance,
			BindingResult bindingResult, HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");
		if (bindingResult.hasErrors()) {
			this.status = 400;
			this.message = bindingResult.getFieldError().getDefaultMessage();
		} else {
			// 获取验证码
			String code = "";
			if (session.getAttribute("code") != null) {
				code = session.getAttribute("code").toString();
			}

			if (!code.equals("")) {
				if (!verifyCode.equals(code)) {
					this.status = 400;
					this.message = Config.STRING_LOGING_CODE_ERROR;
				} else {
					// 仅在调试阶段使用
					Users user = this.findUserInSession(session);
					// 如果session
					if (user == null) {
						user = this.userManger.findUserByTelephone(userInstance
								.getTelephone());
					}

					if (user == null) {

						user = userInstance;
						user = this.userManger.addUser(user);

						if (user != null) {
							//发送短信
							MsgUtil SMS = new MsgUtil();
							SMS.setTelePhone(user.getTelephone());
							SMS.setMsgType(MessageType.NormalMessage);
							SMS.setContent(Config.STRING_SMS_INVEST_VALID_TRUE);
							//发送验证码
							MsgUtil.send();
							
							Map map = new HashMap();
							map.put("userId", user.getUserId());

							// 返回数据
							this.status = 200;
							this.result.put("data", map);
							this.message = Config.STRING_REGIST_SUCCESS;

							session.setAttribute("userId", user.getUserId());
						} else {
							this.status = 400;
							this.message = Config.STRING_REGIST_FAIL;
						}
					} else {
						session.setAttribute("userId", user.getUserId());
						this.status = 400;
						this.message = Config.STRING_LOGING_FAIL_HAS_REGISTED;
					}
				}
			} else {
				this.status = 400;
				this.message = Config.STRING_LOGING_CODE_NOT_GET;
			}
		}
		return getResult();
	}

	@RequestMapping("/loginUser")
	@ResponseBody
	/***
	 * 用户登录
	 * @param userInstance 传参
	 * @param bindingResult 校验结果绑定
	 * @param session HttpSession
	 * @return 返回值
	 */
	public Map loginUser(@Valid @ModelAttribute("user") Users userInstance,
			BindingResult bindingResult, HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");
		if (bindingResult.hasErrors()) {
			this.status = 400;
			this.message = bindingResult.getFieldError().getDefaultMessage();
		} else {
			Users user = this.userManger.findUserByTelephone(userInstance
					.getTelephone());

			if (user == null) {
				this.status = 400;
				this.message = Config.STRING_LOGING_FAIL_NO_USER;
			} else {
				// 检测用户登录失败是否已经超过当日最大次数
				Loginfailrecord record = new Loginfailrecord();
				record.setUsers(user);
				record.setPlatform(user.getPlatform());
				record.setLoginFailDate(new Date());
				record = this.userManger.addOrUpdateLoginFailRecord(record,
						false);

				// 判断登录是否已达到最大登录失败次数
				if (record != null) {
					if (record.getCount() >= 5) {
						// 返回数据
						this.status = 400;
						this.result.put("data", "");
						this.message = Config.STRING_LOGING_FAIL_MAX;

						// 将用户id保存至session
						session.setAttribute("userId", null);

						return getResult();
					}
				} else {
					record = new Loginfailrecord();
					record.setUsers(user);
					record.setPlatform(user.getPlatform());
					record.setLoginFailDate(new Date());
				}
				// 开始校验密码
				if (user.getPassword().equals(userInstance.getPassword())) {
					// 更新用户登录信息
					user.setPlatform(userInstance.getPlatform());
					user.setLastLoginDate(new Date());

					// 保存更新
					this.userManger.saveOrUpdateUser(user);

					// 返回结果
					Map map = new HashMap();
					map.put("userId", user.getUserId());

					this.status = 200;
					this.result.put("data", map);
					this.message = Config.STRING_LOGING_SUCCESS;

					session.setAttribute("userId", user.getUserId());
				} else {
					// 更新登录失败信息
					record = this.userManger.addOrUpdateLoginFailRecord(record,
							true);
					this.status = 400;
					this.message = String.format(
							Config.STRING_LOGING_FAIL_LEFT, record.getCount(),
							5 - record.getCount());
					// 登录失败记录添加
				}
			}
		}
		return getResult();
	}

	@RequestMapping("/resetPassWordUser")
	@ResponseBody
	/***
	 * 忘记密码
	 * @param userInstance
	 * @param bindingResult
	 * @param session
	 * @return
	 */
	public Map resetPassWordUser(
			@RequestParam(value = "verifyCode") String verifyCode,
			@Valid @ModelAttribute("user") Users userInstance,
			BindingResult bindingResult, HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");
		if (bindingResult.hasErrors()) {
			this.status = 400;
			this.message = bindingResult.getFieldError().getDefaultMessage();
		} else {
			// 判断验证码是否上传
			if (verifyCode == null || verifyCode.equals("")) {
				this.status = 400;
				this.message = Config.STRING_LOGING_CODE_NOT_NULL;

				return getResult();
			}

			// 校验验证码
			String code = "";
			if (session.getAttribute("code") != null) {
				code = session.getAttribute("code").toString();
				System.out.println("获取到验证码:"+session.getAttribute("code"));
			}
			
			
			if (!code.equals("")) {
				if (!verifyCode.equals(code)) {
					this.status = 400;
					this.message = Config.STRING_LOGING_CODE_ERROR;

					return getResult();
				}
			} else {
				this.status = 400;
				this.message = Config.STRING_LOGING_CODE_NOT_GET;

				return getResult();
			}

			// 获取用户
			Users user = this.findUserInSession(session);

			if (user == null) {
				user = this.userManger.findUserByTelephone(userInstance
						.getTelephone());
			}

			if (user == null) {
				this.status = 400;
				this.message = Config.STRING_LOGING_FAIL_NO_USER;
			} else {
				// 开始更新密码
				user.setPassword(userInstance.getPassword());
				user.setPlatform(user.getPlatform());

				this.userManger.saveOrUpdateUser(user);

				this.status = 200;
				this.message = Config.STRING_PASSWORD_RESET_SUCCESS;
			}
		}
		return getResult();
	}

	@RequestMapping("/wechatLoginUser")
	@ResponseBody
	/***
	 * 忘记密码
	 * @param userInstance
	 * @param bindingResult
	 * @param session
	 * @return
	 */
	public Map wechatLoginUser(
			@RequestParam(value = "wechatID") String wechatID,
			@RequestParam(value = "platform") short platform,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");

		if (wechatID == null || wechatID.equals("")) {
			this.status = 400;
			this.message = Config.STRING_LOGING_WECHATID_NOT_NULL;
			return getResult();
		}

		// 获取用户
		Users user = this.userManger.findUserByWechatId(wechatID);

		if (user != null) {
			// 更新用户登录信息
			user.setPlatform(platform);
			user.setWechatId(wechatID);
			user.setLastLoginDate(new Date());

			// 更新用户登录信息
			this.userManger.saveOrUpdateUser(user);

			// 封装返回数据
			Map map = new HashMap();
			map.put("userId", user.getUserId());

			this.status = 200;
			session.setAttribute("userId", user.getUserId());
			this.result.put("data", map);
			this.message = Config.STRING_LOGING_SUCCESS;
		} else {
			// 新用户
			user = new Users();
			user.setTelephone("18729342354");
			user.setPassword("18729342354");
			user.setWechatId(wechatID);
			user.setPlatform(platform);
			user.setLastLoginDate(new Date());
			this.userManger.addUser(user);

			if (user != null) {
				// 封装返回数值
				Map map = new HashMap();
				map.put("userId", user.getUserId());

				this.result.put("data", map);
				session.setAttribute("userId", user.getUserId());

				this.status = 200;
				this.message = Config.STRING_LOGING_WECHAT_SUCCESS;
			} else {
				this.status = 400;
				this.message = Config.STRING_LOGING_WECHAT_FAIL;
			}
		}

		return getResult();
	}

	@RequestMapping("/noLogoInfo")
	@ResponseBody
	public Map noLogoInfo(ModelMap mm) {
		this.result = new HashMap();
		this.status = 401;
		this.message = Config.STRING_LOGING_TIP;
		this.result.put("data", "");
		return getResult();
	}
	@RequestMapping("/signupUser")
	@ResponseBody
	public Map signupUser(ModelMap mm,HttpSession session) {
		this.result = new HashMap();
		this.status = 200;
		session.setAttribute("userId", null);
		
		this.message = Config.STRING_LOGING_OUT;
		this.result.put("data", "");
		return getResult();
	}
	
	@RequestMapping("/isLoginUser")
	@ResponseBody
	public Map isLoginUser(ModelMap mm,HttpSession session) {
		this.result = new HashMap();
		
		this.status = 200;
		this.result.put("data", "");
		this.message = Config.STRING_LOGING_STATUS_ONLINE;
		
		//检测用户是否已登录
//		session.setAttribute("userId", null);
		if(session.getAttribute("userId")==null)
		{
			this.status = 400;
			this.message = Config.STRING_LOGING_STATUS_OFFLINE;
		}
		
		return getResult();
	}
	@RequestMapping("/requestChangeHeaderPicture")
	@ResponseBody
	/***
	 * 更新用户头像
	 * @param file 图片文件
	 * @param session
	 * @return
	 */
	public Map requestChangeHeaderPicture(
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpSession session) {
		this.result = new HashMap();
		
		this.status = 200;
		this.result.put("data", "");
		this.message = Config.STRING_LOGING_STATUS_ONLINE;
		
		// 获取用户
		Users user = this.findUserInSession(session);
		
		if(user==null)
		{
			this.status = 400;
			this.message = Config.STRING_LOGING_STATUS_OFFLINE;
		}else{
			// 保存图片
			String fileName = String.format(
					Config.STRING_USER_HEADER_PICTURE_FORMAT, user.getUserId());
			String result = FileUtil.savePicture(file, fileName,
					"upload/headerImages/");
			if (!result.equals("")) {
				fileName = Config.STRING_SYSTEM_ADDRESS + "upload/headerImages/"
						+ result;
				user.setHeadSculpture(fileName);
				
				//更新信息
				this.userManger.saveOrUpdateUser(user);
				//返回信息
				this.status = 200;
				this.message = Config.STRING_USER_HEADER_PICTURE_UPDATE_SUCCESS;
			} else {
				this.status = 200;
				this.message = Config.STRING_USER_HEADER_PICTURE_UPDATE_FAIL;
			}
			
		}
		
		return getResult();
	}
	@RequestMapping("/requestModifyCompany")
	@ResponseBody
	/***
	 * 更新公司名称
	 * @param name 公司名称
	 * @param session
	 * @return
	 */
	public Map requestModifyCompany(
			@RequestParam(value = "name", required = false) String name,
			HttpSession session) {
		this.result = new HashMap();
		
		this.status = 200;
		this.result.put("data", "");
		this.message = Config.STRING_LOGING_STATUS_ONLINE;
		
		// 获取用户
		Users user = this.findUserInSession(session);
		
		if(user==null)
		{
			this.status = 400;
			this.message = Config.STRING_LOGING_STATUS_OFFLINE;
		}else{

			//更新信息
			Object[] authentics = user.getAuthentics().toArray();
			
			Authentic authentic = null;
			for(int i = 0;i<authentics.length;i++){
				authentic = (Authentic) authentics[i];
				authentic.setCompanyName(name);
			}
			this.userManger.saveOrUpdateUser(user);
			//返回信息
			this.status = 200;
			this.message = Config.STRING_USER_COMPNY_UPDATE_SUCCESS;
		}
		
		return getResult();
	}
	@RequestMapping("/requestModifyPosition")
	@ResponseBody
	/***
	 * 更新职位
	 * @param position 职位
	 * @param session
	 * @return
	 */
	public Map requestModifyPosition(
			@RequestParam(value = "position", required = false) String position,
			HttpSession session) {
		this.result = new HashMap();
		
		this.status = 200;
		this.result.put("data", "");
		this.message = Config.STRING_LOGING_STATUS_ONLINE;
		
		// 获取用户
		Users user = this.findUserInSession(session);
		
		if(user==null)
		{
			this.status = 400;
			this.message = Config.STRING_LOGING_STATUS_OFFLINE;
		}else{
			
			//更新信息
			Object[] authentics = user.getAuthentics().toArray();
			
			Authentic authentic = null;
			for(int i = 0;i<authentics.length;i++){
				authentic = (Authentic) authentics[i];
				authentic.setPosition(position);
			}
			this.userManger.saveOrUpdateUser(user);
			//返回信息
			this.status = 200;
			this.message = Config.STRING_USER_POSITION_UPDATE_SUCCESS;
		}
		
		return getResult();
	}
	@RequestMapping("/requestModifyCity")
	@ResponseBody
	/***
	 * 更新地址
	 * @param cityId 城市id
	 * @param session
	 * @return
	 */
	public Map requestModifyCity(
			@RequestParam(value = "cityId", required = false) Integer cityId,
			HttpSession session) {
		this.result = new HashMap();
		
		this.status = 200;
		this.result.put("data", "");
		this.message = Config.STRING_LOGING_STATUS_ONLINE;
		
		// 获取用户
		Users user = this.findUserInSession(session);
		
		if(user==null)
		{
			this.status = 400;
			this.message = Config.STRING_LOGING_STATUS_OFFLINE;
		}else{
			
			//更新信息
			Object[] authentics = user.getAuthentics().toArray();
			
			Authentic authentic = null;
			City city  = this.authenticManager.findCityByCityId(cityId);
			for(int i = 0;i<authentics.length;i++){
				authentic = (Authentic) authentics[i];
				authentic.setCity(city);
			}
			this.userManger.saveOrUpdateUser(user);
			//返回信息
			this.status = 200;
			this.message = Config.STRING_USER_ADDRESS_UPDATE_SUCCESS;
		}
		
		return getResult();
	}
	@RequestMapping("/requestModifyPassword")
	@ResponseBody
	/***
	 * 更新密码
	 * @param passwordOld 旧密码
	 * @param passwordNew 新密码
	 * @param session
	 * @return
	 */
	public Map requestModifyPassword	(
			@RequestParam(value = "passwordOld", required = false) String passwordOld,
			@RequestParam(value = "passwordNew", required = false) String passwordNew,
			HttpSession session) {
		this.result = new HashMap();
		
		this.status = 200;
		this.result.put("data", "");
		this.message = Config.STRING_LOGING_STATUS_ONLINE;
		
		// 获取用户
		Users user = this.findUserInSession(session);
		
		if(user==null)
		{
			this.status = 400;
			this.message = Config.STRING_LOGING_STATUS_OFFLINE;
		}else{
			
			//旧密码比对
			if(user.getPassword().equals(passwordOld)){
				//更新信息
				user.setPassword(passwordNew);
				this.userManger.saveOrUpdateUser(user);
				
				//返回信息
				this.status = 200;
				this.message = Config.STRING_USER_PASSWORD_UPDATE_SUCCESS;
			}else{
				this.status = 400;
				this.message = Config.STRING_USER_PASSWORD_COMPARE_FAIL;
			}
		}
		
		return getResult();
	}
	@RequestMapping("/requestChangeBindTelephone")
	@ResponseBody
	/***
	 * 更新密码
	 * @param telephone 手机号码
	 * @param code 短信验证码
	 * @param session
	 * @return
	 */
	public Map requestChangeBindTelephone	(
			@RequestParam(value = "telephone", required = false) String telephone,
			@RequestParam(value = "code", required = false) int code,
			HttpSession session) {
		this.result = new HashMap();
		
		this.status = 200;
		this.result.put("data", "");
		this.message = Config.STRING_LOGING_STATUS_ONLINE;
		
		//检验验证码
		if(session.getAttribute("code")!=null){
			//比对验证码
			int codeSession = (int) session.getAttribute("code");
			if(codeSession == code)
			{
				// 获取用户
				Users user = this.findUserInSession(session);
				
				if(user==null)
				{
					this.status = 400;
					this.message = Config.STRING_LOGING_STATUS_OFFLINE;
				}else{
					//设置手机号码
					user.setTelephone(telephone);
					//保存信息
					this.userManger.saveOrUpdateUser(user);
					
					//返回信息
					this.status = 200;
					this.message = Config.STRING_USER_TELEPHONE_UPDATE_SUCCESS;
				}
			}else{
				this.status = 400;
				this.message = Config.STRING_LOGING_CODE_ERROR;
			}
		}else{
			this.status = 400;
			this.message = Config.STRING_LOGING_CODE_NOT_GET;
		}
		
		return getResult();
	}
	@RequestMapping("/requestMineCollection")
	@ResponseBody
	/***
	 * 获取用户关注
	 * @param page 当前页
	 * @param type 类型,0:项目，1：投资人
	 * @param session
	 * @return
	 */
	public Map requestMineCollection	(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "type", required = false) short type,
			HttpSession session) {
		this.result = new HashMap();
		
		this.status = 200;
		this.result.put("data", "");
		this.message = Config.STRING_LOGING_STATUS_ONLINE;
		
		// 获取用户
		Users user = this.findUserInSession(session);
		
		if(user==null)
		{
			this.status = 400;
			this.message = Config.STRING_LOGING_STATUS_OFFLINE;
		}else{
			//获取列表
			List list = null;
			if(type==0)
			{
				list = this.userManger.findUserCollectionProjects(user, page);
			}else{
				list = this.userManger.findUserCollectionUsers(user, page);
			}
			
			if(list!=null )
			{
				this.result.put("data",list);
			}else{
				this.result.put("data","");
			}
			
			//返回信息
			this.status = 200;
			
			this.message = "";
		}
		
		return getResult();
	}
	@RequestMapping("/requestMineAction")
	@ResponseBody
	/***
	 * 获取用户参加活动
	 * @param page 当前页
	 * @param session
	 * @return
	 */
	public Map requestMineAction	(
			@RequestParam(value = "page", required = false) Integer page,
			HttpSession session) {
		this.result = new HashMap();
		
		this.status = 200;
		this.result.put("data", "");
		this.message = Config.STRING_LOGING_STATUS_ONLINE;
		
		// 获取用户
		Users user = this.findUserInSession(session);
		
		if(user==null)
		{
			this.status = 400;
			this.message = Config.STRING_LOGING_STATUS_OFFLINE;
		}else{
			//获取列表
			List list = null;
			list = this.userManger.findUserCollectionProjects(user, page);
			
			if(list!=null )
			{
				this.result.put("data",list);
			}else{
				this.result.put("data","");
			}
			
			//返回信息
			this.status = 200;
			
			this.message = "";
		}
		
		return getResult();
	}
	@RequestMapping("/requestGoldTradList")
	@ResponseBody
	/***
	 * 金条交易记录
	 * @param page 当前页
	 * @param session
	 * @return
	 */
	public Map requestGoldTradList	(
			@RequestParam(value = "page", required = false) Integer page,
			HttpSession session) {
		this.result = new HashMap();
		
		this.status = 200;
		this.result.put("data", "");
		this.message = Config.STRING_LOGING_STATUS_ONLINE;
		
		// 获取用户
		Users user = this.findUserInSession(session);
		
		if(user==null)
		{
			this.status = 400;
			this.message = Config.STRING_LOGING_STATUS_OFFLINE;
		}else{
			//获取列表
			List list = null;
			Object[] l = user.getRewardsystems().toArray();
			Rewardsystem system = (Rewardsystem) l[0];
			
			list = this.userManger.findGoldTradList(system, page);
			
			if(list!=null )
			{
				this.result.put("data",list);
			}else{
				this.result.put("data","");
			}
			
			//返回信息
			this.status = 200;
			
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
		
		if(user==null)
		{
			this.status = 400;
			this.message = Config.STRING_LOGING_STATUS_OFFLINE;
		}else{
			//获取列表
			this.result.put("data",user.getRewardsystems());
			
			//返回信息
			this.status = 200;
			
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
			user = this.userManger.findUserById(userId);
		}

		return user;
	}
	
	

}
