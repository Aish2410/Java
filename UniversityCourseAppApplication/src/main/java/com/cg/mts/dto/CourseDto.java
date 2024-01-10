package com.cg.mts.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseDto {

	public int courseId;
	
	@NotEmpty(message = "Coures Name should not be blank")
	public String couresName;

	@NotEmpty(message = "Coures Duration should not be blank")
	public String couresDuration;
	
	public LocalDate couresStartDate;

	public LocalDate couresEndDate;

	@NotEmpty(message = "Coures Fees should not be blank")
	public String couresFees;
	public Boolean active=true;


}
