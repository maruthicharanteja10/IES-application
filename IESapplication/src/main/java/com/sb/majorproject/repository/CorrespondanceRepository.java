package com.sb.majorproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sb.majorproject.entity.CorrespondanceNotices;

public interface CorrespondanceRepository extends JpaRepository<CorrespondanceNotices, Integer>{


	
	List<CorrespondanceNotices> findByCaseNoAndNoticeStatus(Long caseNo, String string);

	boolean existsByCaseNo(Long caseNo);

}
