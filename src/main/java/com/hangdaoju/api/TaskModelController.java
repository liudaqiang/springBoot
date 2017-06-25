package com.hangdaoju.api;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hangdaoju.core.ResponseModel;
import com.hangdaoju.model.OneTask;
import com.hangdaoju.model.TaskModel;
import com.hangdaoju.service.TaskService;
import com.hangdaoju.util.GetMapUtils;
import com.hangdaoju.util.GsonUtil;

/**
 * 修改白本任务
 * 
 * @author l.q
 *
 */
@Controller
@RequestMapping("/taskModel")
public class TaskModelController {
	@Autowired
	private TaskService taskService;
	/**
	 * 新版本插入task
	 * 
	 * 
	 * 
	 * @param data
	 * @return
	 */
	@PostMapping("/add")
	@ResponseBody
	public ResponseModel add(String data,String scheduleId) {
		if(StringUtils.isNotBlank(scheduleId)){
			List<OneTask> oneTaskList = GsonUtil.parseJsonArrayWithGson(data, OneTask.class);
			TaskModel taskModel = new TaskModel();
			taskModel.setOneTask(oneTaskList);
			taskModel.setScheduleId(scheduleId);
			taskService.save(taskModel);
			return new ResponseModel(200,null,"插入成功");
		}
		return new ResponseModel(300,null,"插入失败,id不存在");
	}
	
	@PostMapping("/update")
	@ResponseBody
	public ResponseModel update(String data,String scheduleId){
		if(StringUtils.isNotBlank(scheduleId)){
			taskService.delete(scheduleId);
			List<OneTask> oneTaskList = GsonUtil.parseJsonArrayWithGson(data, OneTask.class);
			TaskModel taskModel = new TaskModel();
			taskModel.setOneTask(oneTaskList);
			taskModel.setId(scheduleId);
			taskService.save(taskModel);
			return new ResponseModel(200,null,"修改成功");
		}
		return new ResponseModel(300,null,"修改失败，id不存在");
	}
	
	@PostMapping("/findById")
	@ResponseBody
	public ResponseModel findById(String scheduleId){
		if(StringUtils.isNotBlank(scheduleId)){
			TaskModel taskModel = taskService.findByCondition(scheduleId);
			return new ResponseModel(200,GetMapUtils.getMap("taskModel", taskModel),"查询成功");
		}
		return new ResponseModel(300,null,"计划id不存在");
	}
}
