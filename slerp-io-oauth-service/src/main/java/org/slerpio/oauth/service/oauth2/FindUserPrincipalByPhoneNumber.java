package org.slerpio.oauth.service.oauth2;

import org.slerp.core.CoreException;
import org.slerp.core.Domain;
import org.slerp.core.business.DefaultBusinessFunction;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerp.core.validation.NumberValidation;
import org.slerpio.oauth.entity.UserPrincipal;
import org.slerpio.oauth.repository.UserPrincipalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@KeyValidation("phoneNumber")
@NumberValidation({})
@NotBlankValidation({})
public class FindUserPrincipalByPhoneNumber extends DefaultBusinessFunction {

	@Autowired
	UserPrincipalRepository userPrincipalRepository;

	@Override
	public Domain handle(Domain userPrincipalDomain) {
		super.handle(userPrincipalDomain);
		UserPrincipal userPrincipal = userPrincipalRepository
				.findUserPrincipalByPhoneNumber(userPrincipalDomain.getString("phoneNumber"));
		if (userPrincipal == null)
			throw new CoreException("failed.to.found.user");
		Domain userDomain = new Domain().put("userPrincipal", userPrincipal);
		return userDomain;
	}
}