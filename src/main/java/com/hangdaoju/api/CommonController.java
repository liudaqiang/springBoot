package com.hangdaoju.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hangdaoju.core.ResponseModel;

@Controller
@RequestMapping("/common")
public class CommonController {
	@GetMapping("/toLogin")
	@ResponseBody
	public ResponseModel toLogin(){
		return new ResponseModel(300,null,"没有登录");
	}
}
