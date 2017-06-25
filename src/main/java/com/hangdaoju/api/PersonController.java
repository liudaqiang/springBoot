package com.hangdaoju.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hangdaoju.core.Global;
import com.hangdaoju.core.ResponseModel;
import com.hangdaoju.model.Person;
import com.hangdaoju.service.PersonService;
import com.hangdaoju.util.EncryptUtils;
import com.hangdaoju.util.GetMapUtils;
import com.hangdaoju.util.StringUtils;
import com.hangdaoju.util.ehcache.EHCacheUtil;

import static com.hangdaoju.util.StringUtils.*;

/**
 * 用户操作
 * 
 * @author lq
 *
 */
@Controller
@RequestMapping("/person")
public class PersonController {
	@Autowired
	private PersonService personService;

	// /**
	// * 用户注册
	// *
	// * @param userName
	// * @param password
	// * @param email
	// * @return
	// */
	// @PostMapping("register")
	// @ResponseBody
	// public ResponseModel register(String userName, String password, String
	// email) {
	// if (StringUtils.isNotBlank(password) && StringUtils.isNotBlank(userName)
	// && StringUtils.isNotBlank(email)
	// && VolidateUtils.isChinaPhoneLegal(userName) &&
	// VolidateUtils.isEamil(email)) {
	// return personService.register(userName, password, email);
	// }
	// return new ResponseModel(300, null, "请按照规范输入");
	// }

	/**
	 * 用户登录
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	@PostMapping("login")
	@ResponseBody
	public ResponseModel login(String userName, String password, HttpServletRequest request) {
		if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
			return new ResponseModel(300, null, "账号或密码不能为空");
		}
		Person person = personService.login(userName);

		if (person == null) {
			return new ResponseModel(300, null, "用户名不存在");
		}
		String passwordCode = person.getPassword();
		try {
			if (!passwordCode.equals(EncryptUtils.Encrypt3DES(password, Global.getCuntomConfig("saltKey")))) {
				return new ResponseModel(300, null, "用户名或密码错误");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("person", person);
		// 将当前用户放入到缓存中
		EHCacheUtil.initCacheManager("person");
		EHCacheUtil.put(userName, person);
		if (password.equals("111111")) {
			return new ResponseModel(200, GetMapUtils.getMap("person", person), "首次登录成功,请修改初始密码！");
		}
		return new ResponseModel(200, GetMapUtils.getMap("person", person), "登录成功");
	}

	/**
	 * 修改用户密码
	 * 
	 * @param userName
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	@PostMapping("updatePersonPassword")
	@ResponseBody
	public ResponseModel updatePersonPassword(String userName, String oldPassword, String newPassword)
			throws Exception {
		if (EHCacheUtil.get(userName) == null) {
			return new ResponseModel(300, null, "此用户非当前用户不能修改");
		}
		if (StringUtils.isBlank(newPassword)) {
			return new ResponseModel(300, null, "新密码不能为空");
		}
		if (newPassword.equals("111111")) {
			return new ResponseModel(300, null, "请不要将密码设置为初始密码！");
		}
		return personService.updatePersonPassword(userName, oldPassword, newPassword);
	}

	/**
	 * 根据邮件修改密码
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	@PostMapping("updatePasswordByEmal")
	@ResponseBody
	public ResponseModel updatePasswordByEmal(String userName, String password) {
		if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)) {
			return personService.updatePasswordByEmail(userName, password);
		}
		return new ResponseModel(300, null, "账号或密码不能为空");
	}

	/**
	 * 用户找回密码 发送邮件
	 * 
	 * @param userName
	 * @param request
	 * @return
	 */
	@PostMapping("backPassword")
	@ResponseBody
	public ResponseModel backPassword(String userName, HttpServletRequest request) {
		if (StringUtils.isBlank(userName)) {
			return new ResponseModel(300, null, "用户名不能为空");
		}
		return personService.backPassword(userName, request);
	}

	/**
	 * 邮箱返回页面
	 * 
	 * @param param
	 * @param model
	 * @return
	 */
	@GetMapping("emailPassword")
	public String emailPassword(String param, Model model) {
		if (param != null && !param.equals("")) {// 由于由于http请求会将原本&符号变成空格形式，这里修改成加号
			return personService.emailPassword(param, model);
		}
		model.addAttribute("error", "传入的参数不正确");
		return "error";
	}

	/**
	 * 条件查询用户列表 companyId(可传)
	 * 
	 * @return
	 */
	@PostMapping("findByCondition")
	@ResponseBody
	public ResponseModel findByCondition(Person person) {
		List<Person> personList = personService.findByCondition(person);
		return new ResponseModel(200, GetMapUtils.getMap("personList", personList), "查询成功");
	}

	@PostMapping("/add")
	@ResponseBody
	public ResponseModel add(Person person) {
		if (isAnyBlank(person.getUserName(), person.getName(), person.getPassword(), person.getCompanyId(),
				person.getProjectId(), person.getAvatar(), person.getPersonType())) {
			return new ResponseModel(300, null, "参数不得为空");
		}
		try {
			person.setPassword(EncryptUtils.Encrypt3DES(person.getPassword(), Global.getCuntomConfig("saltKey")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 校验用户名是否存在
		Person checkPerson = new Person();
		checkPerson.setUserName(person.getUserName());
		List<Person> exsited = personService.findByCondition(checkPerson);
		if (!exsited.isEmpty()) {
			return new ResponseModel(300, null, "用户已经存在");
		}
		person.setCreateTime(new Date());
		person.setUpdateTime(new Date());
		String id = personService.add(person);
		return new ResponseModel(200, GetMapUtils.getMap("person_id", id), "添加成功");
	}
}
