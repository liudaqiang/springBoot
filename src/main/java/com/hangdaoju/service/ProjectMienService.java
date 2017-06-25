package com.hangdaoju.service;

import java.util.List;

import com.hangdaoju.core.Page;
import com.hangdaoju.model.ProjectMien;

public interface ProjectMienService {
	public void add(ProjectMien company);

	public void delete(ProjectMien company);

	public void update(ProjectMien company);

	public List<ProjectMien> findAll();
	
	public Page<ProjectMien> findByCondition(ProjectMien condition,Page<ProjectMien> page);
	
	public ProjectMien findById(String id);
	
	public List<ProjectMien> findByCondition(ProjectMien condition);
}
