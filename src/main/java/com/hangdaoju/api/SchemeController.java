package com.hangdaoju.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hangdaoju.core.ResponseModel;
import com.hangdaoju.model.Scheme;
import com.hangdaoju.model.SchemeTree;
import com.hangdaoju.service.DictService;
import com.hangdaoju.service.SchemeService;
import com.hangdaoju.util.GetMapUtils;

import static com.hangdaoju.util.StringUtils.*;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;

@Controller
@RequestMapping("/scheme")
public class SchemeController {
	@Autowired
	DictService dictService;

	@Autowired
	SchemeService schemeService;

	@PostMapping("/save")
	@ResponseBody
	public ResponseModel save(Scheme scheme) {
		if (null == scheme || isBlank(scheme.getName()) || null == scheme.getProperties()
				|| scheme.getProperties().isEmpty()) {
			return new ResponseModel(300, null, "参数不能为空");
		}
		if (scheme.getProperties().size() > new HashSet<>(scheme.getProperties()).size()) {
			return new ResponseModel(300, null, "存在重复的属性");
		}
		Scheme sameName = new Scheme();
		sameName.setName(scheme.getName());
		if (!schemeService.findByCondition(sameName).isEmpty()) {
			return new ResponseModel(300, null, "存在同名的方案");
		}
		if (isNotBlank(scheme.getId())) {
			schemeService.update(scheme);
			return new ResponseModel(200, null, "修改成功");
		}
		if (isAnyBlank(scheme.getName())) {
			return new ResponseModel(300, null, "参数不能为空");
		}
		scheme.setCreateTime(new Date());
		String id = schemeService.add(scheme);
		if (isBlank(id)) {
			return new ResponseModel(300, null, "插入失败");
		}
		return new ResponseModel(200, GetMapUtils.getMap("id", id), "插入成功");
	}

	@RequestMapping("/delete")
	@ResponseBody
	public ResponseModel delete(String id) {
		if (isBlank(id)) {
			return new ResponseModel(300, null, "参数不得为空");
		}
		Scheme scheme = new Scheme();
		scheme.setId(id);
		schemeService.delete(scheme);
		return new ResponseModel(200, null, "删除成功");
	}

	@RequestMapping("/find")
	@ResponseBody
	public ResponseModel findByCondition(Scheme scheme) {
		if (null == scheme) {
			return new ResponseModel(300, null, "参数不得为空");
		}
		if (isNotBlank(scheme.getId())) {
			scheme = schemeService.findById(scheme.getId());
			if (null == scheme) {
				return new ResponseModel(300, null, "查询失败");
			}
			return new ResponseModel(200, GetMapUtils.getMap("scheme", scheme), "查询成功");
		}
		java.util.List<Scheme> schemes = schemeService.findByCondition(scheme);
		if (null == schemes || 0 == schemes.size()) {
			return new ResponseModel(300, null, "查询失败");
		}
		return new ResponseModel(200, GetMapUtils.getMap("scheme_list", schemes), "查询成功");
	}

	@RequestMapping("/findList")
	@ResponseBody
	public ResponseModel findList(String[] id) {
		if (null == id || 0 == id.length) {
			return new ResponseModel(300, null, "参数不得为空");
		}
		java.util.List<String> idList = Arrays.asList(id);

		java.util.List<Scheme> schemes = schemeService.findByIds(idList);
		if (null == schemes || 0 == schemes.size()) {
			return new ResponseModel(300, null, "查询失败");
		}
		return new ResponseModel(200, GetMapUtils.getMap("scheme_list", schemes), "查询成功");
	}

	@RequestMapping("/findTree")
	@ResponseBody
	public ResponseModel findTree(String schemeId) {
		if (isBlank(schemeId)) {
			return new ResponseModel(300, null, "参数不能为空");
		}
		Scheme scheme = schemeService.findById(schemeId);
		if (null == scheme) {
			return new ResponseModel(300, null, "没有查询到对应方案");
		}
		java.util.List<String> properties = scheme.getProperties();
		return new ResponseModel(200, GetMapUtils.getMap("scheme_tree",
				new SchemeTree().init(dictService, schemeService, new LinkedList<>(properties))), "查询成功");
	}
}
