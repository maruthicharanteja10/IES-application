package com.sb.majorproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sb.majorproject.entity.PlanSelection;
import com.sb.majorproject.service.PlanSelectionService;

@Controller
@RequestMapping("/IES/DC")
public class DataCollectionController {
	@Autowired
	private PlanSelectionService planSelectionService;

	@GetMapping("/planSelection")
	public String planSelectionpage(@RequestParam("caseNo") String caseNo, Model model) {
		model.addAttribute("selection", new PlanSelection());
		model.addAttribute("caseno", caseNo);
		return "/dataCollection/planSelection";
	}

	@PostMapping("/planSelection")
	public String planSelection(@ModelAttribute("caseNo") PlanSelection selection) {
		boolean status=planSelectionService.savePlanSelection(selection);
		return "/dataCollection/planSelection";
	}

	@GetMapping("/incomeDetails")
	public String incomeDetailspage() {
		return "/dataCollection/incomeDetails";
	}

	@GetMapping("/EducationDetails")
	public String eductionDetailspage() {
		return "/dataCollection/educationDetails";
	}

	@GetMapping("/kidsDetails")
	public String kidsDetailspage() {
		return "/dataCollection/kidsDetails";
	}

	@GetMapping("/summaryScreen")
	public String summaryScreenpage() {
		return "/dataCollection/summaryScreen";
	}

	@GetMapping("/planSelectionError")
	public String planselectionerrorpage() {
		return "/dataCollection/planSelectionError";
	}
}
