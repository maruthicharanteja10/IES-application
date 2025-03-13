package com.sb.majorproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/IES/BI")
public class BenefitIssuanceController {
	
	@GetMapping("/benifits")
	public String createApplicationpage() {
		return "/benefitIssuance/benefitsIssuance";
	}
	
}
