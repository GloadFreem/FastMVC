package com.jinzht.web.filter;

import javax.interceptor.Interceptor;
import javax.jws.soap.InitParam;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.jinzht.tools.Config;


public class LogInterceptor implements HandlerInterceptor {
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest hrequest,
			HttpServletResponse hresponse, Object arg2) throws Exception {
		// 获取session会话
		HttpSession session = hrequest.getSession(true);
		
		// 输出请求信息
		System.out.println("Filter 截获到用户倾听求地址:  " + hrequest.getServletPath());
		
		//如果带有直连接口属性，直接通过
		if(hrequest.getParameter("requestType")!=null)
		{
			String typeString = hrequest.getParameter("requestType").toString();
			if(typeString!=null && typeString.equals("webRequest")){
				session.setAttribute("userId", 5333);
				return true;
			}
		}
		
		long after = System.currentTimeMillis();

		System.out.println("过滤结束...");
		// 获取客户请求页面路径
		String requestPath = hrequest.getServletPath();
		
		//官网
		if(requestPath.contains("/web/") || requestPath.contains("/wechat/") )
		{
			return true;
		}

		boolean flag = false;
		for (Object str : Config.STRING_INTEFACE_FLITER) {
			String path = str.toString();
			if (requestPath.endsWith(path)) {
				flag = true;
				break;
			}
		}

		if (session.getAttribute("userId") == null && !flag) {
			if (!requestPath.contains("admin")) {
				boolean  signal = false;
				for (Object str : Config.STRING_INTEFACE_ARRAY_FLITER) {
					String path = str.toString();
					if (requestPath.endsWith(path)) {
						signal = true;
						break;
					}
				}
				// 重定位到登录页面
				hrequest.setAttribute("tip", "您没有登录！");
				hresponse.sendRedirect("noLogoInfo.action?flag="+signal);
				return false;
			} else {
				// 重定位到登录页面
				flag = false;
				for (Object str : Config.STRING_INTEFACE_WEB_FLITER) {
					String path = str.toString();
					if (requestPath.endsWith(path)) {
						flag = true;
						break;
					}
				}
				if (session.getAttribute("userId") == null && !flag) {
					session.setAttribute("tip", "您没有登录！");
					hresponse.sendRedirect("adminLogin.action");
					return false;
				}
			}
		}

		return true;
	}

}
