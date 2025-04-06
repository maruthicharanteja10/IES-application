package com.sb.majorproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sb.majorproject.entity.CorrespondanceNotices;

public interface CorrespondanceRepository extends JpaRepository<CorrespondanceNotices, Integer>{

	boolean existsByCaseNo(Long caseNo);

}
