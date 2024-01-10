package com.cg.mts.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantDto {

	public int applicantId;
	
	@NotEmpty(message = "applicantName is required")
	public String applicantName;

	@Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message = "Mobile number is invalid")
	@Column(name = "applicant_mobile_number", nullable = false)
	public String applicantMobileNumber;

	@NotEmpty(message = "applicantDegree is required")
	public String applicantDegree;
	
	@NotNull(message = "applicantGraduationPercent is required")
	public int applicantGraduationPercent;

	public String admissionStatus;
	
	@NotEmpty(message = "Email is required")
	@Email(message = "Email should be valid")
	public String email;
	
	public int courseId;
	
	public Boolean active=true;


}
