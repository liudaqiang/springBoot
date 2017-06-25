package com.hangdaoju.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hangdaoju.dao.PersonDao;
import com.hangdaoju.dao.ScheduleDao;
import com.hangdaoju.model.Dict;
import com.hangdaoju.model.Person;
import com.hangdaoju.model.Schedule;
import com.hangdaoju.service.ScheduleService;
/**
 * 进度计划Service层
 * @author l.q
 *
 */
@Service
public class ScheduleServiceImpl implements ScheduleService{
	@Autowired
	private ScheduleDao scheduleDao;
	@Autowired
	private PersonDao personDao;
	@Override
	public void add(Schedule schedule) {
		Person person = personDao.findById(schedule.getLeader().getId());
		schedule.setLeader(person);
		scheduleDao.customAdd(schedule);
	}

	@Override
	public void update(Schedule schedule) {
	}

	@Override
	public void updateEquipment(String id, Dict dict) {
		Schedule schedule = scheduleDao.findById(id);
		List<Dict> dictList =schedule.getEquipmentList();
		if(dictList == null){
			dictList = new ArrayList<Dict>();
		}
		boolean flag = true;
		//将原有的进行比较，如果存在则不插入
		for(Dict oneDict:dictList){
			if(oneDict.getId().equals(dict.getId())){
				flag = false;
			}
		}
		if(flag){
			dictList.add(dict);
			schedule.setEquipmentList(dictList);
			scheduleDao.customAdd(schedule);
		}
	}

	@Override
	public void deleteEquipment(String id, Dict dict) {
		Schedule schedule = scheduleDao.findById(id);
		List<Dict> dictList =schedule.getEquipmentList();
		if(dictList == null){
			dictList = new ArrayList<Dict>();
		}
		boolean flag = false;
		//将原有的进行比较，如果存在则直接删除
		for(int i=0;i<dictList.size();i++){
			if(dictList.get(i).getId().equals(dict.getId())){
				dictList.remove(i);
				flag = true;
			}
		}
		if(flag){
			schedule.setEquipmentList(dictList);
			scheduleDao.customAdd(schedule);
		}
	}

	@Override
	public void updateMaterial(String id, Dict dict) {
		Schedule schedule = scheduleDao.findById(id);
		List<Dict> dictList =schedule.getMaterialList();
		if(dictList == null){
			dictList = new ArrayList<Dict>();
		}
		boolean flag = true;
		//将原有的进行比较，如果存在则不插入
		for(Dict oneDict:dictList){
			if(oneDict.getId().equals(dict.getId())){
				flag = false;
			}
		}
		if(flag){
			dictList.add(dict);
			schedule.setMaterialList(dictList);;
			scheduleDao.customAdd(schedule);
		}
	}

	@Override
	public void deleteMaterial(String id, Dict dict) {
		Schedule schedule = scheduleDao.findById(id);
		List<Dict> dictList =schedule.getMaterialList();
		if(dictList == null){
			dictList = new ArrayList<Dict>();
		}
		boolean flag = false;
		//将原有的进行比较，如果存在则直接删除
		for(int i=0;i<dictList.size();i++){
			if(dictList.get(i).getId().equals(dict.getId())){
				dictList.remove(i);
				flag = true;
			}
		}
		if(flag){
			schedule.setMaterialList(dictList);;
			scheduleDao.customAdd(schedule);
		}
	}
	@Override
	public void updateTypeWord(String id, Dict dict) {
		Schedule schedule = scheduleDao.findById(id);
		List<Dict> dictList =schedule.getTypeWordList();
		if(dictList == null){
			dictList = new ArrayList<Dict>();
		}
		boolean flag = true;
		//将原有的进行比较，如果存在则不插入
		for(Dict oneDict:dictList){
			if(oneDict.getId().equals(dict.getId())){
				flag = false;
			}
		}
		if(flag){
			dictList.add(dict);
			schedule.setTypeWordList(dictList);
			scheduleDao.customAdd(schedule);
		}
	}

	@Override
	public void deleteTypeWord(String id, Dict dict) {
		Schedule schedule = scheduleDao.findById(id);
		List<Dict> dictList =schedule.getTypeWordList();
		if(dictList == null){
			dictList = new ArrayList<Dict>();
		}
		boolean flag = false;
		//将原有的进行比较，如果存在则直接删除
		for(int i=0;i<dictList.size();i++){
			if(dictList.get(i).getId().equals(dict.getId())){
				dictList.remove(i);
				flag = true;
			}
		}
		if(flag){
			schedule.setTypeWordList(dictList);
			scheduleDao.customAdd(schedule);
		}
	}
	@Override
	public List<Schedule> findByCondition(Schedule schedule) {
		return scheduleDao.findByCondition(schedule);
	}

	@Override
	public Schedule findById(Schedule schedule) {
		return scheduleDao.findById(schedule.getId());
	}

	@Override
	public List<Dict> findMaterialListById(String id) {
		Schedule schedule = scheduleDao.findById(id);
		if(schedule != null){
			return schedule.getMaterialList();
		}
		return null;
	}

	@Override
	public List<Dict> findTypeWordListById(String id) {
		Schedule schedule = scheduleDao.findById(id);
		if(schedule != null){
			return schedule.getTypeWordList();
		}
		return null;
	}

	@Override
	public List<Dict> findEquipmentListById(String id) {
		Schedule schedule = scheduleDao.findById(id);
		if(schedule != null){
			return schedule.getEquipmentList();
		}
		return null;
	}


	
}
