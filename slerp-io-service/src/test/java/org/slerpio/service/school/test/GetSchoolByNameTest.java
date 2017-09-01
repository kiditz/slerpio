package org.slerpio.service.school.test;

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
public class GetSchoolByNameTest extends AbstractTransactionalJUnit4SpringContextTests {

	static private Logger log = LoggerFactory.getLogger(GetSchoolByNameTest.class);
	@Autowired
	BusinessFunction getSchoolByName;

	@Before
	public void prepare() {
		executeSqlScript("classpath:org/slerpio/service/school/test/GetSchoolByNameTest.sql", false);
	}

	@Test
	public void testSuccess() {
		String schoolName = "SMA";
		Long page = 0l;
		Long size = 10l;
		Domain schoolDomain = new Domain();
		schoolDomain.put("schoolName", schoolName);
		schoolDomain.put("page", page);
		schoolDomain.put("size", size);
		Domain outputSchool = getSchoolByName.handle(schoolDomain);
		log.info("Result Test {}", outputSchool);		
	}
}