package com.hangdaoju.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hangdaoju.model.Dict;
import com.hangdaoju.model.Person;
import com.hangdaoju.service.DictService;
import com.hangdaoju.util.ehcache.EHCacheUtil;

@Controller
@RequestMapping("/admin/dict")
public class DictAdminController {
	@Autowired
	private DictService dictService;

	// 新增字典
	@GetMapping("/add")
	public String add(Dict dict, Model model, HttpServletRequest request) {
		if (StringUtils.isNotBlank(dict.getDictType()) && StringUtils.isNotBlank(dict.getName())) {
			dict.setCreateTime(new Date());
			dict.setUpdateTime(new Date());
			Person person = (Person) EHCacheUtil.get(request.getSession().getAttribute("adminCurrentUser"));
			if (person == null) {
				return "redirect:/admin/person/index";
			}
			dict.setCreateUser(person.getId());
			dict.setUpdateUser(person.getId());
			dict.setId(null);
			boolean flag = dictService.save(dict);
			if (!flag) {
				model.addAttribute("error", "出现重复数据，插入失败");
				return "/condition";
			}
			return "/redirect:/admin/dict/list";
		}
		model.addAttribute("error", "存在空字段");
		return "/condition";
	}

	/**
	 * 分页查询
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/list")
	public String findList(Model model) {
		List<Dict> dicts = dictService.findByCondition(new Dict());
		model.addAttribute("dictList", dicts);
		return "/view/dict/dictList";
	}

	/**
	 * 根据id删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete")
	public String delete(String id, Model model) {
		if (StringUtils.isNotBlank(id)) {
			Dict dict = new Dict();
			dict.setId(id);
			dictService.delete(dict);
			return "redirect:/admin/dict/list";
		}
		model.addAttribute("error", "id不能为空");
		return "/condition";
	}

	/**
	 * 跳转到新增/修改页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit")
	public String edit(String id, Model model) {
		if (StringUtils.isNotBlank(id)) {
			Dict dict = dictService.findById(id);
			model.addAttribute("dict", dict);
		}
		return "/view/dict/dictEdit";
	}

	/**
	 * 修改
	 * 
	 * @param person
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update")
	public String update(Dict dict, Model model) {
		if (StringUtils.isNotBlank(dict.getId())) {
			dictService.updateById(dict);
			return "redirect:/admin/dict/list";
		}
		model.addAttribute("error", "id不能为空");
		return "/condition";
	}
}
