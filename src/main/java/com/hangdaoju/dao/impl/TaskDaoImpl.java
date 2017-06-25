package com.hangdaoju.dao.impl;

import org.springframework.stereotype.Repository;

import com.hangdaoju.core.mongo.dao.impl.BaseMongoDaoImpl;
import com.hangdaoju.dao.TaskDao;
import com.hangdaoju.model.TaskModel;
@Repository
public class TaskDaoImpl extends BaseMongoDaoImpl<TaskModel> implements TaskDao {
}
