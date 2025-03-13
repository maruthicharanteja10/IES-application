package com.sb.majorproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/IES/ED")
public class EligibilityDeterminationController {
	
	@GetMapping("/eligibility")
	public String createApplicationpage() {
		return "/eligibilityCriteria/DetermineEligibility";
	}
	
}
