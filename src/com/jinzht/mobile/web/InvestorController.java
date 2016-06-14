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
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.Comment;
import com.jinzht.web.entity.Contentimages;
import com.jinzht.web.entity.Contentprise;
import com.jinzht.web.entity.Industoryarea;
import com.jinzht.web.entity.Investorcollect;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.Publiccontent;
import com.jinzht.web.entity.Share;
import com.jinzht.web.entity.Sharetype;
import com.jinzht.web.entity.Users;
import com.jinzht.web.hibernate.HibernateSessionFactory;
import com.jinzht.web.manager.InvestorManager;
import com.jinzht.web.manager.SystemManager;
import com.jinzht.web.manager.UserManager;
import com.sun.mail.imap.protocol.Item;

@Controller
public class InvestorController extends BaseController {

	@Autowired
	private InvestorManager investorManager;
	@Autowired
	private UserManager userManager;

	@RequestMapping(value = "/requestInvestorList")
	@ResponseBody
	/***
	 * 获取投资人列表
	 * @param page
	 * @param type
	 * @param session
	 * @return
	 */
	public Map requestInvestorList(
			@RequestParam(value = "page", required = true) Integer page,
			@RequestParam(value = "type", required = true) short type,
			HttpSession session) {
		this.result = new HashMap();

		// 获取投资人信息
		Users user = this.findUserInSession(session);
		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {
			// 获取列表
			List list = this.investorManager.findInvestorByCursor(user, page,
					type);
			if (list != null && list.size() > 0) {
				this.status = 200;
				this.message = "";
				if (type != 3) {
					this.result.put("data", list);
				} else {

					Map map = new HashMap();
					map.put("investors", list);

					if (page == 0) {
						List l = this.investorManager.findDefaultFoundations();
						if (l == null) {
							l = new ArrayList();
						}

						map.put("founddations", l);
					}

					this.result.put("data", map);
				}
			} else {
				
				Map map = new HashMap();
				map.put("investors", list);
				
				this.status = 201;
				this.result.put("data",map);
				this.message = "";
			}
		}

		return getResult();
	}

	@RequestMapping(value = "/requestInvestorDetail")
	@ResponseBody
	/***
	 * 投资人详情
	 * @param investorId 投资人id
	 * @param session
	 * @return
	 */
	public Map requestInvestorDetail(
			@RequestParam(value = "investorId", required = true) Integer investorId,
			HttpSession session) {
		this.result = new HashMap();

		// 获取投资人信息
		Users user = this.userManager.findUserById(investorId);
		// 获取投资领域
		List l = new ArrayList();
		// 获取认证信息
		if (user.getAuthentics() != null && user.getAuthentics().size() > 0) {
			Object[] authentices = user.getAuthentics().toArray();
			Authentic authentic = (Authentic) authentices[0];

			authentic.setAuthenticstatus(null);
			authentic.setIdentiytype(null);
			authentic.setIdentiyCarA(null);
			authentic.setIdentiyCarB(null);
			authentic.setIdentiyCarNo(null);
			authentic.setAuthId(null);
			authentic.setBuinessLicence(null);
			authentic.setBuinessLicenceNo(null);
			authentic.setAutrhrecords(null);
			authentic.setOptional(null);

			String industoryArea = authentic.getIndustoryArea();
			if (industoryArea != null && industoryArea != "") {
				String[] aa = industoryArea.split(",");
				for (int j = 0; j < aa.length; j++) {
					System.out.println("--" + aa[j]);
					Industoryarea area = this.investorManager
							.getIndustoryAreaDao().findById(
									Integer.parseInt(aa[j].toString()));
					l.add(area.getName());
				}
			}

		}

		Map map = new HashMap();

		// 过滤信息
		user.setUserstatus(null);
		user.setTelephone(null);
		user.setPassword(null);
		user.setPlatform(null);
		user.setLastLoginDate(null);

		map.put("user", user);
		map.put("areas", l);

		this.result.put("data", map);

		this.status = 200;
		this.message = "";

		return getResult();
	}

	@RequestMapping(value = "/requestProjectCommit")
	@ResponseBody
	/***
	 * 获取圈子列表
	 * @param page 当前页
	 * @param session
	 * @return
	 */
	public Map requestProjectCommit(
			@RequestParam(value = "projectId", required = true) Integer projectId,
			@RequestParam(value = "content", required = true) String content,
			@RequestParam(value = "userId", required = true) Integer userId,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");

		this.investorManager
				.projectCommitToInvestor(userId, content, projectId);

		this.status = 200;
		this.message = Config.STRING_INVESTOR_PROJECT_COMMIT_SUCCESS;
		return getResult();
	}

	@RequestMapping(value = "/requestInvestorCollect")
	@ResponseBody
	/***
	 * 关注投资人
	 * @param contentId
	 * @param bindingResult
	 * @param session
	 * @return
	 */
	public Map requestInvestorCollect(
			@RequestParam(value = "userId") Integer userId,
			@RequestParam(value = "flag") short flag, HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");

		// 获取投资人信息
		Users user = this.findUserInSession(session);
		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {
			Users collectUser = this.userManager.findUserById(userId);

			Investorcollect collect = this.investorManager
					.findInvestCollectByUser(user, collectUser);
			Map map = new HashMap();
			if (collect != null) {
				this.investorManager.getInvestorCollectDao().delete(collect);
				map.put("flag", 2);
				this.status = 200;
			} else {
				this.investorManager.addInvestCollectByUser(user, collectUser);
				map.put("flag", 1);
				this.status = 200;
			}
			this.result.put("data", map);
		}

		return getResult();
	}

	@RequestMapping(value = "/requestShareInvestor")
	@ResponseBody
	/***
	 * 状态分享
	 * @param contentId
	 * @param content
	 * @param session
	 * @return
	 */
	public Map requestShareInvestor(
			@RequestParam(value = "investorId") Integer investorId,
			HttpSession session) {

		this.result = new HashMap();
		this.result.put("data", "");

		// 获取当前发布内容用户
		Users user = this.findUserInSession(session);

		if (user == null) {
			this.status = 400;
			this.message = Config.STRING_LOGING_FAIL_NO_USER;
		} else {

			Share share = Tools.generateShareContent(investorId, 5);

			// 保存分享记录
			this.investorManager.saveShareRecord(share);

			share.setSharetype(null);
			share.setShareDate(null);
			// 封装返回结果
			this.status = 200;
			this.result.put("data", share);
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
