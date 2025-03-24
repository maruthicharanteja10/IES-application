package com.sb.majorproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sb.majorproject.entity.ApplicationDetails;
import com.sb.majorproject.entity.EducationDetails;
import com.sb.majorproject.entity.IncomeDetails;
import com.sb.majorproject.entity.KidsDetails;
import com.sb.majorproject.entity.Plan;
import com.sb.majorproject.entity.PlanSelection;
import com.sb.majorproject.service.EducationDetailsService;
import com.sb.majorproject.service.IncomeDetailsService;
import com.sb.majorproject.service.KidsDetailsService;
import com.sb.majorproject.service.PlanSelectionService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/IES/DC")
public class DataCollectionController {
	@Autowired
	private PlanSelectionService planSelectionService;
	@Autowired
	private IncomeDetailsService incomeDetailsService;
	@Autowired
	private EducationDetailsService educationDetailsService;
	@Autowired
	private KidsDetailsService kidsDetailsService;

	@GetMapping("/planSelection")
	public String planSelectionpage(@RequestParam("caseNo") Long caseNo, Model model) {
		List<String> plansList = planSelectionService.getAllPlanNames();
		model.addAttribute("plans", plansList);
		model.addAttribute("selection", new PlanSelection());
		model.addAttribute("caseno", caseNo);
		return "/dataCollection/planSelection";
	}

	@PostMapping("/plansSelection")
	public String planSelection(@ModelAttribute("selection") PlanSelection selection,
			@RequestParam("caseNo") Long caseNo, HttpSession session, Model model) {
		selection.setCaseNo(caseNo);
		boolean status = planSelectionService.savePlanSelection(selection);
		session.setAttribute("caseNo", caseNo);
		if (status) {

			return "redirect:/IES/DC/incomeDetails";
		} else {

			model.addAttribute("errMsg", "Only one plan selection is created for one caseNo");
			return "/dataCollection/planSelection";
		}

	}

	@GetMapping("/incomeDetails")
	public String incomeDetailsPage(Model model, HttpSession session) {
		model.addAttribute("incomeDetails", new IncomeDetails());
		Long caseNo = (Long) session.getAttribute("caseNo");
		model.addAttribute("caseNo", caseNo);
		return "/dataCollection/incomeDetails";
	}

	@PostMapping("/incomeDetails")
	public String incomeDetails(@ModelAttribute("incomeDetails") IncomeDetails incomeDetails,
			@RequestParam("caseNo") Long caseNo, HttpSession session) {

		incomeDetailsService.saveIncomeDetails(incomeDetails, caseNo);
		session.setAttribute("caseNo", caseNo);
		return "redirect:/IES/DC/EducationDetails";
	}

	@GetMapping("/EducationDetails")
	public String eductionDetailspage(Model model, HttpSession session) {
		Long caseNo = (Long) session.getAttribute("caseNo");
		model.addAttribute("caseNo", caseNo);
		model.addAttribute("educationDetails", new EducationDetails());
		return "/dataCollection/educationDetails";
	}

	@PostMapping("/EducationDetails")
	public String eductionDetails(@RequestParam("caseNo") Long caseNo, HttpSession session,
			@ModelAttribute("educationDetails") EducationDetails educationDetails) {
		educationDetailsService.saveEducationDetails(educationDetails, caseNo);
		session.setAttribute("caseNo", caseNo);
		return "redirect:/IES/DC/kidsDetails";
	}

	@GetMapping("/kidsDetails")
	public String kidsDetailspage(Model model, HttpSession session) {
		Long caseNo = (Long) session.getAttribute("caseNo");
		model.addAttribute("caseNo", caseNo);
		model.addAttribute("kidsDetails", new KidsDetails());
		return "/dataCollection/kidsDetails";
	}

	@PostMapping("/kidsDetails")
	public String kidsDetailspage(@RequestParam("caseNo") Long caseNo, HttpSession session,
			@ModelAttribute("kidsDetails") KidsDetails kidsDetails, Model model) {
		boolean status = kidsDetailsService.savekidsDetails(kidsDetails, caseNo);
		if (status) {
			model.addAttribute("Msg", "All your data is succesfully stored");
		} else {
			model.addAttribute("Msg", "Some details are missed check once");
		}
		return "/dataCollection/kidsDetails";
	}

	@GetMapping("/summaryScreen")
	public String summaryScreenpage(Model model) {
		List<PlanSelection> planselection = planSelectionService.getAllPlanSelctions();
		List<IncomeDetails> incomeDetailsList = incomeDetailsService.getAllIncomeDetails();
		List<EducationDetails> educationDetails = educationDetailsService.getAllEducationDetails();
		List<KidsDetails> kidsDetails = kidsDetailsService.getAllKidsDetails();
		model.addAttribute("selection", planselection);
		model.addAttribute("educationDetails", educationDetails);
		model.addAttribute("incomedetails", incomeDetailsList);
		model.addAttribute("kidsDetails", kidsDetails);
		return "/dataCollection/summaryScreen";
	}

	@GetMapping("/planSelectionError")
	public String planselectionerrorpage() {
		return "/dataCollection/planSelectionError";
	}
}
