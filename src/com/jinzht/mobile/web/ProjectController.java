package com.jinzht.mobile.web;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jinzht.tools.Config;
import com.jinzht.tools.DateUtils;
import com.jinzht.tools.FileUtil;
import com.jinzht.tools.MailUtil;
import com.jinzht.tools.MessageType;
import com.jinzht.tools.MsgUtil;
import com.jinzht.tools.Tools;
import com.jinzht.tools.YeePayUtil;
import com.jinzht.web.dao.FinancialstandingDAO;
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.Businessplan;
import com.jinzht.web.entity.Collection;
import com.jinzht.web.entity.Comment;
import com.jinzht.web.entity.Contentprise;
import com.jinzht.web.entity.Controlreport;
import com.jinzht.web.entity.Financestatus;
import com.jinzht.web.entity.Financialstanding;
import com.jinzht.web.entity.Financingcase;
import com.jinzht.web.entity.Financingexit;
import com.jinzht.web.entity.Investmentrecord;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.Project;
import com.jinzht.web.entity.Projectcommitrecord;
import com.jinzht.web.entity.Publiccontent;
import com.jinzht.web.entity.Roadshow;
import com.jinzht.web.entity.Share;
import com.jinzht.web.entity.Sharetype;
import com.jinzht.web.entity.Status;
import com.jinzht.web.entity.Users;
import com.jinzht.web.hibernate.HibernateSessionFactory;
import com.jinzht.web.manager.ProjectManager;
import com.jinzht.web.manager.SystemManager;
import com.jinzht.web.manager.UserManager;

@Controller
public class ProjectController extends BaseController {

	@Autowired
	private ProjectManager ProjectManager;
	@Autowired
	private UserManager userManager;
	@Autowired
	private SystemManager systemManager;

	@RequestMapping(value = "/requestProjectList")
	@ResponseBody
	/***
	 * 获取圈子列表
	 * @param page 当前页
	 * @param session
	 * @return
	 */
	public Map requestProjectList(
			@RequestParam(value = "page", required = true) Integer page,
			@RequestParam(value = "type", required = true) int type,
			HttpSession session) {
		this.result = new HashMap();
		List list = this.ProjectManager.findProjectsByCursor(type,page);
		if (list != null && list.size() > 0) {
			this.status = 200;
			this.message = "";
			this.result.put("data", list);
		} else {
			this.status = 201;
			this.result.put("data", new ArrayList());
			this.message = Config.STRING_FEELING_NO_DATA;
		}

		return getResult();
	}

	@RequestMapping(value = "/requestProjectDetail")
	@ResponseBody
	/***
	 * 项目有详情
	 * @param projectId 项目id
	 * @param session
	 * @return
	 */
	public Map requestProjectDetail(
			@RequestParam(value = "projectId", required = true) Integer projectId,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");

		// 获取用户
		Users user = this.findUserInSession(session);
		if (user != null) {
			// 获取项目
			Project project = this.ProjectManager.findProjectById(projectId);
			if(project!=null)
			{
				// 获取用户是否已关注该项目
				Collection collection = this.ProjectManager
						.findProjectCollectionByUser(project, user);

				if (collection != null) {
					project.setCollected(true);
				} else {
					project.setCollected(false);
				}
				List list = new ArrayList();

				// 商业计划书
				Object[] objs = project.getBusinessplans().toArray();
				if (objs != null && objs.length>0) {
					list.add(objs[0]);
				}

				// 风险报告
				objs = project.getControlreports().toArray();
				if (objs != null && objs.length>0) {
					list.add(objs[0]);
				}
				// 融资计划
				objs = project.getFinancialstandings().toArray();
				if (objs != null && objs.length>0) {
					list.add(objs[0]);
				}
				// 融资案例
				objs = project.getFinancingcases().toArray();
				if (objs != null && objs.length>0) {
					list.add(objs[0]);
				}
				// 退出渠道
				objs = project.getFinancingexits().toArray();
				if (objs != null && objs.length>0) {
					list.add(objs[0]);
				}

				Map map = new HashMap();
				map.put("project", project);
				map.put("extr", list);

				project.setBusinessplans(null);
				project.setControlreports(null);
				project.setFinancialstandings(null);
				project.setFinancingcases(null);
				project.setFinancingexits(null);
				
				// 封装返回结果
				this.status = 200;
				this.result.put("data", map);
				this.message = "";
			}else{
				// 封装返回结果
				this.status = 400;
				this.result.put("data", "");
				this.message = "无法获取该项目详情";
			}

			
		} else {
			this.status = 400;
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		}

		return getResult();
	}

	@RequestMapping(value = "/requestProjectMember")
	@ResponseBody
	/***
	 * 项目成员
	 * @param projectId 项目id
	 * @param session
	 * @return
	 */
	public Map requestProjectMember(
			@RequestParam(value = "projectId", required = true) Integer projectId,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");

		// 获取用户
		Users user = this.findUserInSession(session);
		if (user != null) {

			// 获取项目
			Project project = this.ProjectManager.findProjectById(projectId);
			// 返回数据
			this.result.put("data", project.getMembers());
		} else {
			this.status = 400;
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		}

		return getResult();
	}
	@RequestMapping(value = "/requestProjectCommitRecords")
	@ResponseBody
	/***
	 * 项目成员
	 * @param projectId 项目id
	 * @param page 页码
	 * @param session
	 * @return
	 */
	public Map requestProjectCommitRecords(
			@RequestParam(value = "projectId", required = true) Integer projectId,
			@RequestParam(value = "page", required = true) Integer page,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");
		
		// 获取用户
		Users user = this.findUserInSession(session);
		if (user != null) {
			
			//获取提交记录
			List list = this.ProjectManager.findRecordByProjectId(projectId,page);
			
			List result = new ArrayList();
			Map map = new HashMap();
			if(list!=null && list.size()>0)
			{
				for(int i =0;i<list.size();i++)
				{
					map = new HashMap();
					Projectcommitrecord record = (Projectcommitrecord) list.get(i);
					Users u = this.userManager.findUserById(record.getUsers().getUserId());
					u.setPassword(null);
					u.setTelephone(null);
					u.setWechatId(null);
					u.setRegId(null);
					u.setLastLoginDate(null);
					u.setPlatform(null);
					u.setExtUserId(null);
					u.setName(null);
					
					Object[] objs = u.getAuthentics().toArray();
					for(int j =0;j<objs.length;j++)
					{
						Authentic authentic = (Authentic) objs[j];
						authentic.setIdentiyCarA(null);
						authentic.setIdentiyCarB(null);
						authentic.setIdentiyCarNo(null);
						authentic.setAuthenticstatus(null);
						authentic.setAutrhrecords(null);
						authentic.setCity(null);
						authentic.setIndustoryArea(null);
						authentic.setOptional(null);
						authentic.setIntroduce(null);
						authentic.setCompanyIntroduce(null);
					}
					
					map.put("record", record);
					map.put("user", u);
					
					result.add(map);
				}
			}
			
			// 返回数据
			this.message="";
			this.result.put("data", result);
		} else {
			this.status = 400;
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		}
		
		return getResult();
	}

	@RequestMapping(value = "/requestProjectCommentList")
	@ResponseBody
	/***
	 * 评论列表
	 * @param projectId 项目id
	 * @param page 当前页码
	 * @param session
	 * @return
	 */
	public Map requestProjectCommentList(
			@RequestParam(value = "projectId", required = true) Integer projectId,
			@RequestParam(value = "page", required = true) Integer page,
			@RequestParam(value = "platform", required = false) Integer platform,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");

		// 获取用户
		Users user = this.findUserInSession(session);
		if (user != null) {

			// 获取项目
			Project project = this.ProjectManager.findProjectById(projectId);
			if(platform==null)
			{
				platform=0;
			}
			// 获取项目评论数量
			List list = this.ProjectManager.findProjectComment(project, page,platform);
			// 返回数据
			this.status = 200;
			this.result.put("data", list);
		} else {
			this.status = 400;
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		}

		return getResult();
	}

	@RequestMapping(value = "/requestInvestProject")
	@ResponseBody
	/***
	 * 财务状况
	 * @param projectId
	 * @param session
	 * @return
	 */
	public Map requestInvestProject(
			@RequestParam(value = "projectId", required = true) Integer projectId,
			@RequestParam(value = "amount", required = true) final float amount,
			@RequestParam(value = "tradeCode", required = true) String tradeCode,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");
		
		final Users user = this.findUserInSession(session);
		if (user != null) {
			final Project project = this.ProjectManager.findProjectById(projectId);
			Investmentrecord record = new Investmentrecord();
			record.setInvestAmount(amount);
			record.setInvestCode(tradeCode);
			record.setInvestDate(new Date());
			record.setStatusId(1);
			record.setUsers(user);
			record.setProject(project);
			
			this.ProjectManager.getInvestmentRecordDao().save(record);
			// 发送短信
			final String message = String.format(
					Config.STRING_SMS_INVEST_VALID_TRUE,
					DateUtils.formatDate(new Date()), project.getAbbrevName(), amount);
			
			new Thread(){
				public void run()
				{
					MailUtil mu = new MailUtil();
					try {
						try {
							MsgUtil SMS = new MsgUtil();
							SMS.setTelePhone(user.getTelephone());
							SMS.setMsgType(MessageType.NormalMessage);
							SMS.setContent(message);
							// 发送验证码
							MsgUtil.send();
							
							//发送邮件
							try {
								mu.sendUserInvest(mu,user.getTelephone(),user.getName(),project.getAbbrevName(),amount);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();
			
			// 封装返回结果
			this.status = 200;
			this.result.put("data", "");
		}else{
			this.status = 400;
			this.message = Config.STRING_PROJECT_NO_DETAIL;
		}

		return getResult();
	}

	@RequestMapping(value = "/requestProjectFinanceCase")
	@ResponseBody
	/***
	 * 
	 * @param projectId
	 * @param session
	 * @return
	 */
	public Map requestProjectFinanceCase(
			@RequestParam(value = "projectId", required = true) Integer projectId,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");

		Financingcase financingCase = this.ProjectManager
				.findProjectFinancialCase(projectId);

		// 封装返回结果
		this.status = 200;
		this.result.put("data", financingCase);

		if (financingCase != null) {
			this.message = "";
		} else {
			this.status = 400;
			this.message = Config.STRING_PROJECT_NO_DETAIL;
		}

		return getResult();
	}

	@RequestMapping(value = "/requestProjectFinancePlan")
	@ResponseBody
	/***
	 * 获取项目商业计划书
	 * @param projectId
	 * @param session
	 * @return
	 */
	public Map requestProjectFinancePlan(
			@RequestParam(value = "projectId", required = true) Integer projectId,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");

		Businessplan businessPlan = this.ProjectManager
				.findProjectBusinessPlan(projectId);

		// 封装返回结果
		this.status = 200;
		this.result.put("data", businessPlan);

		if (businessPlan != null) {
			this.message = "";
		} else {
			this.status = 400;
			this.message = Config.STRING_PROJECT_NO_DETAIL;
		}

		return getResult();
	}

	@RequestMapping(value = "/requestProjectFinanceExit")
	@ResponseBody
	/***
	 * 退出渠道
	 * @param projectId
	 * @param session
	 * @return
	 */
	public Map requestProjectFinanceExit(
			@RequestParam(value = "projectId", required = true) Integer projectId,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");

		Financingexit financingExit = this.ProjectManager
				.findProjectFinanceExit(projectId);

		// 封装返回结果
		this.status = 200;
		this.result.put("data", financingExit);

		if (financingExit != null) {
			this.message = "";
		} else {
			this.status = 400;
			this.message = Config.STRING_PROJECT_NO_DETAIL;
		}

		return getResult();
	}

	@RequestMapping(value = "/requestProjectFinanceControl")
	@ResponseBody
	/***
	 * 风控报告
	 * @param projectId
	 * @param session
	 * @return
	 */
	public Map requestProjectFinanceControl(
			@RequestParam(value = "projectId", required = true) Integer projectId,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");

		Controlreport controlReport = this.ProjectManager
				.findProjectControlReport(projectId);

		// 封装返回结果
		this.status = 200;
		this.result.put("data", controlReport);

		if (controlReport != null) {
			this.message = "";
		} else {
			this.status = 400;
			this.message = Config.STRING_PROJECT_NO_DETAIL;
		}

		return getResult();
	}

	@RequestMapping(value = "/requestProjectFinanceStatus")
	@ResponseBody
	public Map requestProjectFinanceStatus(
			@RequestParam(value = "projectId", required = true) Integer projectId,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");

		Financestatus project = this.ProjectManager
				.findProjectFinancialStatus(projectId);

		// 封装返回结果
		this.status = 200;
		this.result.put("data", project);

		return getResult();
	}

	@RequestMapping(value = "/signVerify")
	@ResponseBody
	/***
	 * 易宝支付
	 * @param req 请求加密xml 格式数据
	 * @param method 加密/校验
	 * @param sign 密文
	 * @param session
	 * @return 加密后密文
	 */
	public Map signVerify(@RequestParam(value = "req") String req,
			@RequestParam(value = "method") String method,
			@RequestParam(value = "type") short type,
			@RequestParam(value = "sign") String sign, HttpSession session) {

		this.result = new HashMap();
		this.result.put("data", "");

		if (method.equals("sign")) {
			String result = YeePayUtil.sign(req, type);
			if (!result.equals("")) {
				this.status = 200;

				// 处理直连或者网关接口
				if (type == 0) {
					;
					try {
						result = URLEncoder.encode(result, "utf-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// result = result.substring(5, result.length());
				}

				Map map = new HashMap();
				map.put("sign", result);

				this.result.put("data", map);
				this.message = Config.STRING_YEEPAY_ENCRYPT_SUCCESS;
			} else {
				this.status = 400;
				this.message = Config.STRING_YEEPAY_ENCRYPT_FAIL;
			}
		} else {
			String result = YeePayUtil.verify(req, sign);
			if (!result.equals("FAIL")) {
				this.status = 200;
				this.message = Config.STRING_YEEPAY_VERIFY_SUCCESS;
			} else {
				this.status = 400;
				this.message = Config.STRING_YEEPAY_VERIFY_FAIL;
			}

			Map map = new HashMap();
			map.put("verify", result);

			this.result.put("data", map);
		}

		return getResult();
	}

	@RequestMapping(value = "/requestShareProject")
	@ResponseBody
	/***
	 * 状态分享
	 * @param contentId
	 * @param content
	 * @param session
	 * @return
	 */
	public Map requestShareProject(@RequestParam(value = "type") int type,
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
			// 查看当前操作状态，1:评论,2:回复
			Publiccontent publicContent = this.ProjectManager
					.findPublicContentById(contentId);
			// 生成分享链接
			Share share = new Share();
			Sharetype shareType = new Sharetype();
			shareType.setShareTypeId(type);
			share.setSharetype(shareType);
			share.setShareDate(new Date());
			share.setContent(content);
			share.setContentId(contentId);

			String url = ""; // 生成分享链接
			url = String.format("%s%s.action?contentId=%d", Config.STRING_SYSTEM_ADDRESS,
					Config.STRING_SYSTEM_SHARE_PROJECT_DETAIL, contentId);

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

	@RequestMapping(value = "/requestProjectCollect")
	@ResponseBody
	/***
	 * 关注项目
	 * @param projectId
	 * @param session
	 * @return
	 */
	public Map requestProjectCollect(
			@RequestParam(value = "projectId", required = true) Integer projectId,
			@RequestParam(value = "flag", required = true) Integer flag,
			HttpSession session) {
		this.result = new HashMap();

		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.result.put("data", "");
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {
			flag = this.ProjectManager.projectCollect(projectId, user);

			Map map = new HashMap();
			map.put("flag", flag);

			// 封装返回数据
			this.status = 200;
			this.result.put("data", map);
			if (flag == 0) {
				this.message = Config.STRING_PROJECT_COLLECT_CANCEL;
			} else {
				this.message = Config.STRING_PROJECT_COLLECT_ADD;
			}
		}

		return getResult();
	}

	@RequestMapping(value = "/requestProjectComment")
	@ResponseBody
	/***
	 * 评论项目
	 * @param projectId
	 * @param session
	 * @return
	 */
	public Map requestProjectComment(
			@RequestParam(value = "projectId", required = true) Integer projectId,
			@RequestParam(value = "content", required = true) String content,
			HttpSession session) {
		this.result = new HashMap();

		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.result.put("data", "");
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {

			// 添加评论
			this.ProjectManager.projectComment(projectId, content, user);

			// 封装返回数据
			this.status = 200;
			this.result.put("data", "");
			this.message = Config.STRING_PROJECT_COMMENT_SUCCESS;
		}
		return getResult();
	}
	@RequestMapping(value = "/requestProjectCommentDelete")
	@ResponseBody
	/***
	 * 评论项目
	 * @param projectId
	 * @param session
	 * @return
	 */
	public Map requestProjectCommentDelete(
			@RequestParam(value = "commentId", required = true) Integer commentId,
			HttpSession session) {
		this.result = new HashMap();
		
		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);
		
		if (user == null) {
			this.status = 400;
			this.result.put("data", "");
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {
			
			// 删除评论
			this.ProjectManager.deleteProjectComment(commentId);
			
			// 封装返回数据
			this.status = 200;
			this.result.put("data", "");
			this.message = Config.STRING_PROJECT_DELETE_SUCCESS;
		}
		return getResult();
	}

	@RequestMapping(value = "/requestScene")
	@ResponseBody
	/***
	 * 项目现场
	 * @param projectId
	 * @param session
	 * @return
	 */
	public Map requestScene(
			@RequestParam(value = "projectId", required = true) Integer projectId,
			HttpSession session) {
		this.result = new HashMap();

		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.result.put("data", "");
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {

			// 添加评论
			List list = this.ProjectManager.findSceneByProjectId(projectId,
					user);

			// 封装返回数据
			this.status = 200;
			this.result.put("data", list);
			this.message = Config.STRING_PROJECT_SCENE_SUCCESS;
		}
		return getResult();
	}

	@RequestMapping(value = "/requestProjectSceneCommentList")
	@ResponseBody
	/***
	 * 项目评论列表
	 * @param sceneId
	 * @param page
	 * @param session
	 * @return
	 */
	public Map requestProjectSceneCommentList(
			@RequestParam(value = "sceneId", required = true) Integer sceneId,
			@RequestParam(value = "page", required = true) Integer page,
			@RequestParam(value = "platform", required = false) Integer platform,
			HttpSession session) {
		this.result = new HashMap();

		// 添加
		if (session.getAttribute("userId") != null) {
			Integer userId = Integer.parseInt(session.getAttribute("userId")
					.toString());
			if(platform==null)
			{
				platform=0;
			}
			List list = this.ProjectManager.findProjectSceneCommentList(
					sceneId, page, userId,platform);

			// 封装返回数据
			this.status = 200;
			if (list != null && list.size() > 0) {
				this.message = Config.STRING_PROJECT_SCENE_COMMENT_SUCCESS;
			} else {
				this.message = Config.STRING_PROJECT_SCENE_COMMENT_COMPLETED;
			}
			this.result.put("data", list);
		} else {
			this.status = 400;
			this.result.put("data", "");
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		}

		return getResult();
	}

	@RequestMapping(value = "/requestRecorData")
	@ResponseBody
	/***
	 * 项目现场信息
	 * @param sceneId
	 * @param session
	 * @return
	 */
	public Map requestRecorData(
			@RequestParam(value = "sceneId", required = true) Integer sceneId,
			@RequestParam(value = "page", required = true) Integer page,
			HttpSession session) {
		this.result = new HashMap();

		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.result.put("data", "");
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {

			// 添加评论
			List list = this.ProjectManager.findAudioRecordBySceneId(sceneId,
					page);
			if (list != null && list.size() > 0) {
				// 封装返回数据
				this.status = 200;
			} else {
				// 封装返回数据
				this.status = 201;
			}

			this.result.put("data", list);
			this.message = Config.STRING_PROJECT_SCENE_SUCCESS;
		}
		return getResult();
	}

	@RequestMapping(value = "/requestRoadShowByProjectId")
	@ResponseBody
	/***
	 * 获取项目路演信息
	 * @param projectId
	 * @param session
	 * @return
	 */
	public Map requestRoadShowByProjectId(
			@RequestParam(value = "projectId", required = true) Integer projectId,
			HttpSession session) {
		this.result = new HashMap();

		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.result.put("data", "");
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {

			// 添加评论
			Roadshow roadShow = this.ProjectManager
					.findRoadShowByProject(projectId);

			// 封装返回数据
			this.status = 200;
			this.result.put("data", roadShow);
			this.message = Config.STRING_PROJECT_SCENE_SUCCESS;
		}
		return getResult();
	}

	@RequestMapping(value = "/requestSceneComment")
	@ResponseBody
	/***
	 * 现场交流
	 * @param sceneId
	 * @param content
	 * @param session
	 * @return
	 */
	public Map requestSceneComment(
			@RequestParam(value = "sceneId", required = true) Integer sceneId,
			@RequestParam(value = "content", required = true) String content,
			HttpSession session) {
		this.result = new HashMap();

		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.result.put("data", "");
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {

			// 添加评论
			this.ProjectManager.saveSceneComment(sceneId, user, content);
			// 封装返回数据
			this.status = 200;
			this.result.put("data", "");
			this.message = Config.STRING_PROJECT_SCENE_ADD_SUCCESS;
		}
		return getResult();
	}

	@RequestMapping(value = "/requestProjectFinance")
	@ResponseBody
	/***
	 * 现场交流
	 * @param sceneId
	 * @param content
	 * @param session
	 * @return
	 */
	public Map requestProjectFinance(
			@RequestParam(value = "projectId", required = true) Integer projectId,
			@RequestParam(value = "amount", required = true) float amount,
			@RequestParam(value = "investCode", required = true) String investCode,
			HttpSession session) {
		this.result = new HashMap();

		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.result.put("data", "");
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {

			// 添加评论
			this.ProjectManager.saveProjectInvest(projectId, user, amount,
					investCode);
			// 封装返回数据
			this.status = 200;
			this.result.put("data", "");
			this.message = Config.STRING_PROJECT_INVEST_ADD_SUCCESS;
		}
		return getResult();
	}

	@RequestMapping(value = "/requestProjectShare")
	@ResponseBody
	/***
	 * 项目分享
	 * @param sceneId 现场id
	 * @param session 会话
	 * @return
	 */
	public Map requestProjectShare(
			@RequestParam(value = "projectId", required = true) Integer projectId,
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
			// Project project = this.ProjectManager.findProjectById(projectId);
			// 生成分享链接
			Share share = Tools.generateShareContent(projectId, 1);
			Project project = this.ProjectManager.findProjectById(projectId);
			share.setContent(project.getDescription());
			share.setImage(project.getStartPageImage());
			share.setTitle(project.getAbbrevName()+"【金指投投融资】");
			
			String url = share.getUrl()+"?projectId="+project.getProjectId();
			share.setUrl(url);
			
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

	@RequestMapping(value = "/requestIgorneProjectCommit")
	@ResponseBody
	/***
	 * 项目分享
	 * @param projectId 项目Id
	 * @param session 会话
	 * @return
	 */
	public Map requestIgorneProjectCommit(
			@RequestParam(value = "projectId", required = true) Integer projectId,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");

		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {
			Project project = this.ProjectManager.findProjectById(projectId);
			// 获取项目提交记录
			Projectcommitrecord commit = this.ProjectManager
					.findProjectcommitByProject(project, user);

			Status status = new Status();
			status.setRecordId(3);

			// 设置忽略状态
			commit.setStatus(status);
			// 保存分享记录
			this.ProjectManager.getProjectCommitRecordDao()
					.saveOrUpdate(commit);
			// 封装返回结果
			this.status = 200;
			this.result.put("data", "");
			this.message = "操作成功！";
		}

		return getResult();
	}

	@RequestMapping(value = "/requestProjectCenter")
	@ResponseBody
	/***
	 * 项目中心
	 * @param type 类型，0：项目方，1：投资人，2：投资机构，3：智囊团
	 * @param page 当前页
	 * @param session
	 * @return
	 */
	public Map requestProjectCenter(
			@RequestParam(value = "type", required = true) short type,
			@RequestParam(value = "page", required = true) Integer page,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");

		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {
			// 根据请求类型获取相应数据
			List list = null;
			switch (type) {
			case 0:
				list = this.ProjectManager.findProjectsByUser(user, page);
				if (list != null && list.size() > 0) {
					this.result.put("data", list);
				} else {
					this.result.put("data", new ArrayList());
				}
				break;
			case 3:
				Map map = new HashMap();
				// 获取投资人
				list = this.ProjectManager.findProjectsInvestmentWithUser(user,
						page);
				// 加入到返回数据中
				map.put("invest", list);
				// 获取投资收到提交项目
				list = this.ProjectManager.findCommentProjectByUser(user, page);
				// 加入到返回数据中
				map.put("comment", list);
				this.result.put("data", map);
				break;
			default:
				map = new HashMap();
				// 获取投资人
				list = this.ProjectManager.findProjectsInvestmentWithUser(user,
						page);
				// 加入到返回数据中
				map.put("invest", list);
				// 获取投资收到提交项目
				list = this.ProjectManager.findRecivedProjectCommitByUser(user,
						page);
				// 加入到返回数据中
				map.put("commit", list);
				this.result.put("data", map);
				break;
			}
			// Project project = this.ProjectManager.findProjectById(projectId);

			// 封装返回结果
			this.status = 200;
			this.message = "";
		}

		return getResult();
	}
	
	//基金详情
	@RequestMapping(value="/foundationDetail")
	public String foundationDetail( 
			ModelMap model) {
		return "foundation";
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
