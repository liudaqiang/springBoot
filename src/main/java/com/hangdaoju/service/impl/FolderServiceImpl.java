package com.hangdaoju.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.hangdaoju.dao.FolderDao;
import com.hangdaoju.model.Folder;
import com.hangdaoju.model.Project;
import com.hangdaoju.service.FolderService;
import com.hangdaoju.service.ProjectService;

@Service
public class FolderServiceImpl implements FolderService {
	@Autowired
	FolderDao folderDao;
	@Autowired
	private ProjectService projectService;

	public String add(Folder folder) {
		// 校验同级别文件名是否存在
		String parentId = folder.getParentId();
		Folder extise = new Folder();
		extise.setParentId(parentId);
		extise.setName(folder.getName());
		List<Folder> folderList = folderDao.findByCondition(extise);
		if (folderList != null && folderList.size() > 0) {
			return null;
		}
		folder.setId(null);
		return folderDao.save(folder).getId();
	}

	@Override
	public boolean update(Folder folder) {
		// 校验同级别文件名是否存在
		String parentId = folder.getParentId();
		Folder extise = new Folder();
		extise.setParentId(parentId);
		extise.setName(folder.getName());
		List<Folder> folderList = folderDao.findByCondition(extise);
		if (folderList != null && folderList.size() > 0) {
			return false;
		}
		folderDao.updateById(folder.getId(), folder);
		return true;
	}

	@Override
	public void delete(Folder folder) {
		folderDao.delete(folder);
	}

	@Override
	public void deleteById(String id) {
		Folder folder = new Folder();
		folder.setId(id);
		folderDao.delete(folder);
	}

	@Override
	public List<Folder> findByCondition(Folder folder) {
		return folderDao.findByCondition(folder);
	}

	@Override
	public Folder findById(String id) {
		return folderDao.findById(id);
	}

	@Override
	public List<Folder> findByParent(String parentId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("parent_id").is(parentId));
		return folderDao.find(query);
	}

	@Override
	public List<Folder> findRootByProject(String projectId) {
		Folder folder = new Folder();
		folder.setProjectId(projectId);
		folderDao.findByCondition(folder);
		return folderDao.findByCondition(folder);
	}

	/**
	 * 根据公司id和文件类型查询文件夹列表
	 */
	@Override
	public List<Folder> findRootByCompanyId(String companyId) {
		Project project = new Project();
		project.setFatherProjectId(null);
		project.setCompanyId(companyId);
		List<Project> projectList = projectService.findProjectListByCondition(project);
		if (projectList == null || projectList.size() == 0) {
			return null;
		}
		String projectId = projectList.get(0).getId();
		Folder folder = new Folder();
		folder.setProjectId(projectId);
		folderDao.findByCondition(folder);
		return folderDao.findByCondition(folder);
	}
}
