package org.slerpio.oauth.service.oauth2;

import org.slerp.core.business.DefaultBusinessFunction;
import org.springframework.stereotype.Service;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NumberValidation;
import org.slerpio.oauth.entity.Client;
import org.slerpio.oauth.repository.ClientRepository;
import org.slerp.core.validation.NotBlankValidation;
import org.slerp.core.Domain;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@KeyValidation("clientId")
@NumberValidation({})
@NotBlankValidation({})
public class FindClientByClientId extends DefaultBusinessFunction {

	@Autowired
	ClientRepository clientRepository;

	@Override
	public Domain handle(Domain clientDomain) {
		Client client = clientRepository.getByClientId(clientDomain.getString("clientId"));
		return new Domain().put("client", client);
	}
}