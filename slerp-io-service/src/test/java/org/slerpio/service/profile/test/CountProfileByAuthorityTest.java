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
@TestExecutionListeners(listeners = { DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DependencyInjectionTestExecutionListener.class }, inheritListeners = false)
@Rollback
public class CountProfileByAuthorityTest extends AbstractTransactionalJUnit4SpringContextTests {

	static private Logger log = LoggerFactory.getLogger(CountProfileByAuthorityTest.class);
	@Autowired
	BusinessFunction countProfileByAuthority;

	@Before
	public void prepare() {
		executeSqlScript("classpath:org/slerpio/service/profile/test/CountProfileByAuthorityTest.sql", false);
	}

	@Test
	public void testCountFromAuthorityTeacher() {
		String authority = "TEACHER";
		Domain profileDomain = new Domain();
		profileDomain.put("authority", authority);
		Domain outputProfile = countProfileByAuthority.handle(profileDomain);
		log.info("Result Test {}", outputProfile);
		Assertions.assertThat(outputProfile.getLong("counter")).isEqualTo(1l);
	}
	
	@Test
	public void testCountFromAuthorityStudent() {
		String authority = "STUDENT";
		Domain profileDomain = new Domain();
		profileDomain.put("authority", authority);
		Domain outputProfile = countProfileByAuthority.handle(profileDomain);
		log.info("Result Test {}", outputProfile);
		Assertions.assertThat(outputProfile.getLong("counter")).isEqualTo(2l);
	}
}