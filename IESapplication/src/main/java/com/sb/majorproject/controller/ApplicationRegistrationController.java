package com.sb.majorproject.controller;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sb.majorproject.entity.ApplicationDetails;
import com.sb.majorproject.service.ApplicationDetailsService;


@Controller
@RequestMapping("/IES/AR")
public class ApplicationRegistrationController {

	@Autowired
	private ApplicationDetailsService applicationDetailsService;

	@GetMapping("/createApplication")
	public String createApplicationpage(Model model) {
		model.addAttribute("application", new ApplicationDetails());
		return "/ApplicationRegistration/createApplication";
	}

	@PostMapping("/createApplication")
	public String createApplication(@ModelAttribute("application") ApplicationDetails details, Model model)
			throws Exception {
		try {
			boolean status = applicationDetailsService.createApplication(details);
			if (status) {
				model.addAttribute("successMsg", "Application Created CaseNumber has been sent to your email.");
			}
		} catch (Exception e) {
			model.addAttribute("errMsg", "You are not eligible to register for Application");
		}
		return "/ApplicationRegistration/createApplication";
	}

	@GetMapping("/viewApplication")
	public String viewApplicationpage(Model model) {
		List<ApplicationDetails> details = applicationDetailsService.viewApplications();
		model.addAttribute("applications", details);
		return "/ApplicationRegistration/viewApplication";
	}
}
