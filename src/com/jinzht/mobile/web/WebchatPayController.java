package com.jinzht.mobile.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.jinzht.tools.HttpUtil;
import com.jinzht.tools.OauthBean;
import com.jinzht.web.entity.BusinessSchool;
import com.jinzht.web.entity.Users;
import com.jinzht.web.manager.CourseManager;
import com.jinzht.web.manager.UserManager;

/**
 * 微信公众号支付
 * 
 * @author EasonYang
 *
 */
@Controller
public class WebchatPayController extends BaseController {

	private final String OPENID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxdcd3847ae7077ffe&secret=483fb1af10abb33c5d40ffccc7e2961a&grant_type=authorization_code&code=";

	@Autowired
	private CourseManager courseManager;

	@Autowired
	private UserManager userManager;

	/***
	 * 课程详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "/wechat/detail.action")
	public String addressUs(
			@RequestParam(value = "state", required = false) Integer state,
			@RequestParam(value = "code", required = false) String code,
			ModelMap map) {
		
		//微信auth认证通过
		if(code!=null&&!code.equals("")){
			if(state!=null){
				BusinessSchool school = this.courseManager.getBuinessSchoolDao()
						.findById(state);
				if(school!=null){
					//code 转换 openid  uid
					OauthBean  ob = new OauthBean();	
					try {
						String   jsonStr = HttpUtil.getHtmlString(OPENID_URL+code);
						System.out.println("oauthbeanStr:"+jsonStr);
						Gson gson = new Gson();
						java.lang.reflect.Type type = new TypeToken<OauthBean>() {
						}.getType();
						ob = gson.fromJson(jsonStr, type);
					} catch (Exception e) {
						e.printStackTrace();
					}	
					if(ob.getUnionid()!=null&&!ob.getUnionid().equals("")){
						//获取成功！
						String  uid = ob.getUnionid();	
						map.put("uid", uid);
						map.put("openId", ob.getOpenid());
						map.put("contentId", state);
						String inputUrl = ""+uid;
						System.out.println("获取成功:"+uid);
						//是否参课
						List<Users> users = this.userManager.getUserDao().findByProperty("unionid",uid);
						if(users!=null&&users.size()>0){
							List<BusinessSchool>  l = this.courseManager.getBusniessJoinDao().findByProperty("users", users.get(0));
							if(l!=null&&l.size()>0){
		
							map.put("isJoin", "true");
							}
						}
						
					}
								
					map.put("data", school);
				}else{
					map.put("data", new BusinessSchool());
				}
			}else{
				map.put("data", new BusinessSchool());
			}
			
			return "/wechat/detail";
		}else{
			//未微信auth认证
			return "/wechat/detail";
		}
	}

	/***
	 * 课程详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "/web/detail2.action")
	public String addressUs2() {
		return "/web/html/content/main";
	}

	/***
	 * 用户验证
	 * 
	 * @return
	 */
	@RequestMapping(value = "/wechat/input.action")
	public String service() {
		return "/wechat/input";
	}

	/***
	 * 提交支付
	 * 
	 * @return
	 */
	@RequestMapping(value = "/wechat/pay.action")
	public String policy() {
		return "/wechat/zhifu";
	}

	/***
	 * 支付完成
	 * 
	 * @return
	 */
	@RequestMapping(value = "/wechat/result.action")
	public String pay() {
		return "/wechat/result";
	}

}
