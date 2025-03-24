package com.sb.majorproject.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.majorproject.entity.Plan;
import com.sb.majorproject.entity.PlanSelection;
import com.sb.majorproject.repository.PlanRepository;
import com.sb.majorproject.repository.PlanSelectionRepository;
import com.sb.majorproject.service.PlanSelectionService;

@Service
public class PlanSelectionServiceImpl implements PlanSelectionService {

	@Autowired
	private PlanSelectionRepository planSelectionRepository;
	@Autowired
	private PlanRepository planRepository;

	@Override
	public boolean savePlanSelection(PlanSelection selection) {
		if (selection.getCaseNo() != null && planSelectionRepository.existsByCaseNo(selection.getCaseNo())) {
			return false;
		}
		planSelectionRepository.save(selection);
		return true;
	}

	@Override
	public List<PlanSelection> getAllPlanSelctions() {
		return planSelectionRepository.findAll();
	}

	@Override
	public List<String> getAllPlanNames() {
		return planRepository.getplanNames();
	}

}
