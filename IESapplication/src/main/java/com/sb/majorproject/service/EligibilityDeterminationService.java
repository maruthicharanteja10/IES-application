package com.sb.majorproject.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sb.majorproject.entity.EligibilityDetermination;

public interface EligibilityDeterminationService {


	List<EligibilityDetermination> findAllListEligibility();

	boolean checkEligibility(EligibilityDetermination determination, Long caseNo) ;

	 Page<EligibilityDetermination> findPaginated(int pageNo, int pageSize) ;

	

	

}
