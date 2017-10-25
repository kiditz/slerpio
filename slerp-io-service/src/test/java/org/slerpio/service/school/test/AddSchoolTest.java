package org.slerpio.service.school.test;

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
import java.math.BigDecimal;
import java.util.Date;
import org.assertj.core.api.Assertions;

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
		BigDecimal longitude = BigDecimal.valueOf(0);
		Date createdAt = new Date();
		Date updateAt = new Date();
		String description = "SMA 95 Jakarta ada sejak dahulu kala";
		Date activeAt = new Date();
		String name = "SMAN 95 JAKARTA";
		String address = "Jl. 1 Maret";
		String active = "Y";
		BigDecimal latitude = BigDecimal.valueOf(0);
		Domain schoolDomain = new Domain();
		schoolDomain.put("longitude", longitude);
		schoolDomain.put("createdAt", createdAt);
		schoolDomain.put("updateAt", updateAt);
		schoolDomain.put("description", description);
		schoolDomain.put("activeAt", activeAt);
		schoolDomain.put("name", name);
		schoolDomain.put("address", address);
		schoolDomain.put("active", active);
		schoolDomain.put("latitude", latitude);
		Domain outputSchool = addSchool.handle(schoolDomain);
		log.info("Result Test {}", outputSchool);				
		Assertions.assertThat(outputSchool.get("description")).isEqualTo(description);		
		Assertions.assertThat(outputSchool.get("name")).isEqualTo(name);
		Assertions.assertThat(outputSchool.get("address")).isEqualTo(address);
		Assertions.assertThat(outputSchool.get("active")).isEqualTo(active);		
	}
}