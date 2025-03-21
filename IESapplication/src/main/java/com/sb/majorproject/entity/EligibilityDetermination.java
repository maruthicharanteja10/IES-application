package com.sb.majorproject.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EligibilityDetermination {
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
