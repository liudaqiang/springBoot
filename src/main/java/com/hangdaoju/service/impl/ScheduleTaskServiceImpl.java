package com.hangdaoju.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hangdaoju.dao.ScheduleTaskDao;
import com.hangdaoju.model.Component;
import com.hangdaoju.model.Equipment;
import com.hangdaoju.model.Material;
import com.hangdaoju.model.RelationTask;
import com.hangdaoju.model.ScheduleTask;
import com.hangdaoju.model.Task;
import com.hangdaoju.model.TypeWord;
import com.hangdaoju.service.ScheduleTaskService;
/**
 * 计划任务Service层实现
 * @author l.q
 *
 */
@Service
public class ScheduleTaskServiceImpl implements ScheduleTaskService{
	@Autowired
	private ScheduleTaskDao scheduleTaskDao;
	
	@Override
	public void addTask(Task task, String id) {
		//根据计划id查询   计划任务是否存在
		ScheduleTask scheduleTask = new ScheduleTask();
		scheduleTask.setScheduleId(id);
		List<ScheduleTask> scheduleTaskList = scheduleTaskDao.findByCondition(scheduleTask);
		//有则修改(非第一个)
		if(scheduleTaskList == null || scheduleTaskList.size() == 0){
			scheduleTask = new ScheduleTask();
		}else{
			scheduleTask = scheduleTaskList.get(0);
		}
		List<Task> taskList = scheduleTask.getTaskList();
		if(taskList == null){
			taskList = new ArrayList<Task>();
		}
		task.setId(new ObjectId().toString());
		taskList.add(task);
		scheduleTask.setTaskList(taskList);
		scheduleTask.setScheduleId(id);
		scheduleTaskDao.customSave(scheduleTask);
	}

	@Override
	public Task findTask(String taskId, String id) {
		ScheduleTask scheduleTask = new ScheduleTask();
		scheduleTask.setScheduleId(id);
		List<ScheduleTask> scheduleTaskList = scheduleTaskDao.findByCondition(scheduleTask);
		if(scheduleTaskList == null || scheduleTaskList.size() == 0){
			return null;
		}
		scheduleTask = scheduleTaskList.get(0);
		if(scheduleTask != null){
			List<Task> taskList = scheduleTask.getTaskList();
			for(Task oneTask:taskList){
				if(oneTask.getId().equals(taskId)){
					return oneTask;
				}
			}
		}
		return null;
	}
	@Override
	public void updateTask(Task task, String id) {
		//根据计划id查询   计划任务是否存在
		ScheduleTask scheduleTask = new ScheduleTask();
		scheduleTask.setScheduleId(id);
		List<ScheduleTask> scheduleTaskList = scheduleTaskDao.findByCondition(scheduleTask);
		//因为是修改  所以计划必须存在
		if(scheduleTaskList != null && scheduleTaskList.size() > 0){
			scheduleTask = scheduleTaskList.get(0);
			List<Task> taskList = scheduleTask.getTaskList();
			if(taskList != null){
				//去掉原有的   新增修改的
				for(int i=0;i<taskList.size();i++){
					if(taskList.get(i).getId().equals(task.getId())){
						taskList.remove(i);
						taskList.add(task);
						break;
					}
				}
				scheduleTask.setTaskList(taskList);
				scheduleTaskDao.customSave(scheduleTask);
			}
			
		}
	}
	@Override
	public void deleteTask(Task task, String id) {
		ScheduleTask scheduleTask = new ScheduleTask();
		scheduleTask.setScheduleId(id);
		List<ScheduleTask> scheduleTaskList = scheduleTaskDao.findByCondition(scheduleTask);
		if(scheduleTaskList != null && scheduleTaskList.size() != 0){
			List<Task> taskList = scheduleTaskList.get(0).getTaskList();
			scheduleTask = scheduleTaskList.get(0);
			if(taskList != null && taskList.size() != 0){
				//遍历task并将对应的给删除掉
				//记录下来删除掉的sort
				int sort = 0;
				for(int i= 0;i<taskList.size();i++){
					if(taskList.get(i).getId().equals(task.getId())){
						sort = taskList.get(i).getSort();
						taskList.remove(i);
						break;
					}
				}
				//将sort后的所有sort前移
				for(Task oneTask:taskList){
					if(oneTask.getSort()>sort){
						oneTask.setSort(oneTask.getSort()-1);
					}
				}
			}
			
			scheduleTask.setTaskList(taskList);
			scheduleTaskDao.customSave(scheduleTask);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> findList(String id) {
		ScheduleTask scheduleTask = new ScheduleTask();
		scheduleTask.setScheduleId(id);
		List<ScheduleTask> scheduleTaskList = scheduleTaskDao.findByCondition(scheduleTask);
		if(scheduleTaskList != null && scheduleTaskList.size() != 0){
			scheduleTask = scheduleTaskList.get(0);
		}else{
			return null;
		}
		if(scheduleTask != null){
			List<Task> taskList = scheduleTask.getTaskList();
			//校验查询结果，并排序
			if(taskList ==null || taskList.size()==0){
				return null;
			}
			Collections.sort(taskList);
			return taskList;
			//获得顶级任务列表
//			List<Task> topTaskList = new ArrayList<Task>();
//			for(Task oneTask:taskList){
//				if(oneTask.getGrade() == 1){
//					topTaskList.add(oneTask);
//				}
//			}
//			for(int i=0;i<topTaskList.size();i++){
//				if(topTaskList.size()-1 == i){
//					getChildTask(topTaskList.get(i),topTaskList.get(i).getSort()+1,taskList.size(),2,taskList);
//				}else{
//					getChildTask(topTaskList.get(i),topTaskList.get(i).getSort()+1,topTaskList.get(i+1).getSort()-1,2,taskList);
//				}
//			}
			//return topTaskList;
		}
		return null;
	}
	private void getChildTask(Task task,Integer beginSort,Integer endSort,Integer grade,List<Task> taskAllList){
		List<Task>topTaskList = new ArrayList<Task>();
		for(int i=0;i<taskAllList.size();i++){
			if(taskAllList.get(i).getSort()>=beginSort && taskAllList.get(i).getSort()<=endSort && taskAllList.get(i).getGrade().equals(grade)){
				topTaskList.add(taskAllList.get(i));
			}
		}
		task.setChildTaskList(topTaskList);
		for(int i=0;i<topTaskList.size();i++){
			if(topTaskList.size()-1 == i){
				getChildTask(topTaskList.get(i),topTaskList.get(i).getSort()+1,endSort,topTaskList.get(i).getGrade()+1,taskAllList);
			}else{
				getChildTask(topTaskList.get(i),topTaskList.get(i).getSort()+1,topTaskList.get(i+1).getSort()-1,topTaskList.get(i).getGrade()+1,taskAllList);
			}
		}
	}
	@Override
	public void updateTaskSort(String taskId, String id,Integer sort) {
		ScheduleTask scheduleTask = new ScheduleTask();
		scheduleTask.setScheduleId(id);
		List<ScheduleTask> scheduleTaskList = scheduleTaskDao.findByCondition(scheduleTask);
		//校验计划是否存在
		if(scheduleTaskList != null && scheduleTaskList.size() != 0){
			List<Task> taskList = scheduleTaskList.get(0).getTaskList();
			scheduleTask=scheduleTaskList.get(0);
			//校验任务是否存在
			if(taskList != null && taskList.size() != 0){
				//遍历任务
				//将sort之后的任务全部+1
				//并且等级变化成跟原本这个位置的相同
				//将对应的sort改变
				//将对应的等级改变
				int grade = 0;
				for(Task oneTask:taskList){
					if(oneTask.getSort().equals(sort)){
						grade = oneTask.getGrade();
					}
					if(oneTask.getSort() >= sort){
						oneTask.setSort(oneTask.getSort()+1);
					}
					if(taskId.equals(oneTask.getId())){
						oneTask.setSort(sort);
						oneTask.setGrade(grade);
					}
				}
				scheduleTaskDao.customSave(scheduleTask);
			}
		}
	}

	@Override
	public void downgradeTask(String taskId, String id) {
		ScheduleTask scheduleTask = new ScheduleTask();
		scheduleTask.setScheduleId(id);
		List<ScheduleTask> scheduleTaskList = scheduleTaskDao.findByCondition(scheduleTask);
		if(scheduleTaskList != null && scheduleTaskList.size() != 0){
			List<Task> taskList = scheduleTaskList.get(0).getTaskList();
			scheduleTask = scheduleTaskList.get(0);
			if(taskList != null && taskList.size() != 0){
				for(Task oneTask:taskList){
					if(oneTask.getId().equals(taskId)){
						oneTask.setGrade(oneTask.getGrade()+1);
					}
				}
				scheduleTaskDao.customSave(scheduleTask);
			}
		}
	}

	@Override
	public void upgradeTask(String taskId, String id) {
		ScheduleTask scheduleTask = new ScheduleTask();
		scheduleTask.setScheduleId(id);
		List<ScheduleTask> scheduleTaskList = scheduleTaskDao.findByCondition(scheduleTask);
		if(scheduleTaskList != null && scheduleTaskList.size() != 0){
			List<Task> taskList = scheduleTaskList.get(0).getTaskList();
			scheduleTask = scheduleTaskList.get(0);
			if(taskList != null && taskList.size() != 0){
				for(Task oneTask:taskList){
					if(oneTask.getId().equals(taskId)){
						oneTask.setGrade(oneTask.getGrade()-1);
					}
				}
				scheduleTaskDao.customSave(scheduleTask);
			}
		}
	}

	@Override
	public boolean addRelationTask(String scheduleId, String taskId, String relationId, Integer laterDate,
			Integer relationType) {
		ScheduleTask scheduleTask = new ScheduleTask();
		scheduleTask.setScheduleId(scheduleId);
		List<ScheduleTask> scheduleTaskList = scheduleTaskDao.findByCondition(scheduleTask);
		if(scheduleTaskList != null && scheduleTaskList.size() != 0){
			scheduleTask = scheduleTaskList.get(0);
			//获得t计划中的所有任务
			List<Task> taskList = scheduleTask.getTaskList();
			if(taskList != null && taskList.size() != 0){
				Task task = null;
				Task relationTask = null;
				//获得传入的任务id对象
				for(Task oneTask:taskList){
					if(oneTask.getId().equals(taskId)){
						task = oneTask;
					}
					if(oneTask.getId().equals(relationId)){
						relationTask = oneTask;
					}
				}
				if(task == null || relationTask == null){
					return false;
				}
				RelationTask rTask = new RelationTask();
				rTask.setLaterDate(laterDate);
				rTask.setRelationType(relationType);
				rTask.setTask(relationTask);
				//加入关系
				List<RelationTask> relationTaskList = task.getRelationTaskList()==null?new ArrayList<RelationTask>():task.getRelationTaskList();
				//校验关系是否已经存在
				for(int i=0;i<relationTaskList.size();i++){
					if(relationTaskList.get(i).getTask().getId().equals(relationId)){
						relationTaskList.remove(i);
						break;
					}
				}
				relationTaskList.add(rTask);
				task.setRelationTaskList(relationTaskList);
				scheduleTaskDao.customSave(scheduleTask);
				return true;
			}
		}
		return false;
	}


	@Override
	public boolean deleteRelationTask(String scheduleId, String taskId, String relationId) {
		ScheduleTask scheduleTask = new ScheduleTask();
		scheduleTask.setScheduleId(scheduleId);
		List<ScheduleTask> scheduleTaskList = scheduleTaskDao.findByCondition(scheduleTask);
		if(scheduleTaskList != null && scheduleTaskList.size() != 0){
			scheduleTask = scheduleTaskList.get(0);
			//获得t计划中的所有任务
			List<Task> taskList = scheduleTask.getTaskList();
			if(taskList != null && taskList.size() != 0){
				Task task = null;
				Task relationTask = null;
				//获得传入的任务id对象
				for(Task oneTask:taskList){
					if(oneTask.getId().equals(taskId)){
						task = oneTask;
					}
					if(oneTask.getId().equals(relationId)){
						relationTask = oneTask;
					}
				}
				if(task == null || relationTask == null){
					return false;
				}
				//获得对应任务关系
				List<RelationTask> relationTaskList = task.getRelationTaskList()==null?new ArrayList<RelationTask>():task.getRelationTaskList();
				//校验关系是否已经存在
				for(int i=0;i<relationTaskList.size();i++){
					if(relationTaskList.get(i).getTask().getId().equals(relationId)){
						relationTaskList.remove(i);
						break;
					}
				}
				scheduleTaskDao.customSave(scheduleTask);
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public boolean addComponent(List<Component> componentList,String scheduleId,String taskId) {
		ScheduleTask scheduleTask = new ScheduleTask();
		scheduleTask.setScheduleId(scheduleId);
		List<ScheduleTask> scheduleTaskList = scheduleTaskDao.findByCondition(scheduleTask);
		if(scheduleTaskList != null && scheduleTaskList.size() != 0){
			scheduleTask = scheduleTaskList.get(0);
			//获得t计划中的所有任务
			List<Task> taskList = scheduleTask.getTaskList();
			Task task = null;
			if(taskList != null && taskList.size() != 0){
				//将我们需要的task取出来
				for(Task oneTask:taskList){
					if(oneTask.getId().equals(taskId)){
						task = oneTask;
						break;
					}
				}
				if(task == null){
					return false;
				}
				task.setComponentList(componentList);
				scheduleTaskDao.customSave(scheduleTask);
			}
			return false;
		}
		return false;
	}

	@Override
	public boolean addTypeWord(List<TypeWord> typeWordList, String scheduleId, String taskId) {
		ScheduleTask scheduleTask = new ScheduleTask();
		scheduleTask.setScheduleId(scheduleId);
		List<ScheduleTask> scheduleTaskList = scheduleTaskDao.findByCondition(scheduleTask);
		if(scheduleTaskList != null && scheduleTaskList.size() != 0){
			scheduleTask = scheduleTaskList.get(0);
			//获得t计划中的所有任务
			List<Task> taskList = scheduleTask.getTaskList();
			Task task = null;
			if(taskList != null && taskList.size() != 0){
				//将我们需要的task取出来
				for(Task oneTask:taskList){
					if(oneTask.getId().equals(taskId)){
						task = oneTask;
						break;
					}
				}
				if(task == null){
					return false;
				}
				task.setTypeWordList(typeWordList);
				scheduleTaskDao.customSave(scheduleTask);
			}
			return false;
		}
		return false;
	}

	@Override
	public boolean addMaterial(List<Material> mterialList, String scheduleId, String taskId) {
		ScheduleTask scheduleTask = new ScheduleTask();
		scheduleTask.setScheduleId(scheduleId);
		List<ScheduleTask> scheduleTaskList = scheduleTaskDao.findByCondition(scheduleTask);
		if(scheduleTaskList != null && scheduleTaskList.size() != 0){
			scheduleTask = scheduleTaskList.get(0);
			//获得t计划中的所有任务
			List<Task> taskList = scheduleTask.getTaskList();
			Task task = null;
			if(taskList != null && taskList.size() != 0){
				//将我们需要的task取出来
				for(Task oneTask:taskList){
					if(oneTask.getId().equals(taskId)){
						task = oneTask;
						break;
					}
				}
				if(task == null){
					return false;
				}
				task.setMaterialList(mterialList);
				scheduleTaskDao.customSave(scheduleTask);
			}
			return false;
		}
		return false;
	}

	@Override
	public boolean addEquipment(List<Equipment> equipmentList, String scheduleId, String taskId) {
		ScheduleTask scheduleTask = new ScheduleTask();
		scheduleTask.setScheduleId(scheduleId);
		List<ScheduleTask> scheduleTaskList = scheduleTaskDao.findByCondition(scheduleTask);
		if(scheduleTaskList != null && scheduleTaskList.size() != 0){
			scheduleTask = scheduleTaskList.get(0);
			//获得t计划中的所有任务
			List<Task> taskList = scheduleTask.getTaskList();
			Task task = null;
			if(taskList != null && taskList.size() != 0){
				//将我们需要的task取出来
				for(Task oneTask:taskList){
					if(oneTask.getId().equals(taskId)){
						task = oneTask;
						break;
					}
				}
				if(task == null){
					return false;
				}
				task.setEquipmentList(equipmentList);
				scheduleTaskDao.customSave(scheduleTask);
			}
			return false;
		}
		return false;
	}

	

}
