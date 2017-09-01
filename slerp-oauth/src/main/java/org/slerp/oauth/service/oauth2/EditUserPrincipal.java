package org.slerp.oauth.service.oauth2;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.slerp.oauth.repository.UserPrincipalRepository;
import org.slerp.core.Domain;
import org.slerp.oauth.entity.UserPrincipal;
import org.slerp.core.CoreException;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerp.core.validation.NumberValidation;
import org.slerp.core.validation.EmailValidation;
import org.slerp.core.business.DefaultBusinessTransaction;

@Service
@Transactional
@KeyValidation({ "userId", "username", "password", "email", "accountNonExpired", "accountNonLocked",
		"credentialsNonExpired", "enabled" })
@NotBlankValidation({ "username", "password", "email", "accountNonExpired", "accountNonLocked", "credentialsNonExpired",
		"enabled" })
@NumberValidation("userId")
@EmailValidation("email")
public class EditUserPrincipal extends DefaultBusinessTransaction {

	@Autowired
	UserPrincipalRepository userPrincipalRepository;

	@Override
	public void prepare(Domain userPrincipalDomain) throws Exception {
		UserPrincipal userPrincipal = userPrincipalRepository.findOne(userPrincipalDomain.getLong("userId"));
		if (userPrincipal == null) {
			throw new CoreException("org.slerp.oauth.entity.UserPrincipal.notFound");
		}
		userPrincipalDomain.put("hashedPassword", userPrincipalDomain.getString("password").getBytes());
	}

	@Override
	public Domain handle(Domain userPrincipalDomain) {
		super.handle(userPrincipalDomain);
		try {
			UserPrincipal userPrincipal = userPrincipalDomain.convertTo(UserPrincipal.class);
			userPrincipal = userPrincipalRepository.save(userPrincipal);
			return new Domain(userPrincipal);
		} catch (Exception e) {
			throw new CoreException(e);
		}
	}
}