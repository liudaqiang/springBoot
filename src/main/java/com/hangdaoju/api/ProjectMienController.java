package com.hangdaoju.api;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hangdaoju.core.ResponseModel;
import com.hangdaoju.model.ProjectMien;
import com.hangdaoju.service.ProjectMienService;
import com.hangdaoju.util.GetMapUtils;

/**
 * 项目风采
 * @author l.q
 *
 */
@Controller
@RequestMapping("/projectMien")
public class ProjectMienController {
	@Autowired
	private ProjectMienService projectMienService;
	/**
	 * 获取风采列表
	 * projectId
	 * projectMienType
	 * currentUser
	 * @return
	 */
	@PostMapping("/findByCondition")
	@ResponseBody
	public ResponseModel findByCondition(ProjectMien projectMien){
		if(StringUtils.isNotBlank(projectMien.getProjectId()) &&
				StringUtils.isNotBlank(projectMien.getProjectMienType())){
			List<ProjectMien> projectMienList = projectMienService.findByCondition(projectMien);
			return new ResponseModel(200,GetMapUtils.getMap("projectMienList", projectMienList),"查询成功");
		}
		return new ResponseModel(300,null,"查询失败");
	}
	/**
	 * 获取风采详情
	 * id
	 * currentUser
	 * @param id
	 * @return
	 */
	@PostMapping("/findById")
	@ResponseBody
	public ResponseModel findById(String id){
		if(StringUtils.isNotBlank(id)){
			ProjectMien projectMien = projectMienService.findById(id);
			return new ResponseModel(200,GetMapUtils.getMap("projectMien", projectMien),"查询成功");
		}
		return new ResponseModel(300,null,"id不能为空");
	}
	
}
