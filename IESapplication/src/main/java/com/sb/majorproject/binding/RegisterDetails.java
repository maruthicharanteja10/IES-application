package com.sb.majorproject.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class RegisterDetails {

	private String fullName;
	private String email;
	private String mobileno;
	private String aadhaarNumber;
	private String gender;
	private LocalDate dob;
}
