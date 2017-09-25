package org.slerpio.oauth.service.oauth2.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slerp.core.Domain;
import org.slerp.core.business.BusinessTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.junit.Before;
import org.junit.Test;
import org.assertj.core.api.Assertions;

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

	@Before
	public void prepare() {
		executeSqlScript("classpath:org/slerpio/oauth/service/oauth2/test/EditUserPrincipalTest.sql", false);
	}

	@Test
	public void testSuccess() {
		String username = "kiditz";
		String password = "rioters7";
		String newUsername = "rifky";
		Boolean enabled = true;
		String email = "kiditzbastara@gmail.com";
		Domain userPrincipalDomain = new Domain();
		userPrincipalDomain.put("oldUsername", username);
		userPrincipalDomain.put("newUsername", newUsername);
		userPrincipalDomain.put("password", password);
		userPrincipalDomain.put("enabled", enabled);
		userPrincipalDomain.put("email", email);
		Domain outputUserPrincipal = editUserPrincipal.handle(userPrincipalDomain);
		log.info("Result Test {}", outputUserPrincipal);
		Assertions.assertThat(userPrincipalDomain.get("password")).isEqualTo(password);
		Assertions.assertThat(userPrincipalDomain.get("enabled")).isEqualTo(enabled);
		Assertions.assertThat(userPrincipalDomain.get("email")).isEqualTo(email);
	}
}