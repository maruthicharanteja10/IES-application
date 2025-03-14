package com.sb.majorproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/IES")
public class IndexController {
	@GetMapping("/")
	public String IesPage() {
		return "ies";
	}

	@GetMapping("/login")
	public String loginpage() {
		return "login";
	}
	@GetMapping("/newpassword")
	public String registerpage() {
		return "newpassword";
	}
	@GetMapping("/dashboard")
	public String dashboardpage() {
		return "dashboard";
	}
	@GetMapping("/profile")
	public String profilepage() {
		return "profile";
	}

}
