package com.hangdaoju.service;

import java.util.List;
import java.util.Map;

import com.hangdaoju.core.Page;
import com.hangdaoju.model.Project;

public interface ProjectService {
	//新增项目
	public void add(Project project);
	//查询项目列表(分类，区域，名称)
	public List<Project> findProjectListByCondition(Project project);
	//根据类型查询饼状图
	public Map<String,Double> getPieChartByProjectType(Project project);
	//根据规模查询饼状图
	public Map<String,Double> getPieCharByScale(Project project);
	//根据区域查询饼状图
	public Map<String,Double> getPieCharByLocal(Project project);
	//根据id查询项目详情
	public Project findById(String id);
	//后台分页查询项目列表
	public Page<Project> findByCondition(Project condition, Page<Project> page);
	//删除
	public void delete(Project project);
	//修改
	public void update(Project project);
}
