package org.slerpio.repository;

import org.slerpio.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	@Query("SELECT CASE  WHEN count(s)> 0 THEN true ELSE false END FROM Teacher s WHERE s.username = :username")
	public boolean isExistByUsername(@Param("username") String username);

	@Query("SELECT t FROM Teacher t WHERE t.username = :username")
	public Teacher getTeacherByUsername(@Param("username") String username);
}