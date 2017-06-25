package com.hangdaoju.dao;

import com.hangdaoju.core.mongo.dao.BaseMongoDao;
import com.hangdaoju.model.Schedule;

public interface ScheduleDao  extends BaseMongoDao<Schedule>{
	public void customAdd(Schedule schedule);
}
