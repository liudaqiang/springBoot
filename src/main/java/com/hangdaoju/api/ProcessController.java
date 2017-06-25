package com.hangdaoju.api;

import static org.apache.commons.lang3.StringUtils.isAnyBlank;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hangdaoju.core.ResponseModel;
import com.hangdaoju.model.Process;
import com.hangdaoju.service.ProcessService;
import com.hangdaoju.util.DateUtils;
import com.hangdaoju.util.GetMapUtils;
import com.hangdaoju.util.excel.ExportExcel;

@Controller
@RequestMapping("/process")
public class ProcessController {

	@Autowired
	ProcessService processService;

	@PostMapping("/save")
	@ResponseBody
	public ResponseModel save(Process process) {
		if (null == process) {
			return new ResponseModel(300, null, "参数不得为空");
		}
		process.setUpdateTime(new Date());
		if (isNotBlank(process.getId())) {
			processService.update(process);
			return new ResponseModel(200, null, "修改成功");
		} else {
			if (isAnyBlank(process.getName(), process.getContent(), process.getDescription(),
					process.getTimeConsuming(), process.getFinishTime(), process.getAccountId(),
					process.getApprovalResult(), process.getAttachment())) {
				return new ResponseModel(300, null, "参数不能为空");
			}
			process.setCreateTime(new Date());
			String id = processService.add(process);
			if (isBlank(id)) {
				return new ResponseModel(300, null, "插入失败");
			}
			return new ResponseModel(200, GetMapUtils.getMap("id", id), "插入成功");
		}
	}

	@PostMapping("/delete")
	@ResponseBody
	public ResponseModel delete(String id) {
		if (isBlank(id)) {
			return new ResponseModel(300, null, "参数不得为空");
		}
		processService.deleteById(id);
		return new ResponseModel(200, null, "删除成功");
	}

	@PostMapping("/find")
	@ResponseBody
	public ResponseModel findByCondition(Process process) {
		if (null == process) {
			return new ResponseModel(300, null, "参数不得为空");
		}
		if (isNotBlank(process.getId())) {
			process = processService.find(process.getId());
			if (null == process) {
				return new ResponseModel(300, null, "查询失败");
			}
			return new ResponseModel(200, GetMapUtils.getMap("process", process), "查询成功");
		}
		java.util.List<Process> processs = processService.findByCondition(process);
		if (null == processs || processs.isEmpty()) {
			return new ResponseModel(300, null, "没有查询到任何流程");
		}
		return new ResponseModel(200, GetMapUtils.getMap("process_list", processs), "查询成功");
	}

	@RequestMapping("/export")
	@ResponseBody
	public ResponseModel exportExcel(Process process, HttpServletResponse response) {
		try {
			String fileName = "流程信息" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			java.util.List<Process> processList = processService.findByCondition(process);
			new ExportExcel("流程信息", Process.class).setDataList(processList).write(response, fileName).dispose();
		} catch (Exception e) {
			return new ResponseModel(300, GetMapUtils.getMap("error", e.getMessage()), "导出失败，请联系后台");
		}
		return null;
	}
}
