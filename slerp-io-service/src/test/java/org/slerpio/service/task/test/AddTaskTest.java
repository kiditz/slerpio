
package org.slerpio.service.task.test;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slerp.core.Domain;
import org.slerp.core.business.BusinessTransaction;
import org.slerp.core.utils.StreamUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
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
public class AddTaskTest extends AbstractTransactionalJUnit4SpringContextTests {

	static private Logger log = LoggerFactory.getLogger(AddTaskTest.class);
	@Autowired
	BusinessTransaction addTask;
	@Autowired
	ResourceLoader resource;
	@Before
	public void prepare() {
		executeSqlScript(
				"classpath:org/slerpio/service/task/test/AddTaskTest.sql",
				false);
	}

	@Test
	public void testSuccess() throws IOException {
		InputStream in = resource.getResource("classpath:org/slerpio/service/task/test/documents.json").getInputStream();
		String data = StreamUtils.copyStreamToString(in, 1024);
		Domain taskDomain = new Domain(data);
		Domain outputTask = addTask.handle(taskDomain);
		log.info("Result Test {}", outputTask);
	}
}