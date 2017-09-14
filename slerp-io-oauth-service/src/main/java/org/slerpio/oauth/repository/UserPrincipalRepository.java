package org.slerpio.oauth.repository;

import org.slerpio.oauth.entity.UserPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

public interface UserPrincipalRepository extends JpaRepository<UserPrincipal, Long> {

	@Query("SELECT u FROM UserPrincipal u WHERE u.username = :username")
	public UserPrincipal findUserPrincipalByUsername(@Param("username") String username);

	@Query("SELECT u.username FROM UserPrincipal u WHERE u.username = :username")
	public UserPrincipal findUser(@Param("username") String username);
}