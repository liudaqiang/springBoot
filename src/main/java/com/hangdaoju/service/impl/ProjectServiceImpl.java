package com.hangdaoju.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.hangdaoju.core.Page;
import com.hangdaoju.dao.CompanyDao;
import com.hangdaoju.dao.PersonDao;
import com.hangdaoju.dao.ProjectDao;
import com.hangdaoju.model.Company;
import com.hangdaoju.model.Person;
import com.hangdaoju.model.Project;
import com.hangdaoju.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private PersonDao personDao;
	@Override
	public void add(Project project) {
		projectDao.save(project);
	}
	/**
	 * 根据条件查询项目列表
	 */
	@Override
	public List<Project> findProjectListByCondition(Project project) {
		if(project.getName() == null){
			return projectDao.findByCondition(project);
		}
		Criteria cirteria = new Criteria();
		cirteria.orOperator(
				Criteria.where("name").regex(project.getName()),
				Criteria.where("scale").regex(project.getScale()),
				Criteria.where("local").regex(project.getLocal()));
		Query query = new Query();
		query.addCriteria(cirteria);
		return projectDao.find(query);
	}
	/**
	 * 根据公司id查询饼状图By类型
	 */
	@Override
	public Map<String, Double> getPieChartByProjectType(Project project) {
		// TODO Auto-generated method stub
		List<Project> projectList = projectDao.findByCondition(project);
		if(projectList.size() == 0){
			return null;
		}
		int allCount = projectList.size();
		int sjct = 0;
		int jd = 0;
		int sz = 0;
		int sgjz = 0;
		int djcl = 0;
		for(Project oneProject:projectList){
			switch (oneProject.getProjectType()) {
			case "1":
				sjct++;
				break;
			case "2":
				jd++;
				break;
			case "3":
				sz++;
				break;
			case "4":
				sgjz++;
				break;
			case "5":
				djcl++;
				break;
			default:
				break;
			}
		}
		Map<String,Double> map = new HashMap<String,Double>();
		map.put("疏浚吹填类", (double) sjct/(double)allCount);
		map.put("筑堤类", (double) jd/(double)allCount);
		map.put("市政类", (double)sz/(double)allCount);
		map.put("水工建筑类", (double)sgjz/(double)allCount);
		map.put("地基处理类", (double) djcl/(double)allCount);
		return map;
	}
	/**
	 * 根据公司id查询饼状图By规模
	 */
	@Override
	public Map<String, Double> getPieCharByScale(Project project) {
		List<Project> projectList = projectDao.findByCondition(project);
		if(projectList.size() == 0){
			return null;
		}
		int allCount = projectList.size();
		int ctdx = 0;
		int tdx = 0;
		int dx = 0;
		int zxx = 0;
		for(Project oneProject:projectList){
			switch (oneProject.getScale()) {
			case "超特大型":
				ctdx++;
				break;
			case "特大型":
				tdx++;
				break;
			case "大型":
				dx++;
				break;
			case "中小型":
				zxx++;
				break;
			default:
				break;
			}
		}
		Map<String,Double> map = new HashMap<String,Double>();
		map.put("超特大型", (double)ctdx/allCount);
		map.put("特大型", (double)tdx/allCount);
		map.put("大型", (double)dx/allCount);
		map.put("中小型", (double)zxx/allCount);
		return map;
	}
	/**
	 * 根据公司id查询饼状图By地区
	 */
	@Override
	public Map<String, Double> getPieCharByLocal(Project project) {
		List<Project> projectList = projectDao.findByCondition(project);
		if(projectList.size() == 0){
			return null;
		}
		int allCount = projectList.size();
		int dbdq = 0;
		int hbdq = 0;
		int hddq = 0;
		int hzdq = 0;
		int hndq = 0;
		int xbdq = 0;
		int xndq = 0;
		int gatdq = 0;
		//区域分布   东北地区  华北地区  华东地区  华中地区   华南地区   西北地区  西南地区  港澳台地区 
		for(Project oneProject:projectList){
			switch (oneProject.getLocal()) {
			case "东北地区":
				dbdq++;
				break;
			case "华北地区":
				hbdq++;
				break;
			case "华东地区":
				hddq++;
				break;
			case "华中地区":
				hzdq++;
				break;
			case "华南地区":
				hndq++;
				break;
			case "西北地区":
				xbdq++;
				break;
			case "西南地区":
				xndq++;
				break;
			case "港澳台地区":
				gatdq++;
				break;
			default:
				break;
			}
		}
		Map<String,Double> map = new HashMap<String,Double>();
		map.put("东北地区", (double)dbdq/allCount);
		map.put("华北地区", (double)hbdq/allCount);
		map.put("华东地区", (double)hddq/allCount);
		map.put("华中地区", (double)hzdq/allCount);
		map.put("华南地区", (double)hndq/allCount);
		map.put("西北地区", (double)xbdq/allCount);
		map.put("西南地区", (double)xndq/allCount);
		map.put("港澳台地区", (double)gatdq/allCount);
		return map;
	}
	@Override
	public Project findById(String id) {
		Project project = projectDao.findById(id);
		//将公司名放入
		if(project.getCompanyId() != null){
			Company company = companyDao.findById(project.getCompanyId());
			if(company != null){
				project.setCompanytName(company.getName());
			}
		}
		//将父项目名称放入
		if(!project.getFatherProjectId().equals("1")){
			Project fatherProject = projectDao.findById(project.getFatherProjectId());
			if(fatherProject != null){
				project.setFatherProjectName(fatherProject.getName());
			}
		}
		if(project.getManagerPerson() != null){
			Person person = personDao.findById(project.getManagerPerson());
			if(person != null){
				project.setManagerPersonName(person.getUserName());
			}
		}
		return project;
	}
	@Override
	public Page<Project> findByCondition(Project condition, Page<Project> page) {
		return projectDao.findPage(page, condition);
	}
	@Override
	public void delete(Project project) {
		projectDao.delete(project);
	}
	@Override
	public void update(Project project) {
		project.setFatherProjectName(null);
		projectDao.updateById(project.getId(), project);
	}
	
}
