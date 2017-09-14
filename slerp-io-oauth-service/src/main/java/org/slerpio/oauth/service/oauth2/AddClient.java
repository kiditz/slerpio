package org.slerpio.oauth.service.oauth2;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.slerp.core.Domain;
import org.slerp.core.CoreException;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerpio.oauth.entity.Client;
import org.slerpio.oauth.repository.ClientRepository;
import org.slerp.core.business.DefaultBusinessTransaction;

@Service
@Transactional
@KeyValidation({ "clientId", "clientSecret", "scope", "authorizedGrantTypes", "registeredRedirectUri" })
@NotBlankValidation({ "clientId", "clientSecret" })
public class AddClient extends DefaultBusinessTransaction {

	@Autowired
	ClientRepository clientRepository;

	@Override
	public void prepare(Domain clientDomain) throws Exception {

	}

	@Override
	public Domain handle(Domain clientDomain) {
		super.handle(clientDomain);
		try {
			Client client = clientDomain.convertTo(Client.class);
			client = clientRepository.save(client);
			return new Domain(client);
		} catch (Exception e) {
			throw new CoreException(e);
		}
	}
}