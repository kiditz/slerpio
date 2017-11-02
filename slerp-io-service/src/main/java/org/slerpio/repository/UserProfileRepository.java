package org.slerpio.repository;

import org.slerpio.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

	@Query("SELECT p FROM UserProfile p WHERE p.profileId = :profileId")
	public UserProfile findProfileById(@Param("profileId") Long profileId);

	@Query("SELECT p FROM UserProfile p WHERE p.phoneNumber = :phoneNumber")
	public UserProfile findProfileByPhoneNumber(@Param("phoneNumber") String phoneNumber);

	@Query("SELECT CASE WHEN p.phoneNumber = :phoneNumber THEN true ELSE FALSE END FROM UserProfile p WHERE p.phoneNumber = :phoneNumber")
	public Boolean isExistsByPhoneNumber(@Param("phoneNumber") String phoneNumber);
	
}