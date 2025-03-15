package com.sb.majorproject.serviceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.majorproject.binding.RegisterDetails;
import com.sb.majorproject.entity.UserDetails;
import com.sb.majorproject.repository.UserDetailsRepository;
import com.sb.majorproject.service.UserDetailsService;
import com.sb.majorproject.utils.PasswordUtils;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserDetailsRepository userDetailRepo;

	@Override
	public boolean registerUserDetails(RegisterDetails details) {
		UserDetails user = userDetailRepo.findByEmail(details.getEmail());
		if(user!=null) {
			return false;
		}
		//CopyData from binding object to Entity object
		UserDetails entity=new UserDetails();
		BeanUtils.copyProperties(user, entity);
		//To generate Password
	String	tempPswd=PasswordUtils.generateRandomPswd();
		return false;
	}

}
