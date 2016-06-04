package com.jinzht.mobile.web;

import java.io.File;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jinzht.tools.Config;
import com.jinzht.tools.FileUtil;
import com.jinzht.tools.MessageType;
import com.jinzht.tools.MsgUtil;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.AuthenticEntity;
import com.jinzht.web.entity.Authenticstatus;
import com.jinzht.web.entity.City;
import com.jinzht.web.entity.Identiytype;
import com.jinzht.web.entity.Industoryarea;
import com.jinzht.web.entity.Industorytype;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.MessageBean;
import com.jinzht.web.entity.Users;
import com.jinzht.web.hibernate.HibernateSessionFactory;
import com.jinzht.web.manager.AuthenticManager;
import com.jinzht.web.manager.UserManager;
import com.jinzht.web.test.User;

@Controller
public class AuthenticController extends BaseController {

	@Autowired
	private AuthenticManager authenticManager;
	@Autowired
	private UserManager userManager;

	@RequestMapping(value="/updateIdentiyTypeUser")
	@ResponseBody
	/***
	 * 忘记密码
	 * @param userInstance
	 * @param bindingResult
	 * @param session
	 * @return
	 */
	public Map updateIdentiyTypeUser(
			@RequestParam(value = "ideniyType") short ideniyType,
			@RequestParam(value = "file",required = false) MultipartFile file,
			HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");
		
		// 获取用户
		Users user = this.findUserInSession(session);
		
		//身份类型
		if (ideniyType == 0) {
			this.status = 400;
			this.message = Config.STRING_AUTH_IDENTIY_TYPE_NOT_NULL;

			return getResult();
		}
		
		String headerPicture = "";
		if(file !=null && !file.isEmpty()){
			 //定义一个数组，用于保存可上传的文件类型  
	         List fileTypes = new ArrayList();  
	         fileTypes.add("jpg");  
	         fileTypes.add("jpeg");  
	         fileTypes.add("bmp");  
	         fileTypes.add("gif"); 
	         
	       //获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名 
	        String orignalFileName = file.getOriginalFilename();
	        String ext = orignalFileName.substring(orignalFileName.lastIndexOf(".")+1,orignalFileName.length()); 
			
	        System.out.println("文件长度: " + file.getSize());  
            System.out.println("文件类型: " + file.getContentType());  
            System.out.println("文件名称: " + file.getName());  
            System.out.println("文件原名: " + file.getOriginalFilename());  
            System.out.println("========================================");
            
            //保存
            String fileName = String.format(Config.STRING_USER_HEADER_PICTURE_FORMAT, user.getUserId())
                    + "." + ext;
            if(FileUtil.saveFile(fileName, file, "upload")){
            	headerPicture = "upload/"+fileName;
            }
		}


		if (user != null) {
			// 获取身份类型
			Identiytype identityType = this.authenticManager
					.findIdentityTypeById(ideniyType);
			// 生成认证记录
			Authentic authentic = new Authentic();
			authentic.setIdentiytype(identityType);

			// 更新用户登录信息
			Set authenticSet = new HashSet();
			authenticSet.add(authentic);

			user.setAuthentics(authenticSet);
			
			//头像
			if(!headerPicture.equals("")){
				user.setHeadSculpture(headerPicture);
			}

			// 更新用户登录信息
			this.userManager.saveOrUpdateUser(user);

			// 封装返回数据
			this.status = 200;
			this.message = Config.STRING_AUTH_IDENTIY_SUCCESS;
		} else {
			this.status = 400;
			this.message = Config.STRING_AUTH_IDENTIY_FAIL;
		}

		return getResult();
	}

	@RequestMapping("/getProtocolAuthentic")
	@ResponseBody
	/***
	 * 忘记密码
	 * @param userInstance
	 * @param bindingResult
	 * @param session
	 * @return
	 */
	public Map getProtocolAuthentic(HttpSession session) {
		this.result = new HashMap();
		this.status=200;
		this.result.put("data",  Config.STRING_AUTH_QUALIFICATION);

		return getResult();
	}
	@RequestMapping("/getIndustoryTypeAuthentic")
	@ResponseBody
	/***
	 * 获取行业列表
	 * @param session
	 * @return
	 */
	public Map getIndustoryTypeAuthentic(HttpSession session) {
		this.result = new HashMap();
		this.status=200;
		
		List list = this.authenticManager.findAllIndustoryType();
		this.result.put("data",  list);
		
		return getResult();
	}
	@RequestMapping("/getProvinceListAuthentic")
	@ResponseBody
	/***
	 * 获取省份列表
	 * @param session
	 * @return
	 */
	public Map getProvinceListAuthentic(HttpSession session) {
		this.result = new HashMap();
		this.status=200;
		
		List list = this.authenticManager.findAllProvinceList();
		this.result.put("data",  list);
		
		return getResult();
	}
	@RequestMapping("/getCityListAuthentic")
	@ResponseBody
	/***
	 * 获取省份列表
	 * @param session
	 * @return
	 */
	public Map getCityListAuthentic(HttpSession session) {
		this.result = new HashMap();
		this.status=200;
		
		List list = this.authenticManager.findAllCityList();
		this.result.put("data",  list);
		
		return getResult();
	}
	@RequestMapping("/getIndustoryAreaListAuthentic")
	@ResponseBody
	/***
	 * 获取行业类型
	 * @param session
	 * @return
	 */
	public Map getIndustoryAreaListAuthentic(HttpSession session) {
		this.result = new HashMap();
		this.status=200;
		
		List list = this.authenticManager.findAllIndustoryList();
		this.result.put("data",  list);
		
		return getResult();
	}
	@RequestMapping("/getCityListByProvinceIdAuthentic")
	@ResponseBody
	/***
	 * 根据省份id获取城市
	 * @param session
	 * @return
	 */
	public Map getCityListByProvinceIdAuthentic(@RequestParam(value="provinceId",required = false) Integer provinceId, HttpSession session) {
		this.result = new HashMap();
		this.status=200;
		
		List list = this.authenticManager.findCitiesByProvinceId(provinceId);
		this.result.put("data",  list);
		
		return getResult();
	}
	
	
	@RequestMapping("/requestAuthentic")
	@ResponseBody
	/***
	 * 获取省份列表
	 * @param session
	 * @return
	 */
	public Map requestAuthentic(@Valid AuthenticEntity entity ,BindingResult bindingResult,HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");
		this.status=200;
		if (bindingResult.hasErrors()) {
			this.status = 400;
			this.message = bindingResult.getFieldError().getDefaultMessage();
		}else{
			Users user = this.findUserInSession(session);
			if(user!=null){
				//获取区域
				Industoryarea industoryArea = this.authenticManager.findIndustoryAreaById(entity.getAreaId());
				//获取行业
				Industorytype industoryType = this.authenticManager.findIndustorytypeById(entity.getIndustoryId());
				//获取身份类型
				Identiytype identiytype = null;
				//获取城市地址
				City city = this.authenticManager.findCityByCityId(entity.getCityId());
				
				
				//获取认证状态
				Object[] list = user.getAuthentics().toArray();
				Authenticstatus status = null;
				Authentic authentic = null;
				if(list!=null && list.length>0){
					if(list.length>1){
						for(int i = 0;i<list.length;i++){
							authentic = (Authentic) list[i];
							status =authentic.getAuthenticstatus();
							//获取用户身份认证状态
							int authStatus = Config.STRING_AUTH_STATUS.get(status.getName());
							if(authStatus==2){
								authentic.setIntroduce(entity.getIntroduce());
								authentic.setPosition(entity.getPosition());
								authentic.setBuinessLicenceNo(entity.getBuinessLicenceNo());
								authentic.setCompanyAddress(entity.getCompanyAddress());
								authentic.setCompanyIntroduce(entity.getCompanyIntroduce());
								authentic.setCompanyName(entity.getCompanyName());
								authentic.setIdentiyCarNo(entity.getIdentiyCarNo());
								authentic.setCity(city);
								authentic.setIndustoryarea(industoryArea);
								authentic.setIndustorytype(industoryType);
								authentic.setName(entity.getName());
								authentic.setOptional(entity.getOptional());
							}else{
								this.status = 200;
								this.message= Config.STRING_AUTH_CONFIRM_FAIL;
								
								return getResult();
							}
						}
					}else{
						authentic = (Authentic) list[0];
						status =authentic.getAuthenticstatus();
					
						//获取用户身份认证状态
						int authStatus = Config.STRING_AUTH_STATUS.get(status.getName());
						if(authStatus != 3){
							authentic.setIntroduce(entity.getIntroduce());
							authentic.setPosition(entity.getPosition());
							authentic.setBuinessLicenceNo(entity.getBuinessLicenceNo());
							authentic.setCompanyAddress(entity.getCompanyAddress());
							authentic.setCompanyIntroduce(entity.getCompanyIntroduce());
							authentic.setCompanyName(entity.getCompanyName());
							authentic.setIdentiyCarNo(entity.getIdentiyCarNo());
							authentic.setCity(city);
							authentic.setIndustoryarea(industoryArea);
							authentic.setIndustorytype(industoryType);
							authentic.setName(entity.getName());
							authentic.setOptional(entity.getOptional());
							
						}else{
							this.status = 400;
							this.message = Config.STRING_AUTH_HAS;
							
							return getResult();
						}
					}
				}else{
					authentic = new Authentic();
					authentic.setUsers(user);
					authentic.setIntroduce(entity.getIntroduce());
					authentic.setPosition(entity.getPosition());
					authentic.setBuinessLicenceNo(entity.getBuinessLicenceNo());
					authentic.setCompanyAddress(entity.getCompanyAddress());
					authentic.setCompanyIntroduce(entity.getCompanyIntroduce());
					authentic.setCompanyName(entity.getCompanyName());
					authentic.setIdentiyCarNo(entity.getIdentiyCarNo());
					authentic.setCity(city);
					authentic.setIndustoryarea(industoryArea);
					authentic.setIndustorytype(industoryType);
					authentic.setName(entity.getName());
					authentic.setOptional(entity.getOptional());
				}
				
				//保存更新用户的身份认证信息
				this.userManager.saveOrUpdateUser(user);
				this.message = Config.STRING_AUTH_SUBMMIT_SUCCESS;
			}else{
				this.message=Config.STRING_LOGING_TIP;
			}
		}
		
		
		return getResult();
	}
	@RequestMapping("/authenticInfoUser")
	@ResponseBody
	/***
	 * 身份认证信息
	 * @param session
	 * @return
	 */
	public Map authenticInfoUser(HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");
		this.status=200;
		
		Users user = this.findUserInSession(session);
		if(user!=null){
			this.status=200;
			this.result.put("data",  user.getAuthentics());
			this.message="";
		}else{	
			this.message=Config.STRING_LOGING_TIP;
		}
		
		
		return getResult();
	}
	
	@RequestMapping("/checkAuthenticStatusUser")
	@ResponseBody
	/***
	 * 检查身份认证状态
	 * @param session
	 * @return
	 */
	public Map checkAuthenticStatusUser(HttpSession session) {
		this.result = new HashMap();
		this.result.put("data", "");
		this.status=200;
		
		Users user = this.findUserInSession(session);
		if(user!=null){
			Object[] list = user.getAuthentics().toArray();
			
			Map map =new  HashMap();
			map.put("status", "0");
			map.put("name", "未认证");
			
			
			//获取认证状态
			Authenticstatus status = null;
			Authentic authentic = null;
			if(list!=null && list.length>0){
				if(list.length>1){
					status = new Authenticstatus();
					status.setStatusId(3);
					status.setName("认证通过");
				}else{
					authentic = (Authentic) list[0];
					status =authentic.getAuthenticstatus();
					status.setStatusId(Config.STRING_AUTH_STATUS.get(status.getName()));
				}
			}
			
			this.status=200;
			this.result.put("data",  status);
			this.message="";
		}else{
			this.message=Config.STRING_LOGING_TIP;
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
