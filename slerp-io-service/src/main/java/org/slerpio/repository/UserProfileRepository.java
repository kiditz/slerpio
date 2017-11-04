package org.slerpio.repository;

import org.slerpio.entity.School;
import org.slerpio.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

	@Query("SELECT p FROM UserProfile p WHERE p.profileId = :profileId")
	public UserProfile findProfileById(@Param("profileId") Long profileId);

	@Query("SELECT p FROM UserProfile p WHERE p.phoneNumber = :phoneNumber")
	public UserProfile findProfileByPhoneNumber(
			@Param("phoneNumber") String phoneNumber);

	@Query("SELECT CASE WHEN COUNT(p) > 0 THEN 'true' ELSE 'false' END FROM UserProfile p WHERE p.phoneNumber = :phoneNumber")
	public Boolean isExistsByPhoneNumber(
			@Param("phoneNumber") String phoneNumber);

	@Query("SELECT p FROM UserProfile p WHERE UPPER(p.fullname) LIKE CONCAT('%',UPPER(:fullname),'%')")
	public Page<UserProfile> getProfileByName(
			@Param("fullname") String fullname, Pageable pageable);

	@Query("SELECT s FROM UserProfile p JOIN p.schoolSet s WHERE p.profileId = :profileId")
	public List<School> findSchoolByProfileId(@Param("profileId") long profileId);

}