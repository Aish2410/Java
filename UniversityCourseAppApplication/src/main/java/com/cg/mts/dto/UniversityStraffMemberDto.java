package com.cg.mts.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UniversityStraffMemberDto {

	int staffId;

	@NotEmpty(message = "Email is required")
	@Email(message = "Email should be valid")
	private String email;

	@NotNull
	@Size(min = 4, message = "password  must have at least 4 characters")
	String password;
	
	@NotEmpty(message = "Email is required")
	String role;
	
	public Boolean active=true;


}
