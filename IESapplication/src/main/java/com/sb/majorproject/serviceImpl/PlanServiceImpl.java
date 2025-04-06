package com.sb.majorproject.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
		plan.setActiveStatus("Y");
		planRepository.save(plan);
		return true;
	}

	@Override
	public List<Plan> getAllPlans() {
		return planRepository.findAll();
	}

	@Override
	public void plantoggleStatus(Integer planId) {
		Optional<Plan> planstat = planRepository.findById(planId);
		if (planstat.isPresent()) {
			Plan plans = planstat.get();
			plans.setActiveStatus(plans.getActiveStatus().equals("Y") ? "N" : "Y");
			planRepository.save(plans);
		}
	}

	@Override
	public Plan editPlanByID(Integer planId) {
		return planRepository.findById(planId).get();
	}

	@Override
	public void updatePlans(Plan existingplan) {
		planRepository.save(existingplan);
	}

	@Override
	public Page<Plan> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return this.planRepository.findAll(pageable);
	}

}
