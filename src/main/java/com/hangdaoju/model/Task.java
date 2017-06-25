package com.hangdaoju.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.hangdaoju.core.customAnnotation.QueryField;
/**
 * 任务
 * @author l.q
 *
 */
@SuppressWarnings("rawtypes")
@Document
public class Task extends BaseModel implements Comparable{
	@Id
	@QueryField
	private String id;
	@QueryField(attribute="task_name")
	@Field("task_name")
	private String taskName;//任务名称
	@Field("schedule_id")
	@QueryField(attribute="schedule_id")
	private String scheduleId;//所属计划id
	@Field("begin_time")
	@QueryField(attribute="begin_time")
	private Date beginTime;//开始时间
	@Field("end_time")
	@QueryField(attribute="end_time")
	private Date endTime;//结束时间
	@Field("time_limit")
	@QueryField(attribute="time_limit")
	private Integer timeLimit;//工期
	@QueryField
	private Integer workload;//工程量
	@QueryField
	private String unit;//单位
	@QueryField
	private Person leader;//任务负责人
	@QueryField
	private Integer workFace=1;//工作面数量
	@Field("is_finish")
	@QueryField(attribute="is_finish")
	private Integer isFinish = 1;//任务完成状况  1未完成2完成
	@Field("is_milepost")
	@QueryField(attribute="is_milepost")
	private Integer isMilepost = 1;//是否为里程碑 1不是2是
	@QueryField
	private Integer grade=1;//任务等级 默认为1
	@QueryField
	private Integer sort;//排序号
	private List<Component> componentList = new ArrayList<Component>();//构件列表
	@QueryField
	private List<TypeWord> typeWordList = new ArrayList<TypeWord>();//工种列表
	@QueryField
	private List<Material> materialList = new ArrayList<Material>();//材料列表
	@QueryField
	private List<Equipment> equipmentList = new ArrayList<Equipment>();//设备列表
	@QueryField
	private List<RelationTask> relationTaskList = new ArrayList<RelationTask>();//任务关系
	@QueryField
	private List<Task> childTaskList = new ArrayList<Task>();//子任务列表
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}
	public Integer getWorkload() {
		return workload;
	}
	public void setWorkload(Integer workload) {
		this.workload = workload;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Person getLeader() {
		return leader;
	}
	public void setLeader(Person leader) {
		this.leader = leader;
	}
	public Integer getWorkFace() {
		return workFace;
	}
	public void setWorkFace(Integer workFace) {
		this.workFace = workFace;
	}
	public Integer getIsFinish() {
		return isFinish;
	}
	public void setIsFinish(Integer isFinish) {
		this.isFinish = isFinish;
	}
	public Integer getIsMilepost() {
		return isMilepost;
	}
	public void setIsMilepost(Integer isMilepost) {
		this.isMilepost = isMilepost;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public List<Component> getComponentList() {
		return componentList;
	}
	public void setComponentList(List<Component> componentList) {
		this.componentList = componentList;
	}
	public List<TypeWord> getTypeWordList() {
		return typeWordList;
	}
	public void setTypeWordList(List<TypeWord> typeWordList) {
		this.typeWordList = typeWordList;
	}
	public List<Material> getMaterialList() {
		return materialList;
	}
	public void setMaterialList(List<Material> materialList) {
		this.materialList = materialList;
	}
	public List<RelationTask> getRelationTaskList() {
		return relationTaskList;
	}
	public void setRelationTaskList(List<RelationTask> relationTaskList) {
		this.relationTaskList = relationTaskList;
	}
	public List<Task> getChildTaskList() {
		return childTaskList;
	}
	public void setChildTaskList(List<Task> childTaskList) {
		this.childTaskList = childTaskList;
	}
	public List<Equipment> getEquipmentList() {
		return equipmentList;
	}
	public void setEquipmentList(List<Equipment> equipmentList) {
		this.equipmentList = equipmentList;
	}
	@Override
	public int compareTo(Object o) {
		Task task = (Task)o;
		int otherSort = task.getSort();
		return this.sort.compareTo(otherSort);
	}
}