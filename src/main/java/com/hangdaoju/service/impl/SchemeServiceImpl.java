package com.hangdaoju.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.hangdaoju.core.Page;
import com.hangdaoju.dao.SchemeDao;
import com.hangdaoju.model.Scheme;
import com.hangdaoju.service.SchemeService;

@Service
public class SchemeServiceImpl implements SchemeService {
	@Autowired
	private SchemeDao schemeDao;

	@Override
	public String add(Scheme scheme) {
		scheme.setId(null);
		return schemeDao.save(scheme).getId();
	}

	@Override
	public void update(Scheme scheme) {
		schemeDao.updateById(scheme.getId(), scheme);
	}

	@Override
	public void delete(Scheme scheme) {
		schemeDao.delete(scheme);
	}

	@Override
	public List<Scheme> findByCondition(Scheme scheme) {
		return schemeDao.findByCondition(scheme);
	}

	@Override
	public java.util.List<Scheme> findByIds(java.util.List<String> ids) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").in(ids));
		return schemeDao.find(query);
	}

	@Override
	public Scheme findById(String id) {
		return schemeDao.findById(id);
	}

	@Override
	public String transferToDictType(String field) {
		if (null == field) {
			return null;
		}
		switch (field) {
		case "major":
			return "4";
		case "extName":
			return "6";
		case "mark":
			return "7";
		default:
			return field;
		}
	}

	@Override
	public Page<Scheme> findByCondition(Scheme scheme, Page<Scheme> page) {
		return schemeDao.findPage(page, scheme);
	}
}
