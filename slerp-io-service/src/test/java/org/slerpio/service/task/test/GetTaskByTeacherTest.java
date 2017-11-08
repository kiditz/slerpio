package org.slerpio.service.task.test;

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
public class GetTaskByTeacherTest extends AbstractTransactionalJUnit4SpringContextTests {

	static private Logger log = LoggerFactory.getLogger(GetTaskByTeacherTest.class);
	@Autowired
	BusinessFunction getTaskByTeacher;

	@Before
	public void prepare() {
		executeSqlScript("classpath:org/slerpio/service/task/test/GetTaskByTeacherTest.sql", false);
	}

	@Test
	public void testSuccess() {
		Long profileId = 3l;
		Integer size = 10;
		Integer page = 0;
		Domain taskDomain = new Domain();
		taskDomain.put("profileId", profileId);
		taskDomain.put("size", size);
		taskDomain.put("page", page);
		Domain outputTask = getTaskByTeacher.handle(taskDomain);
		log.info("Result Test {}", outputTask);
		Assertions.assertThat(taskDomain.get("profileId")).isEqualTo(profileId);
		Assertions.assertThat(taskDomain.get("size")).isEqualTo(size);
		Assertions.assertThat(taskDomain.get("page")).isEqualTo(page);
	}
}