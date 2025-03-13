package com.sb.majorproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/IES/correspondance")
public class CorrespondanceController {
	@GetMapping("/historyNotices")
	public String createAccountpage() {
		return "/Correspondance/historyNotices";
	}
	@GetMapping("/pendingNotices")
	public String viewAccountspage() {
		return "/Correspondance/pendingNotices";
	}
	@GetMapping("/viewNotices")
	public String createPlanspage() {
		return "/Correspondance/viewNotices";
	}
	
	
}
