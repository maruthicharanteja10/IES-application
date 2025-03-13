package com.sb.majorproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/IES/AR")
public class ApplicationRegistrationController {
	@GetMapping("/createApplication")
	public String createApplicationpage() {
		return "/ApplicationRegistration/createApplication";
	}
	@GetMapping("/viewApplication")
	public String viewApplicationpage() {
		return "/ApplicationRegistration/viewApplication";
	}
}
