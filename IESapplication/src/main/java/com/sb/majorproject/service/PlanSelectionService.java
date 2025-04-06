package com.sb.majorproject.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sb.majorproject.entity.Plan;
import com.sb.majorproject.entity.PlanSelection;

public interface PlanSelectionService {

	boolean savePlanSelection(PlanSelection selection);

	List<PlanSelection> getAllPlanSelctions();

	List<String> getAllPlanNames();

	

}
