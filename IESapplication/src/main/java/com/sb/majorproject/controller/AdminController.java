package com.sb.majorproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/IES/admin")
public class AdminController {
	@GetMapping("/createAccount")
	public String createAccountpage() {
		return "/admin/createAccount";
	}
	@GetMapping("/viewAccount")
	public String viewAccountspage() {
		return "/admin/viewAccount";
	}
	@GetMapping("/createPlan")
	public String createPlanspage() {
		return "/admin/createPlan";
	}
	@GetMapping("/viewPlans")
	public String viewPlanspage() {
		return "/admin/viewPlans";
	}
	@GetMapping("/")
	public String Adminpage() {
		return "/admin/Admin";
	}
	
}
