package com.jinzht.mobile.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
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

import org.apache.commons.httpclient.HttpException;
import org.hibernate.type.IdentifierType;
import org.jdom.JDOMException;
import org.json.JSONException;
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

import com.jinzht.web.filter.MySessionContext;
import com.jinzht.tools.Config;
import com.jinzht.tools.DateUtils;
import com.jinzht.tools.FileUtil;
import com.jinzht.tools.MailUtil;
import com.jinzht.tools.MessageType;
import com.jinzht.tools.MsgUtil;
import com.jinzht.tools.Tools;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.Authenticstatus;
import com.jinzht.web.entity.BusinessJionRecord;
import com.jinzht.web.entity.BusinessOrder;
import com.jinzht.web.entity.BusinessPayStatus;
import com.jinzht.web.entity.BusinessPayType;
import com.jinzht.web.entity.BusinessSchool;
import com.jinzht.web.entity.BusniessJoin;
import com.jinzht.web.entity.City;
import com.jinzht.web.entity.Contenttype;
import com.jinzht.web.entity.Identiytype;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.Member;
import com.jinzht.web.entity.MessageBean;
import com.jinzht.web.entity.Project;
import com.jinzht.web.entity.ProjectHotSearch;
import com.jinzht.web.entity.ProjectSearchBean;
import com.jinzht.web.entity.Rewardsystem;
import com.jinzht.web.entity.Roadshow;
import com.jinzht.web.entity.Roadshowplan;
import com.jinzht.web.entity.Users;
import com.jinzht.web.entity.Weburlrecord;
import com.jinzht.web.hibernate.HibernateSessionFactory;
import com.jinzht.web.manager.AuthenticManager;
import com.jinzht.web.manager.CourseManager;
import com.jinzht.web.manager.ImManager;
import com.jinzht.web.manager.InvestorManager;
import com.jinzht.web.manager.OrderManager;
import com.jinzht.web.manager.ProjectManager;
import com.jinzht.web.manager.UserManager;
import com.jinzht.web.manager.WebManager;
import com.jinzht.web.test.User;
import com.message.Enity.Msg;
import com.message.Enity.MsgBean;
import com.message.Enity.MsgDetail;
import com.message.Enity.Newsbanner;
import com.message.Enity.Original;
import com.message.Enity.OriginalDetail;
import com.message.Enity.Originalbanner;
import com.message.manager.MainManager;
import com.message.manager.MessageMananger;

@Controller
public class PayController extends BaseController {

	@Autowired
	private OrderManager orderManager;
	@Autowired
	private CourseManager courseManager;
	@Autowired
	UserManager userManager;
	@Autowired
	private ImManager imManager;
	@Autowired
	private AuthenticManager authenticManager;

	@RequestMapping(value = "/requestConfirmBusinessOrder")
	@ResponseBody
	/***
	 * 璁㈠崟璇︽儏
	 * @param orderId
	 * @param session
	 * @return
	 */
	public Map requestConfirmBusinessOrder(
			@RequestParam(value = "sessionId", required = false) String sessionId,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "unionid", required = false) String openId,
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "telephone", required = false) String telephone,
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "type") Integer type, HttpSession session)
			throws JSONException, HttpException, IOException, JDOMException {
		this.result = new HashMap();
		if (sessionId != null) {
			session = MySessionContext.getSession(sessionId);
		}

		Users user = this.findUserInSession(session);
		if (user == null) {
			if (telephone != null) {
				if (session.getAttribute("code") != null && code != null) {
					String msgCode = session.getAttribute("code").toString();
					if (msgCode.equals(code)) {
						user = this.userManager.findUserByTelephone(telephone);
						if (user != null) {
							session.setAttribute("userId", user.getUserId());
							if (openId != null) {
								user.setUnionid(openId);
								this.userManager.getUserDao()
										.saveOrUpdate(user);
							}
						} else {
							user = new Users();
							user.setTelephone(telephone);
							user.setUnionid(openId);
							user.setName(name);

							Authentic authentic = new Authentic();
							Authenticstatus status = new Authenticstatus();
							status.setStatusId(6);

							authentic.setAuthenticstatus(status);

							short index = -1;
							Identiytype t = new Identiytype();
							t.setIdentiyTypeId(index);

							authentic.setIdentiytype(t);

							this.authenticManager.getAuthenticDao().save(
									authentic);

							Set set = new HashSet();
							set.add(authentic);
							user.setAuthentics(set);

							this.userManager.getUserDao().save(user);

							session.setAttribute("userId", user.getUserId());
							session.setAttribute("new", true);

							final Users u = user;
							final PayController self = this;

							new Thread() {
								public void run() {
									try {
										// 鍙戦�鐢ㄦ埛娉ㄥ唽鎴愬姛鐭俊
										// MsgUtil SMS = new MsgUtil();
										// SMS.setTelePhone(u.getTelephone());
										// SMS.setMsgType(MessageType.NormalMessage);
										// //
										// 鐭俊鍐呭锛氭劅璋綘娉ㄥ唽閲戞寚鎶�-涓撴敞涓浗鎴愰暱鍨嬩紒涓氳偂鏉冩姇铻嶈祫
										// SMS.setContent(Config.STRING_SMS_REGISTE);
										// // 鍙戦�鐭俊
										// MsgUtil.send();

										MailUtil mu = new MailUtil();
										try {
											mu.sendUserRegist(mu,
													u.getTelephone());
										} catch (Exception e) {
											e.printStackTrace();
										}

										if (u.getPassword() != null) {
											result = self.imManager
													.addUserToIM(
															Config.IM_USER_NAME
																	+ u.getUserId(),
															u.getPassword(), u
																	.getName());
										} else {
											result = self.imManager
													.addUserToIM(
															Config.IM_USER_NAME
																	+ u.getUserId(),
															u.getWechatId(), u
																	.getName());
										}

									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}.start();

						}

					} else {
						this.message = "验证码不正确";
						this.result.put("data", "");

						return getResult();
					}
				} else {
					this.message = "请先获取验证码";
					this.result.put("data", "");

					return getResult();
				}
			} else {
				if (code == null) {
					this.message = "请先获取验证码";
					this.result.put("data", "");

					return getResult();

				} else {
					this.message = "请先获取验证码";
					this.result.put("data", "");

					return getResult();

				}
			}
		}

		if (contentId != null && user != null) {
			BusinessSchool school = this.courseManager.getBuinessSchoolDao()
					.findById(contentId);
			if (school != null) {
				// 鍒涘缓璁㈠崟
				BusinessOrder order = new BusinessOrder();
				order.setBusinessSchool(school);
				order.setUsers(user);
				order.setTotalFee(Double.parseDouble(school.getBpriceNow()));

				order.setOrderTime(new Date());
				order.setOrderCode("" + new Date().getTime() + user.getUserId());

				// 璁㈠崟鏀粯鐘跺喌
				BusinessPayStatus status = new BusinessPayStatus();
				status.setStatusId(1);
				order.setBusinessPayStatus(status);

				// 鏀粯绫诲瀷
				BusinessPayType payType = new BusinessPayType();
				if (type == 2) {
					payType.setTypeId(1);
				} else {
					payType.setTypeId(2);
				}

				order.setBusinessPayType(payType);

				// 淇濆瓨璁㈠崟
				this.courseManager.getBusinessOrderDao().save(order);

				order.setUsers(null);
				order.setBusinessSchool(null);
				order.setBusinessPayStatus(null);
				order.setBusinessPayType(null);

				// 鏇存柊璇剧▼
				Integer count = Integer.parseInt(school.getBextr());
				count++;

				school.setBextr("" + count);

				this.status = 200;
				this.result.put("data", order);
				this.message = "信息确认成功!";

				return getResult();
			}
		}

		this.status = 400;
		this.result.put("data", "");
		this.message = "信息确认失败!";

		return getResult();
	}

	@RequestMapping(value = "/requestCompletePayBusinessOrder")
	@ResponseBody
	/***
	 * 璁㈠崟璇︽儏
	 * @param orderId
	 * @param session
	 * @return
	 */
	public Map requestCompletePayBusinessOrder(
			@RequestParam(value = "sessionId", required = false) String sessionId,
			@RequestParam(value = "orderId", required = false) Integer contentId,
			HttpSession session) throws JSONException, HttpException,
			IOException, JDOMException {
		this.result = new HashMap();
		if (sessionId != null) {
			session = MySessionContext.getSession(sessionId);

		}

		Users user = this.findUserInSession(session);

		if (user != null) {
			BusinessOrder order = this.courseManager.getBusinessOrderDao()
					.findById(contentId);
			// 璁㈠崟鏀粯鐘跺喌
			order.getBusinessPayStatus().setStatusId(2);

			this.courseManager.getBusinessOrderDao().saveOrUpdate(order);

			BusniessJoin join = new BusniessJoin();
			join.setUsers(user);
			join.setCattendtime(new Date());
			join.setBusinessSchool(order.getBusinessSchool());

			this.courseManager.getBusniessJoinDao().save(join);

			String pass = Tools.getRandomString(6);
			
			final PayController self = this;
			final Users u = user;
			final BusinessOrder o = order;
			final String s = sessionId;
			final String p = pass;
			final boolean flag;
			if (session.getAttribute("new") == null) {
				flag = false;
			} else {
				flag = (boolean) session.getAttribute("new");
				
				// 发送短信通知
				String password = Tools.generatePassword(pass, user.getTelephone());
				user.setPassword(password);

				this.userManager.getUserDao().saveOrUpdate(user);
			}
			
			new Thread() {
				public void run() {
					if (s != null) {
						// 重新生成密码
						// // 发送用户注册成功短信
						MsgUtil SMS = new MsgUtil();
						SMS.setTelePhone(u.getTelephone());
						SMS.setMsgType(MessageType.NormalMessage);
						// 短信内容：感谢你注册金指投--专注中国成长型企业股权投融资
						String content;
						if (flag) {
							content = String.format(
									Config.STRING_SMS_ORDER_CONFIRM_NEW, o
											.getBusinessSchool().getBname(), u
											.getTelephone(), p);
						} else {
							content = String.format(
									Config.STRING_SMS_ORDER_CONFIRM_OLD, o
											.getBusinessSchool().getBname());

						}
						SMS.setContent(content);

						// 发送短信
						MsgUtil.send();
					}

				};
			}.start();

			this.status = 200;
			this.result.put("data", "");
			this.message = "";

			return getResult();
		}

		this.status = 400;
		this.result.put("data", "");
		this.message = "信息确认失败！";

		return getResult();
	}

	@RequestMapping(value = "/requestOrderSign")
	@ResponseBody
	/***
	 * 
	 * @param orderId
	 * @param session
	 * @return
	 */
	public Map requestOrderSign(
			@RequestParam(value = "orderId", required = false) Integer orderId,
			@RequestParam(value = "unionid", required = false) String unionid,
			@RequestParam(value = "openId", required = false) String openId,
			@RequestParam(value = "type") Integer type, HttpSession session)
			throws JSONException, HttpException, IOException, JDOMException {
		this.result = new HashMap();
		if (orderId != null) {
			BusinessOrder order = this.courseManager.getBusinessOrderDao()
					.findById(orderId);
			// // 鍒涘缓璁㈠崟
			Map result = this.orderManager.orderPayDescription("24000", type,
					"1", openId, order);

			if (result == null || result.equals("")) {
				this.status = 400;
				this.result.put("data", "");
				this.message = "签名失败!";
			} else {
				this.status = 200;
				this.result.put("data", result);
				this.message = "签名成功!";
			}

		} else {
			this.status = 400;
			this.result.put("data", "");
			this.message = "数据获取失败!";

		}

		return getResult();
	}

	@RequestMapping(value = "/notifywxurl")
	public void notifywxurl(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		InputStream in = request.getInputStream();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = in.read(buffer)) != -1) {
			out.write(buffer, 0, len);
		}
		out.close();
		in.close();
		String msgxml = new String(out.toByteArray(), "utf-8");// xml数据
		System.out.println(msgxml);

	}

	@RequestMapping(value = "/notifyappurl")
	public void notifyappurl(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		InputStream in = request.getInputStream();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = in.read(buffer)) != -1) {
			out.write(buffer, 0, len);
		}
		out.close();
		in.close();
		String msgxml = new String(out.toByteArray(), "utf-8");// xml数据
		System.out.println(msgxml);

	}

	/***
	 * 
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

	public static String setXml(String return_code, String return_msg) {
		return "<xml><return_code><![CDATA[" + return_code
				+ "]]></return_code><return_msg><![CDATA[" + return_msg
				+ "]]></return_msg></xml>";
	}

}
