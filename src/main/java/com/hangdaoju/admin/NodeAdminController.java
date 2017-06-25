package com.hangdaoju.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hangdaoju.model.Node;
import com.hangdaoju.model.Person;
import com.hangdaoju.service.NodeService;
import com.hangdaoju.util.DateUtils;
import com.hangdaoju.util.StringUtils;
import com.hangdaoju.util.ehcache.EHCacheUtil;

/**
 * 工程节点后端controller
 * 
 * @author l.q
 *
 */
@Controller
@RequestMapping("/admin/node")
public class NodeAdminController {
	@Autowired
	private NodeService nodeService;

	@RequestMapping("/add")
	public String add(Node node, Model model, HttpServletRequest request, String nodeTimeStr) {
		if (StringUtils.isNotBlank(node.getProjectId())) {
			node.setCreateTime(new Date());
			node.setUpdateTime(new Date());
			try {
				Person person = (Person) EHCacheUtil.get(request.getSession().getAttribute("adminCurrentUser"));
				if (person == null) {
					return "redirect:/admin/person/index";
				}
				node.setId(null);
				node.setCreateUser(person.getId());
				node.setUpdateUser(person.getId());
				node.setNodeTime(DateUtils.parseDate(nodeTimeStr));
				nodeService.add(node);
			} catch (Exception e) {
				return "redirect:/admin/person/index";
			}

			return "";
		}
		model.addAttribute("error", "参数不正确");
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
		List<Node> nodes = nodeService.findByCondition(new Node());
		model.addAttribute("nodeList", nodes);
		return "/view/project-node/nodeList";
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
			Node node = new Node();
			node.setId(id);
			nodeService.delete(node);
			return "redirect:/admin/node/list";
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
			Node node = nodeService.find(id);
			model.addAttribute("node", node);
		}
		return "/view/project-node/nodeEdit";
	}

	/**
	 * 修改
	 * 
	 * @param person
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update")
	public String update(Node node, Model model) {
		if (StringUtils.isNotBlank(node.getId())) {
			nodeService.update(node);
			return "redirect:/admin/node/list";
		}
		model.addAttribute("error", "id不能为空");
		return "/condition";
	}
}
