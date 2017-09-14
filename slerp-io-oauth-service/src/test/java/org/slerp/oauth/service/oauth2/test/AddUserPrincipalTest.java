package org.slerp.oauth.service.oauth2.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slerp.core.CoreException;
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

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@TestExecutionListeners(listeners = { DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DependencyInjectionTestExecutionListener.class }, inheritListeners = false)
@Rollback
public class AddUserPrincipalTest extends AbstractTransactionalJUnit4SpringContextTests {
	static private Logger log = LoggerFactory.getLogger(AddUserPrincipalTest.class);
	@Autowired
	BusinessTransaction addUserPrincipal;

	@Before
	public void prepare() {		
		executeSqlScript("classpath:org/slerpio/oauth/service/oauth2/test/AddUserPrincipalTest.sql", false);
	}

	@Test
	public void testSuccess() {
		byte[] password = "rioters7".getBytes();
		String username = "kiditz";
		String email = "kiditzbastara@gmail.com";
		Domain userPrincipalDomain = new Domain();
		userPrincipalDomain.put("password", password);
		userPrincipalDomain.put("username", username);
		userPrincipalDomain.put("email", email);
		List<Domain> userAuthorityList = new ArrayList<>();
		userAuthorityList.add(new Domain().put("authority", "STUDENT"));
		userPrincipalDomain.put("userAuthorityList", userAuthorityList);
		Domain outputUserPrincipal = addUserPrincipal.handle(userPrincipalDomain);
		log.info("Result Test {}", outputUserPrincipal);
		Assertions.assertThat(outputUserPrincipal.get("username")).isEqualTo(username);
		Assertions.assertThat(outputUserPrincipal.get("email")).isEqualTo(email);
	}

	@Test(expected = CoreException.class)
	public void testUsernameHasbeenUsed() {
		byte[] password = "rioters7".getBytes();
		String username = "aditya";
		String email = "kiditzbastara@gmail.com";
		Domain userPrincipalDomain = new Domain();
		userPrincipalDomain.put("password", password);
		userPrincipalDomain.put("username", username);
		userPrincipalDomain.put("email", email);
		log.info("Request {}", userPrincipalDomain);
		Domain outputUserPrincipal = addUserPrincipal.handle(userPrincipalDomain);
		log.info("Result Test {}", outputUserPrincipal);
		Assertions.assertThat(userPrincipalDomain.get("username")).isEqualTo(username);
		Assertions.assertThat(userPrincipalDomain.get("email")).isEqualTo(email);
		
	}
}