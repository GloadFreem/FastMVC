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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinzht.tools.Config;
import com.jinzht.tools.MessageType;
import com.jinzht.tools.MsgUtil;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.Identiytype;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.MessageBean;
import com.jinzht.web.entity.Users;
import com.jinzht.web.hibernate.HibernateSessionFactory;
import com.jinzht.web.manager.UserManager;
import com.jinzht.web.test.User;

@Controller
public class UserController extends BaseController {

	@Autowired
	private UserManager userManger;

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
			BindingResult bindingResult, HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");
		if (bindingResult.hasErrors()) {
			this.status = 400;
			this.message = bindingResult.getFieldError().getDefaultMessage();
		} else {

			// 仅在调试阶段使用
			if (session.getAttribute("code") != null) {
				System.out.println("发送的验证码为:" + session.getAttribute("code"));
			}
			// 仅在调试阶段使用

			String content = Config.STRING_SMS_REGISTE;
			MsgUtil SMS = new MsgUtil(message.getTelephone(), content,
					MessageType.VerifyCode);
			if (MsgUtil.send(session)) {
				this.status = 200;
				this.message = Config.SMS_HAVE_SEND_STRING;
			} else {
				this.status = 400;
				this.message = Config.SMS_FAIL_SEND_STRING;
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
			Users user = this.findUserInSession(session);

			if (user == null) {
				user = this.userManger.findUserByTelephone(userInstance
						.getTelephone());
			}

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
