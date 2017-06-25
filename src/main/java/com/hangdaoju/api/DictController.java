package com.hangdaoju.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hangdaoju.core.ResponseModel;
import com.hangdaoju.model.Dict;
import com.hangdaoju.service.DictService;
import com.hangdaoju.util.GetMapUtils;
/**
 * 字典controller
 * @author l.q
 *
 */
@Controller
@RequestMapping("/dict")
public class DictController {
	@Autowired
	private DictService dictService;
	/**
	 * 查询设备类型列表
	 * currentUser 
	 * @return
	 */
	@PostMapping("/getEquipmentList")
	@ResponseBody
	public ResponseModel getEquipmentList(){
		Dict dict = new Dict();
		dict.setDictType("1");
		List<Dict> equipmentList = dictService.findByCondition(dict);
		return new ResponseModel(200,GetMapUtils.getMap("equipmentList", equipmentList),"请求成功");
	}
	/**
	 * 查询材料类型列表
	 * currentUser
	 * @return
	 */
	@PostMapping("/getMaterialList")
	@ResponseBody
	public ResponseModel getMaterialList(){
		Dict dict = new Dict();
		dict.setDictType("21");
		List<Dict> rawEquipmentList = dictService.findByCondition(dict);
		dict.setDictType("22");
		List<Dict> finishEquipmentList = dictService.findByCondition(dict);
		rawEquipmentList.addAll(finishEquipmentList);
		return new ResponseModel(200,GetMapUtils.getMap("equipmentList", rawEquipmentList),"请求成功");
	}
	/**
	 * 查询工种类型列表
	 * currentUser
	 * @return
	 */
	@PostMapping("/getTypeWordList")
	@ResponseBody
	public ResponseModel getTypeWordList(){
		Dict dict = new Dict();
		dict.setDictType("3");
		List<Dict> typeWordList = dictService.findByCondition(dict);
		return new ResponseModel(200,GetMapUtils.getMap("typeWorkList", typeWordList),"请求成功");
	}
}
