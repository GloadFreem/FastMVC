package com.jinzht.mobile.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jinzht.tools.Config;
import com.jinzht.tools.FileUtil;
import com.jinzht.web.entity.Action;
import com.jinzht.web.entity.Actioncomment;
import com.jinzht.web.entity.Actionprise;
import com.jinzht.web.entity.Attention;
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.Comment;
import com.jinzht.web.entity.Contentprise;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.Publiccontent;
import com.jinzht.web.entity.Share;
import com.jinzht.web.entity.Sharetype;
import com.jinzht.web.entity.Users;
import com.jinzht.web.hibernate.HibernateSessionFactory;
import com.jinzht.web.manager.ActionManager;
import com.jinzht.web.manager.SystemManager;
import com.jinzht.web.manager.UserManager;

@Controller
public class ActionController extends BaseController {

	@Autowired
	private ActionManager actionManager;
	@Autowired
	private UserManager userManager;
	@Autowired
	private SystemManager systemManager;

	@RequestMapping(value = "/requestActionList")
	@ResponseBody
	/***
	 * 获取活动列表
	 * @param page 当前页
	 * @param session
	 * @return
	 */
	public Map requestActionList(
			@RequestParam(value = "page", required = true) Integer page,
			HttpSession session) {
		this.result = new HashMap();

		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {
			List list = this.actionManager.findActionByCursor(page, user);
			if (list != null && list.size() > 0) {
				this.status = 200;
				this.result.put("data", list);
			} else {
				this.status = 201;
				this.result.put("data", new ArrayList());
				this.message = Config.STRING_FEELING_NO_DATA;
			}
		}

		return getResult();
	}

	@RequestMapping(value = "/requestAttendAction")
	@ResponseBody
	/***
	 * 获取圈子列表
	 * @param page 当前页
	 * @param session
	 * @return
	 */
	public Map requestAttendAction(
			@RequestParam(value = "contentId") Integer contentId,
			@RequestParam(value = "content") String content, HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");
		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {
			// 查看当前操作状态，1:点赞,2:取消点赞
			Action action = this.actionManager.findActionById(contentId);
			// 关联用户
			Attention attention = this.actionManager
					.findAttentionByActionIdAndUser(action, user);
			if (attention == null) {
				attention = new Attention();
				attention.setUsers(user);
				attention.setAction(action);
				attention.setContent(content);
				attention.setEnrollDate(new Date());
				// 保存圈子内容
				this.actionManager.addAttention(attention);

				attention.setUsers(null);
				attention.setAction(null);
				// 封装返回结果
				this.status = 200;
				this.result.put("data", attention);
				this.message = Config.STRING_ACTION_ADD_SUCCESS;
			} else {
				this.status = 400;
				this.message = Config.STRING_ACTION_ADD_REPEAT;
			}

		}
		return getResult();
	}

	@RequestMapping(value = "/requestPriseAction")
	@ResponseBody
	/***
	 * 点赞/取消点赞
	 * @param contentId
	 * @param bindingResult
	 * @param session
	 * @return
	 */
	public Map requestPriseAction(
			@RequestParam(value = "contentId") Integer contentId,
			@RequestParam(value = "flag") short flag, HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");

		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {
			// 查看当前操作状态，1:点赞,2:取消点赞
			Action action = this.actionManager.findActionById(contentId);

			if (flag == 1) {
				action.getActionprises().add(new Actionprise(user, action));

				// 保存圈子内容
				this.actionManager.saveOrUpdate(action);
				flag = 2;
			} else {
				Object[] list = action.getActionprises().toArray();

				Set set = new HashSet();
				for (int i = 0; i < list.length; i++) {
					Actionprise actionPrise = (Actionprise) list[i];
					System.out.println("用户:"
							+ actionPrise.getUsers().getUserId()
							+ user.getUserId());
					int userId1 = actionPrise.getUsers().getUserId();
					int userId2 = user.getUserId();

					if (userId1 == userId2) {
						this.actionManager.cancelPrise(actionPrise);
					}

				}

				flag = 1;
			}

			// 封装返回结果
			this.status = 200;

			Map map = new HashMap();
			map.put("flag", flag);
			map.put("name", user.getName());

			this.result.put("data", map);
			if (flag == 2) {
				this.message = Config.STRING_FEELING_PRISE_ADD_SUCCESS;
			} else {
				this.message = Config.STRING_FEELING_PRISE_EREASE_SUCCESS;
			}
		}

		return getResult();
	}

	@RequestMapping(value = "/requestCommentAction")
	@ResponseBody
	/***
	 * 点赞/取消点赞
	 * @param contentId
	 * @param bindingResult
	 * @param session
	 * @return
	 */
	public Map requestCommentAction(
			@RequestParam(value = "contentId") Integer contentId,
			@RequestParam(value = "content") String content,
			@RequestParam(value = "atUserId") Integer userId,
			@RequestParam(value = "flag") short flag, HttpSession session) {

		this.result = new HashMap();
		this.result.put("data", "");

		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {
			// 查看当前操作状态，1:评论,2:回复
			Action action = this.actionManager.findActionById(contentId);

			Actioncomment comment = new Actioncomment();
			comment.setContent(content);
			comment.setAction(action);
			comment.setUsersByUserId(user);
			if (flag != 1 && userId!= null) {
				Users atUser = this.userManager.findUserById(userId);
				comment.setUsersByAtUserId(atUser);
				comment.setAtUserName(atUser.getName());
			}
			// 保存回复
			action.getActioncomments().add(comment);

			this.actionManager.saveOrUpdate(action);

			// 处理返回值
			Users user1 = comment.getUsersByUserId();
//			if (user1.getAuthentics() != null) {
//				Object[] l = user.getAuthentics().toArray();
//				if (l.length > 0) {
//					Authentic authentic = (Authentic) l[0];
//					user1.setName(authentic.getName());
//				} else {
//					user1.setName("");
//				}
//
//			} else {
//				user1.setName("");
//			}
			user1.setAuthentics(null);
			user1.setUserstatus(null);
			user1.setTelephone(null);
			user1.setPassword(null);
			user1.setPlatform(null);
			user1.setLastLoginDate(null);
			comment.setUsersByUserId(user1);

			user1 = comment.getUsersByAtUserId();
			if (user1 != null) {
//				if (user.getAuthentics() != null) {
//					Object[] l = user.getAuthentics().toArray();
//					if (l.length > 0) {
//						Authentic authentic = (Authentic) l[0];
//						user1.setName(authentic.getName());
//					} else {
//						user1.setName("");
//					}
//
//				} else {
//					user1.setName("");
//				}
				user1.setAuthentics(null);
				user1.setUserstatus(null);
				user1.setTelephone(null);
				user1.setPassword(null);
				user1.setPlatform(null);
				user1.setLastLoginDate(null);
				comment.setUsersByAtUserId(user1);
			}

			// 封装返回结果
			this.status = 200;
			this.result.put("data", comment);
			if (flag != 1) {
				this.message = Config.STRING_FEELING_REPLY_SUCCESS;
			} else {
				this.message = Config.STRING_FEELING_COMMENT_SUCCESS;
			}
		}

		return getResult();
	}

	@RequestMapping(value = "/requestShareAction")
	@ResponseBody
	/***
	 * 活动分享
	 * @param contentId
	 * @param content
	 * @param session
	 * @return
	 */
	public Map requestShareAction(@RequestParam(value = "type") int type,
			@RequestParam(value = "contentId") Integer contentId,
			HttpSession session) {

		this.result = new HashMap();
		this.result.put("data", "");

		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {
			// 查看当前操作状态，1:评论,2:回复
			Action action = this.actionManager.findActionById(contentId);
			// 生成分享链接
			Share share = new Share();
			Sharetype shareType = new Sharetype();
			shareType.setShareTypeId(type);
			share.setSharetype(shareType);
			share.setShareDate(new Date());

			String url = ""; // 生成分享链接
			switch (type) {
			case 3:
				url = Config.STRING_SHARE_APP_URL;
				break;
			default:
				url = String.format("%s%d/%d", Config.STRING_SYSTEM_ADDRESS,
						type, contentId);
				break;
			}

			share.setUrl(url);

			// 保存分享记录
			this.systemManager.saveShareRecord(share);

			share.setShareId(null);
			share.setSharetype(null);
			share.setShareDate(null);
			// 封装返回结果
			this.status = 200;
			this.result.put("data", share);
			this.message = "";
		}

		return getResult();
	}

	@RequestMapping(value = "/")
	@ResponseBody
	/***
	 * 活动详情
	 * @param contentId
	 * @param session
	 * @return
	 */
	public Map requestDetailAction(
			@RequestParam(value = "contentId") Integer contentId,
			HttpSession session) {

		this.result = new HashMap();
		this.result.put("data", "");

		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {
			// 查看当前操作状态，1:评论,2:回复
			Action action = this.actionManager.findActionById(contentId);
			// 获取活动图片
			List list = this.actionManager.findImagesListByAction(action);
			Set set = new HashSet();
			for (int i = 0; i < list.size(); i++) {
				set.add(list.get(i));
			}
			action.setActionimages(set);
			// // 报名人数
			list = this.actionManager.findAttentionListByAction(action, 0);
			set = new HashSet();

			for (int i = 0; i < list.size(); i++) {
				Attention attention = (Attention) list.get(i);
				if (attention.getUsers().getUserId() == user.getUserId()) {
					action.setAttended(true);
				}
			}
			
			list = this.actionManager.findPriseListByAction(action);
			set = new HashSet();
			for (int j = 0; j < list.size(); j++) {
				Actionprise prise = (Actionprise) list.get(j);
				Users u = prise.getUsers();

				Object[] objs = u.getAuthentics().toArray();
				if (objs != null && objs.length > 0) {
					Authentic authentic = (Authentic) objs[0];
					u.setName(authentic.getName());
				} else {
					u.setName("匿名用户");
				}

				if (u.getUserId() == user.getUserId()) {
					action.setFlag(true);
				}

				set.add(u.getName());
			}
			action.setActionprises(null);
			action.setActionprises(set);

			// 封装返回结果
			this.status = 200;
			this.result.put("data", action);
			this.message = "";
		}

		return getResult();
	}

	@RequestMapping(value = "/requestPriseListAction")
	@ResponseBody
	/***
	 * 全部点赞列表
	 * @param contentId
	 * @param session
	 * @return
	 */
	public Map requestPriseListAction(
			@RequestParam(value = "contentId") Integer contentId,
			@RequestParam(value = "page") Integer page, HttpSession session) {

		this.result = new HashMap();
		this.result.put("data", "");

		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {
			// 查看当前操作状态，1:评论,2:回复
			Action action = this.actionManager.findActionById(contentId);

			// 评论
			List list = this.actionManager
					.findCommentListByAction(action, page);
			List l = new ArrayList();
			Map map = new HashMap();

			for (int i = 0; i < list.size(); i++) {
				Actioncomment comment = (Actioncomment) list.get(i);
				Users u = comment.getUsersByUserId();

				if (u.getAuthentics() != null) {
					Object[] objs = u.getAuthentics().toArray();
					objs = u.getAuthentics().toArray();
					if (objs != null && objs.length > 0) {
						Authentic authentic = (Authentic) objs[0];
						u.setName(authentic.getName());
					} else {
						u.setName("匿名用户");
					}
				} else {
					u.setName("匿名用户");
				}
				Users temp = new Users();
				temp.setName(null);
				temp.setAuthentics(null);
				temp.setUserId(u.getUserId());
				comment.setUsersByUserId(temp);
				comment.setUserName(u.getName());

				u = comment.getUsersByAtUserId();
				if (u != null && u.getAuthentics() != null) {
					Object[] objs = u.getAuthentics().toArray();
					objs = u.getAuthentics().toArray();
					if (objs != null && objs.length > 0) {
						Authentic authentic = (Authentic) objs[0];
						u.setName(authentic.getName());
					} else {
						u.setName("匿名用户");
					}
					comment.setAtUserName(u.getName());
				}

				comment.setUsersByAtUserId(null);
				l.add(comment);
			}
			map.put("comments", l);

			if (page == 0) {
				// 点赞
				list = this.actionManager.findPriseListByAction(action);
				Set set = new HashSet();
				for (int i = 0; i < list.size(); i++) {
					Actionprise prise = (Actionprise) list.get(i);
					Users u = prise.getUsers();

					Object[] objs = u.getAuthentics().toArray();
					if (objs != null && objs.length > 0) {
						Authentic authentic = (Authentic) objs[0];
						u.setName(authentic.getName());
					} else {
						u.setName("匿名用户");
					}

					set.add(u.getName());
				}
				map.put("prises", set);
			}

			// 封装返回结果
			if(l==null)
			{
				this.status = 200;
			}else{
				this.status = 200;
			}
			this.result.put("data", map);
			this.message = "";
		}

		return getResult();
	}

	@RequestMapping(value = "/requestAttendListAction")
	@ResponseBody
	/***
	 * 全部评论列表
	 * @param contentId
	 * @param session
	 * @return
	 */
	public Map requestAttendListAction(
			@RequestParam(value = "contentId") Integer contentId,
			@RequestParam(value = "page") Integer page, HttpSession session) {

		this.result = new HashMap();
		this.result.put("data", "");

		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {
			// 查看当前操作状态，1:评论,2:回复
			Action action = this.actionManager.findActionById(contentId);
			// 报名人数
			List list = this.actionManager.findAttentionListByAction(action,
					page);
			Set set = new HashSet();
			for (int i = 0; i < list.size(); i++) {
				Attention attention = (Attention) list.get(i);
				Users u = attention.getUsers();

				Object[] objs = u.getAuthentics().toArray();
				if (objs != null && objs.length > 0) {
					Authentic authentic = (Authentic) objs[0];
					authentic.setIdentiytype(null);
					authentic.setIdentiyCarA(null);
					authentic.setIdentiyCarB(null);
					authentic.setIdentiyCarNo(null);
					authentic.setAuthenticstatus(null);
					authentic.setBuinessLicence(null);
					authentic.setBuinessLicenceNo(null);
					authentic.setCompanyIntroduce(null);
					authentic.setIntroduce(null);
					authentic.setAutrhrecords(null);
					authentic.setCity(null);
					authentic.setAuthId(null);
					u.setLastLoginDate(null);
					u.setName(null);
					u.setTelephone(null);
					u.setPlatform(null);
					u.setPassword(null);
					u.getAuthentics().clear();
					u.getAuthentics().add(authentic);
				} else {
					u.setName("匿名用户");
				}

				System.out.println(u.getName());
				attention.setUserName(u.getName());

				set.add(attention);
			}

			if (set != null && set.size() > 0) {
				this.status = 200;
			} else {
				this.status = 201;
			}

			// 封装返回结果
			this.result.put("data", set);
			this.message = "";
		}

		return getResult();
	}
	@RequestMapping(value = "/requestSearchAction")
	@ResponseBody
	/***
	 * 搜索活动
	 * @param keyword 关键字
	 * @param page 当前页
	 * @param session
	 * @return
	 */
	public Map requestAttendListAction(
			@RequestParam(value = "keyword") String keyword,
			@RequestParam(value = "page") Integer page, HttpSession session) {
		
		this.result = new HashMap();
		this.result.put("data", "");
		
		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);
		
		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {
			List list = this.actionManager.findActionByKeyWord(keyword, page);
			
			// 封装返回结果
			this.result.put("data", list);
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
