package com.sb.majorproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sb.majorproject.entity.PlanSelection;

public interface PlanSelectionRepository extends JpaRepository<PlanSelection, Long> {

	boolean existsByCaseNo(Long caseNo);

	@Query("SELECT p FROM PlanSelection p WHERE p.caseNo = :caseNo")
	Optional<PlanSelection> findByCaseNo(@Param("caseNo") Long caseNo);

}
