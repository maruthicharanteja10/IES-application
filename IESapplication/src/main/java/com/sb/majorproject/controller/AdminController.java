package com.sb.majorproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sb.majorproject.binding.RegisterDetails;
import com.sb.majorproject.service.UserDetailsService;

@Controller
@RequestMapping("/IES/admin")
public class AdminController {
	@Autowired
	private UserDetailsService userdetailsService;
	@GetMapping("/createAccount")
	public String createAccountpage() {
		return "/admin/createAccount";
	}
	@PostMapping("/createAccount")
	public String createAccount(Model model,@ModelAttribute("form")RegisterDetails form) {
		boolean status=userdetailsService.registerUserDetails(form);
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
