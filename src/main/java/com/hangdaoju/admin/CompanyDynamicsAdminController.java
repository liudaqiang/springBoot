package com.hangdaoju.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hangdaoju.core.Page;
import com.hangdaoju.model.Company;
import com.hangdaoju.model.CompanyDynamics;
import com.hangdaoju.model.Person;
import com.hangdaoju.service.*;
import com.hangdaoju.util.StringUtils;
import com.hangdaoju.util.ehcache.EHCacheUtil;

@Controller
@RequestMapping("/admin/companyDynamics")
public class CompanyDynamicsAdminController {
	@Autowired
	private CompanyDynamicsService companyDynamicsService;

	@Autowired
	private CompanyService companyService;

	/**
	 * 新增动态
	 * 
	 * @param companyDynamics
	 */
	@RequestMapping(value = "add")
	public String add(CompanyDynamics companyDynamics, HttpServletRequest request) {
		companyDynamics.setCreateTime(new Date());
		companyDynamics.setUpdateTime(new Date());
		String adminCurrentUser = (String) request.getSession().getAttribute("adminCurrentUser");
		EHCacheUtil.initCacheManager("person");
		Person person = null;
		try {
			person = (Person) EHCacheUtil.get(adminCurrentUser);
		} catch (Exception e) {
			return "/404";
		}

		if (person == null) {
			return "redirect:/admin/person/index";
		}
		companyDynamics.setId(null);
		companyDynamics.setCreateUser(person.getId());
		companyDynamics.setUpdateUser(person.getId());
		companyDynamicsService.save(companyDynamics);
		return "redirect:/admin/companyDynamics/list";
	}

	/**
	 * 分页查询
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/list")
	public String findList(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum) {
		Page<CompanyDynamics> companyDynamicsPage = new Page<>();
		companyDynamicsPage.setPageNumber(pageNum);
		companyDynamicsPage = companyDynamicsService.findPageByCondition(companyDynamicsPage, new CompanyDynamics());
		List<CompanyDynamics> companyDynamicsList = companyDynamicsPage.getRows();
		for (CompanyDynamics companyDynamics : companyDynamicsList) {
			String companyId = companyDynamics.getCompanyId();
			if (StringUtils.isEmpty(companyId)) {
				companyDynamics.setCompanyId("-");
			} else {
				companyDynamics.setCompanyId(companyService.getById(companyId).getName());
			}
		}
		model.addAttribute("companyDynamicsList", companyDynamicsList);
		model.addAttribute("pageCount", companyDynamicsPage.getPageCount());
		return "/view/company-dynamics/companyDynamicsList";
	}

	/**
	 * 根据id删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete")
	public String delete(String id, Model model) {
		if (StringUtils.isNotBlank(id)) {
			CompanyDynamics companyDynamics = new CompanyDynamics();
			companyDynamics.setId(id);
			companyDynamicsService.delete(companyDynamics);
			return "redirect:/admin/companyDynamics/list";
		}
		model.addAttribute("error", "id不能为空");
		return "/condition";
	}

	/**
	 * 跳转到新增/修改页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit")
	public String edit(String id, Model model) {
		List<Company> companyList = companyService.findAll();
		model.addAttribute("companyList", companyList);
		if (StringUtils.isNotBlank(id)) {
			CompanyDynamics companyDynamics = companyDynamicsService.getById(id);
			model.addAttribute("companyDynamics", companyDynamics);
			return "/view/company-dynamics/companyDynamicsEdit";
		}
		return "/view/company-dynamics/companyDynamicsEdit";
	}

	/**
	 * 修改
	 * 
	 * @param person
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update")
	public String update(CompanyDynamics companyDynamics, Model model) {
		if (StringUtils.isNotBlank(companyDynamics.getId())) {
			companyDynamicsService.update(companyDynamics);
			return "redirect:/admin/companyDynamics/list";
		}
		model.addAttribute("error", "id不能为空");
		return "/condition";
	}
}
