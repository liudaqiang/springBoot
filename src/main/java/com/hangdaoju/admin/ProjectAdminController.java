package com.hangdaoju.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hangdaoju.core.Page;
import com.hangdaoju.model.Company;
import com.hangdaoju.model.Person;
import com.hangdaoju.model.Project;
import com.hangdaoju.model.Scheme;
import com.hangdaoju.service.CompanyService;
import com.hangdaoju.service.PersonService;
import com.hangdaoju.service.ProjectService;
import com.hangdaoju.service.SchemeService;
import com.hangdaoju.util.StringUtils;

@Controller
@RequestMapping("/admin/project")
public class ProjectAdminController {
	@Autowired
	private ProjectService projectService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private PersonService personService;
	@Autowired
	private SchemeService schemeService;

	/**
	 * 创建一个项目 (当前页面先写成ajax形式写)
	 * 
	 * @param project
	 * @param model
	 * @return
	 */
	@PostMapping("/add")
	public String save(Project project, Model model) {
		if (StringUtils.isNotBlank(project.getCompanyId())) {
			project.setId(null);
			projectService.add(project);
			return "redirect:/admin/project/list";
		}
		model.addAttribute("error", "公司id不能为空");
		return "/condition";
	}

	@RequestMapping("/delete")
	public String delete(String id, Model model) {
		if (StringUtils.isBlank(id)) {
			model.addAttribute("error", "id不能为空");
			return "/condition";
		}
		Project project = new Project();
		project.setId(id);
		projectService.delete(project);
		return "redirect:/admin/project/list";
	}

	@RequestMapping("/update")
	public String update(Project project, Model model) {
		if (StringUtils.isAnyBlank(project.getId(), project.getFatherProjectId(), project.getName(),
				project.getProjectType())) {
			model.addAttribute("error", "数据有误,请重新输入");
			return "/condition";
		}
		projectService.update(project);
		return "redirect:/admin/project/list";
	}

	@GetMapping("/list")
	public String list(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum) {
		Page<Project> projectPage = new Page<>();
		projectPage.setPageNumber(pageNum);
		Project project = new Project();
		project.setFatherProjectId(null);
		projectPage = projectService.findByCondition(project, projectPage);
		List<Project> projectList = projectPage.getRows();
		model.addAttribute("projectList", projectList);
		model.addAttribute("pageCount", projectPage.getPageCount());
		return "/view/project/projectList";
	}

	/**
	 * 修改或新增项目 跳转
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(String id, Model model) {
		List<Person> personList = personService.findByCondition(new Person());
		model.addAttribute("personList", personList);
		Scheme scheme = new Scheme();
		scheme.setProperties(null);
		List<Scheme> schemeList = schemeService.findByCondition(scheme);
		model.addAttribute("schemeList", schemeList);
		Project queryProject = new Project();
		queryProject.setFatherProjectId(null);
		List<Project> projects = projectService.findProjectListByCondition(queryProject);
		model.addAttribute("projectList", projects);
		List<Company> companyList = companyService.findAll();
		model.addAttribute("companyList", companyList);
		if (StringUtils.isBlank(id)) {
			return "/view/project/projectEdit";
		}
		Project project = projectService.findById(id);
		model.addAttribute("project", project);
		return "/view/project/projectEdit";
	}
}
