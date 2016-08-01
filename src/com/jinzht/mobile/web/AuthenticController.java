package com.jinzht.mobile.web;

import java.io.File;
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
import org.hibernate.validator.internal.util.privilegedactions.GetAnnotationParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jinzht.tools.Config;
import com.jinzht.tools.FileUtil;
import com.jinzht.tools.MailUtil;
import com.jinzht.tools.MessageType;
import com.jinzht.tools.MsgUtil;
import com.jinzht.tools.Tools;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.AuthenticEntity;
import com.jinzht.web.entity.Authenticstatus;
import com.jinzht.web.entity.City;
import com.jinzht.web.entity.Identiytype;
import com.jinzht.web.entity.Industoryarea;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.MessageBean;
import com.jinzht.web.entity.Systemcode;
import com.jinzht.web.entity.Users;
import com.jinzht.web.hibernate.HibernateSessionFactory;
import com.jinzht.web.manager.AuthenticManager;
import com.jinzht.web.manager.UserManager;
import com.jinzht.web.test.User;

@Controller
public class AuthenticController extends BaseController {

	@Autowired
	private AuthenticManager authenticManager;
	@Autowired
	private UserManager userManager;

	@RequestMapping(value = "/updateIdentiyTypeUser")
	@ResponseBody
	/***
	 * 忘记密码
	 * @param userInstance
	 * @param bindingResult
	 * @param session
	 * @return
	 */
	public Map updateIdentiyTypeUser(
			@RequestParam(value = "ideniyType") short ideniyType,
			@RequestParam(value = "isWechatLogin") boolean isWechatLogin,
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");

		// 获取用户
		Users user = this.findUserInSession(session);

		// 身份类型
		if (ideniyType == 0) {
			this.status = 400;
			this.message = Config.STRING_AUTH_IDENTIY_TYPE_NOT_NULL;

			return getResult();
		}

		// 保存图片
		String fileName = String.format(
				Config.STRING_USER_HEADER_PICTURE_FORMAT, user.getUserId());
		String result = FileUtil.savePicture(file, fileName,
				"upload/headerImages/");
		if (!result.equals("")) {
			fileName = Config.STRING_SYSTEM_ADDRESS + "upload/headerImages/"
					+ result;
		} else {
			fileName = "";
		}

		if (user != null) {
			// 获取身份类型
			Identiytype identityType = this.authenticManager
					.findIdentityTypeById(ideniyType);

			Authenticstatus status = new Authenticstatus();
			status.setName("未认证");
			status.setStatusId(6);

			// 生成认证记录
			Authentic authentic = new Authentic();
			authentic.setIdentiytype(identityType);
			authentic.setUsers(user);
			authentic.setAuthenticstatus(status);

			// 保存
			this.authenticManager.saveAuthentic(authentic);

			// 更新用户登录信息
			Set authenticSet = new HashSet();
			authenticSet.add(authentic);

			user.setAuthentics(authenticSet);

			// 头像
			if (!fileName.equals("")) {
				user.setHeadSculpture(fileName);
			}

			// 更新用户登录信息
			this.userManager.saveOrUpdateUser(user);

			// 生成邀请码
			Systemcode systemCode = this.userManager.findSystemCodeByUser(user);
			if (systemCode == null) {
				String code = Tools.generateInviteCode(user.getUserId(),
						isWechatLogin);

				systemCode = new Systemcode();
				systemCode.setCode(code);
				systemCode.setUsers(user);

				// 保存邀请码
				this.userManager.saveSystemCode(systemCode);
			}
			// 封装返回数据
			Map map = new HashMap();
			map.put("inviteCode", systemCode.getCode());

			// 返回状态
			this.status = 200;
			this.result.put("data", map);
			this.message = Config.STRING_AUTH_IDENTIY_SUCCESS;
		} else {
			this.status = 400;
			this.message = Config.STRING_AUTH_IDENTIY_FAIL;
		}

		return getResult();
	}

	@RequestMapping("/getProtocolAuthentic")
	@ResponseBody
	/***
	 * 忘记密码
	 * @param userInstance
	 * @param bindingResult
	 * @param session
	 * @return
	 */
	public Map getProtocolAuthentic(HttpSession session) {
		this.result = new HashMap();
		this.status = 200;
		this.result.put("data", Config.STRING_AUTH_QUALIFICATION);

		return getResult();
	}


	@RequestMapping("/getProvinceListAuthentic")
	@ResponseBody
	/***
	 * 获取省份列表
	 * @param session
	 * @return
	 */
	public Map getProvinceListAuthentic(HttpSession session) {
		this.result = new HashMap();
		this.status = 200;
		this.message = "";

		List list = this.authenticManager.findAllProvinceList();
		this.result.put("data", list);

		return getResult();
	}

	@RequestMapping("/getCityListAuthentic")
	@ResponseBody
	/***
	 * 获取省份列表
	 * @param session
	 * @return
	 */
	public Map getCityListAuthentic(HttpSession session) {
		this.result = new HashMap();
		this.status = 200;
		this.message = "";

		List list = this.authenticManager.findAllCityList();
		this.result.put("data", list);

		return getResult();
	}

	@RequestMapping("/getIndustoryAreaListAuthentic")
	@ResponseBody
	/***
	 * 获取行业类型
	 * @param session
	 * @return
	 */
	public Map getIndustoryAreaListAuthentic(HttpSession session) {
		this.result = new HashMap();
		this.status = 200;
		this.message = "";

		List list = this.authenticManager.findAllIndustoryList();
		this.result.put("data", list);

		return getResult();
	}

	@RequestMapping("/getCityListByProvinceIdAuthentic")
	@ResponseBody
	/***
	 * 根据省份id获取城市
	 * @param session
	 * @return
	 */
	public Map getCityListByProvinceIdAuthentic(
			@RequestParam(value = "provinceId", required = false) Integer provinceId,
			HttpSession session) {
		this.result = new HashMap();
		this.status = 200;
		this.message = "";

		List list = this.authenticManager.findCitiesByProvinceId(provinceId);
		this.result.put("data", list);

		return getResult();
	}

	@RequestMapping("/requestAuthentic")
	@ResponseBody
	/***
	 * 获取省份列表
	 * @param session
	 * @return
	 */
	public Map requestAuthentic(@Valid AuthenticEntity entity,
			BindingResult bindingResult, HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");
		this.status = 200;
		if (bindingResult.hasErrors()) {
			this.status = 400;
			this.message = bindingResult.getFieldError().getDefaultMessage();
		} else {
			final Users user = this.findUserInSession(session);
			if (user != null) {
				// 获取身份类型
				Identiytype identiytype = null;
				// 获取城市地址
				City city = this.authenticManager.findCityByCityId(entity
						.getCityId());

				// 获取认证状态
				Object[] list = user.getAuthentics().toArray();
				Authenticstatus status = null;
				Authentic authentic = null;
				if (list != null && list.length > 0) {
					if (list.length > 1) {
						for (int i = 0; i < list.length; i++) {
							authentic = (Authentic) list[i];
							status = authentic.getAuthenticstatus();
							// 获取用户身份认证状态
							int authStatus = Config.STRING_AUTH_STATUS
									.get(status.getName());
							if (authStatus == 2 || authStatus == 0) {
								authentic.setIntroduce(entity.getIntroduce());
								authentic.setPosition(entity.getPosition());
								authentic.setBuinessLicenceNo(entity
										.getBuinessLicenceNo());
								authentic.setCompanyAddress(entity
										.getCompanyAddress());
								authentic.setCompanyIntroduce(entity
										.getCompanyIntroduce());
								authentic.setCompanyName(entity
										.getCompanyName());
								authentic.setIdentiyCarNo(entity
										.getIdentiyCarNo());
								authentic.setCity(city);
								authentic.setIndustoryArea(entity.getAreaId());
								// authentic.setIndustoryarea(industoryArea);
								// authentic.setIndustorytype(industoryType);
								authentic.setName(entity.getName());
								 authentic.setOptional(entity.getOptional());
								// authentic.setOptional(1);
							} else {
								this.status = 400;
								this.message = Config.STRING_AUTH_CONFIRM_FAIL;

								return getResult();
							}
						}
					} else {
						authentic = (Authentic) list[0];
						status = authentic.getAuthenticstatus();

						// 获取用户身份认证状态
						int authStatus = Config.STRING_AUTH_STATUS.get(status
								.getName());
						if (authStatus != 3) {
							authentic.setIntroduce(entity.getIntroduce());
							authentic.setPosition(entity.getPosition());
							authentic.setBuinessLicenceNo(entity
									.getBuinessLicenceNo());
							authentic.setCompanyAddress(entity
									.getCompanyAddress());
							authentic.setCompanyIntroduce(entity
									.getCompanyIntroduce());
							authentic.setCompanyName(entity.getCompanyName());
							authentic.setIdentiyCarNo(entity.getIdentiyCarNo());
							authentic.setCity(city);
							// authentic.setIndustoryarea(industoryArea);
							// authentic.setIndustorytype(industoryType);
							authentic.setName(entity.getName());
							authentic.setIndustoryArea(entity.getAreaId());
							 authentic.setOptional(entity.getOptional());

						} else {
							this.status = 400;
							this.message = Config.STRING_AUTH_HAS;

							return getResult();
						}
					}
				} else {
					authentic = new Authentic();
					authentic.setUsers(user);
					authentic.setIntroduce(entity.getIntroduce());
					authentic.setPosition(entity.getPosition());
					authentic.setBuinessLicenceNo(entity.getBuinessLicenceNo());
					authentic.setCompanyAddress(entity.getCompanyAddress());
					authentic.setCompanyIntroduce(entity.getCompanyIntroduce());
					authentic.setCompanyName(entity.getCompanyName());
					authentic.setIdentiyCarNo(entity.getIdentiyCarNo());
					authentic.setCity(city);
					authentic.setIndustoryArea(entity.getAreaId());
					authentic.setName(entity.getName());
					authentic.setOptional(entity.getOptional());
				}

				// 保存图片
				// 身份证A面
				// 保存图片
				String fileName ="";
				String result="";
				if (entity.getIdentiyCarA() != null) {
					fileName = String.format(
							Config.STRING_USER_IDENTITY_PICTUREA_FORMAT,
							user.getUserId());
					result = FileUtil.savePicture(
							entity.getIdentiyCarA(), fileName,
							"upload/identityImages/");
					if (!result.equals("")) {
						fileName = Config.STRING_SYSTEM_ADDRESS
								+ "upload/identityImages/" + result;
					} else {
						fileName = "";
					}
					authentic.setIdentiyCarA(fileName);
				}

				// 身份证B面
				// 保存图片
				if (entity.getIdentiyCarB() != null) {
					fileName = String.format(
							Config.STRING_USER_IDENTITY_PICTUREB_FORMAT,
							user.getUserId());
					result = FileUtil.savePicture(entity.getIdentiyCarB(),
							fileName, "upload/identityImages/");
					if (!result.equals("")) {
						fileName = Config.STRING_SYSTEM_ADDRESS
								+ "upload/identityImages/" + result;
					} else {
						fileName = "";
					}

					authentic.setIdentiyCarB(fileName);
				}

				// 营业执照
				// 保存图片
				if (entity.getBuinessLicence() != null) {
					fileName = String.format(
							Config.STRING_USER__IDENTITY_BUINESS_FORMAT,
							user.getUserId());
					result = FileUtil.savePicture(entity.getBuinessLicence(),
							fileName, "upload/identityImages/");
					if (!result.equals("")) {
						fileName = Config.STRING_SYSTEM_ADDRESS
								+ "upload/identityImages/" + result;
					} else {
						fileName = "";
					}

					authentic.setBuinessLicence(fileName);
				}

				// 保存更新用户的身份认证信息
				status = new Authenticstatus();
				status.setStatusId(7);
				authentic.setAuthenticstatus(status);
				user.getAuthentics().add(authentic);
				user.setName(entity.getName());
				
				this.userManager.saveOrUpdateUser(user);
				this.message = Config.STRING_AUTH_SUBMMIT_SUCCESS;
			} else {
				this.message = Config.STRING_LOGING_TIP;
			}
			
			//发送注册成功邮件
			new Thread(){
				public void run()
				{
					MailUtil mu = new MailUtil();
					try {
						mu.sendUserAuthentic(mu,user.getTelephone());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();
		}

		return getResult();
	}

	@RequestMapping("/authenticInfoUser")
	@ResponseBody
	/***
	 * 身份认证信息
	 * @param session
	 * @return
	 */
	public Map authenticInfoUser(HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");
		this.status = 200;

		Users user = this.findUserInSession(session);
		if (user != null) {
			this.status = 200;
			
			Map map = new HashMap();
			map.put("authentics", user.getAuthentics());
			map.put("headSculpture", user.getHeadSculpture());
			map.put("userId", user.getUserId());
			if (user.getExtUserId() != null) {
				map.put("extUserId", user.getExtUserId());
			} else {
				map.put("extUserId", "");
			}
			map.put("telephone", user.getTelephone());
			
			
			this.result.put("data", map);
			this.message = "";
		} else {
			this.message = Config.STRING_LOGING_TIP;
		}

		return getResult();
	}

	@RequestMapping("/checkAuthenticStatusUser")
	@ResponseBody
	/***
	 * 检查身份认证状态
	 * @param session
	 * @return
	 */
	public Map checkAuthenticStatusUser(HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");
		this.status = 200;

		Users user = this.findUserInSession(session);
		if (user != null) {
			Object[] list = user.getAuthentics().toArray();

			Map map = new HashMap();
			map.put("status", "0");
			map.put("name", "未认证");

			// 获取认证状态
			Authenticstatus status = null;
			Authentic authentic = null;
			if (list != null && list.length > 0) {
				if (list.length > 1) {
					status = new Authenticstatus();
					status.setStatusId(3);
					status.setName("认证通过");
				} else {
					authentic = (Authentic) list[0];
					status = authentic.getAuthenticstatus();
					status.setStatusId(Config.STRING_AUTH_STATUS.get(status
							.getName()));
				}
			}

			this.status = 200;
			this.result.put("data", status);
			this.message = "";
		} else {
			this.message = Config.STRING_LOGING_TIP;
		}

		return getResult();
	}
	@RequestMapping("/requestAuthenticQuick")
	@ResponseBody
	/***
	 * 催一催
	 * @param session
	 * @return
	 */
	public Map requestAuthenticQuick(
			@RequestParam(value = "authId", required = false) Integer authId,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");
		this.status = 200;
		
		Users user = this.findUserInSession(session);
		if (user != null) {
			Authentic  authentic = this.authenticManager.findAuthenticById(authId);
			if(authentic!= null)
			{
				//发送催一催
				MailUtil mu = new MailUtil();
				try {
					mu.sendUserAuthenticQuick(mu,user.getTelephone(),authentic.getName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.status = 200;
				this.message = Config.STRING_AUTH_SPEED_SUCCESS;
			}else{
				this.status = 400;
				this.message = "";
				this.message = Config.STRING_AUTH_IDENTIY_FAIL;
			}
			
		} else {
			this.message = Config.STRING_LOGING_TIP;
		}
		
		return getResult();
	}

	/***
	 * 从当前session获取用户对象
	 * 
	 * @param session
	 * @return
	 */
	private Users findUserInSession(
			HttpSession session) {
		Users user = null;
		if (session.getAttribute("userId") != null) {
			Integer userId = (Integer) session.getAttribute("userId");
			user = this.userManager.findUserById(userId);
		}
		return user;
	}

}
