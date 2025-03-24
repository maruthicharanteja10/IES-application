package com.sb.majorproject.service;

import java.util.List;

import com.sb.majorproject.entity.EducationDetails;

public interface EducationDetailsService {

	void saveEducationDetails(EducationDetails educationDetails, Long caseNo);

	List<EducationDetails> getAllEducationDetails();

}
