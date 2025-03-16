package com.sb.majorproject.service;

import com.sb.majorproject.binding.RegisterDetails;
import com.sb.majorproject.binding.UnlockForm;


public interface UserDetailsService {

	boolean CreateUserAccount(RegisterDetails details) throws Exception;
	public boolean unlockaccount(UnlockForm form);
	boolean forgotpswd(String email) throws Exception;
}
