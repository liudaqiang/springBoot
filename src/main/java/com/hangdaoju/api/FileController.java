package com.hangdaoju.api;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hangdaoju.core.ResponseModel;
import com.hangdaoju.model.File;
import com.hangdaoju.service.FileService;
import com.hangdaoju.util.GetMapUtils;
import com.hangdaoju.util.StringUtils;

import static com.hangdaoju.util.StringUtils.*;
import java.util.Date;

@Controller
@RequestMapping("/file")
public class FileController {

	@Autowired
	FileService fileService;

	/**
	 * 保存（新增或编辑后的保存）<br>
	 * 
	 * 注意：文件接口不包括上传文件功能，此接口需要调用上传附件接口(/attachment/upload)后将路径以及文件名传入该接口进行保存
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public ResponseModel save(File file, HttpServletRequest request) {
		if (null == file) {
			return new ResponseModel(300, null, "参数不得为空");
		}
		file.setUpdateTime(new Date());
		if (isNotBlank(file.getId())) {
			fileService.update(file);
			return new ResponseModel(200, null, "修改成功");
		} else {
			if (isAnyBlank(/* file.getLogo(), */ file.getName(), file.getProjectId(), file.getPath())) {
				return new ResponseModel(300, null, "参数不能为空");
			}
			if (fileService.isLogicFileExsits(file)) {
				return new ResponseModel(300, null, "文件已经存在");
			}
			java.io.File diskFile = new java.io.File(request.getSession().getServletContext().getRealPath("/")
					+ "uploads/" + file.getPath() + "/" + file.getName());
			if (!diskFile.exists()) {
				return new ResponseModel(300, null, "物理位置不存在文件");
			}
			file.setSize(Long.toString(diskFile.length()));
			file.setCreateTime(new Date());
			String id = fileService.add(file);
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
			return new ResponseModel(300, null, "参数不能为空");
		}
		fileService.deleteById(id);
		return new ResponseModel(200, null, "删除成功");
	}

	@RequestMapping("/find")
	@ResponseBody
	public ResponseModel findByCondition(File file, String orderBy, String sort) {
		if (null == file) {
			return new ResponseModel(300, null, "参数不得为空");
		}
		if (StringUtils.isNotBlank(sort) && !sort.matches("^((asc)|(desc))$")) {
			return new ResponseModel(300, null, "排序方案不正确");
		}
		if (isNotBlank(file.getId())) {
			file = fileService.find(file.getId());
			if (null == file) {
				return new ResponseModel(300, null, "查询失败");
			}
			return new ResponseModel(200, GetMapUtils.getMap("file", file), "查询成功");
		}
		java.util.List<File> files = fileService.findByCondition(file, orderBy, sort);
		if (null == files || 0 == files.size()) {
			return new ResponseModel(300, null, "查询失败");
		}
		return new ResponseModel(200, GetMapUtils.getMap("file_list", files), "查询成功");
	}
}
