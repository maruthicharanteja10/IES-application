package com.sb.majorproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sb.majorproject.entity.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long>{

	UserDetails findByEmail(String email);



}
