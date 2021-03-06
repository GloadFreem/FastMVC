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
import com.jinzht.tools.PushContentType;
import com.jinzht.tools.PushUtil;
import com.jinzht.tools.Tools;
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.Comment;
import com.jinzht.web.entity.Contentimages;
import com.jinzht.web.entity.Contentprise;
import com.jinzht.web.entity.Contentshare;
import com.jinzht.web.entity.Contenttype;
import com.jinzht.web.entity.Feelingtype;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.Messagetype;
import com.jinzht.web.entity.Publiccontent;
import com.jinzht.web.entity.Share;
import com.jinzht.web.entity.Sharetype;
import com.jinzht.web.entity.Systemmessage;
import com.jinzht.web.entity.Users;
import com.jinzht.web.hibernate.HibernateSessionFactory;
import com.jinzht.web.manager.FeelingManager;
import com.jinzht.web.manager.SystemManager;
import com.jinzht.web.manager.UserManager;
import com.sun.mail.imap.protocol.Item;

@Controller
public class FeelingController extends BaseController {

	@Autowired
	private FeelingManager feelingManager;
	@Autowired
	private UserManager userManager;
	@Autowired
	private SystemManager systemManager;

	@RequestMapping(value = "/requestFeelingList")
	@ResponseBody
	/***
	 * 获取圈子列表
	 * @param page 当前页
	 * @param session
	 * @return
	 */
	public Map requestFeelingList(
			@RequestParam(value = "page", required = true) Integer page,
			@RequestParam(value = "platform", required = false) Integer platform,
			HttpSession session) {
		this.result = new HashMap();

		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {
			if(platform==null)
			{
				platform=0;
			}
			List list = this.feelingManager.findFeelingByCursor(page,
					user.getUserId(),platform);
			if (list != null && list.size() > 0) {
				this.status = 200;
				this.message = "";
				this.result.put("data", list);
			} else {
				this.status = 201;
				this.result.put("data", new ArrayList());
				this.message = Config.STRING_FEELING_NO_DATA;
			}
		}

		return getResult();
	}
	@RequestMapping(value = "/requestUsersFeelingList")
	@ResponseBody
	/***
	 * 获取用户圈子列表
	 * @param page 当前页
	 * @param session
	 * @return
	 */
	public Map requestUsersFeelingList(
			@RequestParam(value = "userId", required = true) Integer userId,
			@RequestParam(value = "page", required = true) Integer page,
			@RequestParam(value = "platform", required = false) Integer platform,
			HttpSession session) {
		this.result = new HashMap();
		
		// 获取当前发布内容用户
		Users user = this.userManager.findUserById(userId);
		
		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {
			List list = this.feelingManager.findFeelingByUser(user,page,platform);
			if (list != null && list.size() > 0) {
				this.status = 200;
				this.message = "";
				this.result.put("data", list);
			} else {
				this.status = 200;
				this.result.put("data", new ArrayList());
				this.message = Config.STRING_FEELING_NO_DATA;
			}
		}
		
		return getResult();
	}

	@RequestMapping(value = "/requestFeelingDetail")
	@ResponseBody
	/***
	 * 圈子详情
	 * @param page
	 * @param session
	 * @return
	 */
	public Map requestFeelingDetail(
			@RequestParam(value = "feelingId", required = true) Integer feelingId, 
			@RequestParam(value = "page", required = true) Integer page, 
			@RequestParam(value = "platform", required = false) Integer platform, 
			HttpSession session) {
		this.result = new HashMap();

		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {
			if(page !=0){
				List list = this.feelingManager.findFeelingCommentByPage(page, feelingId,platform);
				if(list!=null && list.size()>0)
				{
					this.status = 200;
					this.result.put("data", list);
				}else{
					this.status = 201;
					this.result.put("data", new ArrayList());
				}
			}else{
				if(platform==null)
				{
					platform=0;
				}
				Publiccontent content = this.feelingManager.findFeelingById(
						feelingId, user.getUserId(),platform);
				
				
				this.status = 200;
				this.result.put("data", content);
			}
			
			this.message = "";
			
		}

		return getResult();
	}
	

	@RequestMapping(value = "/requestPublicFeeling")
	@ResponseBody
	/***
	 * 发布圈子
	 * @param session
	 * @return
	 */
	public Map requestPublicFeeling(
			@Valid @ModelAttribute(value = "publiccontent") Publiccontent content,
			@RequestParam(value = "images",required = false) MultipartFile[] images,
			BindingResult bindingResult, HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");
		if (bindingResult.hasErrors()) {
			this.status = 400;
			this.message = bindingResult.getFieldError().getDefaultMessage();
		} else {
			// 获取当前发布内容用户
			Users user = this.findUserInSession(session);

			if (user == null) {
				this.status = 400;
				this.message = Config.STRING_LOGING_FAIL_NO_USER;
			} else {
				// 关联用户
				content.setUsers(user);
				content.setPublicDate(new Date());
				
				Feelingtype type = new Feelingtype();
				type.setFeelingTypeId(1);
				
				content.setFeeingtype(type);

				// 保存圈子内容
				this.feelingManager.addPublicContent(content);
				
				// 保存图片
				String fileName ="";
				String result="";
				if(images!=null && images.length>0){
					
					MultipartFile file = null;
					for(int i =0;i<images.length;i++){
						if (images[i]!= null) {
							file = images[i];
							fileName = String.format(
									Config.STRING_USER_FEELING_PICTUREA_FORMAT,
									new Date().getTime(),i);
							result = FileUtil.savePicture(
									file, fileName,
									"upload/feelingImages/");
							if (!result.equals("")) {
								fileName = Config.STRING_SYSTEM_ADDRESS
										+ "upload/feelingImages/" + result;
								
								Contentimages contentImages = new Contentimages();
								contentImages.setUrl(fileName);
								contentImages.setPubliccontent(content);
								contentImages.setPubliccontent(content);
								
								this.feelingManager.getContentImagesDao().save(contentImages);
								
							}
							
						}
					}
					//设置状态图片
				}
				

				// 封装返回结果
				this.status = 200;
				this.result.put("data", content);
				this.message = Config.STRING_FEELING_ADD_SUCCESS;
			}
		}
		return getResult();
	}

	@RequestMapping(value = "/requestPriseFeeling")
	@ResponseBody
	/***
	 * 点赞/取消点赞
	 * @param contentId
	 * @param bindingResult
	 * @param session
	 * @return
	 */
	public Map requestPriseFeeling(
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
			Publiccontent content = this.feelingManager
					.findPublicContentById(contentId);

			if (flag == 1) {
				
				content.getContentprises().add(new Contentprise(user, content));

				// 保存圈子内容
				this.feelingManager.saveOrUpdate(content);
				flag = 2;
			} else {
				Object[] list = content.getContentprises().toArray();

				Set set = new HashSet();
				for (int i = 0; i < list.length; i++) {
					Contentprise contentPrise = (Contentprise) list[i];
					Integer userId = contentPrise.getUsers().getUserId();
					Integer selfId = user.getUserId();
					if (userId.equals(selfId)) {
						user.setName(contentPrise.getUsers().getName());
						this.feelingManager.cancelPrise(contentPrise);
					}

				}

				flag = 1;
			}

			// 封装返回结果
			this.status = 200;
			String name = this.userManager.findUserNameByUserId(user);
			
			if(name==null || name.equals(""))
			{
				if(user.getTelephone()!=null &&!user.getTelephone().equals(""))
				{
					String telephone = user.getTelephone();
					Integer length = telephone.length();
					name = "用户" + telephone.substring(length - 4, length);
				}else{
					String userIdStr = user.getUserId().toString();
					Integer length = userIdStr.length();
					name =length>4?userIdStr.substring(length-4, length):userIdStr;
					name = "用户"+name;
				}
				
			}
			
			Map map = new HashMap();
			map.put("flag", flag); 
			map.put("name", name); 

			this.result.put("data", map);
			if (flag == 2) {
				this.message = Config.STRING_FEELING_PRISE_ADD_SUCCESS;
			} else {
				this.message = Config.STRING_FEELING_PRISE_EREASE_SUCCESS;
			}
		}

		return getResult();
	}

	@RequestMapping(value = "/requestCommentFeeling")
	@ResponseBody
	/***
	 * 评论圈子
	 * @param contentId
	 * @param bindingResult
	 * @param session
	 * @return
	 */
	public Map requestCommentFeeling(
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
			Publiccontent publicContent = this.feelingManager
					.findPublicContentById(contentId);

			Comment comment = new Comment();
			comment.setContent(content);
			comment.setPubliccontent(publicContent);
			comment.setUsersByUserId(user);
			comment.setPublicDate(new Date());
			if (flag != 1) {
				Users atUser = this.userManager.findUserById(userId);
				comment.setUsersByAtUserId(atUser);
			}
			// 保存回复
			publicContent.getComments().add(comment);

			this.feelingManager.saveOrUpdate(publicContent);

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
			user1.setAuthentics(null);
			user1.setUserstatus(null);
			user1.setTelephone(null);
			user1.setPassword(null);
			user1.setPlatform(null);
			user1.setLastLoginDate(null);
			comment.setUsersByUserId(user1);

			user1 = comment.getUsersByAtUserId();
			if (user1 != null) {
				if (user.getAuthentics() != null) {
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
				user1.setAuthentics(null);
				user1.setUserstatus(null);
				user1.setTelephone(null);
				user1.setPassword(null);
//				user1.setPlatform(null);
				user1.setLastLoginDate(null);
				comment.setUsersByAtUserId(user1);
			}
			
			
			//推送消息
			final FeelingController self = this;
			final Users u2=comment.getUsersByAtUserId();
			final Comment c = comment;
			final Publiccontent cc = publicContent;
			
			
			new Thread()
			{
				public void run() {
					
					Systemmessage message = new Systemmessage();
					message.setUsers(cc.getUsers());
					message.setTitle("【有人回复了您的圈子】");
					message.setContent(c.getContent());
					message.setIsRead(false);
					message.setMessageDate(new Date());
					message.setValid(true);
					
					Messagetype type = new Messagetype();
					type.setMessageTypeId(5);
					
					message.setMessagetype(type);
					
					//保存
					self.systemManager.getSystemMessageDao().save(message);
					
					
					//推送消息
					PushUtil pushUtil = new PushUtil();
					pushUtil.setTitle(message.getTitle()+":"+c.getContent());
					pushUtil.setContent(message.getContent());
					pushUtil.setShareUrl("");
					pushUtil.setWebViewTitle("");
					pushUtil.setShareImage("");
					pushUtil.setShareIntroduce("");  
					pushUtil.setContentType(PushContentType.system);
				
					if (cc.getUsers() != null && !cc.getUsers().getRegId().equals(null)) {
						pushUtil.setRegId(cc.getUsers().getRegId());
						pushUtil.setPlatform(cc.getUsers().getPlatform());
						pushUtil.send();
						
						if(u2!=null)
						{
							if(!u2.getRegId().equals(null))
							{
								message = new Systemmessage();
								message.setUsers(u2);
								message.setTitle("【有人@了您】");
								
								message.setContent(c.getContent());
								message.setIsRead(false);
								message.setMessageDate(new Date());
								message.setValid(true);
								
								type = new Messagetype();
								type.setMessageTypeId(5);
								
								message.setMessagetype(type);
								
								pushUtil.setRegId(u2.getRegId());
								pushUtil.setPlatform(u2.getPlatform());
								pushUtil.setContent(message.getContent());
								pushUtil.setTitle(message.getTitle()+":"+c.getContent());
								pushUtil.send();
								u2.setPlatform(null);
								

								
								self.systemManager.getSystemMessageDao().save(message);
								
							}
						}
					}
				};
			}.start();
			

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

	@RequestMapping(value = "/requestShareFeeling")
	@ResponseBody
	/***
	 * 状态分享
	 * @param contentId
	 * @param content
	 * @param session
	 * @return
	 */
	public Map requestShareFeeling(
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
//			// 查看当前操作状态，1:评论,2:回复
			Share share = Tools.generateShareContent(contentId, 2);
			Publiccontent content = this.feelingManager.findPublicContentById(contentId);
			share.setContent(content.getContent());
			
			
			Object[] objs = content.getContentimageses().toArray();
			
			
			if(objs!=null && objs.length>0)
			{
				int count = objs.length;
				int radomIndex = (int)(0+Math.random()*(count));
				
				Contentimages image = (Contentimages)objs[radomIndex];
				share.setImage(image.getUrl());
			}else{
				int radomIndex = (int)(0+Math.random()*(4));
				share.setImage(Config.STRING_SYSTEM_ADDRESS+"images/share/"+Config.STRING_SHARE_FEELING_IMAGES.get(radomIndex));
			}
			
			String publicContentStr = content.getContent();
			share.setTitle("圈子分享--【金指投圈子】");
			if(publicContentStr!=null && !publicContentStr.equals(""))
			{
				share.setContent(content.getContent());
			}else{
				share.setContent(Config.STRING_SHARE_CONTENT_TITLE);
			}
			
			String url = share.getUrl() + "?contentId="+content.getPublicContentId();
			share.setUrl(url);
			
			String contentStr = share.getContent();
			if(contentStr.length()>20)
			{
				contentStr = contentStr.substring(0, 20);
				share.setContent(contentStr);
			}
			
			// 保存分享记录
			this.systemManager.saveShareRecord(share);
			
			share.setSharetype(null);
			share.setShareDate(null);
			// 封装返回结果
			this.status = 200;
			this.result.put("data", share);
			this.message = "";
		}

		return getResult();
	}
	
	@RequestMapping(value = "/requestUpdateShareFeeling")
	@ResponseBody
	/***
	 * 状态分享
	 * @param contentId
	 * @param content
	 * @param session
	 * @return
	 */
	public Map requestUpdateShareFeeling(@RequestParam(value = "type") int type,
			@RequestParam(value = "shareId") Integer shareId,
			@RequestParam(value = "content") String content,
			HttpSession session) {
		
		this.result = new HashMap();
		this.result.put("data", "");
		
		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);
		
		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {
			// 生成分享链接
			Share share = this.feelingManager.findShareFeelingById(shareId);
			if(share!=null)
			{
				share.setContent(content);
				this.feelingManager.updateShare(share);
			}
			
			String contentStr = share.getContent();
			if(contentStr.length()>20)
			{
				contentStr = contentStr.substring(0, 20);
				share.setContent(contentStr);
			}
			
			// 封装返回结果
			this.status = 200;
			this.result.put("data", "");
			this.message = Config.STRING_FEELING_SHARE_UPDATE;
		}
		
		return getResult();
	}
	@RequestMapping(value = "/requestPublicContentDelete")
	@ResponseBody
	/***
	 * 状态分享
	 * @param contentId
	 * @param session
	 * @return
	 */
	public Map requestPublicContentDelete(
			@RequestParam(value = "contentId") Integer contentId,
			HttpSession session) {
		
		this.result = new HashMap();
		this.status = 200;
		this.result.put("data", "");
		
		//删除圈子
		this.feelingManager.deletePublicContent(contentId);
		
		this.message = Config.STRING_PROJECT_DELETE_SUCCESS;
		return getResult();
	}
	@RequestMapping(value = "/requestContentCommentDelete")
	@ResponseBody
	/***
	 * 状态分享
	 * @param commentId
	 * @param session
	 * @return
	 */
	public Map requestContentCommentDelete(
			@RequestParam(value = "commentId") Integer commentId,
			HttpSession session) {
		
		this.result = new HashMap();
		this.status = 200;
		this.result.put("data", "");
		
		//删除圈子
		this.feelingManager.deletePublicContentComment(commentId);
		
		this.message = Config.STRING_PROJECT_DELETE_SUCCESS;
		return getResult();
	}
	@RequestMapping(value = "/shareContentToFeeling")
	@ResponseBody
	public Map shareContentToFeeling(
			@RequestParam(value = "contentId",required=false) Integer contentId,
			@RequestParam(value = "comment",required=false) String comment,
			@RequestParam(value = "tag",required=false) String tag,
			@RequestParam(value = "content",required=false) String content,
			@RequestParam(value = "description",required=false) String description,
			@RequestParam(value = "type",required=false) Integer type,
			@RequestParam(value = "image",required=false) String image,
			HttpSession session
			)
	{
		
		this.result = new HashMap();
		this.result.put("data", "");
		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);
		
		if (user == null) {
			this.status = 400;

			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {

			Contentshare share = new Contentshare();
			share.setImage(image);
			share.setDesc(description);
			share.setContent(content);
			share.setTag(tag);
			Contenttype t = new Contenttype();
//			if(type==0)
//			{
//				type=6;
//			}else  if(type==1)
//			{
//				type=7;
//			}else if(type==2)
//			{
//				type=8;
//			}
			
			t.setTypeId(type);
			share.setContenttype(t);

			
			this.systemManager.getContentShareDao().save(share);
			
			Publiccontent c = new Publiccontent(); 
			c.setUsers(user);
			c.setContent(comment);
			c.setPublicDate(new Date());
			c.setContentshare(share);
			
			Feelingtype feelingType = new Feelingtype();
			
			if(type==6)
			{
				feelingType.setFeelingTypeId(4);
			}else if(type==7){
				feelingType.setFeelingTypeId(3);
			}else{
				feelingType.setFeelingTypeId(2);
			}
			c.setFeeingtype(feelingType);
			this.feelingManager.addPublicContent(c);
			
			this.status=200;
			this.message="内容分享成功！";
			this.result.put("data", "");
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
