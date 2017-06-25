package com.hangdaoju.model;

import com.hangdaoju.core.customAnnotation.QueryField;

/**
 * 任务关系
 * @author l.q
 *
 */
public class RelationTask {
	private Integer relationType;//关联方式1FS完成开始2SS同时开始3FF同时完成4SF开始完成
	@QueryField
	private Task task;//关联的taskId
	private Integer laterDate=0;//延期日期
	public Integer getRelationType() {
		return relationType;
	}
	public void setRelationType(Integer relationType) {
		this.relationType = relationType;
	}
	
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public Integer getLaterDate() {
		return laterDate;
	}
	public void setLaterDate(Integer laterDate) {
		this.laterDate = laterDate;
	}
	
	
}
