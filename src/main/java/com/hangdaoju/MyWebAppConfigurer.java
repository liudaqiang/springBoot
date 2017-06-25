package com.hangdaoju;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hangdaoju.core.interceptor.AdminMenuInterceptor;
import com.hangdaoju.core.interceptor.LoginInterceptor;
import com.hangdaoju.core.interceptor.PersonLoginInterceptor;

@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter{
	   @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        // 多个拦截器组成一个拦截器链
	        // addPathPatterns 用于添加拦截规则
	        // excludePathPatterns 用户排除拦截
	        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/**","/person/login","/person/backPassword","/person/updatePasswordByEmal","/person/emailPassword","/common/toLogin","/picture/**");
	        registry.addInterceptor(new PersonLoginInterceptor()).addPathPatterns("/person/login");
	        registry.addInterceptor(new AdminMenuInterceptor()).addPathPatterns("/admin/**").excludePathPatterns("/admin/person/login","/admin/person/index","/picture/**");
	        super.addInterceptors(registry);
	    }
}
