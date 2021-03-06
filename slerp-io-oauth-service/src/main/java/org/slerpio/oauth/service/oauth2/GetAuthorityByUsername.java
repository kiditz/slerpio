package org.slerpio.oauth.service.oauth2;

import org.slerp.core.business.DefaultBusinessFunction;
import org.springframework.stereotype.Service;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NumberValidation;
import org.slerpio.oauth.entity.UserAuthority;
import org.slerpio.oauth.repository.UserAuthorityRepository;
import org.slerp.core.validation.NotBlankValidation;
import org.slerp.core.Domain;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
@KeyValidation("username")
@NumberValidation({})
@NotBlankValidation({"username"})
public class GetAuthorityByUsername extends DefaultBusinessFunction {

	@Autowired
	UserAuthorityRepository userAuthorityRepository;

	@Override
	public Domain handle(Domain userAuthorityDomain) {
		List<UserAuthority> userAuthorityList = userAuthorityRepository
				.getAuthorityByUsername(userAuthorityDomain.getString("username"));
		return new Domain().put("userAuthorityList", userAuthorityList);
	}
}