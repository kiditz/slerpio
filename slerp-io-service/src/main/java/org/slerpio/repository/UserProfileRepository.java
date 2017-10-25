package org.slerpio.repository;

import org.slerpio.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

	@Query("SELECT p FROM UserProfile p WHERE p.profileId = :profileId")
	public UserProfile findProfileById(@Param("profileId") Long profileId);		
}