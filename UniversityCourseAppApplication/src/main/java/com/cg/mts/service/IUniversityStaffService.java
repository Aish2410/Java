package com.cg.mts.service;

import java.util.List;

import javax.validation.Valid;

import com.cg.mts.dto.ForgotPasswordDto;
import com.cg.mts.dto.UniversityStraffMemberDto;
import com.cg.mts.dto.UserDto;
import com.cg.mts.entities.Course;
import com.cg.mts.entities.UniversityStraffMember;

public interface IUniversityStaffService {

	public UniversityStraffMember addStaff(@Valid UserDto userDto);

	public void removeStaff(int id);

	public UniversityStraffMember viewStaff(int id);

	public Course addCourse(Course course);

	public Course removeCourse(int courseId);

	public Course updateCourse(Course course);

	public List<UniversityStraffMember> viweAllStaff();

	public UniversityStraffMember updateStaff(ForgotPasswordDto userDto);

	public UniversityStraffMember updateStaff(@Valid UniversityStraffMemberDto universityStraffMemberDto);

	public UniversityStraffMember viewUniversityStraffMemberByEmail(String email);

}
