package com.hangdaoju.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.hangdaoju.core.Page;
import com.hangdaoju.dao.MigrantWorkerDao;
import com.hangdaoju.model.MigrantWorker;
import com.hangdaoju.service.MigrantWorkerService;

@Service
public class MigrantWorkerServiceImpl implements MigrantWorkerService {
	@Autowired
	private MigrantWorkerDao migrantWorkerDao;

	@Override
	public String add(MigrantWorker migrantWorker) {
		return migrantWorkerDao.save(migrantWorker).getId();
	}

	@Override
	public void update(MigrantWorker migrantWorker) {
		migrantWorkerDao.updateById(migrantWorker.getId(), migrantWorker);
	}

	@Override
	public void delete(MigrantWorker migrantWorker) {
		migrantWorkerDao.delete(migrantWorker);
	}

	@Override
	public List<MigrantWorker> findByCondition(MigrantWorker migrantWorker) {
		return migrantWorkerDao.findByCondition(migrantWorker);
	}

	@Override
	public MigrantWorker findById(String id) {
		// TODO Auto-generated method stub
		return migrantWorkerDao.findById(id);
	}

	@Override
	public List<MigrantWorker> findByKeyword(String keyWord) {
		// TODO Auto-generated method stub

		// //取得字段名
		// java.lang.reflect.Field[] fields =
		// MigrantWorker.class.getDeclaredFields();
		// //取得Object类的字段
		// java.lang.reflect.Field[] objFields=Object.class.getDeclaredFields();
		// for(java.lang.reflect.Field field:fields){
		// field.setAccessible(true);
		// //1.去掉Object类已有的字段
		// //2.去掉结尾带Id和包含Date的字段
		// //3.将驼峰转换为下划线形式
		// //4.将field.getName()放入queryFields数组
		// }

		String[] queryFields = { "name", "address", "mobile", "address", "company", "work_type", "emergency_contact",
				"emergency_mobile", "special_number", "security_insurance" };
		Query query = new Query();
		Criteria criteria = new Criteria();
		Criteria[] regexCriteria = new Criteria[queryFields.length];
		for (int i = 0; i < queryFields.length; ++i) {
			regexCriteria[i] = Criteria.where(queryFields[i]).regex(keyWord);
		}
		criteria.orOperator(regexCriteria);
		query.addCriteria(criteria);
		return migrantWorkerDao.find(query);
	}

	@Override
	public Page<MigrantWorker> findPage(MigrantWorker migrantWorker, Page<MigrantWorker> page) {
		return migrantWorkerDao.findPage(page, migrantWorker);
	}

}
