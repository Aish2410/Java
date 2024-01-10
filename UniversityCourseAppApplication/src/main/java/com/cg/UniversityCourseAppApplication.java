package com.cg;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cg.mts.entities.Role;
import com.cg.mts.entities.RoleName;
import com.cg.mts.repository.RoleRepository;

@SpringBootApplication
@EnableAutoConfiguration
public class UniversityCourseAppApplication {

	private final static Logger logger = LogManager.getLogger(UniversityCourseAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(UniversityCourseAppApplication.class, args);
		logger.info("################## APPLICATION STARTED #######################");
	}

	@Bean
	CommandLineRunner init(RoleRepository roleRepository) {
		return args -> {
			try {
				Set<Role> roles = new HashSet<>();
				
				Optional<Role> role1 = roleRepository.findByName(RoleName.APPLICANT);
				if(!role1.isPresent()) {
					Role role = new Role();
					role.setName(RoleName.APPLICANT);
					roles.add(role);
					//roleRepository.save(role);
					logger.info("################## APPLICANT ROLE INSERTED #######################");
				}
				
				Optional<Role> role2 = roleRepository.findByName(RoleName.ACM);
				if(!role2.isPresent()) {
					Role role = new Role();
					role.setName(RoleName.ACM);
					roles.add(role);
					//roleRepository.save(role);
					logger.info("################## ACM ROLE INSERTED #######################");
				}
				
				Optional<Role> role3 = roleRepository.findByName(RoleName.STAFF);
				if(!role3.isPresent()) {
					Role role = new Role();
					role.setName(RoleName.STAFF);
					roles.add(role);
					//roleRepository.save(role);
					logger.info("################## STAFF ROLE INSERTED #######################");
					roleRepository.saveAll(roles);
				}

			} catch (Exception e) {
				e.printStackTrace();
				logger.info("################## ALREADY ROLE INSERTED #######################");
			}

//            INSERT INTO roles(name) VALUES('APPLICANT');
//            INSERT INTO roles(name) VALUES('STAFF');
//            INSERT INTO roles(name) VALUES('ACM');

		};
	}
}
