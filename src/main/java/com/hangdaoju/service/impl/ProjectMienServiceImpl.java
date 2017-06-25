package com.hangdaoju.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hangdaoju.core.Page;
import com.hangdaoju.dao.ProjectMienDao;
import com.hangdaoju.model.ProjectMien;
import com.hangdaoju.service.ProjectMienService;
@Service
public class ProjectMienServiceImpl implements ProjectMienService {
	@Autowired
	private ProjectMienDao projectMienDao;

	@Override
	public void add(ProjectMien projectMien) {
		// TODO Auto-generated method stub
		projectMienDao.save(projectMien);
	}

	@Override
	public void delete(ProjectMien projectMien) {
		// TODO Auto-generated method stub
		projectMienDao.delete(projectMien);
	}

	@Override
	public void update(ProjectMien projectMien) {
		// TODO Auto-generated method stub
		projectMienDao.updateById(projectMien.getId(), projectMien);
	}

	@Override
	public List<ProjectMien> findAll() {
		// TODO Auto-generated method stub
		return projectMienDao.findAll();
	}

	@Override
	public Page<ProjectMien> findByCondition(ProjectMien condition, Page<ProjectMien> page) {
		// TODO Auto-generated method stub
		return projectMienDao.findPage(page, condition);
	}

	@Override
	public ProjectMien findById(String id) {
		return projectMienDao.findById(id);
	}

	@Override
	public List<ProjectMien> findByCondition(ProjectMien projectMien) {
		return projectMienDao.findByCondition(projectMien);
	}

}
