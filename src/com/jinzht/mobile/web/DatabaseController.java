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
import com.jinzht.web.manager.DatabaseManager;
import com.jinzht.web.manager.SystemManager;
import com.jinzht.web.manager.UserManager;

@Controller
public class DatabaseController extends BaseController {

	@Autowired
	private DatabaseManager databaseManager;
	@Autowired
	private UserManager userManager;

	@RequestMapping(value = "/requestMoveDatabaseTable")
	@ResponseBody
	/***
	 * 迁移数据库
	 * @param page 页数
	 * @param table 要迁移表
	 * @param session
	 * @return
	 */
	public Map requestMoveDatabaseTable(
			@RequestParam(value = "page", required = true) Integer page,
			@RequestParam(value = "table", required = true) String table,
			@RequestParam(value = "size", required = true) Integer size,
			HttpSession session) {
		this.result = new HashMap();

		this.databaseManager.moveProjectTable(table, page,size);
		 
		this.status = 200;
		this.result.put("data","");
//		this.result.put("data", this.databaseManager.moveProjectTableData(table, page));
		this.message = "开始迁移数据:"+table;
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
