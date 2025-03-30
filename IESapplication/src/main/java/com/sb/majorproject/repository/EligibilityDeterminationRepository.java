package com.sb.majorproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sb.majorproject.entity.EligibilityDetermination;

public interface EligibilityDeterminationRepository extends JpaRepository<EligibilityDetermination, Integer>{

	boolean existsByCaseNo(Long caseNo);

}
