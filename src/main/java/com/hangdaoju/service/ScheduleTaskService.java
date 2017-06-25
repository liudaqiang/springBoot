package com.hangdaoju.service;

import java.util.List;

import com.hangdaoju.model.Component;
import com.hangdaoju.model.Equipment;
import com.hangdaoju.model.Material;
import com.hangdaoju.model.Task;
import com.hangdaoju.model.TypeWord;

public interface ScheduleTaskService {
	//新增计划1
	public void addTask(Task task,String id);
	//查询计划详情
	public Task findTask(String taskId,String id);
	//修改计划2
	public void updateTask(Task task,String id);
	//删除计划3
	public void deleteTask(Task task,String id);
	//根据sort grade进行排序4
	public List<Task> findList(String id);
	//调换任务位置5
	public void updateTaskSort(String taskId,String id,Integer sort);
	//降级任务6
	public void downgradeTask(String taskId,String id);
	//升级任务7
	public void upgradeTask(String taskId,String id);
	//新增构件
	public boolean addComponent(List<Component> componentList,String scheduleId,String taskId);
	//新增工种
	public boolean addTypeWord(List<TypeWord> typeWordList,String scheduleId,String taskId);
	//新增材料
	public boolean addMaterial(List<Material> mterialList,String scheduleId,String taskId);
	//新增设备
	public boolean addEquipment(List<Equipment> equipmentList,String scheduleId,String taskId);
	//新增任务关系
	public boolean addRelationTask(String scheduleId,String taskId,String relationId,Integer laterDate,Integer relationType);
	//删除任务关系
	public boolean deleteRelationTask(String scheduleId,String taskId,String relationId);
}
