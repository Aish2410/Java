package com.cg.mts.serviceImpl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.mts.dto.ApplicantDto;
import com.cg.mts.dto.CancelAdmissionDto;
import com.cg.mts.dto.ForgotPasswordDto;
import com.cg.mts.dto.UserDto;
import com.cg.mts.entities.Admission;
import com.cg.mts.entities.Applicant;
import com.cg.mts.entities.Course;
import com.cg.mts.entities.Role;
import com.cg.mts.entities.RoleName;
import com.cg.mts.entities.User;
import com.cg.mts.enums.AdmissionStatusEnum;
import com.cg.mts.exception.ApplicanNotFoundException;
import com.cg.mts.exception.CourseNotFoundException;
import com.cg.mts.exception.StaffNotFoundException;
import com.cg.mts.exception.UserAlreadyExistException;
import com.cg.mts.repository.IAdmissionRepository;
import com.cg.mts.repository.IApplicantRepository;
import com.cg.mts.repository.ICourseRepository;
import com.cg.mts.repository.ILoginRepository;
import com.cg.mts.repository.RoleRepository;
import com.cg.mts.service.IAdmissionService;
import com.cg.mts.service.IApplicantService;
import com.cg.mts.service.ICourseService;

@Service
@Transactional
public class IApplicantServiceImpl implements IApplicantService {
	@Autowired
	private IApplicantRepository iApplicantRepository;

	@Autowired
	ILoginRepository iLoginRepository;

	@Autowired
	ICourseRepository iCourseRepository;

	@Autowired
	IAdmissionRepository iAdmissionRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Override
	public void delete(int applicantId) {
		iApplicantRepository.deleteById(applicantId);

	}

	@Override
	public Applicant viewApplicant(int applicantid) {

		Applicant Applicant = null;
		try {
			Applicant = iApplicantRepository.findById(applicantid).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return Applicant;
	}

	@Override
	public List<Applicant> viewAllApplicantByStatus(String admissionStatus) {
		List<Applicant> viewAllApplicantlist = iApplicantRepository.viewAllApplicantByStatus(admissionStatus);
		return viewAllApplicantlist;
	}

	@Override
	public List<Applicant> showAllAdmissionByCourseId(Integer courseId) {
		List<Applicant> viewAllApplicantlist = iApplicantRepository.showAllAdmissionByCourseId(courseId);
		return viewAllApplicantlist;
	}

	@Override
	public List<Applicant> showAllAdmissionByDate(LocalDate localDate) {
		List<Applicant> viewAllApplicantlist = iApplicantRepository.showAllAdmissionByDate(localDate);
		return viewAllApplicantlist;
	}

	@Override
	public List<Applicant> viewAllApplicant() {
		List<Applicant> viewAllApplicantlist = iApplicantRepository.findAll();
		return viewAllApplicantlist;
	}

	@Override
	public User addApplicantUser(UserDto userDto) {
		User user = new User();
		if (iLoginRepository.existsByUsername(userDto.getUsername())) {
			throw new UserAlreadyExistException("USER ALREADY EXIST");
		}
		BeanUtils.copyProperties(userDto, user);

		user.setUsername(userDto.getUsername());
		user.setPassword(encoder.encode(userDto.getPassword()));
		Set<Role> roles = new HashSet<>();

		Role role = roleRepository.findByName(RoleName.APPLICANT)
				.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
		roles.add(role);
		user.setRoles(roles);
		user.setActive(true);
		iLoginRepository.save(user);
		return user;

	}

	@Override
	public User updateApplicantUser(ForgotPasswordDto forgotPasswordDto) {
		User user = iLoginRepository.findByUsername(forgotPasswordDto.getUsername())
				.orElseThrow(() -> new StaffNotFoundException("STAFF NOT FOUND"));
		user.setUsername(forgotPasswordDto.getUsername());
		user.setPassword(encoder.encode(forgotPasswordDto.getNewPassword()));
		Set<Role> roles = new HashSet<>();

		Role role = roleRepository.findByName(RoleName.APPLICANT)
				.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
		roles.add(role);
		user.setRoles(roles);
		iLoginRepository.save(user);
		return user;
	}

	@Override
	public Applicant addApplicant(ApplicantDto applicantDto) {

		Applicant applicant = null;

		applicant = iApplicantRepository.findByEmail(applicantDto.getEmail());
		if (applicant == null) {
			applicant = new Applicant();
		}

		Admission admission = new Admission();
		BeanUtils.copyProperties(applicantDto, applicant);
		BeanUtils.copyProperties(applicantDto, admission);

		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		admission.setAdmissionDate(localDate);
		admission.setAdmissionStatus(AdmissionStatusEnum.APPLIED.name());

		Course course1 = null;
		try {
			course1 = iCourseRepository.findById(applicantDto.getCourseId()).get();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (course1 != null) {
			admission.setCourse(course1);
			iAdmissionRepository.save(admission);
			applicant.setAdmission(admission);

			Applicant applicantNew = iApplicantRepository.save(applicant);
			return applicantNew;
		} else {
			throw new CourseNotFoundException("COURSE NOT FOUND");
		}
	}

	@Override
	public Applicant update(ApplicantDto applicantDto) {

		Applicant applicant = iApplicantRepository.findByEmail(applicantDto.getEmail());
		if (applicant == null) {
			throw new ApplicanNotFoundException("APPLICANT NOT FOUND");
		}
		BeanUtils.copyProperties(applicantDto, applicant);
		Admission admission = applicant.getAdmission();
		BeanUtils.copyProperties(applicantDto, admission);

		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		admission.setAdmissionDate(localDate);
		admission.setAdmissionStatus(AdmissionStatusEnum.APPLIED.name());
		Course course1 = null;
		try {
			course1 = iCourseRepository.findById(applicantDto.getCourseId()).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (course1 == null) {
			throw new CourseNotFoundException("COURSE NOT FOUND");
		}

		admission.setCourse(course1);
		iAdmissionRepository.save(admission);
		applicant.setAdmission(admission);
		return applicant;
	}

	@Override
	public Applicant cancelAdmission(@Valid CancelAdmissionDto cancelAdmissionDto) {
		Applicant applicant = iApplicantRepository.findByEmail(cancelAdmissionDto.getEmail());
		if (applicant == null) {
			throw new ApplicanNotFoundException("APPLICANT NOT FOUND");
		}

		Admission admission = applicant.getAdmission();
		admission.setAdmissionStatus(AdmissionStatusEnum.REJECTED.name());
		Course viewCourse = admission.getCourse();
		admission.setCourse(viewCourse);
		iAdmissionRepository.save(admission);
		applicant.setAdmission(admission);
		Applicant ApplicantUpdate = iApplicantRepository.save(applicant);
		return ApplicantUpdate;
	}

	@Override
	public Applicant viewApplicantByEmail(String email) {
		Applicant Applicant = null;
		try {
			Applicant = iApplicantRepository.findByEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return Applicant;
	}

}
