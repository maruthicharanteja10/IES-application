package com.sb.majorproject.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BenifitInsuance {
	private Integer benefit_id;
	private String case_num;
	private String BenftMonthYear;
	private Double benifitAmt;
	private LocalDate transactionDate;
	private String transactionStatus;

}
