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
@TestExecutionListeners(listeners = {DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DependencyInjectionTestExecutionListener.class}, inheritListeners = false)
@Rollback
public class GetTaskFromSchoolTest extends AbstractTransactionalJUnit4SpringContextTests {

	static private Logger log = LoggerFactory
			.getLogger(GetTaskFromSchoolTest.class);
	@Autowired
	BusinessFunction getTaskFromSchool;

	@Before
	public void prepare() {
		executeSqlScript(
				"classpath:org/slerpio/service/task/test/GetTaskFromSchoolTest.sql",
				false);
	}

	@Test
	public void testSuccess() {
		Integer page = 0;
		Integer size = 10;
		Long schoolId = 1l;
		Domain taskDomain = new Domain();
		taskDomain.put("page", page);
		taskDomain.put("size", size);
		taskDomain.put("schoolId", schoolId);
		Domain outputTask = getTaskFromSchool.handle(taskDomain);
		log.info("Result Test {}", outputTask);
		Assertions.assertThat(taskDomain.get("page")).isEqualTo(page);
		Assertions.assertThat(taskDomain.get("size")).isEqualTo(size);
	}
}