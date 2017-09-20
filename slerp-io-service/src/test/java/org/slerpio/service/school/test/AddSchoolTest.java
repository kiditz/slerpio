package org.slerpio.service.school.test;

import java.math.BigDecimal;
import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slerp.core.Domain;
import org.slerp.core.business.BusinessTransaction;
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
public class AddSchoolTest extends AbstractTransactionalJUnit4SpringContextTests {

	static private Logger log = LoggerFactory.getLogger(AddSchoolTest.class);
	@Autowired
	BusinessTransaction addSchool;

	@Before
	public void prepare() {
		executeSqlScript("classpath:org/slerpio/service/school/test/AddSchoolTest.sql", false);
	}

	@Test
	public void testSuccess() {
		BigDecimal latitude = BigDecimal.valueOf(-99);
		String description = "SMA 56 Jakarta";
		BigDecimal longitude = BigDecimal.valueOf(-99);
		Date buildAt = new Date();
		String address = "Jakarta";
		String name = "SMA 56 Jakarta";
		Domain schoolDomain = new Domain();
		schoolDomain.put("latitude", latitude);
		schoolDomain.put("description", description);
		schoolDomain.put("longitude", longitude);
		schoolDomain.put("buildAt", buildAt);
		schoolDomain.put("address", address);
		schoolDomain.put("name", name);
		Domain outputSchool = addSchool.handle(schoolDomain);
		log.info("Result Test {}", outputSchool);		
		Assertions.assertThat(outputSchool.get("description")).isEqualTo(description);
		Assertions.assertThat(outputSchool.get("buildAt")).isEqualTo(buildAt.getTime());
		Assertions.assertThat(outputSchool.get("address")).isEqualTo(address);
		Assertions.assertThat(outputSchool.get("name")).isEqualTo(name);
	}
}