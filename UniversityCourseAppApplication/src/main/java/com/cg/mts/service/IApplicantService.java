package com.cg.mts.service;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import com.cg.mts.dto.ApplicantDto;
import com.cg.mts.dto.CancelAdmissionDto;
import com.cg.mts.dto.ForgotPasswordDto;
import com.cg.mts.dto.UserDto;
import com.cg.mts.entities.Applicant;
import com.cg.mts.entities.User;

public interface IApplicantService {

	public void delete(int applicantId);

	public Applicant viewApplicant(int applicantid);

	public List<Applicant> viewAllApplicantByStatus(String admissionStatus);

	public List<Applicant> showAllAdmissionByCourseId(Integer courseId);

	public List<Applicant> showAllAdmissionByDate(LocalDate localDate);

	public List<Applicant> viewAllApplicant();

	public User addApplicantUser(UserDto userDto);

	public User updateApplicantUser(ForgotPasswordDto forgotPasswordDto);

	public Applicant addApplicant(ApplicantDto applicantDto);

	public Applicant update(ApplicantDto applicantDto);

	public Applicant cancelAdmission(@Valid CancelAdmissionDto cancelAdmissionDto);

	public Applicant viewApplicantByEmail(String email);

}
