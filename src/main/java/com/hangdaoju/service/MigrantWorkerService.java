package com.hangdaoju.service;

import java.util.List;

import com.hangdaoju.core.Page;
import com.hangdaoju.model.MigrantWorker;

public interface MigrantWorkerService {
	// 新增
	String add(MigrantWorker migrantWorker);

	// 修改
	void update(MigrantWorker migrantWorker);

	// 删除
	void delete(MigrantWorker migrantWorker);

	// 查询
	List<MigrantWorker> findByCondition(MigrantWorker migrantWorker);

	MigrantWorker findById(String id);

	List<MigrantWorker> findByKeyword(String keyword);
	
	Page<MigrantWorker> findPage(MigrantWorker migrantWorker,Page<MigrantWorker> page);
}
