package com.sb.majorproject.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.majorproject.repository.EducationDetailsRepository;
import com.sb.majorproject.service.EducationDetailsService;

@Service
public class EducationDetailsServiceImpl implements EducationDetailsService {
	@Autowired
	private EducationDetailsRepository educationDetailsRepository;

}
