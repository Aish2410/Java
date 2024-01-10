package com.cg.mts.service;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import com.cg.mts.dto.CourseDto;
import com.cg.mts.entities.Course;

public interface ICourseService {
	
	public Course addCourse(CourseDto courseDto) throws ParseException;

	public Course removeCourse(int courseId);

	public Course updateCourse(CourseDto courseDto) throws ParseException;

	public Course viewCourse(int courseId);

	public List<Course> viewAllCourses();

}
