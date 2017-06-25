package com.hangdaoju.api;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hangdaoju.core.Page;
import com.hangdaoju.core.ResponseModel;
import com.hangdaoju.model.CompanyDynamics;
import com.hangdaoju.service.CompanyDynamicsService;
import com.hangdaoju.util.GetMapUtils;

@Controller
@RequestMapping("/companyDynamics")
public class CompanyDynamicsController {
	@Autowired
	private CompanyDynamicsService companyDynamicsService;
	/**
	 * 查詢公司動態列表(公司id)  currentUser
	 * @param companyDynamics
	 * @return
	 */
	@PostMapping(value="getCompanyDynamicsListByCondition")
	@ResponseBody
	public ResponseModel getCompanyDynamicsListByCondition(CompanyDynamics companyDynamics){
		if(StringUtils.isNotBlank(companyDynamics.getCompanyId())){
			Page<CompanyDynamics> page = new Page<CompanyDynamics>();
			page.setPageNumber(0);
			page.setPageSize(6);
			//分页查询  6条  根据时间倒序查询
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("companyDynamicsList", companyDynamicsService.findPageByCondition(page, companyDynamics).getRows());
			return new ResponseModel(200,GetMapUtils.getMap("companyDynamicsList",companyDynamicsService.findPageByCondition(page, companyDynamics).getRows()),"请求成功");
		}
		return new ResponseModel(300,null,"请传入公司id,公司id不能为空");
	}
	/**
	 * 根据工程动态id查询详情
	 * @param id
	 * @return
	 */
	public ResponseModel getById(String id){
		if(StringUtils.isNotBlank(id)){
			return new ResponseModel(200,GetMapUtils.getMap("companyDynamics",companyDynamicsService.getById(id)),"查询成功");
		}
		return new ResponseModel(300,null,"请传入动态id，id不能为空");
	}
}
