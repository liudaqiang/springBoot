package com.hangdaoju.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

public class OneTask {
	@Field("UID")
	private String UID;
	private String Start;//开始时间
	private String ActualFinish;//完成
	private String ActualStart;//开始
	private String Finish;//结束时间
	private String PercentComplete;//百分比
	private Integer Summary = 0;//摘要任务0不是1是
	private Integer Critical = 0;//是否关键人物0不是1是
	private Integer Milepost = 0;//是否为里程碑 0不是1是
	private Integer isFinish = 0;//任务完成状况  0未完成1完成
	private List<SimpleRelationTaskModel> PredecessorLink = new ArrayList<SimpleRelationTaskModel>();//任务关系
	private String ParentTaskUID;//级别
	private Integer Workload;//工程量
	private String Unit;//单位
	private String Leader;//任务负责人
	private Integer WorkFace=1;//工作面数量
	private String ComponentList;//构件列表
	private String TypeWordList;//工种列表
	private String MaterialList;//材料列表
	private String EquipmentList;//设备列表
	private String Name;//名称
	private String Duration;//工期
	private List<OneTask> children;
	
	public String getUID() {
		return UID;
	}
	public void setUID(String uID) {
		UID = uID;
	}
	public String getStart() {
		return Start;
	}
	public void setStart(String start) {
		Start = start;
	}
	public String getActualFinish() {
		return ActualFinish;
	}
	public void setActualFinish(String actualFinish) {
		ActualFinish = actualFinish;
	}
	public String getActualStart() {
		return ActualStart;
	}
	public void setActualStart(String actualStart) {
		ActualStart = actualStart;
	}
	public String getFinish() {
		return Finish;
	}
	public void setFinish(String finish) {
		Finish = finish;
	}
	public String getPercentComplete() {
		return PercentComplete;
	}
	public void setPercentComplete(String percentComplete) {
		PercentComplete = percentComplete;
	}
	public Integer getSummary() {
		return Summary;
	}
	public void setSummary(Integer summary) {
		Summary = summary;
	}
	public Integer getCritical() {
		return Critical;
	}
	public void setCritical(Integer critical) {
		Critical = critical;
	}
	public Integer getMilepost() {
		return Milepost;
	}
	public void setMilepost(Integer milepost) {
		Milepost = milepost;
	}
	public Integer getIsFinish() {
		return isFinish;
	}
	public void setIsFinish(Integer isFinish) {
		this.isFinish = isFinish;
	}
	public List<SimpleRelationTaskModel> getPredecessorLink() {
		return PredecessorLink;
	}
	public void setPredecessorLink(List<SimpleRelationTaskModel> predecessorLink) {
		PredecessorLink = predecessorLink;
	}
	
	public String getParentTaskUID() {
		return ParentTaskUID;
	}
	public void setParentTaskUID(String parentTaskUID) {
		ParentTaskUID = parentTaskUID;
	}
	public Integer getWorkload() {
		return Workload;
	}
	public void setWorkload(Integer workload) {
		Workload = workload;
	}
	public String getUnit() {
		return Unit;
	}
	public void setUnit(String unit) {
		Unit = unit;
	}
	public String getLeader() {
		return Leader;
	}
	public void setLeader(String leader) {
		Leader = leader;
	}
	public Integer getWorkFace() {
		return WorkFace;
	}
	public void setWorkFace(Integer workFace) {
		WorkFace = workFace;
	}
	public String getComponentList() {
		return ComponentList;
	}
	public void setComponentList(String componentList) {
		ComponentList = componentList;
	}
	public String getTypeWordList() {
		return TypeWordList;
	}
	public void setTypeWordList(String typeWordList) {
		TypeWordList = typeWordList;
	}
	public String getMaterialList() {
		return MaterialList;
	}
	public void setMaterialList(String materialList) {
		MaterialList = materialList;
	}
	public String getEquipmentList() {
		return EquipmentList;
	}
	public void setEquipmentList(String equipmentList) {
		EquipmentList = equipmentList;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDuration() {
		return Duration;
	}
	public void setDuration(String duration) {
		Duration = duration;
	}
	public List<OneTask> getChildren() {
		return children;
	}
	public void setChildren(List<OneTask> children) {
		this.children = children;
	}
	
	
	
	
	
}
