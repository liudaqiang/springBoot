package com.hangdaoju.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hangdaoju.dao.CompanyDao;
import com.hangdaoju.model.Company;
import com.hangdaoju.service.CompanyService;
import com.hangdaoju.util.StringUtils;
@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private CompanyDao companyDao;
	/*
	 * 获取树状结构公司列表
	 * @see com.hangdaoju.service.CompanyService#getCompanyTree(org.springframework.ui.Model)
	 */
	@Override
	public String getCompanyTree(Model model) {
		try{
			//通过直接过去全部进行本地化处理树状结构
			List<Company> companyList = companyDao.findAll();
			Company company = new Company();
			company.setParentId("1");
			//获取到顶级栏目
			Company fatherCompany = companyDao.findOne(company);
			//树状结构分析(结果全部储存为顶级公司中)
			getChildrenTree(fatherCompany,companyList);
			model.addAttribute("company", fatherCompany);
			return "/view/company/companyList";
		}catch(Exception e){
			model.addAttribute("error", "500异常，请联系后台管理员");
			return "error";
		}
		
	}
	private void getChildrenTree(Company fatherCompany,List<Company>companyList){
		for(Company company:companyList){
			//如果父id == 子parent_id的话
			if(StringUtils.isNotBlank(fatherCompany.getId()) && fatherCompany.getId().equals(company.getParentId())){
				fatherCompany.getCompanyList().add(company);
				getChildrenTree(company,companyList);
			}
		}
	}
	/*
	 * 根据id查询单位
	 * @see com.hangdaoju.service.CompanyService#getById(java.lang.String)
	 */
	@Override
	public Company getById(String id) {
		return companyDao.findById(id);
	}

	@Override
	public void update(Company company) {
		Criteria criteria = new Criteria("id");
		criteria.is(company.getId());
		Query query = new Query(criteria);
		Update update = new Update().set("name", company.getName())
				.set("address", company.getAddress())
				.set("description", company.getDescription())
				.set("parent_id", company.getParentId());
		companyDao.update(query, update);
	}

	@Override
	public void delete(Company company) {
		companyDao.delete(company);
	}
	/*
	 * 新增部门
	 * @see com.hangdaoju.service.CompanyService#add()
	 */
	@Override
	public void add(Company company) {
		companyDao.save(company);
	}
	/*
	 * 获得公司列表
	 * @see com.hangdaoju.service.CompanyService#findAll()
	 */
	@Override
	public List<Company> findAll() {
		return companyDao.findAll();
	}
}
