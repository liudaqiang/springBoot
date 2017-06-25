package com.hangdaoju.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hangdaoju.core.ResponseModel;
import com.hangdaoju.util.GetMapUtils;
import com.hangdaoju.util.StringUtils;

@Controller
@RequestMapping("/attachment")
public class AttachmentController {

	@Autowired
	private HttpServletRequest request;

	@PostMapping("/upload")
	@ResponseBody
	public ResponseModel upload(@RequestParam("file") MultipartFile file, String path) {
		if (file.isEmpty()) {
			return new ResponseModel(300, null, "参数不得为空");
		}
		boolean isAutoPath = false;
		if (StringUtils.isBlank(path)) {
			path = Double.toHexString(Math.random()) + Long.toString(System.currentTimeMillis(), 32);
			isAutoPath = true;
		}

		String rootPath = request.getSession().getServletContext().getRealPath("/");

		String fileSrc = "uploads/" + path + "/" + file.getOriginalFilename();

		// 文件保存路径
		String filePath = rootPath + fileSrc;

		// 转存文件
		try {
			File serverFile = new File(filePath);
			if (!serverFile.getParentFile().exists() || serverFile.getParentFile().isDirectory()) {
				serverFile.getParentFile().mkdirs();
			}
			if (serverFile.exists() && isAutoPath) {
				return upload(file, "");
			} else if (serverFile.exists()) {
				return new ResponseModel(300, null, "上传失败，文件已存在");
			}
			Streams.copy(file.getInputStream(), new FileOutputStream(serverFile), true);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		//文件名
		String fileName = file.getOriginalFilename();
		//文件后缀
		String suffix  = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1); 
		//文件大小
		String size = String.valueOf(file.getSize());
		Map<String,Object> map = GetMapUtils.getMap("url", "/" + fileSrc);
		map.put(fileName, fileName);
		map.put("suffix", suffix);
		map.put("size", size);
		return new ResponseModel(200, map, "上传成功");
	}
}
