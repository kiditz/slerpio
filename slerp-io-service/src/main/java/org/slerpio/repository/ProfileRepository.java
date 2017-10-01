package org.slerpio.repository;

import org.slerpio.entity.Profile;
import org.slerpio.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

	@Query("SELECT s FROM Profile p JOIN p.schoolId s WHERE p.username = :username")
	public School findSchoolByUsername(@Param("username") String username);

	@Query("SELECT CASE WHEN COUNT (p) > 0 THEN true ELSE false end FROM Profile p WHERE p.username = :username ")
	public Boolean isProfileExistByUsername(@Param("username") String username);

	public Profile findProfileByUsername(String username);

	@Query("SELECT COUNT(p) FROM Profile p WHERE p.schoolId.schoolId = :schoolId")
	public Long countProfileBySchoolId(@Param("schoolId") Long schoolId);
	
	@Query("SELECT COUNT(p) FROM Profile p WHERE p.authority = :authority")
	public Long countProfileByAuthority(@Param("authority") String authority);
}