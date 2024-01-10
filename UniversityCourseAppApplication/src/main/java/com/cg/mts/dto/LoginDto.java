package com.cg.mts.dto;

import java.time.LocalDate;

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
public class LoginDto {
	@NotEmpty(message = "Email is required")
	@Email(message = "Email should be valid")
	public String username;
	 
	@NotNull
	@Size(min = 4, message = "password  must have at least 4 characters")
	public String password;
	
	public String role;
}
