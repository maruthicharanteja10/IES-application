package com.sb.majorproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.sb.majorproject.entity.EducationDetails;

public interface EducationDetailsRepository extends JpaRepository<EducationDetails, Integer> {
	
	Optional<EducationDetails> findByApplicationDetails_CaseNo(Long caseNo);

}
