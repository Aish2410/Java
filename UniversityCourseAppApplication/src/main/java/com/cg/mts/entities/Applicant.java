package com.cg.mts.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "applicant")
public class Applicant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "applicant_name", nullable = false)
	String applicantName;


	@Column(name = "email", nullable = false)
	private String email;

	@Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message = "Mobile number is invalid")
	@Column(name = "applicant_mobile_number", nullable = false)
	private String applicantMobileNumber;

	@Column(name = "applicant_degree", nullable = false)
	private String applicantDegree;

	@Column(name = "applicant_graduation_percent", nullable = false)
	private int applicantGraduationPercent;

	@OneToOne
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@JoinColumn(name = "admission_id", referencedColumnName = "id")
	private Admission admission;
	
	@Column(name = "active", nullable = false)
	private Boolean active=true;

}
