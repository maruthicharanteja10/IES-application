package com.sb.majorproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sb.majorproject.entity.UserDetails;
import com.sb.majorproject.service.UserDetailsService;

@Controller
@RequestMapping("/IES")
public class IndexController {
	@Autowired
	private UserDetailsService userDetailsService;

	@GetMapping("/")
	public String IesPage() {
		return "ies";
	}

	@GetMapping("/dashboard")
	public String dashboardpage() {
		
		return "dashboard";
	}
	@GetMapping("/caseWorkerdashboard")
	public String caseWorkerdashboardpage() {
		
		return "dashboard";
	}

	@GetMapping("/profile")
	public String profilepage() {
		return "profile";
	}

}
