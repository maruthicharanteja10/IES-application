package com.sb.majorproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sb.majorproject.binding.HistoryNotices;
import com.sb.majorproject.binding.PendingNotices;
import com.sb.majorproject.entity.CorrespondanceNotices;
import com.sb.majorproject.entity.EligibilityDetermination;
import com.sb.majorproject.service.CorrespondanceNoticesService;
import com.sb.majorproject.service.EligibilityDeterminationService;

import jakarta.servlet.http.HttpServletResponse;

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

	@GetMapping("/toggleNoticeStatus/{notice_id}")
	public String createNoticeStatus(@PathVariable("notice_id") Integer notice_id,HttpServletResponse response) throws Exception {
		correspondanceNoticesService.togglenoticeStatus(notice_id,response);
		return "redirect:/IES/correspondance/viewNotices";

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

	@GetMapping("/pendingNotices")
	public String pendingNoticesPage() {
		return "/Correspondance/pendingNotices";
	}

	@GetMapping("/pendingNoticescaseNo")
	public String pendingNotices(Model model, @RequestParam("caseNo") Long caseNo) {
		List<PendingNotices> pendingList = correspondanceNoticesService.getPendingNoticesByCaseNo(caseNo);
		model.addAttribute("notices", pendingList);
		if(pendingList.isEmpty()) {
			model.addAttribute("Msg","No PendingNotices With these CaseNo");
		}
		return "/correspondance/pendingNotices";
	}

	@GetMapping("/historyNotices")
	public String historyNoticesPage(Model model) {

		return "/Correspondance/historyNotices";
	}

	@GetMapping("/historyNoticescaseNo")
	public String historyNotices(Model model, @RequestParam("caseNo") Long caseNo) {
		List<HistoryNotices> HistoryList = correspondanceNoticesService.getHistoryNoticesByCaseNo(caseNo);
		model.addAttribute("notices", HistoryList);
		if(HistoryList.isEmpty()) {
			model.addAttribute("Msg","No HistoryNotices With these CaseNo");
		}
		return "/correspondance/historyNotices";
	}
}
