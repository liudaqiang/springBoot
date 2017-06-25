package com.hangdaoju.dao;

import com.hangdaoju.core.mongo.dao.BaseMongoDao;
import com.hangdaoju.model.ScheduleTask;

public interface ScheduleTaskDao  extends BaseMongoDao<ScheduleTask>{
	public void customSave(ScheduleTask scheduleTask);
}
