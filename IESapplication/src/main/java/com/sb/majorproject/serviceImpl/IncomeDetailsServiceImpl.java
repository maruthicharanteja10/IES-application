package com.sb.majorproject.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.majorproject.repository.IncomeDetailsRepository;
import com.sb.majorproject.service.IncomeDetailsService;

@Service
public class IncomeDetailsServiceImpl implements IncomeDetailsService {

	@Autowired
	private IncomeDetailsRepository incomeDetailsRepository;

	
}
