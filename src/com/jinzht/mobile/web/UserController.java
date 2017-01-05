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
import com.jinzht.web.hibernate.HibernateSessionFactory;
import com.jinzht.web.manager.AuthenticManager;
import com.jinzht.web.manager.InvestorManager;
import com.jinzht.web.manager.RewardManager;
import com.jinzht.web.manager.UserManager;
import com.jinzht.web.test.User;
import com.sun.jmx.snmp.Timestamp;

@Controller
public class UserController extends BaseController {

	@Autowired
	private UserManager userManger;
	@Autowired
	private AuthenticManager authenticManager;
	@Autowired
	private InvestorManager investorManager;
	@Autowired
	private RewardManager rewardManger;

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
			BindingResult bindingResult, Model model, HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");
		this.message = "";
		if (bindingResult.hasErrors()) {
			this.status = 400;
			this.message = bindingResult.getFieldError().getDefaultMessage();
		} else {
			String content = Config.STRING_SMS_REGISTE;
			MsgUtil SMS = new MsgUtil(message.getTelephone(), content,
					MessageType.VerifyCode);

			// 获取用户输入手机号码是否已经存在
			Users user = this.userManger.findUserByTelephone(message
					.getTelephone());
			// 根据不同状态
			// message.type 0:表示用户注册时发送验证码
			if (message.getType() == 0) {
				// 注册时，判用户是否已注册，已注册用户无需发送此验证码
				if (message.getTelephone() != null
						&& !message.getTelephone().equals("")) {
					if (user != null) {
						// 已注册用户无需发送，直接返回结果
						this.status = 400;
						this.result.put("data", "");
						this.message = Config.SMS_USERS_HAVE_REGISTED;

						// 返回结果
						return getResult();
					}
				}
			} else if (message.getType() == 1) {
				// message.type 1:表示用户忘记密码时发送验证码
				// 如果用户输入手机号码未注册时，提示用户注册
				if (user == null) {
					this.status = 400;
					this.result.put("data", "");
					this.message = Config.SMS_USERS_NOT_REGISTED;
					// 返回结果
					return getResult();
				}
			} else if (message.getType() == 2) {
				// message.type 2:表示用户认证时发送验证码
				if (user != null) {
					this.status = 400;
					this.message = Config.SMS_USERS_HAVE_BIND;

					Map map = new HashMap(0);
					map.put("isRelogin", true);
					this.result.put("data", map);
					return getResult();
				}
			} else {
				// message.type 3:表示用户更换绑定手机号码
				// 判断用户是否输入重复手机号码
				if (user != null) {
					Users u = this.findUserInSession(session);
					if (message.getTelephone().equals(u.getTelephone())) {
						this.status = 400;
						this.message = Config.STRING_LOGING_TEL_NOT_REPEAT;
						return getResult();
					}

					u = this.userManger.findUserByTelephone(message
							.getTelephone());
					if (u != null) {
						this.status = 400;
						this.message = Config.SMS_USERS_HAVE_BIND;

						return getResult();
					}

					this.message = "";

				}
			}

			// 发送验证码
			Integer code = MsgUtil.send();

			if (code != 0) {
				this.status = 200;
				if (this.message.equals("")) {
					this.message = Config.SMS_HAVE_SEND_STRING;
				}

				session.setAttribute("code", code);
				System.out.println(session.getAttribute("code"));
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
	 * 用户注册
	 * @param verifyCode 手机短信验证码
	 * @param userInstance 用户实例
	 * @param bindingResult 校验结果
	 * @param session 会话
	 * @return 返回json格式数据
	 */
	public Map registUser(
			@RequestParam(value = "verifyCode", required = false) String verifyCode,
			@RequestParam(value = "inviteCode", required = false) String inviteCode,
			@Valid @ModelAttribute("user") Users userInstance,
			BindingResult bindingResult, HttpSession session) {
		// 初始化返回结果
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
					// 获取当前操作用户对象
					Users user = this.findUserInSession(session);
					// 如果用户已被持久化
					if (user == null) {
						// 根据手机号码获取用户
						user = this.userManger.findUserByTelephone(userInstance
								.getTelephone());
					}
					// 如果未找到用户记录，生成用户数据
					if (user == null) {
						user = userInstance;
						//设置注册时间
						user.setRegistDate(new Date());
						// 添加至事务操作
						user = this.userManger.addUser(user);
						
						 final Users u = user;
						// 判断用户是否保存成功
						if (user != null) {
							new Thread(){
								public void run()
								{
									try {
										// 发送用户注册成功短信
										MsgUtil SMS = new MsgUtil();
										SMS.setTelePhone(u.getTelephone());
										SMS.setMsgType(MessageType.NormalMessage);
										// 短信内容：感谢你注册金指投--专注中国成长型企业股权投融资
										SMS.setContent(Config.STRING_SMS_REGISTE);
										// 发送短信
										MsgUtil.send();

										// 发送注册成功邮件
										MailUtil mu = new MailUtil();
										try {
											mu.sendUserRegist(mu, u.getTelephone());
										} catch (Exception e) {
											e.printStackTrace();
										}

									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}.start();
							
							userRewardInvite(user, inviteCode, session);

							// 封装返回数据对象
							Map map = new HashMap();
							// 返回用户注册Id，
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
						if (user.getTelephone() != null
								&& user.getTelephone().equals(
										userInstance.getTelephone())) {
							session.setAttribute("userId", user.getUserId());
							this.status = 400;
							this.message = Config.STRING_LOGING_FAIL_HAS_REGISTED;
						}else{
							user.setTelephone(userInstance.getTelephone());
							user.setPassword(userInstance.getPassword());
							
							this.userManger.saveOrUpdateUser(user);
							this.status = 200;
							this.message = "";
						}
					}
				}
			} else {
				this.status = 400;
				this.message = Config.STRING_LOGING_CODE_NOT_GET;
			}
		}
		return getResult();
	}

	@RequestMapping("/completeUserInfo")
	@ResponseBody
	/***
	 * 微信登录用户认证信息完善校验
	 * @param verifyCode 手机短信验证码
	 * @param userInstance 用户实例
	 * @param bindingResult 校验结果
	 * @param session 会话
	 * @return 返回json格式数据
	 */
	public Map completeUserInfo(
			@RequestParam(value = "verifyCode", required = false) String verifyCode,
			@Valid @ModelAttribute("user") Users userInstance,
			BindingResult bindingResult, HttpSession session) {
		// 初始化返回结果
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
					// 获取当前操作用户对象
					Users user = this.findUserInSession(session);
					// 如果用户已被持久化
					if (user == null) {
						// 根据手机号码获取用户
						user = this.userManger.findUserByTelephone(userInstance
								.getTelephone());
					}
					// 如果未找到用户记录，生成用户数据
					if (user != null) {
						// // 发送用户注册成功短信
						// MsgUtil SMS = new MsgUtil();
						// SMS.setTelePhone(user.getTelephone());
						// SMS.setMsgType(MessageType.NormalMessage);
						// // 短信内容：感谢你注册金指投--专注中国成长型企业股权投融资
						// SMS.setContent(Config.STRING_SMS_REGISTE);
						// // 发送短信
						// MsgUtil.send();

						user.setPassword(userInstance.getPassword());
						user.setTelephone(userInstance.getTelephone());
						user.setPlatform(userInstance.getPlatform());

						// 保存信息
						this.userManger.saveOrUpdateUser(user);

						// 封装返回数据对象
						Map map = new HashMap();
						// 返回用户注册Id，
						map.put("userId", user.getUserId());
						map.put("telephone", user.getTelephone());
						map.put("platform", user.getPlatform());

						// 返回数据
						this.status = 200;
						this.result.put("data", map);
						this.message = Config.STRING_WECHAT_COMPLETE_SUCCESS;

						session.setAttribute("userId", user.getUserId());
					} else {
						this.status = 400;
						this.message = Config.STRING_LOGING_TIP;
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
					user.setRegId(userInstance.getRegId());
					user.setPlatform(userInstance.getPlatform());
					user.setLastLoginDate(new Date());

					if (userInstance.getRegId() != null
							&& !userInstance.getRegId().equals("")) {
						user.setRegId(userInstance.getRegId());
					}

					// 保存更新
					this.userManger.saveOrUpdateUser(user);

					// 返回结果
					Map map = new HashMap();
					map.put("userId", user.getUserId());
					if (user.getExtUserId() != null) {
						map.put("extUserId", user.getExtUserId());
					} else {
						map.put("extUserId", "");
					}

					Authentic authentic = this.authenticManager
							.findAuthenticByUserId(user.getUserId());
					if (authentic != null) {
						map.put("identityType", authentic.getIdentiytype());
					} else {
						Identiytype type = new Identiytype();
						short index = -1;
						type.setIdentiyTypeId(index);
						type.setName("无身份");
						map.put("identityType", type);
					}

					this.status = 200;
					this.result.put("data", map);
					this.message = Config.STRING_LOGING_SUCCESS;

					session.setAttribute("userId", user.getUserId());
//					session.setAttribute("userId", null);

					// 金条奖励
					checkUserLoginRecord(user, session);
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
	 * @param verifyCode 手机短信验证码
	 * @param userInstance 用户实例
	 * @param bindingResult 校验结果
	 * @param session 会话
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
				System.out.println("获取到验证码:" + session.getAttribute("code"));
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
				// 返回结果
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
				// 更新用户信息
				this.userManger.saveOrUpdateUser(user);
				// 返回数据封装
				// 封装返回数据
				Map map = new HashMap();
				map.put("userId", user.getUserId());
				if (user.getExtUserId() != null) {
					map.put("extUserId", user.getExtUserId());
				} else {
					map.put("extUserId", "");
				}

				Authentic authentic = this.authenticManager
						.findAuthenticByUserId(user.getUserId());
				if (authentic != null) {
					map.put("identityType", authentic.getIdentiytype());
				} else {
					Identiytype type = new Identiytype();
					short index = -1;
					type.setIdentiyTypeId(index);
					type.setName("无身份");
					map.put("identityType", type);
				}

				this.result.put("data", map);
				// 将用户id加入到session
				session.setAttribute("userId", user.getUserId());
				this.status = 200;
				this.message = Config.STRING_PASSWORD_RESET_SUCCESS;
			}
		}
		return getResult();
	}

	@RequestMapping("/wechatLoginUser")
	@ResponseBody
	/***
	 * 微信登录
	 * @param wechatID 微信识别码
	 * @param platform 设备类型
	 * @param session 会话
	 * @return
	 */
	public Map wechatLoginUser(
			@RequestParam(value = "wechatID", required = false) String wechatID,
			@RequestParam(value = "platform", required = false) String platform,
			@RequestParam(value = "regId", required = false) String regId,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");

		if (wechatID == null || wechatID.equals("")) {
			this.status = 400;
			this.message = Config.STRING_LOGING_WECHATID_NOT_NULL;
			return getResult();
		}

		short plat =0 ;
		if(Integer.parseInt(platform)!=0)
		{
			plat = 1;
		}
		// 获取用户
		Users user = this.userManger.findUserByWechatId(wechatID);

		if (user != null) {
			// 更新用户登录信息
			user.setPlatform(plat);
			user.setWechatId(wechatID);
			user.setRegId(regId);
			user.setLastLoginDate(new Date());

			// 更新用户登录信息
			this.userManger.saveOrUpdateUser(user);

			// 封装返回数据
			Map map = new HashMap();
			map.put("userId", user.getUserId());
			if (user.getExtUserId() != null) {
				map.put("extUserId", user.getExtUserId());
			} else {
				map.put("extUserId", "");
			}

			Authentic authentic = this.authenticManager
					.findAuthenticByUserId(user.getUserId());
			if (authentic != null) {
				map.put("identityType", authentic.getIdentiytype());
			} else {
				Identiytype type = new Identiytype();
				short index = -1;
				type.setIdentiyTypeId(index);
				type.setName("无身份");
				map.put("identityType", type);
			}

			this.status = 200;
			session.setAttribute("userId", user.getUserId());
			this.result.put("data", map);
			this.message = Config.STRING_LOGING_SUCCESS;
		} else {
			// 新用户
			user = new Users();
			user.setRegId(regId);
			user.setWechatId(wechatID);
			user.setPlatform(plat);
			user.setLastLoginDate(new Date());
			this.userManger.addUser(user);

			if (user != null) {
				// 封装返回数值
				Map map = new HashMap();
				map.put("userId", user.getUserId());
				if (user.getExtUserId() != null) {
					map.put("extUserId", user.getExtUserId());
				} else {
					map.put("extUserId", "");
				}

				Identiytype type = new Identiytype();
				short index = -1;
				type.setIdentiyTypeId(index);
				type.setName("无身份");
				map.put("identityType", type);

				this.result.put("data", map);
				session.setAttribute("userId", user.getUserId());

				this.status = 200;
				this.message = Config.STRING_LOGING_WECHAT_SUCCESS;
			} else {
				this.status = 400;
				this.message = Config.STRING_LOGING_WECHAT_FAIL;
			}
		}

		// 金条奖励
		checkUserLoginRecord(user, session);
		return getResult();
	}

	@RequestMapping("/noLogoInfo")
	@ResponseBody
	/***
	 * 用户未登录
	 * @param mm
	 * @return
	 */
	public Map noLogoInfo(
			@RequestParam(value="flag")boolean flag,
			ModelMap mm) {
		this.result = new HashMap();
		this.status = 401;
		this.message = Config.STRING_LOGING_TIP;
		if(flag)
		{
			this.result.put("data", new ArrayList());
		}else{
			this.result.put("data", "");
		}
		return getResult();
	}

	@RequestMapping("/signupUser")
	@ResponseBody
	/***
	 * 用户注销登录
	 * @param mm
	 * @param session
	 * @return
	 */
	public Map signupUser(ModelMap mm, HttpSession session) {
		this.result = new HashMap();
		this.status = 200;
		session.setAttribute("userId", null);

		this.message = Config.STRING_LOGING_OUT;
		this.result.put("data", "");
		return getResult();
	}

	@RequestMapping("/isLoginUser")
	@ResponseBody
	/***
	 * 检查用户是否已登录
	 * @param mm
	 * @param session
	 * @return
	 */
	public Map isLoginUser(ModelMap mm, HttpSession session) {
		this.result = new HashMap();

		this.status = 200;
		this.result.put("data", "");
		this.message = Config.STRING_LOGING_STATUS_ONLINE;

		// 检测用户是否已登录
		System.out.println("获取到Session :UserId："
				+ session.getAttribute("userId"));
		if (session.getAttribute("userId") == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_STATUS_OFFLINE;
		} else {
			Users user = this.findUserInSession(session);
			Authentic authentic = this.authenticManager
					.findAuthenticByUserId(user.getUserId());
			Map map = new HashMap();
			if (authentic != null) {
				map.put("identityType", authentic.getIdentiytype());
			} else {
				Identiytype type = new Identiytype();
				short index = -1;
				type.setIdentiyTypeId(index);
				type.setName("无身份");
				map.put("identityType", type);
			}

			this.result.put("data", map);
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

		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_STATUS_OFFLINE;
		} else {
			// 保存图片
			String fileName = String.format(
					Config.STRING_USER_HEADER_PICTURE_FORMAT, new Date().getTime());
			String result = FileUtil.savePicture(file, fileName,
					"upload/headerImages/");
			if (!result.equals("")) {
				fileName = Config.STRING_SYSTEM_ADDRESS
						+ "upload/headerImages/" + result;
				user.setHeadSculpture(fileName);

				// 更新信息
				this.userManger.saveOrUpdateUser(user);
				// 返回信息
				this.status = 200;
				this.result.put("data", fileName);
				this.message = Config.STRING_USER_HEADER_PICTURE_UPDATE_SUCCESS;
			} else {
				this.status = 400;
				this.result.put("data", "");
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

		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_STATUS_OFFLINE;
		} else {

			// 更新信息
			Object[] authentics = user.getAuthentics().toArray();

			Authentic authentic = null;
			for (int i = 0; i < authentics.length; i++) {
				authentic = (Authentic) authentics[i];
				authentic.setCompanyName(name);
			}
			this.userManger.saveOrUpdateUser(user);
			// 返回信息
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

		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_STATUS_OFFLINE;
		} else {

			// 更新信息
			Object[] authentics = user.getAuthentics().toArray();

			Authentic authentic = null;
			for (int i = 0; i < authentics.length; i++) {
				authentic = (Authentic) authentics[i];
				authentic.setPosition(position);
			}
			this.userManger.saveOrUpdateUser(user);
			// 返回信息
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

		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_STATUS_OFFLINE;
		} else {

			// 更新信息
			Object[] authentics = user.getAuthentics().toArray();

			Authentic authentic = null;
			City city = this.authenticManager.findCityByCityId(cityId);
			for (int i = 0; i < authentics.length; i++) {
				authentic = (Authentic) authentics[i];
				authentic.setCity(city);
			}
			this.userManger.saveOrUpdateUser(user);
			// 返回信息
			this.status = 200;
			this.message = Config.STRING_USER_ADDRESS_UPDATE_SUCCESS;
		}

		return getResult();
	}
	@RequestMapping("/requestUpdateUserInfoAction")
	@ResponseBody
	/***
	 * 更新地址
	 * @param cityId 城市id
	 * @param session
	 * @return
	 */
	public Map requestUpdateUserInfoAction(
			@RequestParam(value = "userIntroduce", required = false) String userIntroduce,
			@RequestParam(value = "companyIntroduce", required = false) String companyIntroduce,
			@RequestParam(value = "areas", required = false) String areas,
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
			
			// 更新信息
			Object[] authentics = user.getAuthentics().toArray();
			
			Authentic authentic = null;
			for (int i = 0; i < authentics.length; i++) {
				authentic = (Authentic) authentics[i];
				if(userIntroduce!=null && !userIntroduce.equals(""))
				{
					authentic.setIntroduce(userIntroduce);
				}
				
				if(companyIntroduce!=null && !companyIntroduce.equals(""))
				{
					authentic.setCompanyIntroduce(companyIntroduce);
				}
				
				if(areas!=null && !areas.equals(""))
				{
					authentic.setIndustoryArea(areas);
				}
			}
			this.userManger.saveOrUpdateUser(user);
			// 返回信息
			this.status = 200;
			this.message = Config.STRING_USER_INFO_UPDATE_SUCCESS;
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
	public Map requestModifyPassword(
			@RequestParam(value = "passwordOld", required = false) String passwordOld,
			@RequestParam(value = "passwordNew", required = false) String passwordNew,
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

			// 旧密码比对
			if (user.getPassword().equals(passwordOld)) {
				// 更新信息
				user.setPassword(passwordNew);
				this.userManger.saveOrUpdateUser(user);

				// 返回信息
				this.status = 200;
				this.message = Config.STRING_USER_PASSWORD_UPDATE_SUCCESS;
			} else {
				this.status = 400;
				this.message = Config.STRING_USER_PASSWORD_COMPARE_FAIL;
			}
		}

		return getResult();
	}

	@RequestMapping("/requestChangeBindTelephone")
	@ResponseBody
	/***
	 * 重新绑定手机号码
	 * @param telephone 手机号码
	 * @param code 短信验证码
	 * @param session
	 * @return
	 */
	public Map requestChangeBindTelephone(
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "telephone", required = false) String telephone,
			@RequestParam(value = "identityCardNo", required = false) String identityCardNo,
			@RequestParam(value = "code", required = false) int code,
			HttpSession session) {
		this.result = new HashMap();

		this.status = 200;
		this.result.put("data", "");
		this.message = Config.STRING_LOGING_STATUS_ONLINE;

		// 检验验证码
		if (session.getAttribute("code") != null) {
			// 比对验证码
			int codeSession = (int) session.getAttribute("code");
			if (codeSession == code) {
				// 获取用户
				Users user = this.findUserInSession(session);

				if (user == null) {
					this.status = 400;
					this.message = Config.STRING_LOGING_STATUS_OFFLINE;
				} else {
					if (telephone != null) {
						Users u = this.userManger
								.findUserByTelephone(telephone);
						if (u == null) {
							// 设置手机号码
							user.setTelephone(telephone);
							// 重置密码
							user.setPassword(password);
							// 如果身份证不为空
							Object[] objs = user.getAuthentics().toArray();
							if (objs != null && objs.length > 0) {
								for (Object obj : objs) {
									Authentic authentic = (Authentic) obj;
									authentic.setIdentiyCarNo(identityCardNo);
								}
							}
							// 保存信息
							this.userManger.saveOrUpdateUser(user);

							// 返回信息
							this.status = 200;
							this.message = Config.STRING_USER_TELEPHONE_UPDATE_SUCCESS;
						} else {
							// 返回信息
							this.status = 400;
							this.message = Config.STRING_USER_TELEPHONE_EQUAL_FAIL;
						}

					} else {
						// 返回信息
						this.status = 400;
						this.message = Config.STRING_LOGING_TEL_NOT_NULL;
					}

				}
			} else {
				this.status = 400;
				this.message = Config.STRING_LOGING_CODE_ERROR;
			}
		} else {
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
	public Map requestMineCollection(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "type", required = false) short type,
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
			List list = null;
			if (type == 0) {
				list = this.userManger.findUserCollectionProjects(user, page);
			} else {
				list = this.investorManager.findUserCollectInvestor(user, page);
			}

			if (list != null && list.size() > 0) {
				// 返回信息
				this.status = 200;
				this.result.put("data", list);
			} else {
				this.status = 200;
				this.result.put("data", new ArrayList());
			}

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
	public Map requestMineAction(
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
			List list = null;
			list = this.userManger.findUserAttendActions(user, page);

			if (list != null && list.size()>0) {
				// 返回信息
				this.status = 200;
				this.result.put("data", list);
			} else {
				// 返回信息
				if(page!=0)
				{
					
					this.status = 201;
				}else{
					
					this.status = 200;
				}
				this.result.put("data", new ArrayList());
			}

			this.message = "";
		}

		return getResult();
	}

	@RequestMapping("/requestGoldTradeList")
	@ResponseBody
	/***
	 * 金条交易记录
	 * @param page 当前页
	 * @param session
	 * @return
	 */
	public Map requestGoldTradeList(
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
			List list = this.userManger.findRewardSystemByUser(user);
			if (list != null && list.size() > 0) {
				Rewardsystem system = (Rewardsystem) list.get(0);
				//
				// Integer userId =
				// this.userManger.findUserIdByRewardSystem(system);
				List result = this.userManger.findGoldTradList(system, page);

				if (result != null) {
					// 返回信息
					this.status = 200;
					this.result.put("data", result);
				} else {
					// 返回信息
					this.status = 200;
					this.result.put("data", new ArrayList());
				}
			}

			this.message = "";
		}

		return getResult();
	}

	private Map checkUserLoginRecord(Users user, HttpSession session) {
		Map map = new HashMap();

		// 获取列表
		List list = this.userManger.findRewardSystemByUser(user);
		if (list != null && list.size() > 0) {
			Rewardsystem system = (Rewardsystem) list.get(0);
			//
			Rewardtradetype type = new Rewardtradetype();
			type.setRewardTypeId(1);

			Map req = new HashMap();
			req.put("rewardsystem", system);
			req.put("rewardtradetype", type);
			boolean result = this.userManger.findTodayLoginReward(system
					.getRewardId());

			if (!result) {
				// 第一次登录
				Rewardtrade trade = new Rewardtrade();
				trade.setTradeDate(new Date());
				trade.setReaded(false);
				trade.setDesc("登录奖励");
				trade.setCount(6);
				trade.setRewardsystem(system);
				trade.setRewardtradetype(type);

				this.userManger.getRewardTradeDao().save(trade);

				system.setCount(system.getCount() + trade.getCount());
				this.userManger.getRewardSystemDao().saveOrUpdate(system);
			}

		}

		return map;
	}

	private Map userRewardInvite(Users user, String inviteCode,
			HttpSession session) {
		Map map = new HashMap();
		if (inviteCode != null && !inviteCode.equals("")) {
			// 根据邀请码获取用户
			List l = this.userManger.getSystemCodeDao().findByCode(inviteCode);
			if (l != null && l.size() > 0) {
				Systemcode systemCode = (Systemcode) l.get(0);
				if (systemCode != null) {
					Users u = systemCode.getUsers();
					// 获取列表
					List list = this.userManger.findRewardSystemByUser(u);
					if (list != null && list.size() > 0) {
						Rewardsystem system = (Rewardsystem) list.get(0);
						//
						Rewardtradetype type = new Rewardtradetype();
						type.setRewardTypeId(4);

						// 第一次登录
						Rewardtrade trade = new Rewardtrade();
						trade.setTradeDate(new Date());
						trade.setReaded(false);
						trade.setDesc("邀请奖励");
						trade.setCount(18);
						trade.setRewardsystem(system);
						trade.setRewardtradetype(type);

						this.userManger.getRewardTradeDao().save(trade);

						system.setCount(system.getCount() + trade.getCount());
						this.userManger.getRewardSystemDao().saveOrUpdate(
								system);
					}
				}
			}
		}

		rewardNewUser(user);
		return map;
	}
	
	

	public void rewardNewUser(Users user) {
		// 获取列表
		Rewardsystem system = this.rewardManger.findRewardByUser(user);
		if (system == null) {
			// 生成金条账户
			system = new Rewardsystem();
			system.setCount(18);
			system.setUsers(user);

			Rewardtrade trade = new Rewardtrade();
			Set set = new HashSet();

			Rewardtradetype type = new Rewardtradetype();
			type.setRewardTypeId(2);

			trade.setTradeDate(new Date());
			trade.setReaded(false);
			trade.setDesc("注册奖励");
			trade.setCount(18);
			trade.setRewardsystem(system);
			trade.setRewardtradetype(type);

			set.add(trade);
			system.setRewardtrades(set);

			this.userManger.getRewardSystemDao().save(system);
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
}
