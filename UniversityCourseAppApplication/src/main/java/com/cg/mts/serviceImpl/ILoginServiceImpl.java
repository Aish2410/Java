package com.cg.mts.serviceImpl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.mts.dto.ForgotPasswordDto;
import com.cg.mts.dto.LoginDto;
import com.cg.mts.entities.AdmissionCommiteeMember;
import com.cg.mts.entities.RoleName;
import com.cg.mts.entities.UniversityStraffMember;
import com.cg.mts.entities.User;
import com.cg.mts.exception.CustomException;
import com.cg.mts.repository.ILoginRepository;
import com.cg.mts.service.IAdmissionCommiteeMemberService;
import com.cg.mts.service.IAdmissionService;
import com.cg.mts.service.IApplicantService;
import com.cg.mts.service.ICourseService;
import com.cg.mts.service.ILoginService;
import com.cg.mts.service.IUniversityStaffService;

@Service
@Transactional
public class ILoginServiceImpl implements ILoginService {

	@Autowired
	ILoginRepository iLoginRepository;
	@Autowired
	IUniversityStaffService iUniversityStaffService;

	@Autowired
	IApplicantService iApplicantService;

	@Autowired
	ICourseService iCourseService;

	@Autowired
	IAdmissionService iAdmissionService;

	@Autowired
	ILoginService iLoginService;

	@Autowired
	IAdmissionCommiteeMemberService iAdmissionCommiteeMemberService;

	@Override
	public boolean loginAsApplicant(String emailId, String pass) {
		boolean verifyApplicant = iLoginRepository.verifyApplicant(emailId, pass);
		return verifyApplicant;
	}

	@Override
	public boolean loginAsAdmissionCommiteeMember(String emailId, String pass) {
		boolean verifyAdmissionCommiteeMember = iLoginRepository.verifyAdmissionCommiteeMember(emailId, pass);
		return verifyAdmissionCommiteeMember;
	}

	@Override
	public boolean loginAsUniversityStaffMember(String emailId, String pass) {
		boolean verifyUniversityStaffMember = iLoginRepository.verifyUniversityStaffMember(emailId, pass);
		return verifyUniversityStaffMember;
	}

	@Override
	public boolean paaswordSet(ForgotPasswordDto forgotPasswordDto) {
		boolean flag = true;

		if (forgotPasswordDto.getRole().equalsIgnoreCase(RoleName.STAFF.name())) {

			boolean loginAsUniversityStaffMember = iLoginService
					.loginAsUniversityStaffMember(forgotPasswordDto.getUsername(), forgotPasswordDto.getOldPassword());

			if (loginAsUniversityStaffMember == true) {

				UniversityStraffMember universityStraffMember = iUniversityStaffService.updateStaff(forgotPasswordDto);

			} else {
				throw new CustomException("USER EMAIL OR PASSWORD IS NOT CORRECT ");
			}

		} else if (forgotPasswordDto.getRole().equalsIgnoreCase(RoleName.APPLICANT.name())) {

			boolean loginAsApplicant = iLoginService.loginAsApplicant(forgotPasswordDto.getUsername(),
					forgotPasswordDto.getOldPassword());

			if (loginAsApplicant == true) {

				User addApplicantUser = iApplicantService.updateApplicantUser(forgotPasswordDto);

			} else {
				throw new CustomException("USER EMAIL OR PASSWORD IS NOT CORRECT ");
			}
		} else if (forgotPasswordDto.getRole().equalsIgnoreCase(RoleName.ACM.name())) {
			boolean loginAsAdmissionCommiteeMember = iLoginService
					.loginAsAdmissionCommiteeMember(forgotPasswordDto.getUsername(), forgotPasswordDto.getOldPassword());

			if (loginAsAdmissionCommiteeMember == true) {

				AdmissionCommiteeMember addCommiteeMember = iAdmissionCommiteeMemberService
						.updateCommiteeMember(forgotPasswordDto);
			} else {
				throw new CustomException("USER EMAIL OR PASSWORD IS NOT CORRECT ");
			}
		}else {
			flag = false;
		}

		return flag;
	}

	@Override
	public boolean login(LoginDto loginDto) {
		boolean flag = true;

		if (loginDto.getRole().equalsIgnoreCase(RoleName.STAFF.name())) {

			boolean loginAsUniversityStaffMember = iLoginService.loginAsUniversityStaffMember(loginDto.getUsername(),
					loginDto.getPassword());

			if (loginAsUniversityStaffMember == false) {
				flag = false;
			}

		} else if (loginDto.getRole().equalsIgnoreCase(RoleName.APPLICANT.name())) {

			boolean loginAsApplicant = iLoginService.loginAsApplicant(loginDto.getUsername(), loginDto.getPassword());

			if (loginAsApplicant == false) {
				flag = false;
			}
		} else if (loginDto.getRole().equalsIgnoreCase(RoleName.ACM.name())) {

			boolean loginAsAdmissionCommiteeMember = iLoginService.loginAsAdmissionCommiteeMember(loginDto.getUsername(),
					loginDto.getPassword());

			if (loginAsAdmissionCommiteeMember == false) {
				flag = false;
			}

		} else {
			flag = false;
		}
		return flag;
	}

}
