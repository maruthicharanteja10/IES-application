package com.sb.majorproject.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.majorproject.repository.KidsDetailsRepository;
import com.sb.majorproject.service.KidsDetailsService;

@Service
public class KidsDetailsServiceImpl implements KidsDetailsService {

	@Autowired
	private KidsDetailsRepository kidsDetailsRepository;

	
}
