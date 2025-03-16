package com.sb.majorproject.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.sb.majorproject.binding.RegisterDetails;
import com.sb.majorproject.binding.UnlockForm;
import com.sb.majorproject.service.UserDetailsService;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/IES/admin")
public class AdminController {
	@Autowired
	private UserDetailsService userdetailsService;

	@GetMapping("/createAccount")
	public String createAccountpage(Model model) {
		model.addAttribute("user", new RegisterDetails());
		return "/admin/createAccount";
	}

	@PostMapping("/createAccount")
	public String createAccount(Model model, @ModelAttribute("user") @Valid RegisterDetails form,BindingResult result) throws Exception {
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
	public String unlockPage(@RequestParam(value="email", required = false, defaultValue = "") String email, Model model) {
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
		        model.addAttribute("errMsg", "Password must be at least 6 characters");
		        return "unlock";
		    }
		if (unlock.getNewPswd().equals(unlock.getConfirmPswd())) {
			boolean status = userdetailsService.unlockaccount(unlock);
			if (status) {
				model.addAttribute("successMsg", "Your Account is unlocked");
			} else {
				model.addAttribute("errMsg", "Given Temporary pswd is incorrect, check your email");
			}
		} else {
			model.addAttribute("errMsg", "New pswd and Confirm pswd should be same");
		}
		return "unlock";
	}

	@GetMapping("/forgot")
	public String forgotpswdPage() {
		return "forgotpswd";
	}

	@PostMapping("/forgotpswd")
	public String forgotpswd(@RequestParam("email") String email, Model model) throws Exception {
		boolean status=userdetailsService.forgotpswd(email);
		if(status) {
			model.addAttribute("successMsg", "password is sent to your mail");
		} else {
			model.addAttribute("errMsg", "Invalid email");
		}
		return "forgotpswd";
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
