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
import org.assertj.core.api.Assertions;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@TestExecutionListeners(listeners = {DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DependencyInjectionTestExecutionListener.class}, inheritListeners = false)
@Rollback
public class EditProfileTest
		extends
			AbstractTransactionalJUnit4SpringContextTests {

	static private Logger log = LoggerFactory.getLogger(EditProfileTest.class);
	@Autowired
	BusinessTransaction editProfile;

	@Before
	public void prepare() {
		executeSqlScript(
				"classpath:org/slerpio/service/profile/test/EditProfileTest.sql",
				false);
	}

	@Test
	public void testSuccess() {
		String phoneNumber = "087788044374";
		Domain address = new Domain();
		address.put("address", "Jakarta Barat");
		Long profileId = 1l;
		String email = "kiditzbastara@gmail.com";
		String imagePath = "default.png";
		String username = "kiditz";
		String fullname = "Rifky Aditya Bastara";
		Domain profileDomain = new Domain();
		profileDomain.put("phoneNumber", phoneNumber);
		profileDomain.put("address", address);
		profileDomain.put("profileId", profileId);
		profileDomain.put("email", email);
		profileDomain.put("imagePath", imagePath);
		profileDomain.put("username", username);
		profileDomain.put("fullname", fullname);
		Domain outputProfile = editProfile.handle(profileDomain);
		log.info("Result Test {}", outputProfile);
		Assertions.assertThat(profileDomain.get("phoneNumber")).isEqualTo(
				phoneNumber);
		Assertions.assertThat(profileDomain.get("profileId")).isEqualTo(
				profileId);
		Assertions.assertThat(profileDomain.get("email")).isEqualTo(email);
		Assertions.assertThat(profileDomain.get("imagePath")).isEqualTo(
				imagePath);
		Assertions.assertThat(profileDomain.get("username"))
				.isEqualTo(username);
		Assertions.assertThat(profileDomain.get("fullname"))
				.isEqualTo(fullname);
	}
}