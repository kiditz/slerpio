package org.slerpio.oauth.service.oauth2;

import org.slerp.core.CoreException;
import org.slerp.core.Domain;
import org.slerp.core.business.DefaultBusinessTransaction;
import org.slerp.core.validation.EmailValidation;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerpio.oauth.OauthConstant;
import org.slerpio.oauth.entity.UserPrincipal;
import org.slerpio.oauth.repository.UserPrincipalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@KeyValidation({ "oldUsername", "newUsername", "email", "enabled" })
@NotBlankValidation({ "oldUsername", "newUsername", "email", "enabled" })
@EmailValidation("email")
public class EditUserPrincipal extends DefaultBusinessTransaction {

	@Autowired
	UserPrincipalRepository userPrincipalRepository;

	@Override
	public void prepare(Domain userPrincipalDomain) throws Exception {
		UserPrincipal userPrincipal = userPrincipalRepository
				.findUserPrincipalByUsername(userPrincipalDomain.getString("oldUsername"));
		if (userPrincipal == null) {
			throw new CoreException(OauthConstant.USER_NOT_FOUND);
		}
		userPrincipalDomain.put("userPrincipal", userPrincipal);
	}

	@Override
	public Domain handle(Domain userPrincipalDomain) {
		super.handle(userPrincipalDomain);
		try {
			UserPrincipal userPrincipal = userPrincipalDomain.getDomain("userPrincipal").convertTo(UserPrincipal.class);
			userPrincipal.setUsername(userPrincipalDomain.getString("newUsername"));
			userPrincipal.setEnabled(userPrincipalDomain.getBoolean("enabled"));
			userPrincipal.setEmail(userPrincipalDomain.getString("email"));
			userPrincipal.setUserAuthorityList(null);
			userPrincipal = userPrincipalRepository.save(userPrincipal);
			return new Domain(userPrincipal);
		} catch (Exception e) {
			throw new CoreException(e);
		}
	}
}