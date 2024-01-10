package com.cg.mts.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.mts.dto.CourseDto;
import com.cg.mts.entities.Course;
import com.cg.mts.exception.CourseNotFoundException;
import com.cg.mts.exception.CustomException;
import com.cg.mts.repository.ICourseRepository;
import com.cg.mts.service.IAdmissionService;
import com.cg.mts.service.IApplicantService;
import com.cg.mts.service.ICourseService;

@Service
@Transactional
public class ICourseServiceImpl implements ICourseService {

	@Autowired
	ICourseRepository iCourseRepository;

	@Override
	public Course addCourse(CourseDto courseDto) throws ParseException {
		Course course= new Course();
		Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(courseDto.getCouresStartDate().toString());
		LocalDate startlocalDate = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(courseDto.getCouresEndDate().toString());
		LocalDate endlocalDate = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		if(endlocalDate.compareTo(startlocalDate)  < 0 ) {
			throw new CustomException("FAIL TO ADD, END DATE CANT NOT BE LESS THAN START DATE");
		}
		long daysBetween = ChronoUnit.MONTHS.between(startlocalDate,
				endlocalDate);
		    String MONTH = Long.toString(daysBetween);
		    courseDto.setCouresDuration(MONTH);
		
		courseDto.setCouresStartDate(startlocalDate);
		courseDto.setCouresEndDate(endlocalDate);
		BeanUtils.copyProperties(courseDto, course);
		Course course1 = iCourseRepository.save(course);
		return course1;
	}

	@Override
	public Course removeCourse(int courseId) {
		iCourseRepository.deleteById(courseId);
		return null;
	}

	@Override
	public Course updateCourse(CourseDto courseDto) throws ParseException {
		
		Course course1 = null;
		try {
			course1 = iCourseRepository.findById(courseDto.getCourseId()).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (course1 == null) {
			throw new CourseNotFoundException("COURSE NOT FOUND");
		}

		Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(courseDto.getCouresStartDate().toString());
		LocalDate startlocalDate = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(courseDto.getCouresEndDate().toString());
		LocalDate endlocalDate = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		courseDto.setCouresStartDate(startlocalDate);
		courseDto.setCouresEndDate(endlocalDate);
		if(endlocalDate.compareTo(startlocalDate)  < 0 ) {
			throw new CustomException("FAIL TO ADD, END DATE CANT NOT BE LESS THAN START DATE");
		}
		BeanUtils.copyProperties(courseDto, course1);
		Course course2 = iCourseRepository.save(course1);
		return course2;
	}

	@Override
	public Course viewCourse(int courseId) {
		Course course1 = null;
		try {
			course1 = iCourseRepository.findById(courseId).get();
		} catch (Exception e) {
			e.printStackTrace();
			throw new CourseNotFoundException("COURSE NOT FOUND");
		}
		return course1;
	}

	@Override
	public List<Course> viewAllCourses() {
	List<Course> couserList = iCourseRepository.findAll();
		return couserList;
	}

}
