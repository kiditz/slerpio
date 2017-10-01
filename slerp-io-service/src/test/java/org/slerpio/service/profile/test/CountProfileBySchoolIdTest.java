package org.slerpio.service.school.test;

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
public class CountProfileBySchoolIdTest
		extends
			AbstractTransactionalJUnit4SpringContextTests {

	static private Logger log = LoggerFactory
			.getLogger(CountProfileBySchoolIdTest.class);
	@Autowired
	BusinessFunction countProfileBySchoolId;

	@Before
	public void prepare() {
		executeSqlScript(
				"classpath:org/slerpio/service/school/test/CountProfileBySchoolIdTest.sql",
				false);
	}

	@Test
	public void testSuccess() {
		Long schoolId = 1l;
		Domain profileDomain = new Domain();
		profileDomain.put("schoolId", schoolId);
		Domain outputProfile = countProfileBySchoolId.handle(profileDomain);
		Assertions.assertThat(outputProfile.getLong("counter")).isEqualTo(2l);
		log.info("Result Test {}", outputProfile);
	}
}