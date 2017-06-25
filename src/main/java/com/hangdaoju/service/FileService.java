package com.hangdaoju.service;

import java.util.List;

import com.hangdaoju.model.File;

public interface FileService {
	// 新增
	String add(File file);

	// 修改
	void update(File file);

	// 删除
	void delete(File file);

	void deleteById(String id);

	// 查询
	List<File> findByCondition(File file);

	List<File> findByCondition(File file, String orderBy, String sort);

	File find(String id);

	boolean isLogicFileExsits(File file);
}
