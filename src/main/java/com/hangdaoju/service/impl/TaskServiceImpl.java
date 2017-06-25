package com.hangdaoju.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hangdaoju.dao.TaskDao;
import com.hangdaoju.model.TaskModel;
import com.hangdaoju.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService{
	@Autowired
	private TaskDao taskDao;

	@Override
	public void save(TaskModel taskModel) {
		taskDao.save(taskModel);
	}

	@Override
	public void delete(String id) {
		TaskModel taskModel = new TaskModel();
		taskModel.setId(id);
		taskDao.delete(taskModel);
	}

	@Override
	public TaskModel findById(String id) {
		return taskDao.findById(id);
	}

	@Override
	public TaskModel findByCondition(String scheduleId) {
		TaskModel taskModel = new TaskModel();
		taskModel.setScheduleId(scheduleId);
		List<TaskModel> taskModelList = taskDao.findByCondition(taskModel);
		return taskModelList==null||taskModelList.size()==0?null:taskModelList.get(0);
	}
}
