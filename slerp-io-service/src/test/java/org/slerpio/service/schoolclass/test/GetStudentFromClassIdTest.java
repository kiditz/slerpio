package org.slerpio.service.schoolclass.test;

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
public class GetStudentFromClassIdTest
		extends
			AbstractTransactionalJUnit4SpringContextTests {

	static private Logger log = LoggerFactory
			.getLogger(GetStudentFromClassIdTest.class);
	@Autowired
	BusinessFunction getStudentFromClassId;

	@Before
	public void prepare() {
		executeSqlScript(
				"classpath:org/slerpio/service/schoolclass/test/GetStudentFromClassIdTest.sql",
				false);
	}

	@Test
	public void testSuccess() {
		Integer page = 0;
		Long schoolClassId = 1l;
		Integer size = 10;
		Domain schoolClassDomain = new Domain();
		schoolClassDomain.put("page", page);
		schoolClassDomain.put("schoolClassId", schoolClassId);
		schoolClassDomain.put("size", size);
		Domain outputSchoolClass = getStudentFromClassId
				.handle(schoolClassDomain);
		log.info("Result Test {}", outputSchoolClass);
		Assertions.assertThat(schoolClassDomain.get("page")).isEqualTo(page);
		Assertions.assertThat(schoolClassDomain.get("schoolClassId"))
				.isEqualTo(schoolClassId);
		Assertions.assertThat(schoolClassDomain.get("size")).isEqualTo(size);
	}
}