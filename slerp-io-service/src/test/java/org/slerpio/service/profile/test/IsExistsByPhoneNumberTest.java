package org.slerpio.service.profile.test;

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
@TestExecutionListeners(listeners = { DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DependencyInjectionTestExecutionListener.class }, inheritListeners = false)
@Rollback
public class IsExistsByPhoneNumberTest extends AbstractTransactionalJUnit4SpringContextTests {

	static private Logger log = LoggerFactory.getLogger(IsExistsByPhoneNumberTest.class);
	@Autowired
	BusinessFunction isExistsByPhoneNumber;

	@Before
	public void prepare() {
		//executeSqlScript("classpath:org/slerpio/service/profile/test/IsExistsByPhoneNumberTest.sql", false);
	}

	@Test
	public void testSuccess() {
		String phoneNumber = "087788044374";
		Domain userProfileDomain = new Domain();
		userProfileDomain.put("phoneNumber", phoneNumber);
		Domain outputUserProfile = isExistsByPhoneNumber.handle(userProfileDomain);
		log.info("Result Test {}", outputUserProfile);
		Assertions.assertThat(userProfileDomain.get("phoneNumber")).isEqualTo(phoneNumber);
	}
}