package com.cg.mts.service;

import javax.validation.Valid;

import com.cg.mts.dto.ForgotPasswordDto;
import com.cg.mts.dto.LoginDto;

public interface ILoginService {
	
	public boolean loginAsApplicant(String emailId, String pass);

	public boolean loginAsAdmissionCommiteeMember(String emailId, String pass);

	public boolean loginAsUniversityStaffMember(String emailId, String pass);

	public boolean paaswordSet(@Valid ForgotPasswordDto forgotPasswordDto);

	public boolean login(LoginDto loginDto);

}
