package com.sb.majorproject.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.majorproject.repository.EligibilityDeterminationRepository;
import com.sb.majorproject.service.EligibilityDeterminationService;

@Service
public class EligibilityDeterminationServiceImpl implements EligibilityDeterminationService {
	@Autowired
	private EligibilityDeterminationRepository determinationRepository;

}
