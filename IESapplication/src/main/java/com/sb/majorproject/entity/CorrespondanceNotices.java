package com.sb.majorproject.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity(name = "ies_corrNotices")
public class CorrespondanceNotices {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer notice_id;
	private Long caseNo;
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
