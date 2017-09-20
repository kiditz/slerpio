package org.slerpio.repository;

import org.slerpio.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

	@Query("SELECT p FROM Profile p WHERE p.username = :username")
	public Profile findProfileByUsername(@Param("username") String username);
}