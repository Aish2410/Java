package com.cg.mts.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.dto.AdmissionCommiteeMemberDto;
import com.cg.mts.dto.ApplicantDto;
import com.cg.mts.dto.UserDto;
import com.cg.mts.entities.Admission;
import com.cg.mts.entities.Applicant;
import com.cg.mts.entities.Course;
import com.cg.mts.entities.AdmissionCommiteeMember;
import com.cg.mts.exception.CourseNotFoundException;
import com.cg.mts.exception.CustomException;
import com.cg.mts.exception.StaffNotFoundException;
import com.cg.mts.service.IAdmissionCommiteeMemberService;
import com.cg.mts.service.IAdmissionService;
import com.cg.mts.service.IApplicantService;
import com.cg.mts.service.ICourseService;
import com.cg.mts.service.IUniversityStaffService;
import com.cg.mts.util.Response;

@RestController
@RequestMapping("admissionCommitee")
public class AdmissionCommiteeController {

	@Autowired
	IAdmissionService iAdmissionService;
	@Autowired
	IAdmissionCommiteeMemberService iAdmissionCommiteeMemberService;
	private final static Logger logger = LogManager.getLogger(AdmissionCommiteeController.class);

	@PostMapping("/add")
	public ResponseEntity<Response> addAdmissionCommiteeMember(
			@RequestBody @Valid  AdmissionCommiteeMemberDto admissionCommiteeMemberDto ) throws ParseException {
		logger.info("Started addAdmissionCommiteeMember method");

		logger.info("Request Object=>" + admissionCommiteeMemberDto);
		Response response = new Response();
		AdmissionCommiteeMember viewAdmissionCommiteeMember = iAdmissionCommiteeMemberService
				.addCommiteeMember(admissionCommiteeMemberDto);
		response.setResponse(viewAdmissionCommiteeMember);
		response.setStatus(Response.SUCCESS);
		response.setStatusCode(HttpStatus.OK);
		logger.info("End addAdmissionCommiteeMember method");
		logger.info("Response Object=>" + response);
		return new ResponseEntity<Response>(response, response.getStatusCode());
	}

	@GetMapping(value = "/view/{id}")
	public ResponseEntity<Response> viewAdmissionCommiteeMember(@PathVariable("id") Integer id) {
		logger.info("Started viewAdmissionCommiteeMember method");

		logger.info("Request Object=>" + id);
		Response response = new Response();
		AdmissionCommiteeMember oldAdmissionCommiteeMember = iAdmissionCommiteeMemberService.viewCommiteeMember(id);

		if (oldAdmissionCommiteeMember == null) {
			throw new StaffNotFoundException("STAFF NOT FOUND");

		}
		response.setResponse(oldAdmissionCommiteeMember);
		response.setStatus(Response.SUCCESS);
		response.setStatusCode(HttpStatus.OK);
		logger.info("End viewAdmissionCommiteeMember method");
		logger.info("Response Object=>" + response);
		return new ResponseEntity<Response>(response, response.getStatusCode());
	}
	
	@GetMapping(value = "/view/by/{email}")
	public ResponseEntity<Response> viewAdmissionCommiteeMemberByEmail(@PathVariable("email") String email) {
		logger.info("Started viewAdmissionCommiteeMember method");

		logger.info("Request Object=>" + email);
		Response response = new Response();
		AdmissionCommiteeMember oldAdmissionCommiteeMember = iAdmissionCommiteeMemberService.viewAdmissionCommiteeMemberByEmail(email);

		if (oldAdmissionCommiteeMember == null) {
			throw new StaffNotFoundException("Admission Commitee MemberNOT FOUND");

		}
		response.setResponse(oldAdmissionCommiteeMember);
		response.setStatus(Response.SUCCESS);
		response.setStatusCode(HttpStatus.OK);
		logger.info("End viewAdmissionCommiteeMember method");
		logger.info("Response Object=>" + response);
		return new ResponseEntity<Response>(response, response.getStatusCode());
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<Response> removeAdmissionCommiteeMember(@PathVariable("id") Integer id) {
		logger.info("Started removeAdmissionCommiteeMember method");
		logger.info("Request Object=>" + id);

		Response response = new Response();
		AdmissionCommiteeMember oldAdmissionCommiteeMember = iAdmissionCommiteeMemberService.viewCommiteeMember(id);
		if (oldAdmissionCommiteeMember == null) {
			throw new StaffNotFoundException("STAFF NOT FOUND");
		}
		iAdmissionCommiteeMemberService.removeCommiteeMember(id);
		response.setResponse("Deleted");
		response.setStatus(Response.SUCCESS);
		response.setStatusCode(HttpStatus.OK);
		logger.info("End removeAdmissionCommiteeMember method");
		logger.info("Response Object=>" + response);
		return new ResponseEntity<Response>(response, response.getStatusCode());
	}

	@PostMapping(value = "/update")
	public ResponseEntity<Response> updateCommiteeMember(@RequestBody @Valid   AdmissionCommiteeMemberDto admissionCommiteeMemberDto)
			throws ParseException {
		logger.info("Started updateCommiteeMember method");

		logger.info("Request Object=>" + admissionCommiteeMemberDto);
		Response response = new Response();
		AdmissionCommiteeMember updateAdmissionCommiteeMember = iAdmissionCommiteeMemberService
				.updateCommiteeMember(admissionCommiteeMemberDto);
		
		if (updateAdmissionCommiteeMember == null) {
			throw new CustomException("UPDATION FAIL");
		}

		response.setResponse(updateAdmissionCommiteeMember);
		response.setStatus(Response.SUCCESS);
		response.setStatusCode(HttpStatus.OK);
		logger.info("End updateCommiteeMember method");
		logger.info("Response Object=>" + response);
		return new ResponseEntity<Response>(response, response.getStatusCode());
	}

	@GetMapping(value = "/get/All")
	public ResponseEntity<Response> viewAllAdmissionCommiteeMembers() {
		logger.info("Started viewAllAdmissionCommiteeMembers method");

		Response response = new Response();
		List<AdmissionCommiteeMember> AdmissionCommiteeMembers = iAdmissionCommiteeMemberService
				.viewAllCommiteeMember();
		if (AdmissionCommiteeMembers.size() == 0) {
			response.setResponse(AdmissionCommiteeMembers);
			response.setStatus(Response.SUCCESS);
			response.setStatusCode(HttpStatus.OK);
		}
		response.setResponse(AdmissionCommiteeMembers);
		response.setStatus(Response.SUCCESS);
		response.setStatusCode(HttpStatus.OK);
		logger.info("End viewAllAdmissionCommiteeMembers method");
		logger.info("Response Object=>" + response);
		return new ResponseEntity<Response>(response, response.getStatusCode());
	}

}