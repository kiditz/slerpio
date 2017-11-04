package org.slerpio.service.schoolclass.test;

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
@TestExecutionListeners(listeners = {DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DependencyInjectionTestExecutionListener.class}, inheritListeners = false)
@Rollback
public class AddStudentToClassTest extends AbstractTransactionalJUnit4SpringContextTests {

	static private Logger log = LoggerFactory
			.getLogger(AddStudentToClassTest.class);
	@Autowired
	BusinessTransaction addStudentToClass;

	@Before
	public void prepare() {		
		
		executeSqlScript(
				"classpath:org/slerpio/service/schoolclass/test/AddStudentToClassTest.sql",
				false);
	}

	@Test
	public void testSuccess() {
		Long classId = 1l;
		Long profileId = 3l;
		Domain schoolClassDomain = new Domain();
		schoolClassDomain.put("classId", classId);
		schoolClassDomain.put("profileId", profileId);
		Domain outputSchoolClass = addStudentToClass.handle(schoolClassDomain);
		log.info("Result Test {}", outputSchoolClass);
		Assertions.assertThat(schoolClassDomain.get("classId")).isEqualTo(
				classId);
		Assertions.assertThat(schoolClassDomain.get("profileId")).isEqualTo(
				profileId);
	}
}