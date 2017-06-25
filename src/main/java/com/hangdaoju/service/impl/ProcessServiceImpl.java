package com.hangdaoju.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hangdaoju.dao.ProcessDao;
import com.hangdaoju.model.Process;
import com.hangdaoju.service.ProcessService;

@Service
public class ProcessServiceImpl implements ProcessService {

	@Autowired
	ProcessDao processDao;

	@Override
	public String add(Process process) {
		process.setId(null);
		return processDao.save(process).getId();
	}

	@Override
	public void update(Process process) {
		processDao.updateById(process.getId(), process);
	}

	@Override
	public void delete(Process process) {
		processDao.delete(process);
	}

	@Override
	public void deleteById(String id) {
		Process process = new Process();
		process.setId(id);
		processDao.delete(process);
	}

	@Override
	public List<Process> findByCondition(Process process) {
		return processDao.findByCondition(process);
	}

	@Override
	public Process find(String id) {
		return processDao.findById(id);
	}

}
