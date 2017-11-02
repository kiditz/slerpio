package org.slerpio.oauth.service.oauth2;

import org.slerp.core.CoreException;
import org.slerp.core.Domain;
import org.slerp.core.business.DefaultBusinessFunction;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerp.core.validation.NumberValidation;
import org.slerpio.oauth.OauthConstant;
import org.slerpio.oauth.entity.UserPrincipal;
import org.slerpio.oauth.repository.UserPrincipalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@KeyValidation("phoneNumber")
@NumberValidation({})
<<<<<<< HEAD:slerp-io-oauth-service/src/main/java/org/slerpio/oauth/service/oauth2/FindUserPrincipalByPhoneNumber.java
@NotBlankValidation({"phoneNumber"})
=======
@NotBlankValidation({})
>>>>>>> 6430a01fbe4730811797ceb4202845c0f4dca20d:slerp-io-oauth-service/src/main/java/org/slerpio/oauth/service/oauth2/FindUserPrincipalByPhoneNumber.java
public class FindUserPrincipalByPhoneNumber extends DefaultBusinessFunction {

	@Autowired
	UserPrincipalRepository userPrincipalRepository;

	@Override
	public Domain handle(Domain userPrincipalDomain) {
		super.handle(userPrincipalDomain);
<<<<<<< HEAD:slerp-io-oauth-service/src/main/java/org/slerpio/oauth/service/oauth2/FindUserPrincipalByPhoneNumber.java
		UserPrincipal userPrincipal = userPrincipalRepository.findUserPrincipalByPhoneNumber(userPrincipalDomain.getString("phoneNumber"));
=======
		UserPrincipal userPrincipal = userPrincipalRepository
				.findUserPrincipalByPhoneNumber(userPrincipalDomain.getString("phoneNumber"));
>>>>>>> 6430a01fbe4730811797ceb4202845c0f4dca20d:slerp-io-oauth-service/src/main/java/org/slerpio/oauth/service/oauth2/FindUserPrincipalByPhoneNumber.java
		if (userPrincipal == null)
			throw new CoreException(OauthConstant.USER_NOT_FOUND);
		Domain userDomain = new Domain().put("userPrincipal", userPrincipal);
		return userDomain;
	}
}