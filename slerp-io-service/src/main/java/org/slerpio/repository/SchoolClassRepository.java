package org.slerpio.repository;

import org.slerpio.entity.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {

	@Query("SELECT s FROM SchoolClass s WHERE s.schoolId.schoolId = :schoolId")
	public List<SchoolClass> getClassBySchoolId(@Param("schoolId") Long schoolId);
}