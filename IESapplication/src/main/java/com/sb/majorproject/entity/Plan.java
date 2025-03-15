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
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UserDetails user;
}
