package org.slerpio.oauth.service.oauth2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slerp.core.CoreException;
import org.slerp.core.Domain;
import org.slerp.core.business.DefaultBusinessTransaction;
import org.slerp.core.validation.EmailValidation;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerp.core.validation.Patterns;
import org.slerpio.oauth.OauthConstant;
import org.slerpio.oauth.entity.UserAuthority;
import org.slerpio.oauth.entity.UserPrincipal;
import org.slerpio.oauth.repository.UserPrincipalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@KeyValidation({ "phoneNumber", "password", "email", "userAuthorityList" })
@NotBlankValidation({ "phoneNumber", "password", "email" })
public class AddUserPrincipal extends DefaultBusinessTransaction {

	@Autowired
	UserPrincipalRepository userPrincipalRepository;

	@Override
	public void prepare(Domain userPrincipalDomain) throws Exception {
		userPrincipalDomain.put("accountNonExpired", true);
		userPrincipalDomain.put("accountNonLocked", true);
		userPrincipalDomain.put("credentialsNonExpired", true);
		userPrincipalDomain.put("enabled", true);
		userPrincipalDomain.put("hashedPassword", userPrincipalDomain.getString("password").getBytes());
		if (userPrincipalRepository
				.findUserPrincipalByPhoneNumber(userPrincipalDomain.getString("phoneNumber")) != null) {
			throw new CoreException(OauthConstant.PHONE_NUMBER_HASBEEN_USED);
		}
		if (!Patterns.PHONE.matcher(userPrincipalDomain.getString("phoneNumber")).matches()) {
			throw new CoreException(OauthConstant.INVALID_PHONE_NUMBER);
		}
		List<Domain> userAuthorityList = userPrincipalDomain.getList("userAuthorityList");
		if (userAuthorityList == null || userAuthorityList.isEmpty())
			throw new CoreException(OauthConstant.DOES_NOT_HAVE_AUTHORITY);

	}

	@Override
	public Domain handle(Domain userPrincipalDomain) {
		super.handle(userPrincipalDomain);
		UserPrincipal userPrincipal = userPrincipalDomain.convertTo(UserPrincipal.class);
		userPrincipal.setActivationCode(UUID.randomUUID().toString().substring(0, 8));
		userPrincipal.setCreatedAt(new Date());
		userPrincipal.setUpdateAt(new Date());
		List<Domain> authoritiesDomain = userPrincipalDomain.getList("userAuthorityList");
		List<UserAuthority> authorities = new ArrayList<>();
		for (Domain domain : authoritiesDomain) {
			authorities.add(new UserAuthority(domain.getString("authority"), userPrincipal));
		}
		userPrincipal.setUserAuthorityList(authorities);
		userPrincipal = userPrincipalRepository.save(userPrincipal);
		return new Domain(userPrincipal);
	}
}
