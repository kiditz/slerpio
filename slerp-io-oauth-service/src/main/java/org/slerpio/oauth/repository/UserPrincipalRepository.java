package org.slerpio.oauth.repository;

import org.slerpio.oauth.entity.UserPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPrincipalRepository extends JpaRepository<UserPrincipal, Long> {
	
	public UserPrincipal findUserPrincipalByPhoneNumber(String phoneNumber);
}