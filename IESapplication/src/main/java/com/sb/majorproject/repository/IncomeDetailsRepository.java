package com.sb.majorproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sb.majorproject.entity.IncomeDetails;

public interface IncomeDetailsRepository extends JpaRepository<IncomeDetails, Integer> {

	@Query("SELECT i FROM IncomeDetails i WHERE i.applicationDetails.caseNo = :caseNo")
	List<IncomeDetails> findAllByApplicationDetails_CaseNo(Long caseNo);

}
