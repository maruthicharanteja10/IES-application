package com.sb.majorproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sb.majorproject.entity.IncomeDetails;
import com.sb.majorproject.entity.KidsDetails;

public interface KidsDetailsRepository extends JpaRepository<KidsDetails, Integer> {

	Optional<KidsDetails> findByApplicationDetails_CaseNo(Long caseNo);

}
