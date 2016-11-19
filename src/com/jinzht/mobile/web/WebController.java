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
import com.jinzht.tools.DateUtils;
import com.jinzht.tools.FileUtil;
import com.jinzht.tools.MessageType;
import com.jinzht.tools.MsgUtil;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.City;
import com.jinzht.web.entity.Contenttype;
import com.jinzht.web.entity.Identiytype;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.Member;
import com.jinzht.web.entity.MessageBean;
import com.jinzht.web.entity.Project;
import com.jinzht.web.entity.Rewardsystem;
import com.jinzht.web.entity.Roadshow;
import com.jinzht.web.entity.Roadshowplan;
import com.jinzht.web.entity.Users;
import com.jinzht.web.entity.Weburlrecord;
import com.jinzht.web.hibernate.HibernateSessionFactory;
import com.jinzht.web.manager.AuthenticManager;
import com.jinzht.web.manager.InvestorManager;
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
public class WebController extends BaseController {
	@Autowired
	private WebManager webManager;
	@Autowired
	private MessageMananger messageManager;
	@Autowired
	private MainManager mainManager;
	@Autowired
	private ProjectManager projectManager;

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
	
	/***
	 * 网站首页
	 * @return
	 */
	@RequestMapping(value = "/web/index")
	public String MainIndex(ModelMap map) {
		//鑾峰彇banner
		List<Newsbanner> bList = messageManager.getNewsBanner();
		map.put("BannerList", bList);
		
		//鑾峰彇鐑棬璧勮
		List<MsgBean> hotList = messageManager.getHotList();
		map.put("HotList", hotList);
		return "/web/html/content/main";
	}
	
	/***
	 * 资讯详情
	 * @return
	 */
	@RequestMapping(value = "/web/MainDetail")
	public String MainDetail(@RequestParam("id") Integer id,ModelMap model) {
		 Msg msg = messageManager.getMsgDao().findById(id);
		 //璁板綍hot
		 Integer hot = msg.getHot()==null?0:msg.getHot();
		 msg.setHot(hot+1);
		 messageManager.getMsgDao().saveOrUpdate(msg);
		    Set<MsgDetail> msgDetail=msg.getMsgDetails();
		    String detail = "";
		    for(MsgDetail det:msgDetail){
		    	detail+=det.getContent();
		    }
		    //璇︽儏
			model.put("Detail", detail);
			//鏍囬
			model.put("Title", msg.getTitle());
			//鐩稿叧璧勮锛�			
		return "/web/html/content/main-detail";
	}
	
	/***
	 * 分析报告
	 * @return
	 */
	@RequestMapping(value = "/web/report.action")
	public String reportList(ModelMap map) {
		//鑾峰彇banner
				List<Originalbanner> oList = messageManager.getOriginalBannerList();
				map.put("BannerList", oList);
				
				
				//鑾峰彇鐑棬鎶ュ憡
				List<Original> hotReportList = mainManager.getHotList();
				map.put("HotReportList", hotReportList);
				
				//鑾峰彇鐑棬璧勮
				List<MsgBean> hotList = messageManager.getHotList();
				System.out.print(hotList.size());
				map.put("HotList", hotList);
		return "/web/html/content/report";
	}
	/***
	 *报告详情
	 * @return
	 */
	@RequestMapping(value = "/web/reportDetail.action")
	public String reportDetail(@RequestParam("id") Integer id,ModelMap model) {

		Original original = (Original) this.mainManager
				.obtainOriginalByInfoId(id);
		 //璁板綍hot
		 Integer hot = original.getHot()==null?0:original.getHot();
		original.setHot(hot+1);
		mainManager.getOrigianlDao().saveOrUpdate(original);
		//detail
		Set<OriginalDetail> originalDetails = original.getOriginalDetails();
		String detail = "";
		for (OriginalDetail value : originalDetails) {
			detail +=value.getContent();
		}
		model.put("Detail", detail);
		model.put("Title", original.getTitle());

		return "/web/html/content/report-detail";
	}
	/***
	 *项目展示
	 * @return
	 */
	@RequestMapping(value = "/web/project.action")
	public String projectList() {
		return "/web/html/content/project";
	}
	/***
	 *项目详情
	 * @return
	 */
	@RequestMapping(value = "/web/projectDetail.action")
	public String projectDetail(@RequestParam("id") Integer id,ModelMap model) {
		

		// 鑾峰彇椤圭洰
		Project project = this.projectManager.findProjectById(id);
		if(project!=null)
		{
//						// 鑾峰彇鐢ㄦ埛鏄惁宸插叧娉ㄨ椤圭洰
//						Collection collection = this.ProjectManager
//								.findProjectCollectionByUser(project, user);
//
//						if (collection != null) {
//							project.setCollected(true);
//						} else {
				project.setCollected(false);

			String desc = project.getDescription();
			
			desc = desc.replace("\n", "<br>");
			project.setDescription(desc);
			System.out.println("");
			System.out.println(desc);
			// 灏佽杩斿洖缁撴灉
			model.put("Project", project);
			Set<Roadshow> roadshows = project.getRoadshows();
			List<Roadshow> rList = new ArrayList<Roadshow>();
			for(Roadshow a:roadshows){
				rList.add(a);
			}
			
			int peset = rList.get(0).getRoadshowplan().getFinancedMount()*100/rList.get(0).getRoadshowplan().getFinanceTotal();
			model.put("NumPeset", peset);
			int time=0;
			try {
				time = DateUtils.getDaysBetween(rList.get(0).getRoadshowplan().getBeginDate(), rList.get(0).getRoadshowplan().getEndDate());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Roadshowplan road = rList.get(0).getRoadshowplan();
			//鍙戣捣浜�		
			Set<Member> menSet = project.getMembers();
			List<Member> menList = new ArrayList<Member>();
			for(Member a:menSet){
				menList.add(a);
			}
			if(menList.size()==0){
				menList.add(new Member());
			}
			model.put("Menber",menList.get(0));
			model.put("Roadshowplan",road);
			model.put("OverTime",time);
		}else{
			// 灏佽杩斿洖缁撴灉
			model.put("Detail", "");
		}
		
//		model.put("Title", original.getTitle());
		return "/web/html/content/project-detail";
	}
	/***
	 *登录
	 * @return
	 */
	@RequestMapping(value = "/web/login.action")
	public String userLogin() {
		return "/web/html/user/Login";
	}
	/***
	 *注册
	 * @return
	 */
	@RequestMapping(value = "/web/regist.action")
	public String userRegist() {
		return "/web/html/user/Register";
	}
	/***
	 *个人中心
	 * @return
	 */
	@RequestMapping(value = "/web/personalCenter.action")
	public String personalCenter() {
		return "/web/html/user/PersonCenter";
	}
	
}
