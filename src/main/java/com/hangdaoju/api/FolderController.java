package com.hangdaoju.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hangdaoju.core.ResponseModel;
import com.hangdaoju.model.Folder;
import com.hangdaoju.model.FolderTree;
import com.hangdaoju.service.FolderService;
import com.hangdaoju.util.GetMapUtils;

import static com.hangdaoju.util.StringUtils.*;

@Controller
@RequestMapping("/folder")
public class FolderController {
	@Autowired
	FolderService folderService;

	/**
	 * 文件夹的新增和修改
	 * 
	 * @param name
	 *            文件夹名称(必须)
	 * @param folderType
	 *            分类类型（1视频2文档3图片4其他）(必须)
	 * @param projectId
	 *            所属project(必须)
	 * @param id(非必须)
	 * @param folder
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public ResponseModel save(Folder folder) {
		if (null == folder) {
			return new ResponseModel(300, null, "参数不得为空");
		}
		folder.setUpdateTime(new Date());
		if (isNotBlank(folder.getId())) {
			if (folderService.update(folder)) {
				return new ResponseModel(200, null, "修改成功");
			}
			return new ResponseModel(300, null, "修改失败,存在重名");
		} else {
			if (isAnyBlank(/* folder.getFolderType(), */folder.getName(), folder.getProjectId())) {
				return new ResponseModel(300, null, "参数不能为空");
			}
			folder.setCreateTime(new Date());
			String id = folderService.add(folder);
			if (isBlank(id)) {
				return new ResponseModel(300, null, "插入失败,存在重名");
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
		Folder folder = new Folder();
		folder.setId(id);
		folderService.delete(folder);
		return new ResponseModel(200, null, "删除成功");
	}

	@RequestMapping("/find")
	@ResponseBody
	public ResponseModel findByCondition(Folder folder) {
		if (null == folder) {
			return new ResponseModel(300, null, "参数不得为空");
		}
		if (isNotBlank(folder.getId())) {
			folder = folderService.findById(folder.getId());
			if (null == folder) {
				return new ResponseModel(300, null, "查询失败");
			}
			return new ResponseModel(200, GetMapUtils.getMap("folder", folder), "查询成功");
		}
		java.util.List<Folder> folders = folderService.findByCondition(folder);
		if (null == folders || 0 == folders.size()) {
			return new ResponseModel(300, null, "查询失败");
		}
		return new ResponseModel(200, GetMapUtils.getMap("folder_list", folders), "查询成功");
	}

	/**
	 * 查询树状文件夹列表
	 * 
	 * @param projectId
	 * @return
	 */
	@RequestMapping("/findTree")
	@ResponseBody
	public ResponseModel findTree(String projectId) {
		if (isAnyBlank(projectId)) {
			return new ResponseModel(300, null, "参数不能为空");
		}
		List<Folder> folders = folderService.findRootByProject(projectId);
		List<FolderTree> result = new ArrayList<>();
		Iterator<Folder> iterator = folders.iterator();
		while (iterator.hasNext()) {
			result.add(new FolderTree(iterator.next()).init(folderService));
		}
		return new ResponseModel(200, GetMapUtils.getMap("folders", result), "查询成功");
	}

	/**
	 * 查询公司结构下的文件夹
	 * 
	 * @param companyId
	 * @param folderType
	 * @return
	 */
	@RequestMapping("/findCompanyTree")
	@ResponseBody
	public ResponseModel findCompanyTree(String companyId) {
		if (isAnyBlank(companyId)) {
			return new ResponseModel(300, null, "参数不能为空");
		}
		List<Folder> folders = folderService.findRootByCompanyId(companyId);
		List<FolderTree> result = new ArrayList<>();
		Iterator<Folder> iterator = folders.iterator();
		while (iterator.hasNext()) {
			result.add(new FolderTree(iterator.next()).init(folderService));
		}
		return new ResponseModel(200, GetMapUtils.getMap("folders", result), "查询成功");
	}
}
