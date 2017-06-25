package com.hangdaoju.service;

import java.util.List;

import com.hangdaoju.core.Page;
import com.hangdaoju.model.CompanyDynamics;

public interface CompanyDynamicsService {
	//根据compnayId查询出风采列表(前台)
	public List<CompanyDynamics> getCompanyDynamicsListByCondition(CompanyDynamics companyDynamics);
	//根据id查询风采详情(前台)
	public CompanyDynamics getById(String id);
	//新增风采(后台)
	public void save(CompanyDynamics companyDynamics);
	//修改风采(后台)
	public void update(CompanyDynamics companyDynamics);
	//分页查询风采列表(根据标题模糊查询,根据id精确查询)(后台)
	public Page<CompanyDynamics> findPageByCondition(Page<CompanyDynamics> page,CompanyDynamics companyDynamics);
	//根据id删除CompanyDynamics
	public void delete(CompanyDynamics companyDynamics);
}
