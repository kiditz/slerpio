package org.slerp.oauth.security;

import org.slerp.core.CoreException;
import org.slerp.core.Domain;
import org.slerp.core.business.BusinessFunction;
import org.slerp.oauth.entity.UserPrincipal;
import org.slerp.oauth.model.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserSecurityService {
	@Autowired
	BusinessFunction findUserPrincipalByUsername;

	@Override
	public UserCredentials loadUserByUsername(String username) {
		Domain userPrincipalDomain = new Domain();
		userPrincipalDomain.put("username", username);
		Domain outputUserPrincipal = findUserPrincipalByUsername.handle(userPrincipalDomain);
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

	// private static final SecureRandom RANDOM;
	// private static final int HASHING_ROUNDS = 10;
	// static {
	// try {
	// RANDOM = SecureRandom.getInstanceStrong();
	// } catch (NoSuchAlgorithmException e) {
	// throw new IllegalStateException(e);
	// }
	// }
	//
	// public static void main(String[] args) {
	// String salt = BCrypt.gensalt(HASHING_ROUNDS);
	// String password = BCrypt.hashpw("rioters7", salt);
	// System.err.println(password);
	// }
}
