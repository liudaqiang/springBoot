package com.hangdaoju.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hangdaoju.core.Page;
import com.hangdaoju.model.MigrantWorker;
import com.hangdaoju.service.MigrantWorkerService;
/**
 * 农民工
 * @author l.q
 *
 */
@Controller
@RequestMapping("/admin/migrantWorker")
public class MigrantWorkerAdminController {
	@Autowired
	private MigrantWorkerService migrantWorkerService;
	
	
	@GetMapping("/find")
	public String findPage(Model model,@RequestParam(value = "page", defaultValue = "1") Integer pageNum,MigrantWorker migrantWorker){
		Page<MigrantWorker> migrantWorkerPage = new Page<>();
		migrantWorkerPage.setPageNumber(pageNum);
		migrantWorkerPage = migrantWorkerService.findPage(migrantWorker, migrantWorkerPage);
		model.addAttribute("migrantWorkerList", migrantWorkerPage.getRows());
		return "/view/migrantWorker/migrantWorkerList";
	}
}
