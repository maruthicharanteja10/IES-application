package com.sb.majorproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sb.majorproject.entity.ApplicationDetails;

public interface ApplicationDetailsRepository extends JpaRepository<ApplicationDetails, Long> {
	 boolean existsByEmail(String email);
}
