package org.slerp.oauth.service.oauth2.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slerp.core.Domain;
import org.slerp.core.business.BusinessFunction;
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
public class GetAuthorityByUsernameTest extends AbstractTransactionalJUnit4SpringContextTests {

	static private Logger log = LoggerFactory.getLogger(GetAuthorityByUsernameTest.class);
	@Autowired
	BusinessFunction getAuthorityByUsername;

	@Before
	public void prepare() {
		executeSqlScript("classpath:org/slerpio/oauth/service/oauth2/test/GetAuthorityByUsernameTest.sql", false);
	}

	@Test
	public void testSuccess() {
		String username = "kiditz";
		Domain userAuthorityDomain = new Domain();
		userAuthorityDomain.put("username", username);
		log.info("Request {}", userAuthorityDomain);
		Domain outputUserAuthority = getAuthorityByUsername.handle(userAuthorityDomain);
		log.info("Result Test {}", outputUserAuthority);
	}
}