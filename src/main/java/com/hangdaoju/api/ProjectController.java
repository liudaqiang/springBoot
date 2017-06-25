package com.hangdaoju.api;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hangdaoju.core.ResponseModel;
import com.hangdaoju.model.Project;
import com.hangdaoju.service.ProjectService;
import com.hangdaoju.util.GetMapUtils;

@Controller
@RequestMapping("/project")
public class ProjectController {
	@Autowired
	private ProjectService projectService;
	/**
	 * 根据条件查询项目列表
	 * 必须字段  companyId currentUser
	 * 可有字段 condition
	 * @return
	 */
	@PostMapping("/findProjectByCondition")
	@ResponseBody
	public ResponseModel findProjectByCondition(Project project,String condition){
		if(StringUtils.isNotBlank(project.getCompanyId())){
			if(StringUtils.isNotBlank(condition)){
				project.setName(condition);
				project.setLocal(condition);
				project.setScale(condition);
			}
			List<Project> projectList = projectService.findProjectListByCondition(project);
			return new ResponseModel(200,GetMapUtils.getMap("projectList", projectList),"查询列表成功");
		}
		return new ResponseModel(300,null,"公司id不能为空");
	}
	/**
	 * 根据项目类型获取饼状图
	 * 必须字段 companyId currentUser
	 * @param project
	 * @return
	 */
	@PostMapping("/getPieChartByProjectType")
	@ResponseBody
	public ResponseModel getPieChartByProjectType(Project project){
		if(StringUtils.isNotBlank(project.getCompanyId())){
			return new ResponseModel(200,GetMapUtils.getMap("PieChartByProjectType", projectService.getPieChartByProjectType(project)),"查询列表成功");
		}
		return new ResponseModel(300,null,"公司id不能为空");
	}
	/**
	 * 根据项目规模获取饼状图
	 * 必须字段 companyId currentUser
	 * @param project
	 * @return
	 */
	@PostMapping("/getPieCharByScale")
	@ResponseBody
	public ResponseModel getPieCharByScale(Project project){
		if(StringUtils.isNotBlank(project.getCompanyId())){
			return new ResponseModel(200,GetMapUtils.getMap("PieChartByProjectType", projectService.getPieCharByScale(project)),"查询列表成功");
		}
		return new ResponseModel(300,null,"公司id不能为空");
	}
	/**
	 * 根据项目规模获取饼状图
	 * 必须字段 companyId currentUser
	 * @param project
	 * @return
	 */
	@PostMapping("/getPieCharByLocal")
	@ResponseBody
	public ResponseModel getPieCharByLocal(Project project){
		if(StringUtils.isNotBlank(project.getCompanyId())){
			return new ResponseModel(200,GetMapUtils.getMap("PieChartByProjectType", projectService.getPieCharByLocal(project)),"查询列表成功");
		}
		return new ResponseModel(300,null,"公司id不能为空");
	}
	/**
	 * 根据id查询项目详情
	 * @param id currentUser
	 * @return
	 */
	@PostMapping("/getProjectById")
	@ResponseBody
	public ResponseModel getProjectById(String id){
		if(StringUtils.isNoneBlank(id)){
			Project project = projectService.findById(id);
			return new ResponseModel(200,GetMapUtils.getMap("project", project),"查询成功");
		}
		return new ResponseModel(300,null,"公司id不能为空");
	}
}
