package com.hangdaoju.service;

import com.hangdaoju.model.TaskModel;

public interface TaskService {
	public void save(TaskModel taskModel);
	public void delete(String id);
	public TaskModel findById(String id);
	public TaskModel findByCondition(String scheduleId);
}
