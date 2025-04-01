package com.sb.majorproject.entity;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "IES_Plans")
public class Plan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer planId;
	private String planName;
	private String planCategory;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private String activeStatus;

	private LocalDate createdDate;
	private LocalDate updatedDate;
	private String createdBy;
	private String updatedBy;

}
