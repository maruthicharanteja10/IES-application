package com.sb.majorproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sb.majorproject.entity.IncomeDetails;
import com.sb.majorproject.entity.KidsDetails;

public interface KidsDetailsRepository extends JpaRepository<KidsDetails, Integer>{

	

}
