package com.sb.majorproject.entity;

import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name = "ies_applicationDetails")
public class ApplicationDetails {
	@Id
	@Column(unique = true, nullable = false)
	private Long caseNo;
	private String fullName;

	@Email(message = "Invalid email format")
	@NotNull(message = "Email is Required")
	private String email;

	@Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be exactly 10 digits")
	private String mobileno;

	private String gender;
	private LocalDate dob;

	@Pattern(regexp = "^[0-9]{12}$", message = "Aadhaar number must be exactly 12 numeric digits")
	private String aadhaarNumber;
	private String stateName;
	private String cityName;
	private String address;
	private LocalDate createdDate;
}
