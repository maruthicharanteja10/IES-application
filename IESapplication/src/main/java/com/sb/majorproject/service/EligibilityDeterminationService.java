package com.sb.majorproject.service;

import java.util.List;

import com.sb.majorproject.entity.EligibilityDetermination;

public interface EligibilityDeterminationService {


	List<EligibilityDetermination> findAllListEligibility();

	void checkEligibility(EligibilityDetermination determination, Long caseNo);

}
