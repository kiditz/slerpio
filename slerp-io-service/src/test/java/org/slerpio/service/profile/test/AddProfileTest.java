package org.slerpio.service.profile.test;

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
public class AddProfileTest extends AbstractTransactionalJUnit4SpringContextTests {

	static private Logger log = LoggerFactory.getLogger(AddProfileTest.class);
	@Autowired
	BusinessTransaction addProfile;

	@Before
	public void prepare() {
		executeSqlScript("classpath:org/slerpio/service/profile/test/AddProfileTest.sql", false);
	}

	@Test
	public void testSuccess() {
		Domain schoolId = new Domain();
		schoolId.put("schoolId", 1);
		String username = "kiditz";
		String address = "Jakarta Barat";
		String email = "kiditzbastara@gmail.com";
		String phoneNumber = "087788044374";
		String imagePath = "default.png";
		String fullname = "Rifky Aditya Bastara";
		Domain profileDomain = new Domain();
		profileDomain.put("schoolId", schoolId);
		profileDomain.put("username", username);
		profileDomain.put("address", address);
		profileDomain.put("email", email);
		profileDomain.put("phoneNumber", phoneNumber);
		profileDomain.put("imagePath", imagePath);
		profileDomain.put("fullname", fullname);
		profileDomain.put("address", "Jakarta Barat");
		Domain outputProfile = addProfile.handle(profileDomain);
		log.info("Result Test {}", outputProfile);
		Assertions.assertThat(outputProfile.get("username")).isEqualTo(username);
		Assertions.assertThat(outputProfile.get("email")).isEqualTo(email);
		Assertions.assertThat(outputProfile.get("phoneNumber")).isEqualTo(phoneNumber);
		Assertions.assertThat(outputProfile.get("imagePath")).isEqualTo(imagePath);
		Assertions.assertThat(outputProfile.get("fullname")).isEqualTo(fullname);
	}
	@Test
	public void testWithEmptyValue() {
		Domain schoolId = new Domain();
		schoolId.put("schoolId", 1);
		String username = "kiditz";
		String address = "";
		String email = "kiditzbastara@gmail.com";
		String phoneNumber = "";
		String imagePath = "";
		String fullname = "";
		Domain profileDomain = new Domain();
		profileDomain.put("schoolId", schoolId);
		profileDomain.put("username", username);
		profileDomain.put("address", address);
		profileDomain.put("email", email);
		profileDomain.put("phoneNumber", phoneNumber);
		profileDomain.put("imagePath", imagePath);
		profileDomain.put("fullname", fullname);
		profileDomain.put("address", "Jakarta Barat");
		profileDomain.put("ignored", true);
		Domain outputProfile = addProfile.handle(profileDomain);
		log.info("Result Test {}", outputProfile);
		Assertions.assertThat(outputProfile.get("username")).isEqualTo(username);
		Assertions.assertThat(outputProfile.get("email")).isEqualTo(email);
		Assertions.assertThat(outputProfile.get("phoneNumber")).isEqualTo(phoneNumber);
		Assertions.assertThat(outputProfile.get("imagePath")).isEqualTo(imagePath);
		Assertions.assertThat(outputProfile.get("fullname")).isEqualTo(fullname);
	}
}