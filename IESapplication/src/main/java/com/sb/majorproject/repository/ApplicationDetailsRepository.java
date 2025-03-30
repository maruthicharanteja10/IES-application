package com.sb.majorproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sb.majorproject.entity.ApplicationDetails;

public interface ApplicationDetailsRepository extends JpaRepository<ApplicationDetails, Long> {
	boolean existsByEmail(String email);

	@Query("SELECT a FROM ApplicationDetails a WHERE a.caseNo = :caseNo")
	Optional<ApplicationDetails> findByCaseNo(@Param("caseNo") Long caseNo);

}
