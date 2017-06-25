package com.hangdaoju.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hangdaoju.core.Page;
import com.hangdaoju.dao.CompanyDynamicsDao;
import com.hangdaoju.model.CompanyDynamics;
import com.hangdaoju.service.CompanyDynamicsService;
@Service 
public class CompanyDynamicsServiceImpl implements CompanyDynamicsService{
	@Autowired
	private CompanyDynamicsDao companyDynamicsDao;
	//根据compnayId查询出风采列表(前台)
	public List<CompanyDynamics> getCompanyDynamicsListByCondition(CompanyDynamics companyDynamics){
		return companyDynamicsDao.findByCondition(companyDynamics);
	}
	//根据id查询风采详情(前台)
	public CompanyDynamics getById(String id){
		return companyDynamicsDao.findById(id);
	}
	//新增风采(后台)
	public void save(CompanyDynamics companyDynamics){
		companyDynamicsDao.save(companyDynamics);
	}
	//修改风采(后台)
	public void update(CompanyDynamics companyDynamics){
		companyDynamicsDao.updateById(companyDynamics.getId(), companyDynamics);
	}
	//分页查询风采列表(根据标题模糊查询,根据id精确查询)(后台)
	public Page<CompanyDynamics> findPageByCondition(Page<CompanyDynamics> page,CompanyDynamics companyDynamics){
		return companyDynamicsDao.findPage(page, companyDynamics);
	}
	//根据id删除CompanyDynamics
	public void delete(CompanyDynamics companyDynamics){
		companyDynamicsDao.delete(companyDynamics);
	}
}