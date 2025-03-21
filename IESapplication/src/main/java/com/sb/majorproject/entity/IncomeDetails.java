package com.sb.majorproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="dc_incomeDetails")
public class IncomeDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer income_id;
	private Double salary;
	private Double rentIncome;
	private Double propertyIncome;
	@OneToOne
	@JoinColumn(name="case_number",referencedColumnName = "caseNo",nullable =false)
	private ApplicationDetails applicationDetails;
	

}
