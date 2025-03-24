package com.sb.majorproject.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.majorproject.entity.ApplicationDetails;
import com.sb.majorproject.entity.EducationDetails;
import com.sb.majorproject.repository.ApplicationDetailsRepository;
import com.sb.majorproject.repository.EducationDetailsRepository;
import com.sb.majorproject.service.EducationDetailsService;

@Service
public class EducationDetailsServiceImpl implements EducationDetailsService {
	@Autowired
	private EducationDetailsRepository educationDetailsRepository;
	@Autowired
	private ApplicationDetailsRepository applicationDetailsRepository;
	@Override
	public void saveEducationDetails(EducationDetails educationDetails, Long caseNo) {
		ApplicationDetails applicationdetails = applicationDetailsRepository.findById(caseNo)
				.orElseThrow(() -> new RuntimeException("Case number not found: " + caseNo));
		educationDetails.setEducationCaseNo(caseNo);
		educationDetails.setApplicationDetails(applicationdetails);
		educationDetailsRepository.save(educationDetails);
		
	}
	@Override
	public List<EducationDetails> getAllEducationDetails() {
		// TODO Auto-generated method stub
		return educationDetailsRepository.findAll();
	}

}
