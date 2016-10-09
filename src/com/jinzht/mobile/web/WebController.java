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
import com.jinzht.tools.MessageType;
import com.jinzht.tools.MsgUtil;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.City;
import com.jinzht.web.entity.Contenttype;
import com.jinzht.web.entity.Identiytype;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.MessageBean;
import com.jinzht.web.entity.Rewardsystem;
import com.jinzht.web.entity.Users;
import com.jinzht.web.entity.Weburlrecord;
import com.jinzht.web.hibernate.HibernateSessionFactory;
import com.jinzht.web.manager.AuthenticManager;
import com.jinzht.web.manager.InvestorManager;
import com.jinzht.web.manager.UserManager;
import com.jinzht.web.manager.WebManager;
import com.jinzht.web.test.User;

@Controller
public class WebController extends BaseController {
	@Autowired
	private WebManager webManager;

	@RequestMapping(value = "/admin/generateWebPage")
	public String generateWebPage(
			@RequestParam(value = "recordId", required = false) Integer recordId,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "editorValue", required = false) String content,
			ModelMap map) {
		Weburlrecord record;
		if (recordId != null) {
			record = this.webManager.findRecordById(recordId);
		} else {
			record = new Weburlrecord();
		}

		record.setCreateDate(new Date());
		record.setTitle(title);
		record.setUrl("#");
		record.setTag("");
		record.setContent(content);

		Contenttype contentType = new Contenttype();
		contentType.setTypeId(type);

		record.setContenttype(contentType);

		if(recordId==null)
		{
			this.webManager.addWebUrlRecord(record);
		}

		String path = String.format("%swebUrlLooker.action?contentId=%d",
				Config.STRING_SYSTEM_ADDRESS, record.getRecordId());
		record.setUrl(path);
		this.webManager.getWebUrlRecordDao().saveOrUpdate(record);
		map.put("record", record);
		List<Contenttype> l = this.webManager.getContentTypeDao().findAll();
		map.put("types", l);

		return "/test/editor";
	}

	@RequestMapping(value = "/webEditor")
	public String webEditor() {
		return "/test/editor";
	}

	@RequestMapping(value = "/generateProjectInfo")
	public String generateProjectInfo() {
		return "/test/generateProjectExtInfo";
	}

	@RequestMapping(value = "/webUrlLooker")
	public String webUrlLooker(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			ModelMap map) {
		Weburlrecord record = this.webManager.findRecordById(contentId);

		map.put("title", record.getTitle());
		map.put("content", record.getContent());

		return "templete";
	}
}
