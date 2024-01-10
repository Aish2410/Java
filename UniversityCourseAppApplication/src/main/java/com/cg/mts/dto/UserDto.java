package com.cg.mts.dto;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDto {

	public Long id;

	@NotNull
	@Size(min = 4, message = "password  must have at least 4 characters")
	public String password;

	@NotEmpty(message = "role is required")
	private Set<String> role;

	@NotBlank
	@Size(min = 3, max = 50)
	private String name;

	@NotEmpty(message = "Email is required")
	@Email(message = "Email should be valid")
	private String username;
	
	@Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message = "Mobile number is invalid")
	public String mobileNumber;

	private boolean active = true;

}
