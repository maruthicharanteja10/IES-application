package com.sb.majorproject.service;

import java.util.List;

import com.sb.majorproject.entity.Plan;

public interface PlanService {

	boolean createSchemePlans(Plan plan);

	List<Plan> getAllPlans();

	void plantoggleStatus(Integer planId);

	Plan editPlanByID(Integer planId);

	void updatePlans(Plan existingplan);

}
