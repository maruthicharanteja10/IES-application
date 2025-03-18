package com.sb.majorproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sb.majorproject.entity.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

	public UserDetails findByEmail(String email);

	public UserDetails findByEmailAndPassword(String email, String pswd);

	

}
