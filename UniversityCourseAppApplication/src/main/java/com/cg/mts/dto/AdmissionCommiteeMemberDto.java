package com.cg.mts.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdmissionCommiteeMemberDto {

	public int adminId;

	public String adminName;

	public String adminContact;

	@NotEmpty(message = "Email is required")
	@Email(message = "Email should be valid")
	public String email;
	 
	@NotNull
	@Size(min = 4, message = "password  must have at least 4 characters")
	public String password;
	
	public Boolean active=true;

}
