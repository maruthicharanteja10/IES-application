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
@Table(name = "dc_educationDetails")
public class EducationDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer education_id;
	private String highestDegree;
	private Integer graduationYear;
	private String university;
	@OneToOne
	@JoinColumn(name = "case_number",referencedColumnName = "caseNo")
	private ApplicationDetails applicationDetails;
}
