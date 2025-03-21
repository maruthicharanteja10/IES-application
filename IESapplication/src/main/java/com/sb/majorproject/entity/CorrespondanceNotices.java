package com.sb.majorproject.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CorrespondanceNotices {
	private Integer notice_id;
	private Long caseNo;
	private Integer eligTraceId;

	private String notice_pdf;

	private LocalDate printDate;
	private String noticeStatus;
	private LocalDate createdDate;

}
