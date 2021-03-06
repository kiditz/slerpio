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
public class GetStudentNotHaveClassTest extends AbstractTransactionalJUnit4SpringContextTests {

	static private Logger log = LoggerFactory.getLogger(GetStudentNotHaveClassTest.class);
	@Autowired
	BusinessFunction getStudentNotHaveClass;

	@Before
	public void prepare() {
		executeSqlScript("classpath:org/slerpio/service/profile/test/GetStudentNotHaveClassTest.sql", false);
	}

	@Test
	public void testSuccess() {
		Integer size = 10;
		Integer page = 0;
		Long classId = 3l;
		String fullname = "Ezy";
		Domain userProfileDomain = new Domain();
		userProfileDomain.put("size", size);
		userProfileDomain.put("page", page);
		userProfileDomain.put("classId", classId);
		userProfileDomain.put("fullname", fullname);
		Domain outputUserProfile = getStudentNotHaveClass.handle(userProfileDomain);
		log.info("Result Test {}", outputUserProfile);
		Assertions.assertThat(userProfileDomain.get("size")).isEqualTo(size);
		Assertions.assertThat(userProfileDomain.get("page")).isEqualTo(page);
		Assertions.assertThat(userProfileDomain.get("classId")).isEqualTo(classId);
		Assertions.assertThat(userProfileDomain.get("fullname")).isEqualTo(fullname);
	}
}