package com.sb.majorproject.controller;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sb.majorproject.entity.ApplicationDetails;
import com.sb.majorproject.entity.Plan;
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
	
		return findPaginatedplans(1,model);
	}
	@GetMapping("/Applications/page/{pageNo}")
	public String findPaginatedplans(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = 3;

		Page<ApplicationDetails> page = applicationDetailsService.findPaginated(pageNo, pageSize);
		List<ApplicationDetails> listapplications = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("applications", listapplications);
		return "/ApplicationRegistration/viewApplication";
	}
}
