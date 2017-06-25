package com.hangdaoju.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hangdaoju.model.Person;
import com.hangdaoju.util.StringUtils;
import com.hangdaoju.util.ehcache.EHCacheUtil;

public class AdminMenuInterceptor implements HandlerInterceptor{
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView modelAndView)
			throws Exception {
		String XRequested =request.getHeader("X-Requested-With");
		//校验是否为ajax请求
		if(!"XMLHttpRequest".equals(XRequested)){
			String father = request.getParameter("father");
			String children = request.getParameter("children");
			HttpSession session = request.getSession();
			//对目录选中进行精确校准
			if(StringUtils.isNotBlank(father) && StringUtils.isNotBlank(children)){
				modelAndView.addObject("father", father);
				modelAndView.addObject("children", children);
				session.setAttribute("father", father);
				session.setAttribute("children", children);
			}
			modelAndView.addObject("father", session.getAttribute("father"));
			modelAndView.addObject("children", session.getAttribute("children"));
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		//后台用户通过session校验    当前用户是否已经是登录状态
		String adminCurrentUser = (String)request.getSession().getAttribute("adminCurrentUser");
		if(adminCurrentUser == null){
			response.sendRedirect(request.getContextPath()+"/admin/person/index");
			return false;
		}
		EHCacheUtil.initCacheManager("person");
		Person person = (Person)EHCacheUtil.get(adminCurrentUser);
		if(person == null){
			response.sendRedirect(request.getContextPath()+"admin/person/index");
			return false;
		}
		return true;
	}
}
