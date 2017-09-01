package org.slerp.oauth.repository;

import org.slerp.oauth.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
	public Client getByClientId(String clientId);
}