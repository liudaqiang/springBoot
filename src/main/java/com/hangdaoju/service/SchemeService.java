package com.hangdaoju.service;

import java.util.List;

import com.hangdaoju.core.Page;
import com.hangdaoju.model.Scheme;

public interface SchemeService {
	// 新增
	String add(Scheme scheme);

	// 修改
	void update(Scheme scheme);

	// 删除
	void delete(Scheme scheme);

	// 查询
	List<Scheme> findByCondition(Scheme scheme);

	Page<Scheme> findByCondition(Scheme scheme, Page<Scheme> page);

	List<Scheme> findByIds(List<String> ids);

	Scheme findById(String id);

	String transferToDictType(String field);
}
