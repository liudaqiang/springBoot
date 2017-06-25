package com.hangdaoju.service;

import java.util.List;

import org.springframework.ui.Model;

import com.hangdaoju.model.Company;

/**
 * 公司service层
 * @author l.q
 *
 */
public interface CompanyService {
	//查询树状结构公司列表
	public String getCompanyTree(Model model);
	//获得公司列表
	public List<Company> findAll();
	//查询部门详细信息
	public Company getById(String id);
	//编辑部门
	public void update(Company company);
	//删除部门
	public void delete(Company company);
	//新增部门
	public void add(Company company);
}
