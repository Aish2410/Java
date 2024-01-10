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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.UniversityCourseAppApplication;
import com.cg.mts.dto.ApplicantDto;
import com.cg.mts.dto.CourseDto;
import com.cg.mts.entities.Admission;
import com.cg.mts.entities.Applicant;
import com.cg.mts.entities.Course;
import com.cg.mts.exception.ApplicanNotFoundException;
import com.cg.mts.exception.CourseNotFoundException;
import com.cg.mts.exception.CustomException;
import com.cg.mts.service.IAdmissionService;
import com.cg.mts.service.IApplicantService;
import com.cg.mts.service.ICourseService;
import com.cg.mts.util.Response;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("course")
public class CourseController {

	@Autowired
	IApplicantService iApplicantService;

	@Autowired
	ICourseService iCourseService;

	@Autowired
	IAdmissionService iAdmissionService;

	private final static Logger logger = LogManager.getLogger(CourseController.class);

	@PostMapping("/add")
	public ResponseEntity<Response> addCourse(@RequestBody @Valid CourseDto courseDto) throws ParseException {
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

	@GetMapping(value = "/view/{id}")
	public ResponseEntity<Response> viewCourse(@PathVariable("id") Integer id) {
		logger.info("Started viewCourse method");
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

	@DeleteMapping("/remove/course/{id}")
	public ResponseEntity<Response> removeCourse(@PathVariable("id") Integer id) {
		logger.info("Starrted removeCourse method");
		logger.info("Request Object=>" + id);
		Response response = new Response();
		Course oldcourse = iCourseService.viewCourse(id);
		if (oldcourse == null) {
			throw new CourseNotFoundException("COURSE NOT FOUND");
		}
		try {
			iCourseService.removeCourse(id);
		} catch (Exception e) {
			e.printStackTrace();
			response.setResponse(
					"You cant delete foreign key constraint fko204p6ebip9vhi9au181lqbcj on table admission");
			response.setStatus(Response.FAILURE);
			response.setStatusCode(HttpStatus.OK);
			logger.info("End removeCourse method");
			logger.info("Response Object=>" + response);
			return new ResponseEntity<Response>(response, response.getStatusCode());
		}
		response.setResponse("Deleted");
		response.setStatus(Response.SUCCESS);
		response.setStatusCode(HttpStatus.OK);
		logger.info("End removeCourse method");
		logger.info("Response Object=>" + response);
		return new ResponseEntity<Response>(response, response.getStatusCode());
	}

	@PostMapping(value = "/update")
	public ResponseEntity<Response> updateCourse(@RequestBody @Valid CourseDto courseDto) throws ParseException {
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

	@GetMapping(value = "/get/All")
	public ResponseEntity<Response> viewAllCourses() {
		logger.info("Starrted viewAllCourses method");
		Response response = new Response();
		List<Course> Courses = iCourseService.viewAllCourses();
		if (Courses.size() == 0) {
			response.setResponse(Courses);
			response.setStatus(Response.SUCCESS);
			response.setStatusCode(HttpStatus.OK);
		}

		response.setResponse(Courses);
		response.setStatus(Response.SUCCESS);
		response.setStatusCode(HttpStatus.OK);
		logger.info("End viewAllCourses method");
		logger.info("Response Object=>" + response);
		return new ResponseEntity<Response>(response, response.getStatusCode());
	}

}
