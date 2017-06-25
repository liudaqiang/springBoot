package com.hangdaoju.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hangdaoju.core.FinalType;
import com.hangdaoju.core.Page;
import com.hangdaoju.model.Dict;
import com.hangdaoju.model.Person;
import com.hangdaoju.service.DictService;
import com.hangdaoju.util.ehcache.EHCacheUtil;

/**
 * 专业后端controller
 * @author l.q
 *
 */
@Controller
@RequestMapping("/admin/major")
public class MajorAdminController {
	@Autowired
	private DictService dictService;

	// 新增专业
	@PostMapping("/add")
	public String add(Dict dict, Model model, HttpServletRequest request) {
		//字典类型  专业
		dict.setDictType(FinalType.DICT_MAJOR);
		if (StringUtils.isNotBlank(dict.getName())) {
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
			return "redirect:/admin/major/list";
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
	public String findList(Model model,@RequestParam(value = "page", defaultValue = "1") Integer pageNum) {
		Dict dict = new Dict();
		dict.setDictType(FinalType.DICT_MAJOR);
		
		
		Page<Dict> dictPage = new Page<>();
		
		dictPage.setPageNumber(pageNum);
		dictPage = dictService.findPageByCondition(dictPage,dict);
		List<Dict> majorList = dictPage.getRows();
		//********************************************************
		/*TODO*/
		
		//Page<Dict> dictPage = new Page<>();
		//List<Dict> dicts = dictService.findByCondition(dict);
		model.addAttribute("majorList", majorList);
		return "/view/major/majorList";
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
			return "redirect:/admin/major/list";
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
			model.addAttribute("major", dict);
		}
		return "/view/major/majorEdit";
	}

	/**
	 * 修改
	 * 
	 * @param person
	 * @param model
	 * @return
	 */
	@PostMapping(value = "update")
	public String update(Dict dict, Model model) {
		if (StringUtils.isNotBlank(dict.getId())) {
			dictService.updateById(dict);
			return "redirect:/admin/major/list";
		}
		model.addAttribute("error", "id不能为空");
		return "/condition";
	}
}
