package com.cg.mts.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.mts.dto.AdmissionCommiteeMemberDto;
import com.cg.mts.dto.ForgotPasswordDto;
import com.cg.mts.dto.UserDto;
import com.cg.mts.entities.Admission;
import com.cg.mts.entities.AdmissionCommiteeMember;
import com.cg.mts.entities.Role;
import com.cg.mts.entities.RoleName;
import com.cg.mts.entities.UniversityStraffMember;
import com.cg.mts.entities.User;
import com.cg.mts.exception.StaffNotFoundException;
import com.cg.mts.exception.UserAlreadyExistException;
import com.cg.mts.repository.IAdmissionCommiteeMemberRepository;
import com.cg.mts.repository.ICourseRepository;
import com.cg.mts.repository.ILoginRepository;
import com.cg.mts.repository.RoleRepository;
import com.cg.mts.service.IAdmissionCommiteeMemberService;
import com.cg.mts.util.Response;

@Service
@Transactional
public class IAdmissionCommiteeMemberServiceImpl implements IAdmissionCommiteeMemberService {

	@Autowired
	IAdmissionCommiteeMemberRepository iAdmissionCommiteeMemberRepository;
	@Autowired
	ILoginRepository iLoginRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Override
	public AdmissionCommiteeMember addCommiteeMember(UserDto userDto) {
		AdmissionCommiteeMember admissionCommiteeMember = new AdmissionCommiteeMember();

		if (iLoginRepository.existsByUsername(userDto.getUsername())) {
			throw new UserAlreadyExistException("USER ALREADY EXIST");
		}
		admissionCommiteeMember.setAdminContact(userDto.getMobileNumber());
		admissionCommiteeMember.setAdminName(userDto.getName());
		admissionCommiteeMember.setEmail(userDto.getUsername());

		AdmissionCommiteeMember saveAdmissionCommiteeMember = iAdmissionCommiteeMemberRepository
				.save(admissionCommiteeMember);
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		user.setUsername(userDto.getUsername());
		user.setPassword(encoder.encode(userDto.getPassword()));
		Set<Role> roles = new HashSet<>();

		Role role = roleRepository.findByName(RoleName.ACM)
				.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
		roles.add(role);
		user.setRoles(roles);
		user.setMobileNumber(userDto.getMobileNumber());
		user.setActive(true);
		iLoginRepository.save(user);
		return saveAdmissionCommiteeMember;
	}

	@Override
	public AdmissionCommiteeMember updateCommiteeMember(UserDto userDto) {
		AdmissionCommiteeMember admissionCommiteeMember = iAdmissionCommiteeMemberRepository
				.findByEmail(userDto.getUsername());

		if (admissionCommiteeMember == null) {
			throw new StaffNotFoundException("STAFF NOT FOUND");
		}
		admissionCommiteeMember.setAdminContact(userDto.getMobileNumber());
		admissionCommiteeMember.setAdminName(userDto.getName());
		admissionCommiteeMember.setEmail(userDto.getUsername());

		AdmissionCommiteeMember admissionCommiteeMember1 = iAdmissionCommiteeMemberRepository
				.save(admissionCommiteeMember);
		User user = iLoginRepository.findByUsername(userDto.getUsername())
				.orElseThrow(() -> new StaffNotFoundException("STAFF NOT FOUND"));
		user.setUsername(userDto.getUsername());
		user.setPassword(encoder.encode(userDto.getPassword()));
		user.setMobileNumber(userDto.getMobileNumber());
		iLoginRepository.save(user);
		return admissionCommiteeMember1;
	}

	@Override
	public AdmissionCommiteeMember viewCommiteeMember(int id) {
		AdmissionCommiteeMember admissionCommiteeMember1 = null;
		try {
			admissionCommiteeMember1 = iAdmissionCommiteeMemberRepository.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return admissionCommiteeMember1;
	}

	@Override
	public void removeCommiteeMember(int id) {
		iAdmissionCommiteeMemberRepository.deleteById(id);
	}

	@Override
	public List<AdmissionCommiteeMember> viewAllCommiteeMember() {
		List<AdmissionCommiteeMember> findAll = iAdmissionCommiteeMemberRepository.findAll();
		return findAll;
	}

	@Override
	public Admission provideAdmissionResult(Admission admission) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdmissionCommiteeMember updateCommiteeMember(ForgotPasswordDto forgotPasswordDto) {
		AdmissionCommiteeMember admissionCommiteeMember = iAdmissionCommiteeMemberRepository
				.findByEmail(forgotPasswordDto.getUsername());

		if (admissionCommiteeMember == null) {
			throw new StaffNotFoundException("STAFF NOT FOUND");
		}
		admissionCommiteeMember.setEmail(forgotPasswordDto.getUsername());

		AdmissionCommiteeMember admissionCommiteeMember1 = iAdmissionCommiteeMemberRepository
				.save(admissionCommiteeMember);
		User user = iLoginRepository.findByUsername(forgotPasswordDto.getUsername())
				.orElseThrow(() -> new StaffNotFoundException("STAFF NOT FOUND"));
		user.setUsername(forgotPasswordDto.getUsername());
		user.setPassword(encoder.encode(forgotPasswordDto.getNewPassword()));
		Set<Role> roles = new HashSet<>();

		Role role = roleRepository.findByName(RoleName.ACM)
				.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
		roles.add(role);
		user.setRoles(roles);
		iLoginRepository.save(user);
		return admissionCommiteeMember1;
	}

	@Override
	public AdmissionCommiteeMember addCommiteeMember(@Valid AdmissionCommiteeMemberDto admissionCommiteeMemberDto) {
		AdmissionCommiteeMember AdmissionCommiteeMember1 = iAdmissionCommiteeMemberRepository
				.findByEmail(admissionCommiteeMemberDto.getEmail());

		if (AdmissionCommiteeMember1 != null) {
			throw new UserAlreadyExistException("User Already Exist ");
		}
		AdmissionCommiteeMember admissionCommiteeMember = new AdmissionCommiteeMember();
		admissionCommiteeMember.setAdminContact(admissionCommiteeMemberDto.getAdminContact());
		admissionCommiteeMember.setAdminName(admissionCommiteeMemberDto.getAdminName());
		admissionCommiteeMember.setEmail(admissionCommiteeMemberDto.getEmail());

		AdmissionCommiteeMember saveAdmissionCommiteeMember = iAdmissionCommiteeMemberRepository
				.save(admissionCommiteeMember);
		User user = new User();
		BeanUtils.copyProperties(admissionCommiteeMemberDto, user);
		user.setUsername(admissionCommiteeMemberDto.getEmail());
		user.setPassword(admissionCommiteeMemberDto.getPassword());
		Set<Role> roles = new HashSet<>();

		Role role = roleRepository.findByName(RoleName.ACM)
				.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
		roles.add(role);
		user.setRoles(roles);
		iLoginRepository.save(user);
		return saveAdmissionCommiteeMember;
	}

	@Override
	public AdmissionCommiteeMember updateCommiteeMember(@Valid AdmissionCommiteeMemberDto admissionCommiteeMemberDto) {
		AdmissionCommiteeMember admissionCommiteeMember = iAdmissionCommiteeMemberRepository
				.findByEmail(admissionCommiteeMemberDto.getEmail());

		if (admissionCommiteeMember == null) {
			throw new StaffNotFoundException("STAFF NOT FOUND");
		}
		admissionCommiteeMember.setAdminContact(admissionCommiteeMemberDto.getAdminContact());
		admissionCommiteeMember.setAdminName(admissionCommiteeMemberDto.getAdminName());
		admissionCommiteeMember.setEmail(admissionCommiteeMemberDto.getEmail());

		AdmissionCommiteeMember admissionCommiteeMember1 = iAdmissionCommiteeMemberRepository
				.save(admissionCommiteeMember);
		User user = iLoginRepository.findByUsername(admissionCommiteeMemberDto.getEmail())
				.orElseThrow(() -> new StaffNotFoundException("STAFF NOT FOUND"));
		user.setUsername(admissionCommiteeMemberDto.getEmail());
		user.setPassword(admissionCommiteeMemberDto.getPassword());
		user.setPassword(encoder.encode(admissionCommiteeMemberDto.getPassword()));
		Set<Role> roles = new HashSet<>();

		Role role = roleRepository.findByName(RoleName.STAFF)
				.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
		roles.add(role);
		user.setRoles(roles);
		iLoginRepository.save(user);
		return admissionCommiteeMember1;
	}

	@Override
	public AdmissionCommiteeMember viewAdmissionCommiteeMemberByEmail(String email) {
		AdmissionCommiteeMember admissionCommiteeMember1 = null;
		try {
			admissionCommiteeMember1 = iAdmissionCommiteeMemberRepository.findByEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return admissionCommiteeMember1;
	}

}
