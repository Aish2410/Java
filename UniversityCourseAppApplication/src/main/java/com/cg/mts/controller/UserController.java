package com.cg.mts.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.dto.ApplicantDto;
import com.cg.mts.dto.ForgotPasswordDto;
import com.cg.mts.dto.LoginDto;
import com.cg.mts.dto.UserDto;
import com.cg.mts.entities.AdmissionCommiteeMember;
import com.cg.mts.entities.Applicant;
import com.cg.mts.entities.Role;
import com.cg.mts.entities.RoleName;
import com.cg.mts.entities.UniversityStraffMember;
import com.cg.mts.entities.User;
import com.cg.mts.exception.CustomException;
import com.cg.mts.exception.UserRegistrationException;
import com.cg.mts.jwtauthentication.security.jwt.JwtProvider;
import com.cg.mts.repository.ILoginRepository;
import com.cg.mts.repository.RoleRepository;
import com.cg.mts.service.IAdmissionCommiteeMemberService;
import com.cg.mts.service.IAdmissionService;
import com.cg.mts.service.IApplicantService;
import com.cg.mts.service.ICourseService;
import com.cg.mts.service.ILoginService;
import com.cg.mts.service.IUniversityStaffService;
import com.cg.mts.util.Response;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {

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
	AuthenticationManager authenticationManager;

	@Autowired
	ILoginRepository iLoginRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	IAdmissionCommiteeMemberService iAdmissionCommiteeMemberService;

	private final static Logger logger = LogManager.getLogger(UserController.class);

	@PostMapping("/register")
	public ResponseEntity<Response> register(@RequestBody @Valid UserDto userDto) throws ParseException {
	 Response response = new Response();
		logger.info("Starrted register method");
		logger.info("Request Object email=>" + userDto.getUsername());
		logger.info("Request Object role=>" + userDto.getRole());
		
		if (iLoginRepository.existsByUsername(userDto.getUsername())) {
			response.setResponse("Username is already taken!");
			response.setStatus(Response.FAILURE);
			response.setStatusCode(HttpStatus.BAD_REQUEST);
			return new ResponseEntity<Response>(response, response.getStatusCode());
		}
		Set<String> strRoles = userDto.getRole();
		Set<Role> roles = new HashSet<>();

		strRoles.forEach(role -> {
			switch (role) {
			case "ACM":
				AdmissionCommiteeMember addCommiteeMember = iAdmissionCommiteeMemberService.addCommiteeMember(userDto);
				if (addCommiteeMember == null) {
					throw new UserRegistrationException("User Registration fail ");
				}
				break;
			case "STAFF":
				UniversityStraffMember universityStraffMember = iUniversityStaffService.addStaff(userDto);
				if (universityStraffMember == null) {
					throw new UserRegistrationException("User Registration fail ");
				}
				break;
				
			case "APPLICANT":
				User addApplicantUser = iApplicantService.addApplicantUser(userDto);
				if (addApplicantUser == null) {
					throw new UserRegistrationException("User Registration fail ");
				}
				break;
			default:
				throw new RuntimeException("ROLE SHOULD BE ACM, APPLICANT, STAFF");
			}
		});
		response.setResponse("SUCCESSFULLY REGISTERED");
		response.setStatus(Response.SUCCESS);
		response.setStatusCode(HttpStatus.OK);
		return new ResponseEntity<Response>(response, response.getStatusCode());
	}

	@PostMapping("/login")
	public ResponseEntity<Response> login(@RequestBody LoginDto LoginDto) throws ParseException {
		logger.info("Starrted login method");

		logger.info("Request Object email=>" + LoginDto.getUsername());
		logger.info("Request Object role=>" + LoginDto.getRole());
		Response response = new Response();
		try {

			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(LoginDto.getUsername(), LoginDto.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtProvider.generateJwtToken(authentication);
			response.setResponse("Bearer "+jwt);
		} catch (Exception e) {
			response.setStatus(Response.FAILURE);
			response.setStatusCode(HttpStatus.NOT_FOUND);
			response.setResponse("LOGIN FAIL");
			return new ResponseEntity<Response>(response, response.getStatusCode());
		}
		
		response.setStatus(Response.SUCCESS);
		response.setStatusCode(HttpStatus.OK);
		return new ResponseEntity<Response>(response, response.getStatusCode());
	}

	@PostMapping(value = "/forgot/password")
	public ResponseEntity<Response> forgotPassword(@RequestBody @Valid ForgotPasswordDto forgotPasswordDto)
			throws ParseException {
		Response response = new Response();
		logger.info("Starrted forgotPassword method");

		logger.info("Request Object email=>" + forgotPasswordDto.getUsername());
		logger.info("Request Object role=>" + forgotPasswordDto.getRole());

		boolean boolean1 = iLoginService.paaswordSet(forgotPasswordDto);
		if (boolean1 == false) {
			response.setStatus(Response.FAILURE);
			response.setStatusCode(HttpStatus.NOT_FOUND);
			response.setResponse("FAIL TO PASSWORD SET");
		} else {
			response.setStatus(Response.SUCCESS);
			response.setStatusCode(HttpStatus.OK);
			response.setResponse("SUCCESSFULLY PASSWORD SET");
		}

		logger.info("End forgotPassword method");
		logger.info("Response Object=>" + response);
		return new ResponseEntity<Response>(response, response.getStatusCode());
	}
	
	@PostMapping("/add/applicant")
	public ResponseEntity<Response> addApplicant(@RequestBody @Valid ApplicantDto applicantDto) {
		logger.info("Started addAdmission method");
		logger.info("Request Object=>" + applicantDto);
		Response response = new Response();
		Applicant addApplicant = iApplicantService.addApplicant(applicantDto);
		if (addApplicant == null) {
			throw new CustomException("FAIL TO ADD");
		}
		response.setResponse(addApplicant);
		response.setStatus(Response.SUCCESS);
		response.setStatusCode(HttpStatus.OK);
		logger.info("End addApplicant method");
		logger.info("Response Object=>" + response);
		return new ResponseEntity<Response>(response, response.getStatusCode());
	}
	

}