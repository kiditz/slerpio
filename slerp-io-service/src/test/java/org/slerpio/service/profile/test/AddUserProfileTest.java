package org.slerpio.service.profile.test;

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
public class AddUserProfileTest extends AbstractTransactionalJUnit4SpringContextTests {

	static private Logger log = LoggerFactory.getLogger(AddUserProfileTest.class);
	@Autowired
	BusinessTransaction addUserProfile;

	@Before
	public void prepare() {
		executeSqlScript("classpath:org/slerpio/service/profile/test/AddUserProfileTest.sql", false);
	}

	@Test
	public void testSuccess() {

		String address = "Jakarta";
		String active = "Y";
		Date activeAt = new Date();
		String phoneNumber = "087788044374";
		String fullname = "Rifky Aditya Bastara";
		BigDecimal latitude = BigDecimal.valueOf(0);
		BigDecimal longitude = BigDecimal.valueOf(0);
		Date updateAt = new Date();
		Date createdAt = new Date();
		String gender = "L";

		Domain schoolId = new Domain();
		schoolId.put("schoolId", 1);
		Domain userProfileDomain = new Domain();
		userProfileDomain.put("address", address);
		userProfileDomain.put("active", active);
		userProfileDomain.put("activeAt", activeAt);
		userProfileDomain.put("phoneNumber", phoneNumber);
		
		userProfileDomain.put("fullname", fullname);
		userProfileDomain.put("longitude", longitude);
		userProfileDomain.put("latitude", latitude);
		
		userProfileDomain.put("schoolId", schoolId);
		userProfileDomain.put("gender", gender);
		userProfileDomain.put("updateAt", updateAt);
		userProfileDomain.put("createdAt", createdAt);
		log.info("Request Test >>> {}", userProfileDomain);
		Domain outputUserProfile = addUserProfile.handle(userProfileDomain);
		log.info("Result Test {}", outputUserProfile);
		Assertions.assertThat(userProfileDomain.get("active")).isEqualTo(active);
		Assertions.assertThat(userProfileDomain.get("phoneNumber")).isEqualTo(phoneNumber);
		Assertions.assertThat(userProfileDomain.get("fullname")).isEqualTo(fullname);
		Assertions.assertThat(userProfileDomain.get("latitude")).isEqualTo(latitude);
		Assertions.assertThat(userProfileDomain.get("gender")).isEqualTo(gender);
		Assertions.assertThat(userProfileDomain.get("createdAt")).isEqualTo(createdAt);
	}
}