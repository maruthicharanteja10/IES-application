package com.sb.majorproject.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sb.majorproject.entity.ApplicationDetails;
import com.sb.majorproject.entity.IncomeDetails;
import com.sb.majorproject.repository.ApplicationDetailsRepository;
import com.sb.majorproject.repository.IncomeDetailsRepository;
import com.sb.majorproject.service.IncomeDetailsService;

@Service
public class IncomeDetailsServiceImpl implements IncomeDetailsService {

	@Autowired
	private IncomeDetailsRepository incomeDetailsRepository;
	@Autowired
	private ApplicationDetailsRepository applicationDetailsRepository;

	@Override
	public void saveIncomeDetails(IncomeDetails details, Long caseNo) {
		ApplicationDetails applicationdetails = applicationDetailsRepository.findById(caseNo)
				.orElseThrow(() -> new RuntimeException("Case number not found: " + caseNo));
		details.setIncomeCaseNo(caseNo);

		details.setApplicationDetails(applicationdetails);
		incomeDetailsRepository.save(details);
	}

	@Override
	public List<IncomeDetails> getAllIncomeDetails() {
		// TODO Auto-generated method stub
		return incomeDetailsRepository.findAll();
	}

}
