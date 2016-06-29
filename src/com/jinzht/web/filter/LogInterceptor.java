package com.jinzht.web.filter;

import javax.interceptor.Interceptor;
import javax.jws.soap.InitParam;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

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
		long after = System.currentTimeMillis();

		System.out.println("过滤结束...");
		// 获取客户请求页面路径
		String requestPath = hrequest.getServletPath();
		if (session.getAttribute("userId") == null&& !requestPath.endsWith("registUser.action") && !requestPath.endsWith("noLogoInfo.action")&& !requestPath.endsWith("verifyCode.action")
				&& !requestPath.endsWith("loginUser.action")&& !requestPath.endsWith("resetPassWordUser.action")&& !requestPath.endsWith("wechatLoginUserw.action")&& !requestPath.endsWith("androidTest.action")) {
			// 重定位到登录页面
			hrequest.setAttribute("tip", "您没有登录！");
			hresponse.sendRedirect("noLogoInfo.action");
		}

		return true;
	}

}
