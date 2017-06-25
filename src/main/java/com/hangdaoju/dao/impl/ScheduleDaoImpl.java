package com.hangdaoju.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.hangdaoju.core.mongo.dao.impl.BaseMongoDaoImpl;
import com.hangdaoju.dao.ScheduleDao;
import com.hangdaoju.model.Schedule;
/**
 * 计划dao层
 * @author l.q
 *
 */
@Repository
public class ScheduleDaoImpl extends BaseMongoDaoImpl<Schedule> implements ScheduleDao{
	@Autowired 
	private MongoTemplate mongoTemplate;
	@Override
	public void customAdd(Schedule schedule) {
		mongoTemplate.save(schedule);
	}
}
