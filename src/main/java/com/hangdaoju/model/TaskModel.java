package com.hangdaoju.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 修改版任务
 * @author l.q
 *
 */
@Document
public class TaskModel extends BaseModel{
	@Id
	private String id;
	private String scheduleId;
	@Field("one_task")
	private List<OneTask> oneTask;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}
	public List<OneTask> getOneTask() {
		return oneTask;
	}
	public void setOneTask(List<OneTask> oneTask) {
		this.oneTask = oneTask;
	}
	
	
}
