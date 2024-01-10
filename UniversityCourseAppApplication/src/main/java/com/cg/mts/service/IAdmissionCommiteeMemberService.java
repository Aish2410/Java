package com.cg.mts.service;

import java.util.List;

import javax.validation.Valid;

import com.cg.mts.dto.AdmissionCommiteeMemberDto;
import com.cg.mts.dto.ForgotPasswordDto;
import com.cg.mts.dto.UserDto;
import com.cg.mts.entities.Admission;
import com.cg.mts.entities.AdmissionCommiteeMember;

public interface IAdmissionCommiteeMemberService {

	public AdmissionCommiteeMember addCommiteeMember(UserDto userDto);

	public AdmissionCommiteeMember addCommiteeMember(@Valid AdmissionCommiteeMemberDto admissionCommiteeMemberDto);

	public AdmissionCommiteeMember viewCommiteeMember(int id);

	public void removeCommiteeMember(int id);

	public List<AdmissionCommiteeMember> viewAllCommiteeMember();

	public Admission provideAdmissionResult(Admission admission);

	public AdmissionCommiteeMember updateCommiteeMember(@Valid AdmissionCommiteeMemberDto admissionCommiteeMemberDto);

	public AdmissionCommiteeMember updateCommiteeMember(UserDto userDto);

	public AdmissionCommiteeMember updateCommiteeMember(ForgotPasswordDto forgotPasswordDto);

	public AdmissionCommiteeMember viewAdmissionCommiteeMemberByEmail(String email);

}
