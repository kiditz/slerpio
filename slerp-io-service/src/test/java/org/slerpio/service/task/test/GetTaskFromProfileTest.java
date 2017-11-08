package org.slerpio.service.task.test;

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
public class GetTaskFromProfileTest extends AbstractTransactionalJUnit4SpringContextTests {

	static private Logger log = LoggerFactory.getLogger(GetTaskFromProfileTest.class);
	@Autowired
	BusinessFunction getTaskFromProfile;

	@Before
	public void prepare() {
		executeSqlScript("classpath:org/slerpio/service/task/test/GetTaskFromProfileTest.sql", false);
	}

	@Test
	public void testSuccess() {
		Integer size = 10;
		Integer page = 0;
		Long classId = 4l;
		Domain taskDomain = new Domain();
		taskDomain.put("size", size);
		taskDomain.put("page", page);
		taskDomain.put("profileId", classId);
		Domain outputTask = getTaskFromProfile.handle(taskDomain);
		log.info("Result Test {}", outputTask);				
	}
}