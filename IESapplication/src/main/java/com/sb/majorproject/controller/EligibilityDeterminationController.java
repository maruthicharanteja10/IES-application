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

import com.sb.majorproject.entity.ApplicationDetails;
import com.sb.majorproject.entity.EligibilityDetermination;
import com.sb.majorproject.entity.UserDetails;
import com.sb.majorproject.service.EligibilityDeterminationService;

@Controller
@RequestMapping("/IES/ED")
public class EligibilityDeterminationController {
	@Autowired
	private EligibilityDeterminationService determinationService;

	@GetMapping("/eligibility")
	public String createApplicationpage(Model model) {
		ApplicationDetails applicationDetails = new ApplicationDetails();
		Long caseNo = applicationDetails.getCaseNo();

		model.addAttribute("caseNo", caseNo);
		return findPaginatedEd(1, model);
	}

	@PostMapping("/checkeligibility")
	public String createApplication(@RequestParam("caseNo") Long caseNo, Model model,
			EligibilityDetermination determination) {
		boolean status = determinationService.checkEligibility(determination, caseNo);
		if (status) {
			model.addAttribute("succMsg", "EligibilityDetermined for the given caseNo");
		} else {
			model.addAttribute("errMsg", "Eligibility AlreadyDetermined");
		}
		return "redirect:/IES/ED/eligibility";
	}

	@GetMapping("/Eg/page/{pageNo}")
	public String findPaginatedEd(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = 3;

		Page<EligibilityDetermination> page = determinationService.findPaginated(pageNo, pageSize);
		List<EligibilityDetermination> listeligibilities = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("eligibilityList", listeligibilities);
		return "/eligibilityCriteria/DetermineEligibility";
	}

}
