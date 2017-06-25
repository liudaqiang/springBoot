package com.hangdaoju.model;

public class SimpleRelationTaskModel {
	private Integer Type;//关联方式1FS完成开始2SS同时开始3FF同时完成4SF开始完成
	private String PredecessorUID;//关系的id
	private Integer LinkLag;
	private String TaskUID;
	public Integer getType() {
		return Type;
	}
	public void setType(Integer type) {
		Type = type;
	}
	public String getPredecessorUID() {
		return PredecessorUID;
	}
	public void setPredecessorUID(String predecessorUID) {
		PredecessorUID = predecessorUID;
	}
	public Integer getLinkLag() {
		return LinkLag;
	}
	public void setLinkLag(Integer linkLag) {
		LinkLag = linkLag;
	}
	public String getTaskUID() {
		return TaskUID;
	}
	public void setTaskUID(String taskUID) {
		TaskUID = taskUID;
	}
	
}
