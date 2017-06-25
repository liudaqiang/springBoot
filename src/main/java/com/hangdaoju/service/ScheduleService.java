package com.hangdaoju.service;

import java.util.List;

import com.hangdaoju.model.Dict;
import com.hangdaoju.model.Schedule;

public interface ScheduleService {
	//配置进度计划
	public void add(Schedule schedule);
	//修改进度计划
	public void update(Schedule schedule);
	//编辑设备类型
	public void updateEquipment(String id,Dict dict);
	//删除设备类型
	public void deleteEquipment(String id,Dict dict);
	//编辑材料类型
	public void updateMaterial(String id,Dict dict);
	//删除材料类型
	public void deleteMaterial(String id,Dict dict);
	//编辑工种类型
	public void updateTypeWord(String id,Dict dict);
	//删除工种类型
	public void deleteTypeWord(String id,Dict dict);
	//查看进度计划列表
	public List<Schedule> findByCondition(Schedule schedule);
	//查看单个进度计划
	public Schedule findById(Schedule schedule);
	//查询某计划材料列表
	public List<Dict> findMaterialListById(String id);
	//查询某计划工种列表
	public List<Dict> findTypeWordListById(String id);
	//查询某计划设备列表
	public List<Dict> findEquipmentListById(String id);
}
