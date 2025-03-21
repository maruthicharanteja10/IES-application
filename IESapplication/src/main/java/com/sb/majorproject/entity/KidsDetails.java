package com.sb.majorproject.entity;

import java.time.LocalDate;

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
@Table(name = "dc_kidsDetails")
public class KidsDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer child_id;
	private String childName;
	private LocalDate dob;
	private Long childAdhaarNo;
	@OneToOne
	@JoinColumn(name="case_number",referencedColumnName = "caseNo",nullable =false)
	private ApplicationDetails applicationDetails;

}
