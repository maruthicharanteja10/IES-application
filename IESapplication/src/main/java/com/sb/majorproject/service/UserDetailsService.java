package com.sb.majorproject.service;

import java.util.List;

import com.sb.majorproject.binding.RegisterDetails;
import com.sb.majorproject.binding.UnlockForm;

import com.sb.majorproject.entity.UserDetails;

public interface UserDetailsService {

	boolean CreateUserAccount(RegisterDetails details) throws Exception;

	public boolean unlockaccount(UnlockForm form);

	boolean forgotpswd(String email) throws Exception;

	String loginAccount(String email, String password);

	List<UserDetails> getAllDetails();



}
