package org.slerpio.oauth.repository;

import org.slerpio.oauth.entity.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Long> {

	@Query("SELECT a.authority FROM UserAuthority a WHERE a.userId.username = :username")
	public List<UserAuthority> getAuthorityByUsername(@Param("username") String username);
}