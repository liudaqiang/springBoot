package com.hangdaoju.api;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

/**
 * 图片控制器
 * 
 * @author Hervey Hall <mail@herveyhall.cf>
 *
 */
@Controller
@RequestMapping("/picture")
public class PictureController {

	@Autowired
	private HttpServletRequest request;

	@PostMapping("/upload")
	@ResponseBody
	public ResponseModel upload(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return new ResponseModel(300, null, "参数不得为空");
		}

		// 验证是否是图片
		try {
			InputStream input = new ByteArrayInputStream(file.getBytes());
			BufferedImage image = javax.imageio.ImageIO.read(input);
			if (image.getHeight() <= 0 || image.getWidth() <= 0) {
				return new ResponseModel(300, null, "这不是一张图片");
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String rootPath = request.getSession().getServletContext().getRealPath("/");

		String fileSrc = "pictures/" + Double.toHexString(Math.random())
				+ Long.toString(System.currentTimeMillis(), 32);

		// 文件保存路径
		String filePath = rootPath + fileSrc;

		// 转存文件
		try {
			File serverFile = new File(filePath);
			if (!serverFile.getParentFile().exists() || serverFile.getParentFile().isDirectory()) {
				serverFile.getParentFile().mkdir();
			}
			if (serverFile.exists()) {
				return upload(file);
			}
			Streams.copy(file.getInputStream(), new FileOutputStream(serverFile), true);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return new ResponseModel(200, GetMapUtils.getMap("src", "/" + fileSrc), "上传成功");
	}
}
