package com.sb.majorproject.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sb.majorproject.binding.LoginForm;
import com.sb.majorproject.binding.RegisterDetails;
import com.sb.majorproject.binding.UnlockForm;
import com.sb.majorproject.entity.Plan;
import com.sb.majorproject.entity.UserDetails;
import com.sb.majorproject.service.PlanService;
import com.sb.majorproject.service.UserDetailsService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/IES/admin")
public class AdminController {
	@Autowired
	private UserDetailsService userdetailsService;
	@Autowired
	private PlanService planService;

	@GetMapping("/createAccount")
	public String createAccountpage(Model model) {
		model.addAttribute("user", new RegisterDetails());
		return "/admin/createAccount";
	}

	@PostMapping("/createAccount")
	public String createAccount(Model model, @ModelAttribute("user") @Valid RegisterDetails form, BindingResult result)
			throws Exception {
		if (result.hasErrors()) {
			return "/admin/createAccount"; // Return to the form with errors
		}
		boolean status = userdetailsService.CreateUserAccount(form);
		if (status) {
			model.addAttribute("succMsg", "Account created Check Your Email");
		} else {
			model.addAttribute("errMsg", "Choose Unique Email");
		}
		return "/admin/createAccount";
	}

	@GetMapping("/unlock")
	public String unlockPage(@RequestParam(value = "email", required = false, defaultValue = "") String email,
			Model model) {
		UnlockForm unlockForm = new UnlockForm();
		unlockForm.setEmail(email);
		model.addAttribute("email", email);
		model.addAttribute("unlock", unlockForm);
		return "/profileCreation/unlock";
	}

	@PostMapping("/unlock")
	public String UnlockUserAccount(@ModelAttribute("unlock") UnlockForm unlock, Model model) {
		System.out.println(unlock);
		if (unlock.getNewPswd().length() < 6) {
			model.addAttribute("Msg", "Password must be at least 6 characters");
			return "unlock";
		}
		if (unlock.getNewPswd().equals(unlock.getConfirmPswd())) {
			boolean status = userdetailsService.unlockaccount(unlock);
			if (status) {
				model.addAttribute("Msg", "Your Account is unlocked");
			} else {
				model.addAttribute("Msg", "Given Temporary pswd is incorrect, check your email");
			}
		} else {
			model.addAttribute("Msg", "New pswd and Confirm pswd should be same");
		}
		return "/profileCreation/unlock";
	}

	@GetMapping("/login")
	public String loginpage(Model model) {
		model.addAttribute("form", new LoginForm());
		return "/profileCreation/login";
	}

	@PostMapping("/login")
	public String login(Model model, @ModelAttribute("form") LoginForm form, HttpSession session) {
		String status = userdetailsService.loginAccount(form.getEmail(), form.getPassword());

		System.out.println(status);
		if ("adminsuccess".equals(status)) {
			session.setAttribute("role", "ADMIN");
			return "redirect:/IES/dashboard";
		}
		if ("caseWorkersuccess".equals(status)) {
			session.setAttribute("role", "USER");
			return "redirect:/IES/dashboard";
		}
		model.addAttribute("Msg", "Account is " + status);
		return "/profileCreation/login";
	}

	@GetMapping("/forgot")
	public String forgotpswdPage() {
		return "/profileCreation/forgotpswd";
	}

	@PostMapping("/forgotpswd")
	public String forgotpswd(@RequestParam("email") String email, Model model) throws Exception {
		boolean status = userdetailsService.forgotpswd(email);
		if (status) {
			model.addAttribute("Msg", "password is sent to your mail");
		} else {
			model.addAttribute("Msg", "Invalid email");
		}
		return "/profileCreation/forgotpswd";
	}

	@GetMapping("/viewAccount")
	public String viewAccountspage(Model model) {
		List<UserDetails> details = userdetailsService.getAllDetails();
		model.addAttribute("detail", details);
		return "/admin/viewAccount";
	}

	@GetMapping("/toggleAccountStatus/{userId}")
	public String toggleUserStatus(@PathVariable Long userId) {
		userdetailsService.toggleStatus(userId);
		return "redirect:/IES/admin/viewAccount";
	}

	@GetMapping("/Edituser/{userId}")
	public String EditUserDetails(Model model, @PathVariable("userId") Long userId) {
		model.addAttribute("user", userdetailsService.editById(userId));
		return "/admin/editAccount";
	}

	@PostMapping("/Edituser/{userId}")
	public String EditUser(Model model, @PathVariable("userId") Long userId, @ModelAttribute UserDetails user) {
		UserDetails details = userdetailsService.editById(userId);
		details.setFullName(user.getFullName());
		details.setGender(user.getGender());
		details.setEmail(user.getEmail());
		details.setAadhaarNumber(user.getAadhaarNumber());
		details.setDob(user.getDob());
		details.setUpdatedDate(LocalDate.now());
		details.setUpdatedBy("tej@gmail.com");
		userdetailsService.updateAccount(details);
		return "redirect:/IES/admin/viewAccount";
	}

	@GetMapping("/createPlan")
	public String createPlanspage(Model model) {
		model.addAttribute("plans", new Plan());
		return "/admin/createPlan";
	}

	@PostMapping("/createPlans")
	public String createPlans(@ModelAttribute("plans") Plan plan, RedirectAttributes redirectAttributes) {
		boolean status = planService.createSchemePlans(plan);
		System.out.println(status);
		if (status) {
			redirectAttributes.addFlashAttribute("SuccessMsg", "Plan created Succesfully");
		} else {
			redirectAttributes.addFlashAttribute("errMsg", "Error in creating plan");
		}
		return "redirect:/IES/admin/createPlan";
	}

	@GetMapping("/viewPlans")
	public String viewPlanspage(Model model) {
		List<Plan> viewplans = planService.getAllPlans();
		model.addAttribute("plans", viewplans);
		return "/admin/viewPlans";
	}

	@GetMapping("/toggleplanStatus/{planId}")
	public String toggleplanStatus(@PathVariable Integer planId) {
		planService.plantoggleStatus(planId);

		return "redirect:/IES/admin/viewPlans";
	}

	@GetMapping("/EditPlan/{planId}")
	public String editplans(Model model, @PathVariable("planId") Integer planId) {
		model.addAttribute("plans", planService.editPlanByID(planId));
		return "/admin/EditPlan";
	}

	@PostMapping("/EditPlan/{planId}")
	public String editplan(@PathVariable("planId") Integer planId, @ModelAttribute Plan plans) {
		Plan existingplan = planService.editPlanByID(planId);
		existingplan.setPlanName(plans.getPlanName());
		existingplan.setPlanCategory(plans.getPlanCategory());
		existingplan.setPlanStartDate(plans.getPlanStartDate());
		existingplan.setPlanEndDate(plans.getPlanEndDate());
		existingplan.setUpdatedBy("tej@gamil.com");
		existingplan.setUpdatedDate(LocalDate.now());
		planService.updatePlans(existingplan);

		return "redirect:/IES/admin/viewPlans";
	}

	@GetMapping("/")
	public String Adminpage() {
		return "/admin/Admin";
	}

}
