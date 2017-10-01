package org.slerpio.service.activity.test;

import java.util.Calendar;
import java.util.Date;

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
public class GetActivityBetweenDateTest extends AbstractTransactionalJUnit4SpringContextTests {

	static private Logger log = LoggerFactory.getLogger(GetActivityBetweenDateTest.class);
	@Autowired
	BusinessFunction getActivityBetweenDate;

	@Before
	public void prepare() {
		executeSqlScript("classpath:org/slerpio/service/activity/test/GetActivityBetweenDateTest.sql", false);
	}

	@Test
	public void testSuccess() {
		Integer size = 10;
		Integer page = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, 24);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date today = calendar.getTime();
		
		calendar.add(Calendar.DATE, -1);		
		Date yesterday = calendar.getTime();
		Domain activityDomain = new Domain();
		log.info("Today : {}", today);
		log.info("Yesterday : {}", yesterday);
		activityDomain.put("size", size);
		activityDomain.put("page", page);
		activityDomain.put("endDate", today);
		activityDomain.put("startDate", yesterday);
		Domain outputActivity = getActivityBetweenDate.handle(activityDomain);
		log.info("Result Test {}", outputActivity);
	}
}