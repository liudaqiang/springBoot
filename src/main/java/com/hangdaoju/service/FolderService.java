package com.hangdaoju.service;

import java.util.List;

import com.hangdaoju.model.Folder;

public interface FolderService {
	// 新增
	String add(Folder folder);

	// 修改
	boolean update(Folder folder);

	// 删除
	void delete(Folder folder);

	void deleteById(String id);

	// 查询
	List<Folder> findByCondition(Folder folder);

	Folder findById(String id);

	List<Folder> findByParent(String parentId);
	
	List<Folder> findRootByProject(String projectId);
	
	/**
	 * 查询树状结构文件夹系统，根据type和公司
	 * @param companyId
	 * @param folderType
	 * @return
	 */

	List<Folder> findRootByCompanyId(String companyId);
}
