package org.slerp.oauth.service.oauth2.test;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slerp.core.Domain;
import org.slerp.core.business.BusinessFunction;
import org.slerp.core.business.BusinessTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@TestExecutionListeners(listeners = { DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DependencyInjectionTestExecutionListener.class }, inheritListeners = false)
@Rollback
public class EditUserPrincipalTest extends AbstractTransactionalJUnit4SpringContextTests {

	static private Logger log = LoggerFactory.getLogger(EditUserPrincipalTest.class);
	@Autowired
	BusinessTransaction editUserPrincipal;
	@Autowired
	BusinessFunction findUserPrincipalByUsername;

	@Before
	public void prepare() {
		executeSqlScript("classpath:org/slerpio/oauth/service/oauth2/test/EditUserPrincipalTest.sql", false);
	}

	@Test
	public void testSuccess() {
		String username = "aditya";
		String email = "kiditzbastara@gmail.com";
		Boolean credentialsNonExpired = true;
		Domain userPrincipalDomain = new Domain();
		userPrincipalDomain.put("username", username);
		Domain user = findUserPrincipalByUsername.handle(userPrincipalDomain).getDomain("userPrincipal");
		user.put("password", "rioters7");
		user.put("username", "kiditz");
		user.put("email", email);
		log.info("Request User {}", user);
		Domain outputUserPrincipal = editUserPrincipal.handle(user);
		log.info("Result Test {}", outputUserPrincipal);
		Assertions.assertThat(outputUserPrincipal.get("username")).isEqualTo("kiditz");
		Assertions.assertThat(outputUserPrincipal.get("email")).isEqualTo(email);
		Assertions.assertThat(outputUserPrincipal.get("credentialsNonExpired")).isEqualTo(credentialsNonExpired);
	}
}