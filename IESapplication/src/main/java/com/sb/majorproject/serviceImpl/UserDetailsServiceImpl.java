package com.sb.majorproject.serviceImpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Service;

import com.sb.majorproject.binding.RegisterDetails;
import com.sb.majorproject.binding.UnlockForm;
import com.sb.majorproject.entity.Plan;
import com.sb.majorproject.entity.UserDetails;
import com.sb.majorproject.repository.PlanRepository;
import com.sb.majorproject.repository.UserDetailsRepository;
import com.sb.majorproject.service.UserDetailsService;
import com.sb.majorproject.utils.EmailUtils;
import com.sb.majorproject.utils.PasswordUtils;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserDetailsRepository userDetailRepo;
		
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public boolean CreateUserAccount(RegisterDetails details) throws Exception {
		UserDetails user = userDetailRepo.findByEmail(details.getEmail());
		if (user != null) {
			return false;
		}

		// CopyData from binding object to Entity object
		UserDetails entity = new UserDetails();
		entity.setAadhaarNumber(details.getAadhaarNumber());
		BeanUtils.copyProperties(details, entity);
		// To generate Password
		String tempPswd = PasswordUtils.generateRandomPswd();
		entity.setPassword(tempPswd);

		entity.setAcctStatus("LOCKED");
		entity.setActiveStatus("Y");
		entity.setRole("USER");
		userDetailRepo.save(entity);
//To send Email
		String recipient = details.getEmail();
		String subject = "Unlock Your Account";
		String emailBody = "<h1>Use below temporary password to unlock account</h1>" + "Temporary Pswd : " + tempPswd
				+ "<br/>" + "<a href=\"http://localhost:8080/IES/admin/unlock?email=" + recipient
				+ "\">Click Here to Unlock Account</a>";
		emailUtils.sendEmail(recipient, subject, emailBody);
		return true;
	}

	@Override
	public boolean unlockaccount(UnlockForm form) {
		UserDetails entity = userDetailRepo.findByEmail(form.getEmail());

		if (form == null || form.getEmail() == null || form.getEmail().isEmpty()) {
			throw new IllegalArgumentException("Email cannot be null or empty");
		}

		if (entity == null) {
			throw new IllegalArgumentException("No user found with email: " + form.getEmail());
		}
		if (entity.getPassword().equals(form.getTempPswd())) {
			entity.setPassword(form.getNewPswd());
			entity.setAcctStatus("UNLOCKED");
			userDetailRepo.save(entity);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean forgotpswd(String email) throws Exception {
		if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
			return false;
		}
		UserDetails entity = userDetailRepo.findByEmail(email);
		if (entity == null) {
			return false;
		}
		String subject = "Recover Password";
		String body = "Your Pswd :" + entity.getPassword();
		emailUtils.sendEmail(email, subject, body);
		return true;
	}

	@Override
	public String loginAccount(String email, String password) {
		UserDetails entity = userDetailRepo.findByEmailAndPassword(email, password);

		if (entity == null) {
			return "invalid";
		}
		if (entity.getAcctStatus().equals("LOCKED")) {
			return "locked";
		}

//		// create session and store user data in session
//		Session.setAttribute("userId", (Long)entity.getUserId());
		return "success";
	}

	@Override
	public List<UserDetails> getAllDetails() {
		
		return userDetailRepo.findAll();
	}

	


	


}
