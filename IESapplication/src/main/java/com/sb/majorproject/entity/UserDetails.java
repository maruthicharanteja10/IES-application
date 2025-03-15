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
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "IES_Users")
public class UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long UserId;
	
	@NotBlank(message = "FullName is Required")
	private String fullName;
	
	@Email(message = "Invalid email format")
	@Column(nullable = false, unique = true, length = 100)
	@NotBlank(message = "Email is Required")
	private String email;
	
	@Column(nullable = false, length = 50)
	@Size(min = 5, max = 50, message = "password must be between 5 to 50")
	@NotBlank(message = "Password is Required")
	private String password;
	
	@Column(nullable = false, length = 10, unique = true)
	@Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be exactly 10 digits")
	@NotBlank(message = "Mobileno is Required")
	private Long mobileno;
	
	private String gender;
	private LocalDate dob;
	
	@Column(nullable = false, length = 10)
	@Size(min = 10, max = 10, message = "AdharNumber must have 10digits")
	@NotBlank(message = "AdharNo is Required")
	private Long AdharNumber;
	
	private String AcctStatus;
	private String ActiveStatus;

	@OneToOne
	@JoinColumn(name = "role_id", unique = true, nullable = false)
	private UserRoles role;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Plan> plans;

}
