package com.sb.majorproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		return "unlock";
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
		return "unlock";
	}

	@GetMapping("/login")
	public String loginpage(Model model) {
		model.addAttribute("form", new LoginForm());
		return "login";
	}

	@PostMapping("/login")
	public String login(Model model, @ModelAttribute("form") LoginForm form) {
		String status = userdetailsService.loginAccount(form.getEmail(), form.getPassword());
		System.out.println(status);
		if ("success".equals(status)) {
			return "redirect:/IES/dashboard";
		}
		model.addAttribute("Msg", "Account is " + status);
		return "login";
	}

	@GetMapping("/forgot")
	public String forgotpswdPage() {
		return "forgotpswd";
	}

	@PostMapping("/forgotpswd")
	public String forgotpswd(@RequestParam("email") String email, Model model) throws Exception {
		boolean status = userdetailsService.forgotpswd(email);
		if (status) {
			model.addAttribute("Msg", "password is sent to your mail");
		} else {
			model.addAttribute("Msg", "Invalid email");
		}
		return "forgotpswd";
	}

	@GetMapping("/viewAccount")
	public String viewAccountspage(Model model) {
		List<UserDetails> details = userdetailsService.getAllDetails();
		model.addAttribute("detail", details);
		return "/admin/viewAccount";
	}

	@GetMapping("/createPlan")
	public String createPlanspage(Model model) {
		model.addAttribute("plans", new Plan());
		return "/admin/createPlan";
	}

	@PostMapping("/createPlans")
	public String createPlans( @ModelAttribute("plans") Plan plan,RedirectAttributes redirectAttributes) {
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
	List<Plan>	viewplans=planService.getAllPlans();
	model.addAttribute("plans",viewplans);
		return "/admin/viewPlans";
	}

	@GetMapping("/")
	public String Adminpage() {
		return "/admin/Admin";
	}

}
