package org.slerpio.repository;

import org.slerpio.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

	@Query("SELECT p FROM Profile p WHERE p.username = :username")
	public Profile findProfileByUsername(@Param("username") String username);

	@Query("SELECT COUNT(p) FROM Profile p WHERE p.schoolId.schoolId = :schoolId")
	public Long countProfileBySchoolId(@Param("schoolId") Long schoolId);

	@Query("SELECT CASE WHEN COUNT(p) > 0 THEN true else false END FROM Profile p WHERE p.username = :username")
	public Boolean isProfileExistByUsername(@Param("username") String username);
}