package org.slerpio.service.activity.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slerp.core.Domain;
import org.slerp.core.business.BusinessTransaction;
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
public class AddActivityTest extends AbstractTransactionalJUnit4SpringContextTests {

	static private Logger log = LoggerFactory.getLogger(AddActivityTest.class);
	@Autowired
	BusinessTransaction addActivity;

	@Before
	public void prepare() {
		executeSqlScript("classpath:org/slerpio/service/activity/test/AddActivityTest.sql", false);
	}

	@Test
	public void testSuccess() {
		String title = "Ibu guru Handayani membuat soal Matematika";
		String content = "Soal Matematika untuk kelas 6 sd di kumpulkan hari senin";
		Domain activityDomain = new Domain();
		activityDomain.put("title", title);
		activityDomain.put("content", content);
		Domain outputActivity = addActivity.handle(activityDomain);
		log.info("Result Test {}", outputActivity);
		Assertions.assertThat(activityDomain.get("title")).isEqualTo(title);
		Assertions.assertThat(activityDomain.get("content")).isEqualTo(content);
	}
}