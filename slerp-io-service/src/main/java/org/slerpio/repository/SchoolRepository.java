package org.slerpio.repository;

import org.slerpio.entity.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SchoolRepository extends JpaRepository<School, Long> {

	@Query("SELECT s FROM School s WHERE UPPER(s.name) LIKE %:schoolName%")
	public Page<School> getSchoolByName(@Param("schoolName") String schoolName, Pageable pageable);

	@Query("SELECT s FROM Student st JOIN st.schoolId s WHERE st.username = :username ")
	public School getSchoolByStudentUserName(@Param("username") String username);

	@Query("SELECT s FROM Teacher t JOIN t.schoolId s WHERE t.username = :username ")
	public School getSchoolByTeacherUserName(@Param("username") String username);
}