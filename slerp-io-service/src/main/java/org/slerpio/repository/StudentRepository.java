package org.slerpio.repository;

import java.util.List;

import org.slerpio.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Long> {

	@Query("SELECT s FROM Student s WHERE s.username = :username")
	public Student getStudentByUsername(@Param("username") String username);

	@Query("SELECT s FROM Student s WHERE s.classId.schoolClassId = :schoolClassId")
	public List<Student> getStudentByClassId(@Param("schoolClassId") long schoolClassId);

	@Query("SELECT CASE  WHEN count(s)> 0 THEN true ELSE false END FROM Student s WHERE s.username = :username")
	public boolean isExistByUsername(@Param("username") String username);
	
}