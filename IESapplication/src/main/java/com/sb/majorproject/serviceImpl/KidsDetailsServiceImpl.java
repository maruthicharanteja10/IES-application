package com.sb.majorproject.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sb.majorproject.entity.ApplicationDetails;
import com.sb.majorproject.entity.KidsDetails;
import com.sb.majorproject.repository.ApplicationDetailsRepository;
import com.sb.majorproject.repository.KidsDetailsRepository;
import com.sb.majorproject.service.KidsDetailsService;

@Service
public class KidsDetailsServiceImpl implements KidsDetailsService {

	@Autowired
	private KidsDetailsRepository kidsDetailsRepository;
	@Autowired
	private ApplicationDetailsRepository applicationDetailsRepository;

	@Override
	public boolean savekidsDetails(KidsDetails kidsDetails, Long caseNo) {

		ApplicationDetails details = applicationDetailsRepository.findById(caseNo)
				.orElseThrow(() -> new RuntimeException("Case number not found: " + caseNo));
		kidsDetails.setKidsCaseNo(caseNo);
		kidsDetails.setApplicationDetails(details);
		kidsDetailsRepository.save(kidsDetails);
		return true;
	}

	@Override
	public List<KidsDetails> getAllKidsDetails() {
		// TODO Auto-generated method stub
		return kidsDetailsRepository.findAll();
	}

	

}
