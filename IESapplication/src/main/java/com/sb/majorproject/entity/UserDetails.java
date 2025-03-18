package com.sb.majorproject.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "IES_Users")
public class UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@NotBlank(message = "FullName is Required")
	private String fullName;

	@Email(message = "Invalid email format")
	@NotNull(message = "Email is Required")
	private String email;

	private String password;

	@Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be exactly 10 digits")
	private String mobileno;

	private String gender;
	private LocalDate dob;

	@Pattern(regexp = "^[0-9]{12}$", message = "Aadhaar number must be exactly 12 numeric digits")
	private String aadhaarNumber;

	private String AcctStatus;
	private String ActiveStatus;

	private String role;

}
