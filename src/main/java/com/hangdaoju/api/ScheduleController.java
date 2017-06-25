package com.hangdaoju.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hangdaoju.core.ResponseModel;
import com.hangdaoju.model.Dict;
import com.hangdaoju.model.Person;
import com.hangdaoju.model.Schedule;
import com.hangdaoju.service.DictService;
import com.hangdaoju.service.PersonService;
import com.hangdaoju.service.ScheduleService;
import com.hangdaoju.util.CustomUtils;
import com.hangdaoju.util.DateUtils;
import com.hangdaoju.util.GetMapUtils;
import com.hangdaoju.util.StringUtils;
/**
 * 进度计划Controller
 * @author l.q
 *
 */
@Controller
@RequestMapping("/schedule")
public class ScheduleController {
	@Autowired
	private ScheduleService scheduleService;
	@Autowired
	private DictService dictService;
	@Autowired
	private PersonService personService;
	/**
	 * 修改和插入计划
	 * @param name
	 * @param startTimeStr
	 * @param endTimeStr
	 * @param leaderId
	 * @param equipmentListStr
	 * @param materiaListStr
	 * @param typeWordListStr
	 * @param companyId
	 * @param icon
	 * @param id
	 * @return
	 */
	@PostMapping("/save")
	@ResponseBody
	public ResponseModel save(String name,String startTimeStr,String endTimeStr,String leaderId,String icon,String equipmentListStr,String materiaListStr,String typeWordListStr,String companyId,String id){
		if(StringUtils.isNotBlank(name) &&
				StringUtils.isNotBlank(startTimeStr) &&
				StringUtils.isNotBlank(endTimeStr) &&
				StringUtils.isNotBlank(leaderId) &&
				StringUtils.isNotBlank(companyId)){
			String [] equipmentStrs = null;
			List<Dict> equipmentList = new ArrayList<Dict>();
			String [] materiaStrs = null;
			List<Dict> materiaList = new ArrayList<Dict>();
			String [] typeWordStrs = null;
			List<Dict> typeWordList = new ArrayList<Dict>();
			//校验设备列表 
			if(StringUtils.isNotBlank(equipmentListStr)){
				equipmentStrs = equipmentListStr.split(",");
				for(String dict:equipmentStrs){
					equipmentList.add(dictService.findById(dict));
				}
			}
			//校验材料列表
			if(StringUtils.isNotBlank(materiaListStr)){
				materiaStrs = materiaListStr.split(",");
				for(String dict:materiaStrs){
					materiaList.add(dictService.findById(dict));
				}
			}
			//校验工种列表
			if(StringUtils.isNotBlank(typeWordListStr)){
				typeWordStrs = typeWordListStr.split(",");
				for(String dict:typeWordStrs){
					typeWordList.add(dictService.findById(dict));
				}
			}
			//插入字段
			Schedule schedule = new Schedule();
			schedule.setName(name);
			schedule.setStartTime(DateUtils.parseDate(startTimeStr));
			schedule.setEndTime(DateUtils.parseDate(endTimeStr));
			schedule.setLeader(personService.findById(leaderId));
			schedule.setEquipmentList(equipmentList);
			schedule.setMaterialList(materiaList);
			schedule.setTypeWordList(typeWordList);
			schedule.setCompanyId(companyId);
			schedule.setIcon(icon);
			//修改
			if(StringUtils.isNotBlank(id)){
				schedule.setId(id);
			}
			scheduleService.add(schedule);
			return new ResponseModel(200,null,"插入成功"); 
		}
		return new ResponseModel(300,null,"数据不正确"); 
	}
	
	/**
	 * 新增进度计划
	 * 必须字段
	 * 
	 * currentUser
	 * @return
	 */
	@PostMapping("/add")
	@ResponseBody
	public ResponseModel add(Schedule schedule,String leaderId,String startTimeStr,String endTimeStr,HttpServletRequest request){
		if(StringUtils.isNotBlank(schedule.getName()) &&
				StringUtils.isNotBlank(startTimeStr) &&
				StringUtils.isNotBlank(endTimeStr) &&
				StringUtils.isNotBlank(schedule.getCompanyId())){
			schedule.setStartTime(DateUtils.parseDate(startTimeStr));
			schedule.setEndTime(DateUtils.parseDate(endTimeStr));
			schedule.setCreateTime(new Date());
			schedule.setUpdateTime(new Date());
			String userId = CustomUtils.getCustomUser(request);
			if(userId == null){
				return new ResponseModel(300,null,"插入失败,当前用户为空,请登录");
			}
			schedule.setCreateUser(userId);
			schedule.setUpdateUser(userId);
			Person leader = new Person();
			leader.setId(leaderId);
			schedule.setLeader(leader);
			scheduleService.add(schedule);
			return new ResponseModel(200,null,"插入成功");
		}
		return new ResponseModel(300,null,"插入失败");
	}
	
	/**
	 * 修改进度计划
	 * 必须字段
	 * name,startTimeStr,endTimeStr,companyId,id,leaderId
	 * currentUser
	 * @return
	 */
	@PostMapping("/update")
	@ResponseBody
	public ResponseModel update(Schedule schedule,String leaderId,String startTimeStr,String endTimeStr,HttpServletRequest request){
		if(StringUtils.isNotBlank(schedule.getId()) &&
				StringUtils.isNotBlank(schedule.getName()) &&
				StringUtils.isNotBlank(startTimeStr) &&
				StringUtils.isNotBlank(endTimeStr) &&
				StringUtils.isNotBlank(schedule.getCompanyId())){
			schedule.setStartTime(DateUtils.parseDate(startTimeStr));
			schedule.setEndTime(DateUtils.parseDate(endTimeStr));
			schedule.setCreateTime(new Date());
			schedule.setUpdateTime(new Date());
			String userId = CustomUtils.getCustomUser(request);
			if(userId == null){
				return new ResponseModel(300,null,"修改失败,当前用户为空,请登录");
			}
			schedule.setCreateUser(userId);
			schedule.setUpdateUser(userId);
			Person leader = new Person();
			leader.setId(leaderId);
			schedule.setLeader(leader);
			scheduleService.add(schedule);
			return new ResponseModel(200,null,"修改成功");
		}
		return new ResponseModel(300,null,"修改失败");
	}
	/**
	 * 查看进度计划列表
	 * companyId 必须字段
	 * currentUser
	 * @param schedule
	 * @return
	 */
	@PostMapping("/findScheduleByCondition")
	@ResponseBody
	public ResponseModel findScheduleByCondition(Schedule schedule){
		if(StringUtils.isNotBlank(schedule.getCompanyId())){
			List<Schedule> scheduleList = scheduleService.findByCondition(schedule);
			return new ResponseModel(200,GetMapUtils.getMap("scheduleList", scheduleList),"查询成功");
		}
		return new ResponseModel(300,null,"查询失败，公司id不能为空");
	}
	/**
	 * 根据id查看进度计划
	 * @param schedule
	 * @return
	 */
	@PostMapping("/findById")
	@ResponseBody
	public ResponseModel findById(Schedule schedule){
		if(StringUtils.isNotBlank(schedule.getId())){
			schedule = scheduleService.findById(schedule);
			return new ResponseModel(200,GetMapUtils.getMap("schedule", schedule),"查询成功");
		}
		return new ResponseModel(300,null,"id不能为空");
	}
	/**
	 * 查询某计划材料类型列表
	 * @param id
	 * @return
	 */
	@PostMapping("/findMaterialListById")
	@ResponseBody
	public ResponseModel findMaterialListById(String id){
		if(StringUtils.isNotBlank(id)){
			List<Dict> dictList = scheduleService.findMaterialListById(id);
			return new ResponseModel(200,GetMapUtils.getMap("dictList", dictList),"查询成功");
		}
		return new ResponseModel(300,null,"id不能为空");
	}
	/**
	 * 查询某计划设备类型列表
	 * @param id
	 * @return
	 */
	@PostMapping("/findEquipmentListById")
	@ResponseBody
	public ResponseModel findEquipmentListById(String id){
		if(StringUtils.isNotBlank(id)){
			List<Dict> dictList = scheduleService.findEquipmentListById(id);
			return new ResponseModel(200,GetMapUtils.getMap("dictList", dictList),"查询成功");
		}
		return new ResponseModel(300,null,"id不能为空");
	}
	/**
	 * 查询某计划工种类型列表
	 * @param id
	 * @return
	 */
	@PostMapping("/findTypeWordListById")
	@ResponseBody
	public ResponseModel findTypeWordListById(String id){
		if(StringUtils.isNotBlank(id)){
			List<Dict> dictList = scheduleService.findTypeWordListById(id);
			return new ResponseModel(200,GetMapUtils.getMap("dictList", dictList),"查询成功");
		}
		return new ResponseModel(300,null,"id不能为空");
	}
//	/**
//	 * 编辑设备类型(只有新增)
//	 * currentUser
//	 * id计划id
//	 * equipmentId设备id
//	 * @param schedule
//	 * @return
//	 */
//	@PostMapping("/updateEquipment")
//	@ResponseBody
//	public ResponseModel updateEquipment(Schedule schedule,String equipmentId){
//		Dict dict = dictService.findById(equipmentId);
//		if(dict == null){
//			return new ResponseModel(300,null,"该设备id不存在");
//		}
//		if(StringUtils.isNotBlank(schedule.getId()) &&
//				StringUtils.isNotBlank(equipmentId)){
//			scheduleService.updateEquipment(schedule.getId(), dict);
//			return new ResponseModel(200,null,"编辑成功");
//		}
//		return new ResponseModel(300,null,"传入参数不正确");
//	}
//	/**
//	 * 删除设备类型
//	 * @param schedule
//	 * @param equipmentId
//	 * @return
//	 */
//	@PostMapping("/deleteEquipment")
//	@ResponseBody
//	public ResponseModel deleteEquipment(Schedule schedule,String equipmentId){
//		Dict dict = dictService.findById(equipmentId);
//		if(dict == null){
//			return new ResponseModel(300,null,"该设备id不存在");
//		}
//		if(StringUtils.isNotBlank(schedule.getId()) &&
//				StringUtils.isNotBlank(equipmentId)){
//			scheduleService.deleteEquipment(schedule.getId(), dict);
//			return new ResponseModel(200,null,"删除成功");
//		}
//		return new ResponseModel(300,null,"传入参数不正确");
//	}
//	/**
//	 * 编辑设备类型(只有新增)
//	 * currentUser
//	 * id计划id
//	 * equipmentId设备id
//	 * @param schedule
//	 * @return
//	 */
//	@PostMapping("/updateMaterial")
//	@ResponseBody
//	public ResponseModel updateMaterial(Schedule schedule,String materialId){
//		Dict dict = dictService.findById(materialId);
//		if(dict == null){
//			return new ResponseModel(300,null,"该设备id不存在");
//		}
//		if(StringUtils.isNotBlank(schedule.getId()) &&
//				StringUtils.isNotBlank(materialId)){
//			scheduleService.updateMaterial(schedule.getId(), dict);
//			return new ResponseModel(200,null,"编辑成功");
//		}
//		return new ResponseModel(300,null,"传入参数不正确");
//	}
//	/**
//	 * 删除设备类型
//	 * @param schedule
//	 * @param equipmentId
//	 * @return
//	 */
//	@PostMapping("/deleteMaterial")
//	@ResponseBody
//	public ResponseModel deleteMaterial(Schedule schedule,String materialId){
//		Dict dict = dictService.findById(materialId);
//		if(dict == null){
//			return new ResponseModel(300,null,"该设备id不存在");
//		}
//		if(StringUtils.isNotBlank(schedule.getId()) &&
//				StringUtils.isNotBlank(materialId)){
//			scheduleService.deleteMaterial(schedule.getId(), dict);
//			return new ResponseModel(200,null,"删除成功");
//		}
//		return new ResponseModel(300,null,"传入参数不正确");
//	}
//	
//	/**
//	 * 编辑工种类型(只有新增)
//	 * currentUser
//	 * id计划id
//	 * typeWordId工种id
//	 * @param schedule
//	 * @return
//	 */
//	@PostMapping("/updateTypeWord")
//	@ResponseBody
//	public ResponseModel updateTypeWord(Schedule schedule,String typeWordId){
//		Dict dict = dictService.findById(typeWordId);
//		if(dict == null){
//			return new ResponseModel(300,null,"该工种id不存在");
//		}
//		if(StringUtils.isNotBlank(schedule.getId()) &&
//				StringUtils.isNotBlank(typeWordId)){
//			scheduleService.updateTypeWord(schedule.getId(), dict);
//			return new ResponseModel(200,null,"编辑成功");
//		}
//		return new ResponseModel(300,null,"传入参数不正确");
//	}
//	/**
//	 * 删除工种类型
//	 * @param schedule
//	 * @param typeWordId
//	 * @return
//	 */
//	@PostMapping("/deleteTypeWord")
//	@ResponseBody
//	public ResponseModel deleteTypeWord(Schedule schedule,String typeWordId){
//		Dict dict = dictService.findById(typeWordId);
//		if(dict == null){
//			return new ResponseModel(300,null,"该工种id不存在");
//		}
//		if(StringUtils.isNotBlank(schedule.getId()) &&
//				StringUtils.isNotBlank(typeWordId)){
//			scheduleService.deleteTypeWord(schedule.getId(), dict);
//			return new ResponseModel(200,null,"删除成功");
//		}
//		return new ResponseModel(300,null,"传入参数不正确");
//	}
}
