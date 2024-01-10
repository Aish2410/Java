package com.cg.mts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.mts.entities.User;

@Repository
public interface ILoginRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	//Boolean existsByEmail(String email);

	@Query(value = "SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM users u  WHERE  u.username = :email and  u.password = :password", nativeQuery = true)
	public boolean verifyAdmissionCommiteeMember(String email, String password);

	@Query(value = "SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM users u  WHERE  u.username = :email and  u.password = :password", nativeQuery = true)
	public boolean verifyApplicant(String email, String password);

	@Query(value = "SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM users u  WHERE  u.username = :email and  u.password = :password", nativeQuery = true)
	public boolean verifyUniversityStaffMember(@Param("email") String email, @Param("password") String password);
}
