package com.sb.majorproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "dc_planselction")
public class PlanSelection {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer selectionId;
	private Long caseNo;
	private String planName;
}
