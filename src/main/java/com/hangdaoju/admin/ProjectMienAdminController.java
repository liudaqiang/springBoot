package com.hangdaoju.admin;

import java.util.Date;
import java.util.List;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hangdaoju.core.Page;
import com.hangdaoju.model.Person;
import com.hangdaoju.model.Project;
import com.hangdaoju.model.ProjectMien;
import com.hangdaoju.service.ProjectMienService;
import com.hangdaoju.service.ProjectService;
import com.hangdaoju.util.StringUtils;
import com.hangdaoju.util.ehcache.EHCacheUtil;

@Controller
@RequestMapping("/admin/projectMien")
public class ProjectMienAdminController {
	@Autowired
	private ProjectMienService projectMienService;

	@Autowired
	private ProjectService projectService;

	@RequestMapping("/add")
	public String add(ProjectMien projectMien, HttpServletRequest request, Model model) {
		System.out.println(projectMien.getProjectId());
		if (StringUtils.isAnyBlank(projectMien.getProjectId(), projectMien.getPicture(), projectMien.getDescription(),
				projectMien.getProjectMienType())) {
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
		projectMien.setId(null);
		projectMien.setCreateTime(new Date());
		projectMien.setCreateUser(person.getId());
		projectMien.setUpdateTime(new Date());
		projectMien.setUpdateUser(person.getId());
		projectMienService.add(projectMien);
		return "redirect:/admin/projectMien/list";
	}

	@RequestMapping("/delete")
	public String delete(String id, Model model) {
		if (StringUtils.isBlank(id)) {
			model.addAttribute("error", "id不能为空");
			return "/condition";
		}
		ProjectMien projectMien = new ProjectMien();
		projectMien.setId(id);
		projectMienService.delete(projectMien);
		return "redirect:/admin/projectMien/list";
	}

	@RequestMapping("/update")
	public String update(ProjectMien projectMien, Model model) {
		if (StringUtils.isAnyBlank(projectMien.getId(), projectMien.getProjectId(), projectMien.getName(),
				projectMien.getProjectMienType(), projectMien.getDescription())) {
			model.addAttribute("error", "数据有误,请重新输入");
			return "/condition";
		}
		projectMienService.update(projectMien);
		return "redirect:/admin/project-mien/list";
	}

	@RequestMapping("/list")
	public String findList(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum) {
		Page<ProjectMien> projectMienPage = new Page<>();
		projectMienPage.setPageNumber(pageNum);
		projectMienPage = projectMienService.findByCondition(new ProjectMien(), projectMienPage);
		List<ProjectMien> projectMienList = projectMienPage.getRows();
		System.out.println(projectMienList.size());
		for (ProjectMien projectMien : projectMienList) {
			String companyId = projectMien.getProjectId();
			if (StringUtils.isEmpty(companyId)) {
				projectMien.setProjectId("-");
			} else {
				projectMien.setProjectId(projectService.findById(companyId).getName());
			}
			switch (projectMien.getProjectMienType()) {
			case "1":
				projectMien.setProjectMienType("党员示范岗");
				break;
			case "2":
				projectMien.setProjectMienType("青年突击队");
				break;
			case "3":
				projectMien.setProjectMienType("立功竞赛");
				break;
			case "4":
				projectMien.setProjectMienType("每月之星");
				break;
			default:
				projectMien.setProjectMienType("-");
				break;
			}
		}
		model.addAttribute("projectMienList", projectMienList);
		model.addAttribute("pageCount", projectMienPage.getPageCount());
		return "/view/project-mien/projectMienList";
	}

	@RequestMapping("/edit")
	public String edit(String id, Model model) {
		List<Project> projects = projectService.findProjectListByCondition(new Project());
		model.addAttribute("projectList", projects);
		if (StringUtils.isBlank(id)) {
			return "/view/project-mien/projectMienEdit";
		}
		ProjectMien projectMien = projectMienService.findById(id);
		model.addAttribute("projectMien", projectMien);
		return "/view/project-mien/projectMienEdit";
	}
}
