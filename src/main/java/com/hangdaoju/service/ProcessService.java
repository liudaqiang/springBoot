package com.hangdaoju.service;

import java.util.List;

import com.hangdaoju.model.Process;

public interface ProcessService {
	// 新增
	public String add(Process process);

	// 修改
	public void update(Process process);

	// 删除
	public void delete(Process process);

	public void deleteById(String id);

	// 查询
	public List<Process> findByCondition(Process process);

	public Process find(String id);
}
