package com.sb.majorproject.service;

import java.util.List;

import com.sb.majorproject.entity.IncomeDetails;

public interface IncomeDetailsService {

	void saveIncomeDetails(IncomeDetails details, Long caseNo);

	List<IncomeDetails> getAllIncomeDetails();

}
