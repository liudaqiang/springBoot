package com.hangdaoju.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.hangdaoju.core.customAnnotation.QueryField;

/**
 * 计划任务
 * @author l.q
 *
 */
@Document(collection="schedule_task")
public class ScheduleTask extends BaseModel{
	@Id
	@QueryField
	private String id;
	@Field("schedule_id")
	@QueryField(attribute="schedule_id")
	private String scheduleId;
	private List<Task> taskList = new ArrayList<Task>();
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
	public List<Task> getTaskList() {
		return taskList;
	}
	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}
	
}
