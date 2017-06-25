package com.hangdaoju.admin;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hangdaoju.core.Page;
import com.hangdaoju.model.Person;
import com.hangdaoju.model.Scheme;
import com.hangdaoju.service.SchemeService;
import com.hangdaoju.util.StringUtils;
import com.hangdaoju.util.ehcache.EHCacheUtil;

@Controller
@RequestMapping("/admin/scheme")
public class SchemeAdminController {
	@Autowired
	private SchemeService schemeService;

	@RequestMapping("/add")
	public String add(Scheme scheme, HttpServletRequest request, Model model) {
		if (null == scheme || isBlank(scheme.getName()) || null == scheme.getProperties()
				|| scheme.getProperties().isEmpty()) {
			model.addAttribute("error", "数据有误,请重新输入");
			return "/condition";
		}
		if (scheme.getProperties().size() > new HashSet<>(scheme.getProperties()).size()) {
			model.addAttribute("error", "数据有误,请重新输入");
			return "/condition";
		}
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
		scheme.setId(null);
		scheme.setCreateTime(new Date());
		scheme.setCreateUser(person.getId());
		scheme.setUpdateTime(new Date());
		scheme.setUpdateUser(person.getId());
		schemeService.add(scheme);
		return "redirect:/admin/scheme/list";
	}

	@RequestMapping("/delete")
	public String delete(String id, Model model) {
		if (StringUtils.isBlank(id)) {
			model.addAttribute("error", "id不能为空");
			return "/condition";
		}
		Scheme scheme = new Scheme();
		scheme.setId(id);
		schemeService.delete(scheme);
		return "redirect:/admin/scheme/list";
	}

	@RequestMapping("/update")
	public String update(Scheme scheme, Model model) {
		if (null == scheme || isBlank(scheme.getName()) || null == scheme.getProperties()
				|| scheme.getProperties().isEmpty()) {
			model.addAttribute("error", "数据有误,请重新输入");
			return "/condition";
		}
		if (scheme.getProperties().size() > new HashSet<>(scheme.getProperties()).size()) {
			model.addAttribute("error", "数据有误,请重新输入");
			return "/condition";
		}
		schemeService.update(scheme);
		return "redirect:/admin/scheme/list";
	}

	@RequestMapping("/list")
	public String findList(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum) {
		Page<Scheme> schemePage = new Page<>();
		schemePage.setPageNumber(pageNum);
		Scheme condition = new Scheme();
		condition.setProperties(null);
		schemePage = schemeService.findByCondition(condition, schemePage);
		List<Scheme> schemeList = schemePage.getRows();
		model.addAttribute("schemeList", schemeList);
		model.addAttribute("pageCount", schemePage.getPageCount());
		return "/view/scheme/schemeList";
	}

	@RequestMapping("/edit")
	public String edit(String id, Model model) {
		if (StringUtils.isBlank(id)) {
			return "/view/scheme/schemeEdit";
		}
		Scheme scheme = schemeService.findById(id);
		model.addAttribute("scheme", scheme);
		return "/view/scheme/schemeEdit";
	}
}
