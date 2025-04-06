package com.sb.majorproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sb.majorproject.entity.CorrespondanceNotices;
import com.sb.majorproject.entity.EligibilityDetermination;
import com.sb.majorproject.service.CorrespondanceNoticesService;
import com.sb.majorproject.service.EligibilityDeterminationService;

@Controller
@RequestMapping("/IES/correspondance")
public class CorrespondanceController {
	@Autowired
	private EligibilityDeterminationService determinationService;

	@Autowired
	private CorrespondanceNoticesService correspondanceNoticesService;

	@GetMapping("/viewNotices")
	public String createPlanspage(Model model) {
		correspondanceNoticesService.saveNotices();
		
		return findPaginatedEd(1, model);
	}
	@GetMapping("/cn/page/{pageNo}")
	public String findPaginatedEd(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = 3;

		Page<CorrespondanceNotices> page = correspondanceNoticesService.findPaginated(pageNo, pageSize);
		List<CorrespondanceNotices> listnotices = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("correspondance", listnotices);
		return "/Correspondance/viewNotices";
	}
	@GetMapping("/historyNotices")
	public String createAccountpage() {
		return "/Correspondance/historyNotices";
	}

	@GetMapping("/pendingNotices")
	public String viewAccountspage() {
		return "/Correspondance/pendingNotices";
	}

}
