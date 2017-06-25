package com.hangdaoju.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hangdaoju.model.Company;
import com.hangdaoju.service.CompanyService;
import com.hangdaoju.util.StringUtils;

@Controller
@RequestMapping(value = "/admin/company")
public class CompanyAdminController {
	@Autowired
	private CompanyService companyService;

	/**
	 * 获得公司树状结构
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("getCompanyTree")
	public String getCompanyTree(Model model) {
		return companyService.getCompanyTree(model);
	}

	/**
	 * 跳转到新增或修改页面
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("edit")
	public String edit(Model model, String id) {
		if (StringUtils.isNotBlank(id)) {
			Company company = companyService.getById(id);
			model.addAttribute("company", company);
		}
		List<Company> companyList = companyService.findAll();
		model.addAttribute("companyList", companyList);
		return "/view/company/companyEdit";
	}
	/**
	 * 修改公司
	 * @param model
	 * @param company
	 * @return
	 */
	@RequestMapping("update")
	public String update(Model model, Company company) {
		if (StringUtils.isNotBlank(company.getId()) && StringUtils.isNotBlank(company.getName())
				&& StringUtils.isNotBlank(company.getParentId())) {
			companyService.update(company);
			 return "redirect:/admin/company/getCompanyTree"; 
		}
		model.addAttribute("error", "数据有误,请重新输入");
		return "/condition";
	}

	@RequestMapping("add")
	public String add(Model model, Company company) {
		if( StringUtils.isNotBlank(company.getName())
				&& StringUtils.isNotBlank(company.getParentId())){
			company.setId(null);
			companyService.add(company);
			 return "redirect:/admin/company/getCompanyTree"; 
		}
		model.addAttribute("error", "数据有误,请重新输入");
		return "/condition";
	}
	@RequestMapping("delete")
	public String delete(Model model,Company company){
		companyService.delete(company);
		return "redirect:/admin/company/getCompanyTree"; 
	}
}
