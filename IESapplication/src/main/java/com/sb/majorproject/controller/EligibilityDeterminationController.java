package com.sb.majorproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sb.majorproject.service.EligibilityDeterminationService;

@Controller
@RequestMapping("/IES/ED")
public class EligibilityDeterminationController {
	@Autowired
	private EligibilityDeterminationService determinationService;
	@GetMapping("/eligibility")
	public String createApplicationpage() {
		return "/eligibilityCriteria/DetermineEligibility";
	}
	
}
