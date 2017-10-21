package org.slerpio.oauth.security;


import org.slerp.core.CoreException;
import org.slerp.core.Domain;
import org.slerp.core.business.BusinessFunction;
import org.slerpio.oauth.entity.UserPrincipal;
import org.slerpio.oauth.model.UserCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserSecurityService {
	@Autowired
	BusinessFunction findUserPrincipalByPhoneNumber;
	Logger log = LoggerFactory.getLogger(getClass());
	@Override
	public UserCredentials loadUserByUsername(String phoneNumber) {
		Domain userPrincipalDomain = new Domain();
		userPrincipalDomain.put("phoneNumber", phoneNumber);
		log.debug("request : >>", userPrincipalDomain);
		Domain outputUserPrincipal = findUserPrincipalByPhoneNumber.handle(userPrincipalDomain);
		log.debug("user Principal : {}", outputUserPrincipal);
		if (outputUserPrincipal == null)
			throw new CoreException("login.failed.cannot.found.user");
		UserPrincipal principal;
		try {
			principal = outputUserPrincipal.getDomain("userPrincipal").convertTo(UserPrincipal.class);

			UserCredentials credentials = new UserCredentials(principal);
			return credentials;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CoreException("login.failed.cannot.found.user");
		}
	}
}
