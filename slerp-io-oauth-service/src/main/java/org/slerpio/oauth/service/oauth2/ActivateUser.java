package org.slerpio.oauth.service.oauth2;

import org.slerp.core.CoreException;
import org.slerp.core.Domain;
import org.slerp.core.business.DefaultBusinessTransaction;
import org.slerp.core.validation.NotBlankValidation;
import org.slerpio.oauth.OauthConstant;
import org.slerpio.oauth.entity.UserPrincipal;
import org.slerpio.oauth.repository.UserPrincipalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@NotBlankValidation({ "phoneNumber", "activationCode" })
public class ActivateUser extends DefaultBusinessTransaction {

	@Autowired
	UserPrincipalRepository userPrincipalRepository;
	Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public void prepare(Domain userPrincipalDomain) throws Exception {
		UserPrincipal userPrincipal = userPrincipalRepository
				.findUserPrincipalByPhoneNumber(userPrincipalDomain.getString("phoneNumber"));
		if (userPrincipal == null) {
			throw new CoreException(OauthConstant.USER_NOT_FOUND);
		}
		userPrincipal.setEnabled(true);
		if (!userPrincipal.getActivationCode().equals(userPrincipalDomain.getString("activationCode"))) {
			throw new CoreException(OauthConstant.INVALID_ACTIVATION_CODE);
		}
		userPrincipalDomain.put("userPrincipal", userPrincipal);
	}

	@Override
	public Domain handle(Domain userPrincipalDomain) {
		super.handle(userPrincipalDomain);
		log.info("User Principal Domain : {}", userPrincipalDomain);
		try {
			UserPrincipal userPrincipal = userPrincipalDomain.getDomain("userPrincipal").convertTo(UserPrincipal.class);
			// It will be ignore to upadte authority list
			userPrincipal.setUserAuthorityList(null);
			userPrincipal = userPrincipalRepository.saveAndFlush(userPrincipal);
			return new Domain(userPrincipal);
		} catch (Exception e) {
			throw new CoreException(e);
		}
	}
}