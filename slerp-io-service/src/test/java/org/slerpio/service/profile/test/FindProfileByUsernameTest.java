package org.slerpio.service.profile.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slerp.core.Domain;
import org.slerp.core.business.BusinessFunction;
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
@TestExecutionListeners(listeners = {DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DependencyInjectionTestExecutionListener.class}, inheritListeners = false)
@Rollback
public class FindProfileByUsernameTest
		extends
			AbstractTransactionalJUnit4SpringContextTests {

	static private Logger log = LoggerFactory
			.getLogger(FindProfileByUsernameTest.class);
	@Autowired
	BusinessFunction findProfileByUsername;

	@Before
	public void prepare() {
		executeSqlScript(
				"classpath:org/slerpio/service/profile/test/FindProfileByUsernameTest.sql",
				false);
	}

	@Test
	public void testSuccess() {
		String username = "kiditz";
		Domain profileDomain = new Domain();
		profileDomain.put("username", username);
		Domain outputProfile = findProfileByUsername.handle(profileDomain);
		log.info("Result Test {}", outputProfile);
		Assertions.assertThat(profileDomain.get("username"))
				.isEqualTo(username);
	}
}