package org.slerpio.service.profile.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slerp.core.CoreException;
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
@TestExecutionListeners(listeners = { DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DependencyInjectionTestExecutionListener.class }, inheritListeners = false)
@Rollback
public class EditProfileTest extends AbstractTransactionalJUnit4SpringContextTests {

	static private Logger log = LoggerFactory.getLogger(EditProfileTest.class);
	@Autowired
	BusinessTransaction editProfile;

	@Before
	public void prepare() {
		executeSqlScript("classpath:org/slerpio/service/profile/test/EditProfileTest.sql", false);
	}

	@Test
	public void testSuccess() {
		String phoneNumber = "087788044374";
		String oldUsername = "kiditz";
		String email = "kiditzbastara@gmail.com";
		String imagePath = "default.png";
		String newUsername = "rifky";
		String fullname = "Rifky Aditya Bastara";
		String address = "jakarta";
		Domain profileDomain = new Domain();
		profileDomain.put("phoneNumber", phoneNumber);
		profileDomain.put("address", address);
		profileDomain.put("oldUsername", oldUsername);
		profileDomain.put("email", email);
		profileDomain.put("imagePath", imagePath);
		profileDomain.put("newUsername", newUsername);
		profileDomain.put("fullname", fullname);
		Domain outputProfile = editProfile.handle(profileDomain);
		log.info("Result Test {}", outputProfile);
		Assertions.assertThat(outputProfile.get("phoneNumber")).isEqualTo(phoneNumber);
		Assertions.assertThat(outputProfile.get("email")).isEqualTo(email);
		Assertions.assertThat(outputProfile.get("imagePath")).isEqualTo(imagePath);
		Assertions.assertThat(outputProfile.get("fullname")).isEqualTo(fullname);
		Assertions.assertThat(outputProfile.get("username")).isEqualTo(newUsername);
	}

	@Test(expected = CoreException.class)
	public void testFailUsernameHasBeenUsed() {
		String phoneNumber = "087788044374";
		String oldUsername = "kiditz";
		String email = "kiditzbastara@gmail.com";
		String imagePath = "default.png";
		String newUsername = "kiditz";
		String fullname = "Rifky Aditya Bastara";
		String address = "jakarta";
		Domain profileDomain = new Domain();
		profileDomain.put("phoneNumber", phoneNumber);
		profileDomain.put("address", address);
		profileDomain.put("oldUsername", oldUsername);
		profileDomain.put("email", email);
		profileDomain.put("imagePath", imagePath);
		profileDomain.put("newUsername", newUsername);
		profileDomain.put("fullname", fullname);
		Domain outputProfile = editProfile.handle(profileDomain);
		log.info("Result Test {}", outputProfile);
	}
	@Test(expected = CoreException.class)
	public void testFailUsernameNotFound() {
		String phoneNumber = "087788044374";
		String oldUsername = "unknown";
		String email = "kiditzbastara@gmail.com";
		String imagePath = "default.png";
		String newUsername = "	";
		String fullname = "Rifky Aditya Bastara";
		String address = "jakarta";
		Domain profileDomain = new Domain();
		profileDomain.put("phoneNumber", phoneNumber);
		profileDomain.put("address", address);
		profileDomain.put("oldUsername", oldUsername);
		profileDomain.put("email", email);
		profileDomain.put("imagePath", imagePath);
		profileDomain.put("newUsername", newUsername);
		profileDomain.put("fullname", fullname);
		Domain outputProfile = editProfile.handle(profileDomain);
		log.info("Result Test {}", outputProfile);
	}
}