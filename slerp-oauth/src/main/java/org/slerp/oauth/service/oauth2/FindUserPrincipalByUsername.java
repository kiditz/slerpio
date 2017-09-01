package org.slerp.oauth.service.oauth2;

import org.slerp.core.CoreException;
import org.slerp.core.Domain;
import org.slerp.core.business.DefaultBusinessFunction;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerp.core.validation.NumberValidation;
import org.slerp.oauth.entity.UserPrincipal;
import org.slerp.oauth.repository.UserPrincipalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@KeyValidation("username")
@NumberValidation({})
@NotBlankValidation({})
public class FindUserPrincipalByUsername extends DefaultBusinessFunction {

	@Autowired
	UserPrincipalRepository userPrincipalRepository;

	@Override
	public Domain handle(Domain userPrincipalDomain) {
		UserPrincipal userPrincipal = userPrincipalRepository
				.findUserPrincipalByUsername(userPrincipalDomain.getString("username"));
		if (userPrincipal == null)
			throw new CoreException("failed.to.found.user");
		Domain userDomain = new Domain().put("userPrincipal", userPrincipal);
		return userDomain;
	}
}