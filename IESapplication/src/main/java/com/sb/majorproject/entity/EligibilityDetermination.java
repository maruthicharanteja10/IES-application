package com.sb.majorproject.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name="ies_elig")
public class EligibilityDetermination {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ed_trace_id;
	private Long caseNo;
	private String planName;
	private String planStatus;
	private LocalDate eligStartDate;
	private LocalDate eligEndDate;
	private Double benifitAmt;
	private String denialReason;
	private LocalDate createdDate;

}
