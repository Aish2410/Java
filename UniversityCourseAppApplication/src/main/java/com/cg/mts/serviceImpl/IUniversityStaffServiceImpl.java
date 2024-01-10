package com.cg.mts.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.mts.controller.UserController;
import com.cg.mts.dto.ForgotPasswordDto;
import com.cg.mts.dto.UniversityStraffMemberDto;
import com.cg.mts.dto.UserDto;
import com.cg.mts.entities.Course;
import com.cg.mts.entities.Role;
import com.cg.mts.entities.RoleName;
import com.cg.mts.entities.UniversityStraffMember;
import com.cg.mts.entities.User;
import com.cg.mts.exception.ApplicanNotFoundException;
import com.cg.mts.exception.StaffNotFoundException;
import com.cg.mts.exception.UserAlreadyExistException;
import com.cg.mts.repository.ICourseRepository;
import com.cg.mts.repository.ILoginRepository;
import com.cg.mts.repository.IUniversityStaffRepository;
import com.cg.mts.repository.RoleRepository;
import com.cg.mts.service.IUniversityStaffService;

@Service
@Transactional
public class IUniversityStaffServiceImpl implements IUniversityStaffService {

	@Autowired
	ICourseRepository iCourseRepository;

	@Autowired
	IUniversityStaffRepository iUniversityStaffRepository;
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	ILoginRepository iLoginRepository;
	private final static Logger logger = LogManager.getLogger(IUniversityStaffServiceImpl.class);

	@Override
	public UniversityStraffMember addStaff(UserDto userDto) {

		if (iLoginRepository.existsByUsername(userDto.getUsername())) {
			throw new UserAlreadyExistException("USER ALREADY EXIST");
		}

		UniversityStraffMember universityStraffMember = new UniversityStraffMember();
		universityStraffMember.setEmail(userDto.getUsername());
		universityStraffMember.setPassword(encoder.encode(userDto.getPassword()));
		universityStraffMember.setRole(RoleName.STAFF.name());

		UniversityStraffMember saveUniversityStraffMember = iUniversityStaffRepository.save(universityStraffMember);
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setPassword(encoder.encode(userDto.getPassword()));
		Set<Role> roles = new HashSet<>();

		Role role = roleRepository.findByName(RoleName.STAFF)
				.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
		roles.add(role);
		user.setRoles(roles);
		iLoginRepository.save(user);
		return saveUniversityStraffMember;
	}

	@Override
	public UniversityStraffMember updateStaff(ForgotPasswordDto userDto) {
		UniversityStraffMember universityStraffMember = iUniversityStaffRepository.findByEmail(userDto.getUsername());

		if (universityStraffMember == null) {
			throw new StaffNotFoundException("STAFF NOT FOUND");
		}
		universityStraffMember.setEmail(userDto.getUsername());
		universityStraffMember.setPassword(userDto.getNewPassword());
		UniversityStraffMember universityStraffMember1 = iUniversityStaffRepository.save(universityStraffMember);
		User user = iLoginRepository.findByUsername(userDto.getUsername())
				.orElseThrow(() -> new StaffNotFoundException("STAFF NOT FOUND"));
		user.setUsername(userDto.getUsername());
		user.setPassword(encoder.encode(userDto.getNewPassword()));
		Set<Role> roles = new HashSet<>();

		Role role = roleRepository.findByName(RoleName.STAFF)
				.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
		roles.add(role);
		user.setRoles(roles);
		iLoginRepository.save(user);
		return universityStraffMember1;
	}

	@Override
	public void removeStaff(int id) {
		iUniversityStaffRepository.deleteById(id);
	}

	@Override
	public UniversityStraffMember viewStaff(int id) {
		UniversityStraffMember universityStraffMember = null;
		try {
			universityStraffMember = iUniversityStaffRepository.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return universityStraffMember;
	}

	@Override
	public List<UniversityStraffMember> viweAllStaff() {
		List<UniversityStraffMember> findAll = iUniversityStaffRepository.findAll();
		return findAll;
	}

	@Override
	public Course addCourse(Course course) {
		Course course1 = iCourseRepository.save(course);
		return course1;
	}

	@Override
	public Course removeCourse(int courseId) {
		iCourseRepository.deleteById(courseId);
		return null;
	}

	@Override
	public Course updateCourse(Course course) {
		Course course1 = iCourseRepository.save(course);
		return course1;
	}

	@Override
	public UniversityStraffMember updateStaff(@Valid UniversityStraffMemberDto universityStraffMemberDto) {
		UniversityStraffMember universityStraffMember = iUniversityStaffRepository
				.findByEmail(universityStraffMemberDto.getEmail());

		if (universityStraffMember == null) {
			throw new StaffNotFoundException("STAFF NOT FOUND");
		}
		universityStraffMember.setEmail(universityStraffMemberDto.getEmail());
		universityStraffMember.setRole(universityStraffMemberDto.getRole());
		universityStraffMember.setRole(universityStraffMemberDto.getRole());
		universityStraffMember.setPassword(encoder.encode(universityStraffMemberDto.getPassword()));
		UniversityStraffMember universityStraffMember1 = iUniversityStaffRepository.save(universityStraffMember);
		User user = iLoginRepository.findByUsername(universityStraffMemberDto.getEmail())
				.orElseThrow(() -> new StaffNotFoundException("STAFF NOT FOUND"));
		user.setUsername(universityStraffMemberDto.getEmail());
		user.setPassword(encoder.encode(universityStraffMemberDto.getPassword()));
		Set<Role> roles = new HashSet<>();

		Role role = roleRepository.findByName(RoleName.STAFF)
				.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
		roles.add(role);
		user.setRoles(roles);
		iLoginRepository.save(user);
		return universityStraffMember1;

	}

	@Override
	public UniversityStraffMember viewUniversityStraffMemberByEmail(String email) {
		UniversityStraffMember universityStraffMember = null;
		try {
			universityStraffMember = iUniversityStaffRepository.findByEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return universityStraffMember;
	}
}
