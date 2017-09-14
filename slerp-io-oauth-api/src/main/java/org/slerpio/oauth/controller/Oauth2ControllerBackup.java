//
//
//package org.slerpio.oauth.controller;
//
//import org.slerp.core.CoreException;
//import org.slerp.core.Domain;
//import org.slerp.core.business.BusinessFunction;
//import org.slerp.core.business.BusinessTransaction;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCrypt;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class Oauth2ControllerBackup {
//
//	Logger log = LoggerFactory.getLogger(getClass());
//	@Autowired
//	BusinessTransaction addUserPrincipal;
//	@Autowired
//	BusinessFunction findUserPrincipalByUsername;
//	@Autowired
//	BusinessFunction getAuthorityByUsername;
//	@Autowired
//	BusinessTransaction removeUserPrincipal;
//	@Autowired
//	BusinessTransaction editUserPrincipal;	
//	@PostMapping("/register")
//	@ResponseBody
//	public Domain addUserPrincipal(@RequestBody Domain oauth2Domain) throws CoreException {
//		String salt = BCrypt.gensalt(10);
//		oauth2Domain.put("password", BCrypt.hashpw(oauth2Domain.getString("password"), salt));
//		Domain outputDomain = addUserPrincipal.handle(oauth2Domain);
//		Domain resultDomain = new Domain();
//		resultDomain.put("username", outputDomain.getString("username"));
//		resultDomain.put("success", true);
//		return resultDomain;
//	}
//	
//	@GetMapping("authorities")
//	public Domain getUserAuthority(@RequestParam("username") String username) {
//		Domain inputDomain = new Domain().put("username", username);
//		return getAuthorityByUsername.handle(inputDomain);
//	}
//
//	@PutMapping("editUser")
//	public Domain updateUser(@RequestParam("username") String username, @RequestBody Domain userDomain) {
//		String salt = BCrypt.gensalt(10);
//		Domain user = findUserPrincipalByUsername.handle(userDomain.put("username", username)).getDomain("userPrincipal");
//		user.put("email", userDomain.getString("email"));
//		user.put("username", userDomain.getString("username"));
//		user.put("password", BCrypt.hashpw(userDomain.getString("password"), salt));
//		return editUserPrincipal.handle(userDomain);
//	}
//}
