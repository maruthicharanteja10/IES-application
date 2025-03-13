package com.sb.majorproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/IES/DC")
public class DataCollectionController {
	@GetMapping("/planSelection")
	public String createAccountpage() {
		return "/dataCollection/planSelection";
	}
	@GetMapping("/incomeDetails")
	public String viewAccountspage() {
		return "/dataCollection/incomeDetails";
	}
	@GetMapping("/EducationDetails")
	public String createPlanspage() {
		return "/dataCollection/educationDetails";
	}
	@GetMapping("/kidsDetails")
	public String viewPlanspage() {
		return "/dataCollection/kidsDetails";
	}
	@GetMapping("/summaryScreen")
	public String Adminpage() {
		return "/dataCollection/summaryScreen";
	}
	
}
