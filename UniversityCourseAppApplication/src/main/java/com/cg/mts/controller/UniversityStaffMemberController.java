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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.dto.CourseDto;
import com.cg.mts.dto.UniversityStraffMemberDto;
import com.cg.mts.dto.UserDto;
import com.cg.mts.entities.Course;
import com.cg.mts.entities.UniversityStraffMember;
import com.cg.mts.exception.CourseNotFoundException;
import com.cg.mts.exception.CustomException;
import com.cg.mts.exception.StaffNotFoundException;
import com.cg.mts.service.IAdmissionService;
import com.cg.mts.service.IApplicantService;
import com.cg.mts.service.ICourseService;
import com.cg.mts.service.IUniversityStaffService;
import com.cg.mts.util.Response;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("staff/member")
public class UniversityStaffMemberController {

	@Autowired
	IUniversityStaffService iUniversityStaffService;

	@Autowired
	IApplicantService iApplicantService;

	@Autowired
	ICourseService iCourseService;

	@Autowired
	IAdmissionService iAdmissionService;
	private final static Logger logger = LogManager.getLogger(UniversityStaffMemberController.class);

	@PostMapping("/add")
	public ResponseEntity<Response> addUniversityStraffMember(@RequestBody @Valid  UserDto userDto) throws ParseException {
		Response response = new Response();
		logger.info("Starrted addUniversityStraffMember method");

		logger.info("Request Object=>" + userDto);
		UniversityStraffMember viewUniversityStraffMember = iUniversityStaffService.addStaff(userDto);
		response.setResponse(viewUniversityStraffMember);
		response.setStatus(Response.SUCCESS);
		response.setStatusCode(HttpStatus.OK);
		logger.info("End addUniversityStraffMember method");
		logger.info("Response Object=>" + response);
		return new ResponseEntity<Response>(response, response.getStatusCode());
	}

	@GetMapping(value = "/view/{id}")
	public ResponseEntity<Response> viewUniversityStraffMember(@PathVariable("id") Integer id) {
		logger.info("Starrted viewUniversityStraffMember method");

		logger.info("Request Object=>" + id);
		Response response = new Response();
		UniversityStraffMember oldUniversityStraffMember = iUniversityStaffService.viewStaff(id);

		if (oldUniversityStraffMember == null) {
			throw new StaffNotFoundException("STAFF NOT FOUND");

		}
		response.setResponse(oldUniversityStraffMember);
		response.setStatus(Response.SUCCESS);
		response.setStatusCode(HttpStatus.OK);
		logger.info("End viewUniversityStraffMember method");
		logger.info("Response Object=>" + response);
		return new ResponseEntity<Response>(response, response.getStatusCode());
	}
	
	@GetMapping(value = "/view/by/{email}")
	public ResponseEntity<Response> viewUniversityStraffMemberByEmail(@PathVariable("email") String email) {
		logger.info("Starrted viewUniversityStraffMember method");

		logger.info("Request Object=>" + email);
		Response response = new Response();
		UniversityStraffMember oldUniversityStraffMember = iUniversityStaffService.viewUniversityStraffMemberByEmail(email);

		if (oldUniversityStraffMember == null) {
			throw new StaffNotFoundException("STAFF NOT FOUND");

		}
		response.setResponse(oldUniversityStraffMember);
		response.setStatus(Response.SUCCESS);
		response.setStatusCode(HttpStatus.OK);
		logger.info("End viewUniversityStraffMember method");
		logger.info("Response Object=>" + response);
		return new ResponseEntity<Response>(response, response.getStatusCode());
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<Response> removeUniversityStraffMember(@PathVariable("id") Integer id) {
		logger.info("Starrted removeUniversityStraffMember method");

		logger.info("Request Object=>" + id);

		Response response = new Response();
		UniversityStraffMember oldUniversityStraffMember = iUniversityStaffService.viewStaff(id);
		if (oldUniversityStraffMember == null) {
			throw new StaffNotFoundException("STAFF NOT FOUND");
		}
		iUniversityStaffService.removeStaff(id);
		response.setResponse("Deleted");
		response.setStatus(Response.SUCCESS);
		response.setStatusCode(HttpStatus.OK);
		logger.info("End removeUniversityStraffMember method");
		logger.info("Response Object=>" + response);
		return new ResponseEntity<Response>(response, response.getStatusCode());
	}

	@PostMapping(value = "/update")
	public ResponseEntity<Response> updateUniversityStraffMember(@RequestBody @Valid  UniversityStraffMemberDto universityStraffMemberDto) throws ParseException {
		Response response = new Response();
		logger.info("Starrted updateUniversityStraffMember method");

		logger.info("Request Object=>" + universityStraffMemberDto);

		UniversityStraffMember updateUniversityStraffMember = iUniversityStaffService.updateStaff(universityStraffMemberDto);

		response.setResponse(updateUniversityStraffMember);
		response.setStatus(Response.SUCCESS);
		response.setStatusCode(HttpStatus.OK);
		logger.info("End updateUniversityStraffMember method");
		logger.info("Response Object=>" + response);
		return new ResponseEntity<Response>(response, response.getStatusCode());
	}

	@GetMapping(value = "/get/All")
	public ResponseEntity<Response> viewAllUniversityStraffMembers() {
		logger.info("Starrted viewAllUniversityStraffMembers method");

		Response response = new Response();
		List<UniversityStraffMember> UniversityStraffMembers = iUniversityStaffService.viweAllStaff();
		if (UniversityStraffMembers.size() == 0) {
			response.setResponse(UniversityStraffMembers);
			response.setStatus(Response.SUCCESS);
			response.setStatusCode(HttpStatus.OK);
		}

		response.setResponse(UniversityStraffMembers);
		response.setStatus(Response.SUCCESS);
		response.setStatusCode(HttpStatus.OK);
		logger.info("End viewAllUniversityStraffMembers method");
		logger.info("Response Object=>" + response);
		return new ResponseEntity<Response>(response, response.getStatusCode());
	}
	
	@PostMapping("/course/add")
	public ResponseEntity<Response> addCourse(@RequestBody @Valid  CourseDto courseDto) throws ParseException {
		logger.info("Started addCourse method");
		logger.info("Request Object=>" + courseDto);
		Response response = new Response();
		Course viewCourse = iCourseService.addCourse(courseDto);
		if (viewCourse == null) {
			throw new CustomException("COURSE FAIL TO ADD");
		}
		response.setResponse(viewCourse);
		response.setStatus(Response.SUCCESS);
		response.setStatusCode(HttpStatus.OK);
		logger.info("End addCourse method");
		logger.info("Response Object=>" + response);
		return new ResponseEntity<Response>(response, response.getStatusCode());
	}

	@GetMapping(value = "/course/view/{id}")
	public ResponseEntity<Response> viewCourse(@PathVariable("id") Integer id) {
		logger.info("Starrted viewCourse method");

		logger.info("Request Object=>" + id);
		Response response = new Response();
		Course oldcourse = iCourseService.viewCourse(id);
		if (oldcourse == null) {
			throw new CourseNotFoundException("COURSE NOT FOUND");

		}
		response.setResponse(oldcourse);
		response.setStatus(Response.SUCCESS);
		response.setStatusCode(HttpStatus.OK);
		logger.info("End viewCourse method");
		logger.info("Response Object=>" + response);
		return new ResponseEntity<Response>(response, response.getStatusCode());
	}

	@DeleteMapping("/course/remove/course/{id}")
	public ResponseEntity<Response> removeCourse(@PathVariable("id") Integer id) {
		logger.info("Starrted removeCourse method");

		logger.info("Request Object=>" + id);
		Response response = new Response();
		Course oldcourse = iCourseService.viewCourse(id);
		if (oldcourse == null) {
			throw new CourseNotFoundException("COURSE NOT FOUND");
		}
		iCourseService.removeCourse(id);
		response.setResponse("Deleted");
		response.setStatus(Response.SUCCESS);
		response.setStatusCode(HttpStatus.OK);
		logger.info("End removeCourse method");
		logger.info("Response Object=>" + response);
		return new ResponseEntity<Response>(response, response.getStatusCode());
	}

	@PostMapping(value = "/course/update")
	public ResponseEntity<Response> updateCourse(@RequestBody @Valid  CourseDto courseDto) throws ParseException {
		logger.info("Starrted updateCourse method");
		logger.info("Request Object=>" + courseDto);
		Response response = new Response();

		Course updateCourse = iCourseService.updateCourse(courseDto);
		if (updateCourse == null) {
			throw new CustomException("COURSE FAIL TO UPDATE");
		}
		response.setResponse(updateCourse);
		response.setStatus(Response.SUCCESS);
		response.setStatusCode(HttpStatus.OK);
		logger.info("End updateCourse method");
		logger.info("Response Object=>" + response);
		return new ResponseEntity<Response>(response, response.getStatusCode());
	}

	@GetMapping(value = "/course/get/All")
	public ResponseEntity<Response> viewAllCourses() {
		logger.info("Starrted viewAllCourses method");

		Response response = new Response();
		List<Course> Courses = iCourseService.viewAllCourses();
		if (Courses.size() == 0) {
			throw new CourseNotFoundException("COURSES NOT FOUND");
		}

		response.setResponse(Courses);
		response.setStatus(Response.SUCCESS);
		response.setStatusCode(HttpStatus.OK);
		logger.info("End viewAllCourses method");
		logger.info("Response Object=>" + response);
		return new ResponseEntity<Response>(response, response.getStatusCode());
	}


}
