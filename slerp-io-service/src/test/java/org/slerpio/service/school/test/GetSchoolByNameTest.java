package org.slerpio.service.school.test;

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
public class GetSchoolByNameTest
		extends
			AbstractTransactionalJUnit4SpringContextTests {

	static private Logger log = LoggerFactory
			.getLogger(GetSchoolByNameTest.class);
	@Autowired
	BusinessFunction getSchoolByName;

	@Before
	public void prepare() {
		executeSqlScript(
				"classpath:org/slerpio/service/school/test/GetSchoolByNameTest.sql",
				false);
	}

	@Test
	public void testSuccess() {
		Integer page = 0;
		String name = "SMAN 95";
		Integer size = 10;
		Domain schoolDomain = new Domain();
		schoolDomain.put("page", page);
		schoolDomain.put("name", name);
		schoolDomain.put("size", size);
		Domain outputSchool = getSchoolByName.handle(schoolDomain);
		log.info("Result Test {}", outputSchool);
		Assertions.assertThat(schoolDomain.get("page")).isEqualTo(page);
		Assertions.assertThat(schoolDomain.get("name")).isEqualTo(name);
		Assertions.assertThat(schoolDomain.get("size")).isEqualTo(size);
	}
}