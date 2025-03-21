package com.sb.majorproject.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.majorproject.entity.PlanSelection;
import com.sb.majorproject.repository.PlanSelectionRepository;
import com.sb.majorproject.service.PlanSelectionService;

@Service
public class PlanSelectionServiceImpl implements PlanSelectionService {

	@Autowired
	private PlanSelectionRepository planSelectionRepository;

	@Override
	public boolean savePlanSelection(PlanSelection selection) {
		planSelectionRepository.save(selection);
		return true;
	}

	
}
