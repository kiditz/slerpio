package org.slerpio.service.user.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slerp.core.Domain;
import org.slerp.core.business.BusinessTransaction;
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
@TestExecutionListeners(listeners = {DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DependencyInjectionTestExecutionListener.class}, inheritListeners = false)
@Rollback
public class RemoveUserTest
		extends
			AbstractTransactionalJUnit4SpringContextTests {

	static private Logger log = LoggerFactory.getLogger(RemoveUserTest.class);
	@Autowired
	BusinessTransaction removeUser;

	@Before
	public void prepare() {
		executeSqlScript(
				"classpath:org/slerpio/service/user/test/RemoveUserTest.sql",
				false);
	}

	@Test
	public void testSuccess() {
		String type = "teacher";
		String username = "hidayanti";
		Domain teacherDomain = new Domain();
		teacherDomain.put("type", type);
		teacherDomain.put("username", username);
		Domain outputTeacher = removeUser.handle(teacherDomain);
		log.info("Result Test {}", outputTeacher);
		
	}
}