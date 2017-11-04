package org.slerpio.service.schoolclass.test;

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
public class GetSchoolClassByProfileIdTest
		extends
			AbstractTransactionalJUnit4SpringContextTests {

	static private Logger log = LoggerFactory
			.getLogger(GetSchoolClassByProfileIdTest.class);
	@Autowired
	BusinessFunction getSchoolClassByProfileId;

	@Before
	public void prepare() {
		executeSqlScript(
				"classpath:org/slerpio/service/schoolclass/test/GetSchoolClassByProfileIdTest.sql",
				false);
	}

	@Test
	public void testSuccess() {
		Long profileId = 1l;
		Domain schoolClassDomain = new Domain();
		schoolClassDomain.put("profileId", profileId);
		Domain outputSchoolClass = getSchoolClassByProfileId
				.handle(schoolClassDomain);
		log.info("Result Test {}", outputSchoolClass);
		Assertions.assertThat(schoolClassDomain.get("profileId")).isEqualTo(
				profileId);
	}
}