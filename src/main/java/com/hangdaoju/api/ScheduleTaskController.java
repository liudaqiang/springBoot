package com.hangdaoju.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hangdaoju.core.ResponseModel;
import com.hangdaoju.model.Component;
import com.hangdaoju.model.Dict;
import com.hangdaoju.model.Equipment;
import com.hangdaoju.model.Material;
import com.hangdaoju.model.Person;
import com.hangdaoju.model.Task;
import com.hangdaoju.model.TypeWord;
import com.hangdaoju.service.DictService;
import com.hangdaoju.service.PersonService;
import com.hangdaoju.service.ScheduleTaskService;
import com.hangdaoju.util.DateUtils;
import com.hangdaoju.util.GetMapUtils;
import com.hangdaoju.util.StringUtils;
/**
 * 计划任务
 * @author l.q
 *
 */
@Controller
@RequestMapping("/scheduleTask")
public class ScheduleTaskController {
	@Autowired
	private ScheduleTaskService scheduleTaskService;
	@Autowired
	private PersonService personService;
	@Autowired
	private DictService dictService;
	/**
	 * 新增任务
	 * @param task
	 * @param scheduleId计划id
	 * @param taskName计划名称
	 * @param beginTimeStr开始时间
	 * @param endTimeStr结束时间
	 * @param leaderId责任人
	 * @param timeLimit工期
	 * @param workload工作量
	 * @param unit单位
	 * @param workFace工作面数量
	 * @param isFinish是否完成
	 * @param isMilepost是否里程碑
	 * @param grade等级
	 * @param sort排序号
	 * @return
	 */
	@PostMapping("/addTask")
	@ResponseBody
	public ResponseModel addTask(Task task,String scheduleId,String beginTimeStr,String endTimeStr,String leaderId){
		if(StringUtils.isNotBlank(scheduleId) &&
				StringUtils.isNotBlank(task.getTaskName()) &&
				StringUtils.isNotBlank(beginTimeStr) &&
				StringUtils.isNotBlank(endTimeStr) &&
				StringUtils.isNotBlank(task.getUnit()) &&
				StringUtils.isNotBlank(leaderId) &&
				task.getGrade()!=null &&
				task.getSort() != null &&
				task.getWorkload() != null){
			Person person = personService.findById(leaderId);
			task.setScheduleId(scheduleId);
			task.setLeader(person);
			task.setBeginTime(DateUtils.parseDate(beginTimeStr));
			task.setEndTime(DateUtils.parseDate(endTimeStr));
			scheduleTaskService.addTask(task, scheduleId);
			return new ResponseModel(200,null,"插入成功");
		}
		return new ResponseModel(300,null,"插入失败，数据参数不正确");
	}
	/**
	 * 查询某任务
	 * @param taskId 任务id
	 * @param scheduleId 计划id
	 * @return
	 */
	@PostMapping("/findTask")
	@ResponseBody
	public ResponseModel findTask(String taskId,String scheduleId){
		if(StringUtils.isNotBlank(taskId) &&
				StringUtils.isNotBlank(scheduleId)){
			Task task = scheduleTaskService.findTask(taskId, scheduleId);
			if(task == null){
				return new ResponseModel(200,null,"查询成功，但查询结果为空，说明传入的任务id不正确");
			}
			return new ResponseModel(200,GetMapUtils.getMap("task", task),"查询成功");
		}
		return new ResponseModel(300,null,"查询失败，数据不正确");
	}
	/**
	 * 查询任务列表
	 * @param id  计划id
	 * @return
	 */
	@PostMapping("/findTaskList")
	@ResponseBody
	public ResponseModel findTaskList(String id){
		if(StringUtils.isNotBlank(id)){
			List<Task> taskList = scheduleTaskService.findList(id);
			if(taskList == null || taskList.size() == 0){
				return new ResponseModel(200,null,"请求成功，但查询结果为空");
			}
			return new ResponseModel(200,GetMapUtils.getMap("taskList", taskList),"查询成功");
		}
		return new ResponseModel(300,null,"查询失败，计划id为空");
	}
	/**
	 * 修改任务
	 * @param task
	 * @param scheduleId计划id
	 * @param taskName计划名称
	 * @param beginTimeStr开始时间
	 * @param endTimeStr结束时间
	 * @param leaderId责任人
	 * @param timeLimit工期
	 * @param workload工作量
	 * @param unit单位
	 * @param workFace工作面数量
	 * @param isFinish是否完成
	 * @param isMilepost是否里程碑
	 * @param grade等级
	 * @param sort排序号
	 * @param id 任务id
	 * @return
	 */
	@PostMapping("/updateTask")
	@ResponseBody
	public ResponseModel updateTask(Task task,String scheduleId,String beginTimeStr,String endTimeStr,String leaderId){
		if(StringUtils.isNotBlank(task.getId()) &&
				StringUtils.isNotBlank(scheduleId) &&
				StringUtils.isNotBlank(task.getTaskName()) &&
				StringUtils.isNotBlank(beginTimeStr) &&
				StringUtils.isNotBlank(endTimeStr) &&
				StringUtils.isNotBlank(task.getUnit()) &&
				StringUtils.isNotBlank(leaderId) &&
				task.getGrade()!=null &&
				task.getSort() != null &&
				task.getWorkload() != null){
			scheduleTaskService.updateTask(task, scheduleId);
			return new ResponseModel(200,null,"修改成功");
		}
		return new ResponseModel(300,null,"修改失败，数据参数不正确");
	}
	/**
	 * 
	 * @param id任务ID
	 * @param scheduleId计划id
	 * @return
	 */
	@PostMapping("/deleteTask")
	@ResponseBody
	public ResponseModel deleteTask(Task task,String scheduleId){
		if(StringUtils.isNotBlank(task.getId()) &&
				StringUtils.isNotBlank(scheduleId)){
			scheduleTaskService.deleteTask(task, scheduleId);
			return new ResponseModel(200,null,"删除成功");
		}
		return new ResponseModel(300,null,"删除失败,参数不正确");
	}
	/**
	 * 修改任务排序
	 * @param taskId任务id
	 * @param scheduleId计划id
	 * @param sort排序
	 * @return
	 */
	@PostMapping("/updateTaskSort")
	@ResponseBody
	public ResponseModel updateTaskSort(String taskId,String scheduleId,Integer sort){
		if(StringUtils.isNoneBlank(taskId) &&
				StringUtils.isNotBlank(scheduleId) &&
				sort != null){
			scheduleTaskService.updateTaskSort(taskId, scheduleId, sort);
			return new ResponseModel(200,null,"修改成功");
		}
		return new ResponseModel(300,null,"参数异常");
	}
	/**
	 * 降级
	 * @param taskId任务id
	 * @param id计划id
	 * @return
	 */
	@PostMapping("/downgradeTask")
	@ResponseBody
	public ResponseModel downgradeTask(String taskId, String id){
		if(StringUtils.isNotBlank(taskId) &&
				StringUtils.isNotBlank(id)){
			scheduleTaskService.downgradeTask(taskId, id);
			return new ResponseModel(200,null,"修改成功");
		}
		return new ResponseModel(300,null,"参数异常");
	}
	/**
	 * 升级
	 * @param taskId任务id
	 * @param id计划id
	 * @return
	 */
	@PostMapping("/upgradeTask")
	@ResponseBody
	public ResponseModel upgradeTask(String taskId, String id){
		if(StringUtils.isNotBlank(taskId) &&
				StringUtils.isNotBlank(id)){
			scheduleTaskService.upgradeTask(taskId, id);
			return new ResponseModel(200,null,"修改成功");
		}
		return new ResponseModel(300,null,"参数异常");
	}
	/**
	 * 编辑任务关系
	 * @param scheduleId 计划id(必须)
	 * @param taskId	任务id(必须)
	 * @param relationId	关系任务Id(必须)
	 * @param laterDate	延迟时间(非必须 默认0)
	 * @param relationType	关系类型(关联方式1FS完成开始2SS同时开始3FF同时完成4SF开始完成)
	 * @return
	 */
	@PostMapping("/addRelationTask")
	@ResponseBody
	public ResponseModel addRelationTask(String scheduleId, String taskId, String relationId, Integer laterDate,
			Integer relationType){
		if(StringUtils.isNotBlank(scheduleId) &&
				StringUtils.isNotBlank(taskId) &&
				StringUtils.isNotBlank(relationId) &&
				relationType != null){
			if(scheduleTaskService.addRelationTask(scheduleId, taskId, relationId, laterDate, relationType)){
				return new ResponseModel(200,null,"编辑成功");
			}
			return new ResponseModel(300,null,"编辑失败，原因id不存在");
		}
		return new ResponseModel(300,null,"参数不正确");
	}
	/**
	 * 删除任务关系
	 * @param scheduleId 计划id (必须)
	 * @param taskId	任务id(必须)
	 * @param relationId	关系id(必须)
	 * @return
	 */
	@PostMapping("/deleteRelationTask")
	@ResponseBody
	public ResponseModel deleteRelationTask(String scheduleId, String taskId, String relationId){
		if(StringUtils.isNotBlank(scheduleId) &&
				StringUtils.isNotBlank(taskId) &&
				StringUtils.isNotBlank(relationId)){
			if(scheduleTaskService.deleteRelationTask(scheduleId, taskId, relationId)){
				return new ResponseModel(200,null,"删除成功");
			}
			return new ResponseModel(300,null,"删除失败，原因id不存在");
		}
		return new ResponseModel(300,null,"参数不正确");
	}
	/**
	 * 新增构件
	 * @param names 构件名称列表  以逗号形式分割(必须)
	 * @param componentTypes	组件类型列表 以逗号形式分割(必须)
	 * @param nums	组件数量列表 以逗号形式分割(必须)
	 * @param scheduleId	计划id
	 * @param taskId	任务id
	 * @param currentUser
	 * @return
	 */
	@PostMapping("/addComponent")
	@ResponseBody
	public ResponseModel addComponent(String names,String componentTypes,String nums,String scheduleId,String taskId){
		if(StringUtils.isNotBlank(names) &&
				StringUtils.isNotBlank(componentTypes) &&
				StringUtils.isNotBlank(nums)){
			String [] nameStrList = names.split(",");
			String [] componentTypeStrList = componentTypes.split(",");
			String [] numsStr = nums.split(",");
			if(nameStrList.length == componentTypeStrList.length && nameStrList.length == numsStr.length){
				List<Component> componentList = new ArrayList<Component>();
				for(int i=0;i<nameStrList.length;i++){
					Component component = new Component();
					component.setComponentType(componentTypeStrList[i]);
					component.setNum(Integer.parseInt(numsStr[i]));
					component.setName(nameStrList[i]);
					componentList.add(component);
				}
				scheduleTaskService.addComponent(componentList, scheduleId, taskId);
				return new ResponseModel(200,null,"插入成功");
			}
			return new ResponseModel(300,null,"参数不正确"); 
 		}
		return new ResponseModel(300,null,"参数不正确");
	}
	/**
	 * 编辑工种
	 * @param ids 工种名称列表  以逗号形式分割(必须)
	 * @param nums	数量列表 以逗号形式分割(必须)
	 * @param scheduleId	计划id
	 * @param taskId	任务id
	 * @param currentUser
	 * @return
	 */
	@PostMapping("/addTypeWord")
	@ResponseBody
	public ResponseModel addTypeWord(String ids,String nums,String scheduleId,String taskId){
		if(StringUtils.isNotBlank(ids) &&
				StringUtils.isNotBlank(nums)){
			String [] idStrList = ids.split(",");
			String [] numsStr = nums.split(",");
			if(numsStr.length == idStrList.length){
				List<TypeWord> typeWordList = new ArrayList<TypeWord>();
				for(int i=0;i<idStrList.length;i++){
					Dict dict = dictService.findById(idStrList[i]);
					TypeWord typeWord = new TypeWord();
					typeWord.setDict(dict);
					typeWord.setNum(Integer.parseInt(numsStr[i]));
					typeWordList.add(typeWord);
				}
				scheduleTaskService.addTypeWord(typeWordList, scheduleId, taskId);
				return new ResponseModel(200,null,"编辑成功");
			}
			return new ResponseModel(300,null,"参数不正确"); 
 		}
		return new ResponseModel(300,null,"参数不正确");
	}
	/**
	 * 编辑材料
	 * @param ids 材料名称列表  以逗号形式分割(必须)
	 * @param nums	数量列表 以逗号形式分割(必须)
	 * @param scheduleId	计划id
	 * @param taskId	任务id
	 * @param currentUser
	 * @return
	 */
	@PostMapping("/addMaterial")
	@ResponseBody
	public ResponseModel addMaterial(String ids,String nums,String scheduleId,String taskId){
		if(StringUtils.isNotBlank(ids) &&
				StringUtils.isNotBlank(nums)){
			String [] idStrList = ids.split(",");
			String [] numsStr = nums.split(",");
			if(numsStr.length == idStrList.length){
				List<Material> mterialList = new ArrayList<Material>();
				for(int i=0;i<idStrList.length;i++){
					Dict dict = dictService.findById(idStrList[i]);
					Material material = new Material();
					material.setDict(dict);
					material.setNum(Integer.parseInt(numsStr[i]));
					mterialList.add(material);
				}
				scheduleTaskService.addMaterial(mterialList, scheduleId, taskId);
				return new ResponseModel(200,null,"编辑成功");
			}
			return new ResponseModel(300,null,"参数不正确"); 
 		}
		return new ResponseModel(300,null,"参数不正确");
	}
	/**
	 * 设备材料
	 * @param ids 设备名称列表  以逗号形式分割(必须)
	 * @param nums	数量列表 以逗号形式分割(必须)
	 * @param scheduleId	计划id
	 * @param taskId	任务id
	 * @param currentUser
	 * @return
	 */
	@PostMapping("/addEquipment")
	@ResponseBody
	public ResponseModel addEquipment(String ids,String nums,String scheduleId,String taskId){
		if(StringUtils.isNotBlank(ids) &&
				StringUtils.isNotBlank(nums)){
			String [] idStrList = ids.split(",");
			String [] numsStr = nums.split(",");
			if(numsStr.length == idStrList.length){
				List<Equipment> equipmentList = new ArrayList<Equipment>();
				for(int i=0;i<idStrList.length;i++){
					Dict dict = dictService.findById(idStrList[i]);
					Equipment equipment = new Equipment();
					equipment.setDict(dict);
					equipment.setNum(Integer.parseInt(numsStr[i]));
					equipmentList.add(equipment);
				}
				scheduleTaskService.addEquipment(equipmentList, scheduleId, taskId);
				return new ResponseModel(200,null,"编辑成功");
			}
			return new ResponseModel(300,null,"参数不正确"); 
 		}
		return new ResponseModel(300,null,"参数不正确");
	}
}
