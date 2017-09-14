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
import org.assertj.core.api.Assertions;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@TestExecutionListeners(listeners = { DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DependencyInjectionTestExecutionListener.class }, inheritListeners = false)
@Rollback
public class RemoveUserPrincipalTest extends AbstractTransactionalJUnit4SpringContextTests {

	static private Logger log = LoggerFactory.getLogger(RemoveUserPrincipalTest.class);
	@Autowired
	BusinessTransaction removeUserPrincipal;

	@Before
	public void prepare() {		
		executeSqlScript("classpath:org/slerpio/oauth/service/oauth2/test/RemoveUserPrincipalTest.sql", false);
	}

	@Test
	public void testSuccess() {
		Long userId = 1l;
		Domain userPrincipalDomain = new Domain();
		userPrincipalDomain.put("userId", userId);
		log.info("Request Test {}", userPrincipalDomain);
		Domain outputUserPrincipal = removeUserPrincipal.handle(userPrincipalDomain);
		log.info("Result Test {}", outputUserPrincipal);
		Assertions.assertThat(userPrincipalDomain.get("userId")).isEqualTo(userId);
	}

	@Test(expected = CoreException.class)
	public void testUserNotFound() {
		Long userId = 8l;
		Domain userPrincipalDomain = new Domain();
		userPrincipalDomain.put("userId", userId);
		Domain outputUserPrincipal = removeUserPrincipal.handle(userPrincipalDomain);
		log.info("Result Test {}", outputUserPrincipal);
		Assertions.assertThat(userPrincipalDomain.get("userId")).isEqualTo(userId);
	}
}