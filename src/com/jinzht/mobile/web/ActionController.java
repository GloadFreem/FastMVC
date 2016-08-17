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
import com.jinzht.tools.Tools;
import com.jinzht.web.entity.Action;
import com.jinzht.web.entity.Actioncomment;
import com.jinzht.web.entity.Actionimages;
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
			//获取用户是否已经报名参加
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
					int userId1 = actionPrise.getUsers().getUserId();
					int userId2 = user.getUserId();

					if (userId1 == userId2) {
						this.actionManager.cancelPrise(actionPrise);
					}

				}

				flag = 1;
			}
			
			if(user.getName()==null || user.getName().equals(""))
			{
				String telephone = user.getTelephone();
				Integer length = telephone.length();
				String name = "用户"+telephone.substring(length-4, length);
				user.setName(name);
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
			if (flag != 1 && userId!= null && userId!=0) {
				Users atUser = this.userManager.findUserById(userId);
				comment.setUsersByAtUserId(atUser);
				comment.setAtUserName(atUser.getName());
			}

			this.actionManager.saveOpUpdateActionComment(comment);

			// 处理返回值
			Users user1 = comment.getUsersByUserId();
			if (user1.getAuthentics() != null) {
				Object[] l = user.getAuthentics().toArray();
				if (l.length > 0) {
					Authentic authentic = (Authentic) l[0];
					user1.setName(authentic.getName());
				} else {
					user1.setName("");
				}

			} else {
				user1.setName("");
			}
			
			if(user1.getName()==null || user1.getName().equals(""))
			{
				String telephone = user1.getTelephone();
				Integer length = telephone.length();
				String name = "用户"+telephone.substring(length-4, length);
				user1.setName(name);
				comment.setUserName(name);
			}
			
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
				if(user1.getName()==null || user1.getName().equals(""))
				{
					String telephone = user1.getTelephone();
					Integer length = telephone.length();
					String name = "用户"+telephone.substring(length-4, length);
					user1.setName(name);
					comment.setAtUserName(name);
				}
				
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
			share.setTitle(action.getName()+"--【金指投投融资】");
			share.setContent(action.getDescription());
			share.setUrl(Tools.generateWebUrl(Config.STRING_SYSTEM_SHARE_ACTION)+"?actionId="+action.getActionId());

			Object[] images = action.getActionimages().toArray();
			if(images!=null && images.length>0)
			{
				Actionimages image = (Actionimages) images[0];
				share.setImage(image.getUrl());
			}else
			{
				share.setImage("https://is1-ssl.mzstatic.com/image/thumb/Purple60/v4/3a/9c/09/3a9c09aa-5e86-4185-4b6c-b3e282e45d86/pr_source.png/500x500bb.jpg");
			}
				
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

	@RequestMapping(value = "/requestDetailAction")
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
			action.setActionprises(null);
			action.setActionprises(null);
			action.setAttentions(null);
			
			
			Attention attention = this.actionManager.findAttentionByActionIdAndUser(action, user);
			if(attention!=null)
			{
				action.setAttended(true);
			}
			

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
				Integer userId = this.actionManager.findUserIdByCommentId(comment);
				if(userId!=null)
				{
					Users u = this.userManager.findUserById(userId);

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
					
					if(comment.getUserName()==null || comment.getUserName().equals(""))
					{
						String telephone = u.getTelephone();
						Integer length = telephone.length();
						String name = "用户"+telephone.substring(length-4, length);
						comment.setUserName(name);
					}

					Integer atUserId = this.actionManager.findAtUserIdByCommentId(comment);
					if(atUserId!=null)
					{
						u = this.userManager.findUserById(atUserId);
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
							
							if(comment.getUserName()==null || comment.getUserName().equals(""))
							{
								String telephone = u.getTelephone();
								Integer length = telephone.length();
								String name = "用户"+telephone.substring(length-4, length);
								comment.setUserName(name);
							}
						}

						comment.setUsersByAtUserId(null);
					}
					
					l.add(comment);
				}
				
			}
			map.put("comments", l);

			if (page == 0) {
				// 点赞
				list = this.actionManager.findPriseListByAction(action);
				Set set = new HashSet();
				for (int i = 0; i < list.size(); i++) {
					Actionprise prise = (Actionprise) list.get(i);
					
					Integer userId = this.actionManager.findActionPriseUserId(prise);
					
					Users u = this.userManager.findUserById(userId);

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
			List l = new ArrayList();
			for (int i = 0; i < list.size(); i++) {
				Attention attention = (Attention) list.get(i);
				Integer userId = this.actionManager.findUserByAttention(attention);
				if(userId!=null)
				{
					Users u = this.userManager.findUserById(userId);
					String telephone = u.getTelephone();
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
						authentic.setCompanyAddress(null);
						authentic.setOptional(null);
						authentic.setIndustoryArea(null);
						authentic.setIntroduce(null);
						authentic.setAutrhrecords(null);
						authentic.setCity(null);
						authentic.setAuthId(null);
						
						if(authentic.getName()==null || authentic.getName().equals(""))
						{
							if(telephone!=null && telephone.equals(""))
							{
								Integer length = telephone.length();
								String name = "用户"+telephone.substring(length-4, length);
								authentic.setName(name);
							}else{
								authentic.setName("");
							}
						}
						
						u.setLastLoginDate(null);
						u.setName(null);
						u.setTelephone(null);
						u.setPlatform(null);
						u.setPassword(null);
						u.setExtUserId(null);
						u.getAuthentics().clear();
						u.getAuthentics().add(authentic);
						
						
						attention.setUsers(u);					} else {
						u.setName("匿名用户");
					}

					attention.setUserName(u.getName());

//					if(attention.getUserName()==null || attention.getUserName().equals(""))
//					{
//						Integer length = telephone.length();
//						String name = "用户"+telephone.substring(length-4, length);
//						attention.setUserName(name);
//					}
					
					l.add(attention);
				}

				
			}

			if (l != null && l.size() > 0) {
				this.status = 200;
			} else {
				this.status = 200;
			}

			// 封装返回结果
			this.result.put("data", l);
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
	@RequestMapping(value = "/requestActionCommentDelete")
	@ResponseBody
	/***
	 * 删除活动评论
	 * @param commentId 评论id
	 * @param session
	 * @return
	 */
	public Map requestActionCommentDelete(
			@RequestParam(value = "commentId") Integer commentId, HttpSession session) {
		
		this.result = new HashMap();
		this.result.put("data", "");
		
		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);
		
		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {
			// 封装返回结果
			this.status = 200;
			this.actionManager.deleteActionCommentByCommentId(commentId);
			this.message = Config.STRING_PROJECT_DELETE_SUCCESS;
		}
		
		return getResult();
	}
	
	@RequestMapping(value="actionWebLooker")
	public String actionWebLooker(
			@RequestParam(value="actionId",required=false)Integer actionId,
			ModelMap map)
	{
		Action action = this.actionManager.findActionById(actionId);
		
		map.put("action", action);
		return "actionWebLooker";
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
