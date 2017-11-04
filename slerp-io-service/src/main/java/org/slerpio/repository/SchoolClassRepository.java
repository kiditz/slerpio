package org.slerpio.repository;

import org.slerpio.entity.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {

	@Query("SELECT c FROM SchoolClass c WHERE c.userProfileId = :profileId")
	public SchoolClass findSchoolClassByProfileId(
			@Param("profileId") Long profileId);

}