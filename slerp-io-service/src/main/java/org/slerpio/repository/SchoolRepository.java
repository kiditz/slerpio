package org.slerpio.repository;

import org.slerpio.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SchoolRepository extends JpaRepository<School, Long> {

	@Query("SELECT s FROM School s WHERE UPPER(s.name) LIKE CONCAT('%',UPPER(:name),'%')")
	public Page<School> getSchoolByName(@Param("name") String name, Pageable pageable);

	@Query("SELECT s FROM School s WHERE s.schoolId = :schoolId")
	public School findSchoolById(@Param("schoolId") Long schoolId);
}