package com.hangdaoju.admin;

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

import com.hangdaoju.core.Global;
import com.hangdaoju.model.Company;
import com.hangdaoju.model.Person;
import com.hangdaoju.service.CompanyService;
import com.hangdaoju.service.PersonService;
import com.hangdaoju.util.EncryptUtils;
import com.hangdaoju.util.StringUtils;
import com.hangdaoju.util.ehcache.EHCacheUtil;

@Controller
@RequestMapping("/admin/person")
public class PersonAdminController {
	
	
	
	
	@Autowired
	private PersonService personService;
	@Autowired
	private CompanyService companyService;
	@GetMapping(value="/index")
	public String index(){
		return "/view/login";
	}
	@PostMapping("/login")
	public String login(String userName,String password,Model model,HttpServletRequest request){
		if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
			model.addAttribute("error", "用户名或密码为空");
			return "/condition";
		}
		Person person = personService.login(userName);
		
		if (person == null) {
			model.addAttribute("error", "用户名不存在");
			return "/condition";
		}
		String passwordCode = person.getPassword();
		try {
			if (!passwordCode.equals(EncryptUtils.Encrypt3DES(password, Global.getCuntomConfig("saltKey")))) {
				model.addAttribute("error", "用户名或密码错误");
				return "/condition";
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
		request.getSession().setAttribute("adminCurrentUser",userName);
		return "redirect:/admin/person/list"; 
	}
	/**
	 * 分页查询用户列表
	 * @param model
	 * @return
	 */
	@GetMapping(value="/list")
	public String findList(Model model){
		List<Person> personList = personService.getList();
		model.addAttribute("personList", personList);
		return "/view/person/personList";
	}
	/**
	 * 根据用户id删除用户
	 * @param id
	 * @return
	 */
	@RequestMapping(value="delete")
	public String deletePerson(String id,Model model){
		if(StringUtils.isNotBlank(id)){
			 personService.deletePerson(id);
			 return "redirect:/admin/person/list"; 
		}
		model.addAttribute("error", "id不能为空");
		return "/condition";
	}
	/**
	 * 跳转到新增/修改用户页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="edit")
	public String edit(String id,Model model){
		List<Company> companyList = companyService.findAll();
		model.addAttribute("companyList", companyList);
		if(StringUtils.isNotBlank(id)){
			Person person = personService.findById(id);
			model.addAttribute("person", person);
			return "/view/person/personEdit";
		}
		return "/view/person/personEdit";
	}
	/**
	 * 修改用户
	 * @param person
	 * @param model
	 * @return
	 */
	@RequestMapping(value="update")
	public String update(Person person,Model model){
		if(StringUtils.isNotBlank(person.getId())){
			personService.update(person);
			return "redirect:/admin/person/list"; 
		}
		model.addAttribute("error", "id不能为空");
		return "/condition";
	}
	/**
	 * 新增用户
	 * @param person
	 * @param model
	 * @return
	 */
	@RequestMapping(value="add")
	public String add(Person person,Model model){
		//当插入情况时，因为与edit公用相同页面所以导致会出现为空非nul的情况
		if("".equals(person.getId())){
			person.setId(null);
		}
		//必须字段校验
		if(StringUtils.isBlank(person.getPassword()) || 
				StringUtils.isBlank(person.getUserName()) ||
				StringUtils.isBlank(person.getEmail())){
			model.addAttribute("error", "字段格式不正确");
			return "/condition";
		}
		if(person.getPassword().trim().length()==0 ||
				person.getUserName().trim().length()==0 ||
				person.getEmail().trim().length()==0){
			model.addAttribute("error", "字段去空格后为空");
			return "/condition";
		}
		return personService.register(person,model);
	}
}
