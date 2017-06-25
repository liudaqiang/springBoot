package com.hangdaoju.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.hangdaoju.core.mongo.dao.impl.BaseMongoDaoImpl;
import com.hangdaoju.dao.ScheduleTaskDao;
import com.hangdaoju.model.ScheduleTask;
/**
 * 计划任务Dao层实现
 * @author l.q
 *
 */
@Repository
public class ScheduleTaskDaoImpl extends BaseMongoDaoImpl<ScheduleTask> implements ScheduleTaskDao{
	
	@Autowired 
	private MongoTemplate mongoTemplate;
	
	@Override
	public void customSave(ScheduleTask scheduleTask) {
		mongoTemplate.save(scheduleTask);
	}
}
