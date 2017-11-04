package org.slerpio.service.schoolclass.test;

import java.util.Date;

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
@TestExecutionListeners(listeners = {DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DependencyInjectionTestExecutionListener.class}, inheritListeners = false)
@Rollback
public class AddSchoolClassTest
		extends
			AbstractTransactionalJUnit4SpringContextTests {

	static private Logger log = LoggerFactory
			.getLogger(AddSchoolClassTest.class);
	@Autowired
	BusinessTransaction addSchoolClass;

	@Before
	public void prepare() {
		executeSqlScript(
				"classpath:org/slerpio/service/schoolclass/test/AddSchoolClassTest.sql",
				false);
	}

	@Test
	public void testSuccess() {
		String code = "HSAD5647";
		String name = "Kelas IPA 1";
		Long userProfileId = 1l;
		Domain schoolClassDomain = new Domain();
		schoolClassDomain.put("code", code);
		schoolClassDomain.put("name", name);
		schoolClassDomain.put("userProfileId", userProfileId);
		schoolClassDomain.put("createdAt", new Date());
		schoolClassDomain.put("updateAt", new Date());
		Domain outputSchoolClass = addSchoolClass.handle(schoolClassDomain);
		log.info("result 1 >>> {}", outputSchoolClass);
		code = "HSAD5648";
		name = "Kelas IPA 2";
		userProfileId = 1l;
		schoolClassDomain = new Domain();
		schoolClassDomain.put("code", code);
		schoolClassDomain.put("name", name);
		schoolClassDomain.put("userProfileId", userProfileId);
		schoolClassDomain.put("createdAt", new Date());
		schoolClassDomain.put("updateAt", new Date());
		outputSchoolClass = addSchoolClass.handle(schoolClassDomain);
		log.info("result 2 >>> {}", outputSchoolClass);
	}
}