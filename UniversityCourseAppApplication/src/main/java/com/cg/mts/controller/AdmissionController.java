package com.cg.mts.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.dto.ApplicantDto;
import com.cg.mts.dto.CancelAdmissionDto;
import com.cg.mts.entities.Admission;
import com.cg.mts.entities.Applicant;
import com.cg.mts.exception.ApplicanNotFoundException;
import com.cg.mts.exception.CustomException;
import com.cg.mts.service.IAdmissionService;
import com.cg.mts.service.IApplicantService;
import com.cg.mts.service.ICourseService;
import com.cg.mts.util.Response;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("admission")
public class AdmissionController {
	
	@Autowired
	IApplicantService iApplicantService;

	@Autowired
	IAdmissionService iAdmissionService;
	private final static Logger logger = LogManager.getLogger(AdmissionController.class);

	@PostMapping("/add")
	public ResponseEntity<Response> addAdmission(@RequestBody ApplicantDto applicantDto) {
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
		logger.info("End addAdmission method");
		logger.info("Response Object=>" + response);
		return new ResponseEntity<Response>(response, response.getStatusCode());
	}

	@PostMapping(value = "/update")
	public ResponseEntity<Response> updateAdmission(@RequestBody ApplicantDto applicantDto) {
		logger.info("Started updateAdmission method");
		logger.info("Request Object=>" + applicantDto);
		Response response = new Response();
		
		Applicant ApplicantUpdate = iApplicantService.update(applicantDto);
		if (ApplicantUpdate == null) {
			throw new CustomException("FAIL TO UPDATE");
		}
		response.setResponse(ApplicantUpdate);
		response.setStatus(Response.SUCCESS);
		response.setStatusCode(HttpStatus.OK);
		logger.info("End updateAdmission method");
		logger.info("Response Object=>" + response);
		return new ResponseEntity<Response>(response, response.getStatusCode());
	}

	@GetMapping(value = "/view/{addmissionId}")
	public ResponseEntity<Response> viewAdmission(@PathVariable("addmissionId") Integer addmissionId) {
		logger.info("Starrted viewAdmission method");

		logger.info("Request Object=>" + addmissionId);
		Response response = new Response();
		Admission admission = iAdmissionService.viewAdmission(addmissionId);
		if (admission != null) {
			response.setResponse(admission);
			response.setStatus(Response.SUCCESS);
			response.setStatusCode(HttpStatus.OK);
			logger.info("End viewAdmission method");
			logger.info("Response Object=>" + response);
			return new ResponseEntity<Response>(response, response.getStatusCode());
		} else {
			throw new ApplicanNotFoundException("APPLICANT NOT FOUND");

		}
	}

	@PostMapping(value = "/cancel")
	public ResponseEntity<Response> cancelAdmission(@RequestBody @Valid CancelAdmissionDto cancelAdmissionDto) {
		logger.info("Started cancelAdmission method");
		logger.info("Request Object=>" + cancelAdmissionDto);
		Response response = new Response();
		
		Applicant ApplicantUpdate = iApplicantService.cancelAdmission(cancelAdmissionDto);
		if (ApplicantUpdate == null) {
			throw new CustomException("FAIL TO CANCEL");
		}
		response.setResponse(ApplicantUpdate);
		response.setStatus(Response.SUCCESS);
		response.setStatusCode(HttpStatus.OK);
		logger.info("End cancelAdmission method");
		logger.info("Response Object=>" + response);
		return new ResponseEntity<Response>(response, response.getStatusCode());
	}

	@GetMapping(value = "/view/by/courseId/{courseId}")
	public ResponseEntity<Response> showAllAdmissionByCourseId(@PathVariable("courseId") Integer courseId) {
		logger.info("Started showAllAdmissionByCourseId method");
		logger.info("Request Object=>" + courseId);

		Response response = new Response();
		List<Applicant> Applicantlist = iApplicantService.showAllAdmissionByCourseId(courseId);
		response.setResponse(Applicantlist);
		response.setStatus(Response.SUCCESS);
		response.setStatusCode(HttpStatus.OK);
		logger.info("End showAllAdmissionByCourseId method");
		logger.info("Response Object=>" + response);
		return new ResponseEntity<Response>(response, response.getStatusCode());
	}

	@GetMapping(value = "/view/by/date/{localDate}")
	public ResponseEntity<Response> showAllAdmissionByDate(@PathVariable("localDate") String localDate)
			throws ParseException {
		logger.info("Started showAllAdmissionByDate method");
		logger.info("Request Object=>" + localDate);
		Response response = new Response();
		Date date1 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-mm-dd").parse(localDate);
		} catch (Exception e) {
			e.printStackTrace();
			response.setResponse("Date should be yyyy-mm-dd");
			response.setStatus(Response.SUCCESS);
			response.setStatusCode(HttpStatus.OK);
			return new ResponseEntity<Response>(response, response.getStatusCode());
		}

		LocalDate localDate1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		List<Applicant> Applicantlist = iApplicantService.showAllAdmissionByDate(localDate1);
		response.setResponse(Applicantlist);
		response.setStatus(Response.SUCCESS);
		response.setStatusCode(HttpStatus.OK);
		logger.info("End showAllAdmissionByDate method");
		logger.info("Response Object=>" + response);
		return new ResponseEntity<Response>(response, response.getStatusCode());
	}

}
