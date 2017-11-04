package org.slerpio.repository;

import org.slerpio.entity.SchoolClass;
import org.slerpio.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {

	@Query("SELECT c FROM SchoolClass c WHERE c.userProfileId.profileId = :profileId")
	public List<SchoolClass> findSchoolClassByProfileId(
			@Param("profileId") Long profileId);

	@Query("SELECT s FROM SchoolClass c JOIN c.students s WHERE c.schoolClassId = :schoolClassId")
	public Page<UserProfile> getStudentFromClassId(
			@Param("schoolClassId") Long schoolClassId, Pageable pageable);

}