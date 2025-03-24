package com.sb.majorproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sb.majorproject.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan, Integer> {
	@Query("SELECT p.planName from Plan p ")
	List<String> getplanNames();

}
