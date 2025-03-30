package com.sb.majorproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sb.majorproject.entity.ApplicationDetails;
import com.sb.majorproject.entity.EligibilityDetermination;
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
		return "/eligibilityCriteria/DetermineEligibility";
	}

	@PostMapping("/eligibility")
	public String createApplication(@RequestParam("caseNo") Long caseNo, Model model,
			EligibilityDetermination determination) {
		determinationService.checkEligibility(determination, caseNo);
		return "redirect:/IES/ED/eligibility/List";
	}

	@GetMapping("/eligibility/List")
	public String ListEligibility(Model model) {
		List<EligibilityDetermination> listEligibility = determinationService.findAllListEligibility();
		model.addAttribute("eligibilityList", listEligibility);
		return "/eligibilityCriteria/DetermineEligibilityDetails";
	}

}
