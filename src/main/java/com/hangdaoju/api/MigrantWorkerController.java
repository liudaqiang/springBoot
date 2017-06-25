package com.hangdaoju.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hangdaoju.core.ResponseModel;
import com.hangdaoju.model.MigrantWorker;
import com.hangdaoju.service.MigrantWorkerService;
import com.hangdaoju.util.DateUtils;
import com.hangdaoju.util.GetMapUtils;
import com.hangdaoju.util.excel.ExportExcel;

import static com.hangdaoju.util.StringUtils.*;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/migrantWorker")
public class MigrantWorkerController {
	@Autowired
	MigrantWorkerService migrantWorkerService;

	@PostMapping("/save")
	@ResponseBody
	public ResponseModel add(MigrantWorker migrantWorker) {
		if (null == migrantWorker) {
			return new ResponseModel(300, null, "参数不得为空");
		}
		migrantWorker.setUpdateTime(new Date());
		if (isNotBlank(migrantWorker.getId())) {
			migrantWorkerService.update(migrantWorker);
			return new ResponseModel(200, null, "修改成功");
		} else {
			if (isAnyBlank(migrantWorker.getIdCard(), migrantWorker.getName(), migrantWorker.getProject(),
					migrantWorker.getCompany(), migrantWorker.getAddress(), migrantWorker.getMobile(),
					migrantWorker.getWorkType(), migrantWorker.getEmergencyContact(),
					migrantWorker.getEmergencyMobile(), migrantWorker.getSpecialDateStart(),
					migrantWorker.getSpecialDateEnd(), migrantWorker.getWageDate(), migrantWorker.getWageBool(),
					migrantWorker.getSecurityInsurance(), migrantWorker.getQrCode()/*, migrantWorker.getAccountId()*/)) {
				return new ResponseModel(300, null, "参数不能为空");
			}
			if(!migrantWorker.getIdCard().matches("^(([\\d]{17}[\\d|x])|[\\d]{15})$")){
				return new ResponseModel(300,null, "身份证格式不正确");
			}
			migrantWorker.setCreateTime(new Date());
			String id = migrantWorkerService.add(migrantWorker);
			if (isBlank(id)) {
				return new ResponseModel(300, null, "插入失败");
			}
			return new ResponseModel(200, GetMapUtils.getMap("id", id), "插入成功");
		}
	}

	@RequestMapping("/delete")
	@ResponseBody
	public ResponseModel delete(String id) {
		if (isBlank(id)) {
			return new ResponseModel(300, null, "参数不得为空");
		}
		MigrantWorker migrantWorker = new MigrantWorker();
		migrantWorker.setId(id);
		migrantWorkerService.delete(migrantWorker);
		return new ResponseModel(200, null, "删除成功");
	}

	@RequestMapping("/find")
	@ResponseBody
	public ResponseModel findByCondition(MigrantWorker migrantWorker) {
		if (null == migrantWorker) {
			return new ResponseModel(300, null, "参数不得为空");
		}
		if (isNotBlank(migrantWorker.getId())) {
			migrantWorker = migrantWorkerService.findById(migrantWorker.getId());
			if (null == migrantWorker) {
				return new ResponseModel(300, null, "查询失败");
			}
			return new ResponseModel(200, GetMapUtils.getMap("migrant_worker", migrantWorker), "查询成功");
		}
		java.util.List<MigrantWorker> migrantWorkers = migrantWorkerService.findByCondition(migrantWorker);
		if (null == migrantWorkers || 0 == migrantWorkers.size()) {
			return new ResponseModel(300, null, "查询失败");
		}
		return new ResponseModel(200, GetMapUtils.getMap("migrant_worker_list", migrantWorkers), "查询成功");
	}

	@RequestMapping("/search")
	@ResponseBody
	public ResponseModel findByKeyword(String keyword) {
		if (isBlank(keyword)) {
			return new ResponseModel(300, null, "参数不得为空");
		}
		java.util.List<MigrantWorker> migrantWorkers = migrantWorkerService.findByKeyword(keyword);
		if (null == migrantWorkers || 0 == migrantWorkers.size()) {
			return new ResponseModel(300, null, "查询结果为空");
		}
		return new ResponseModel(200, GetMapUtils.getMap("migrant_worker_list", migrantWorkers), "查询成功");
	}

	@RequestMapping("/export")
	@ResponseBody
	public ResponseModel exportExcel(String keyword, HttpServletResponse response) {
		try {
			String fileName = "农民工信息" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			java.util.List<MigrantWorker> migrantWorkers = migrantWorkerService.findByKeyword(keyword);
			new ExportExcel("农民工信息", MigrantWorker.class).setDataList(migrantWorkers).write(response, fileName)
					.dispose();
		} catch (Exception e) {
			return new ResponseModel(300, GetMapUtils.getMap("error", e.getMessage()), "导出失败，请联系后台");
		}
		return null;
	}

}
