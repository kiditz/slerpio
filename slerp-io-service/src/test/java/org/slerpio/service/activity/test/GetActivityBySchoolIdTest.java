package org.slerpio.service.activity.test;

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
public class GetActivityBySchoolIdTest
		extends
			AbstractTransactionalJUnit4SpringContextTests {

	static private Logger log = LoggerFactory
			.getLogger(GetActivityBySchoolIdTest.class);
	@Autowired
	BusinessFunction getActivityBySchoolId;

	@Before
	public void prepare() {
		executeSqlScript(
				"classpath:org/slerpio/service/activity/test/GetActivityBySchoolIdTest.sql",
				false);
	}

	@Test
	public void testSuccess() {
		Integer page = 0;
		Long schoolId = 1l;
		Integer size = 10;
		Domain activityDomain = new Domain();
		activityDomain.put("page", page);
		activityDomain.put("schoolId", schoolId);
		activityDomain.put("size", size);
		Domain outputActivity = getActivityBySchoolId.handle(activityDomain);
		log.info("Result Test {}", outputActivity);
		Assertions.assertThat(activityDomain.get("page")).isEqualTo(page);
		Assertions.assertThat(activityDomain.get("size")).isEqualTo(size);
	}
}