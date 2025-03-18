package com.sb.majorproject.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.majorproject.entity.Plan;
import com.sb.majorproject.repository.PlanRepository;
import com.sb.majorproject.service.PlanService;

@Service
public class PlanServiceImpl implements PlanService {
	@Autowired
	private PlanRepository planRepository;

	@Override
	public boolean createSchemePlans(Plan plan) {
		
		plan.setActiveStatus("N");
		planRepository.save(plan);
		return true;
	}

	@Override
	public List<Plan> getAllPlans() {
		
		return planRepository.findAll();
	}

}
