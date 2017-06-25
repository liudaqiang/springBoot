package com.hangdaoju.util;

import javax.servlet.http.HttpServletRequest;

import com.hangdaoju.model.Person;
import com.hangdaoju.util.ehcache.EHCacheUtil;

public class CustomUtils {
	/**
	 * 前台获取currentUser工具类
	 * @param request
	 * @return
	 */
	public static String getCustomUser(HttpServletRequest request){
		String currentUser = (String)request.getParameter("currentUser");
		EHCacheUtil.initCacheManager("person");
		Person person = null;
		try{
			person = (Person)EHCacheUtil.get(currentUser);
		}catch(Exception e){
			return null;
		}
		return person.getId();
	}
}
