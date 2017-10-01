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
public class FindSchoolByUsernameTest
		extends
			AbstractTransactionalJUnit4SpringContextTests {

	static private Logger log = LoggerFactory
			.getLogger(FindSchoolByUsernameTest.class);
	@Autowired
	BusinessFunction findSchoolByUsername;

	@Before
	public void prepare() {
		executeSqlScript(
				"classpath:org/slerpio/service/profile/test/FindSchoolByUsernameTest.sql",
				false);
	}

	@Test
	public void testSuccess() {
		Integer size = 10;
		String username = "adit";
		Integer page = 0;
		Domain profileDomain = new Domain();
		profileDomain.put("size", size);
		profileDomain.put("username", username);
		profileDomain.put("page", page);
		Domain outputProfile = findSchoolByUsername.handle(profileDomain);
		log.info("Result Test {}", outputProfile);
		Assertions.assertThat(profileDomain.get("size")).isEqualTo(size);
		Assertions.assertThat(profileDomain.get("username"))
				.isEqualTo(username);
		Assertions.assertThat(profileDomain.get("page")).isEqualTo(page);
	}
}