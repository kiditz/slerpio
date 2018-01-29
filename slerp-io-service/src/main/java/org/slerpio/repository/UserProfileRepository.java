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

	@Query("SELECT CASE WHEN COUNT(p) > 0 THEN 'true' ELSE 'false' END FROM UserProfile p WHERE p.phoneNumber = :phoneNumber")
	public Boolean isExistsByPhoneNumber(
			@Param("phoneNumber") String phoneNumber);

	@Query("SELECT p FROM UserProfile p WHERE UPPER(p.fullname) LIKE CONCAT('%',UPPER(:fullname),'%')")
	public Page<UserProfile> getProfileByName(
			@Param("fullname") String fullname, Pageable pageable);

	@Query("SELECT s FROM UserProfile p JOIN p.schoolSet s WHERE p.profileId = :profileId")
	public List<School> findSchoolByProfileId(@Param("profileId") long profileId);

	@Query(value = "SELECT p.user_profile_id, p.phone_number, p.fullname, p.gender, p.address, p.authority, p.latitude, p.longitude, p.active, p.active_at, p.created_at, p.update_at FROM user_profile p\n"
			+ " LEFT OUTER JOIN class_student s ON p.user_profile_id = s.user_profile_id\n"
			+ " WHERE UPPER(p.fullname) LIKE CONCAT('%',UPPER(:fullname),'%') AND s.school_class_id IS NULL AND p.authority = 'STUDENT' ORDER BY p.user_profile_id DESC", nativeQuery = true)
	public List<UserProfile> getStudentNotHaveClass(
			@Param("fullname") String fullname);

	@Query("SELECT p FROM UserProfile p WHERE p.phoneNumber = :phoneNumber")
	public UserProfile findProfileByPhoneNumber(
			@Param("phoneNumber") String phoneNumber);

}