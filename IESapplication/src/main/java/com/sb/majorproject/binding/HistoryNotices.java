package com.sb.majorproject.binding;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class HistoryNotices {
	private Long caseNo;
	private Integer ed_trace_id;
	private String planName;
	private String planStatus;
	private LocalDate eligStartDate;
	private LocalDate eligEndDate;
	private String benifitAmt;
	private String denialReason;
	private String notice_pdf;
	private LocalDate printDate;
	private String noticeStatus;
	private LocalDate createdDate;

}
