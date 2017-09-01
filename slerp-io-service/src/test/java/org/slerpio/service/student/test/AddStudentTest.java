package org.slerpio.service.student.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slerp.core.CoreException;
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
public class AddStudentTest extends AbstractTransactionalJUnit4SpringContextTests {

	static private Logger log = LoggerFactory.getLogger(AddStudentTest.class);
	@Autowired
	BusinessTransaction addUser;

	@Before
	public void prepare() {
		executeSqlScript("classpath:org/slerpio/service/student/test/AddStudentTest.sql", false);
	}

	@Test
	public void testSuccessStudent() {
		Domain schoolId = new Domain();
		schoolId.put("schoolId", 1l);
		String username = "anes";
		String lastname = "Anatya Bastari";
		String firstname = "Hidayanti";
		Domain studentDomain = new Domain();
		studentDomain.put("schoolId", schoolId);
		studentDomain.put("type", "student");
		studentDomain.put("username", username);
		studentDomain.put("lastname", lastname);
		studentDomain.put("firstname", firstname);
		Domain outputStudent = addUser.handle(studentDomain);
		log.info("Result Test {}", outputStudent);
	}
	@Test
	public void testSuccessTeacher() {
		Domain schoolId = new Domain();
		schoolId.put("schoolId", 1l);
		String username = "anes";
		String lastname = "Anatya Bastari";
		String firstname = "Hidayanti";
		Domain studentDomain = new Domain();
		studentDomain.put("schoolId", schoolId);
		studentDomain.put("type", "teacher");
		studentDomain.put("username", username);
		studentDomain.put("lastname", lastname);
		studentDomain.put("firstname", firstname);
		Domain outputStudent = addUser.handle(studentDomain);
		log.info("Result Test {}", outputStudent);
	}
	@Test(expected = CoreException.class)
	public void testFailRequiredType() {
		Domain schoolId = new Domain();
		schoolId.put("schoolId", 1l);
		String username = "anes";
		String lastname = "Anatya Bastari";
		String firstname = "Hidayanti";
		Domain studentDomain = new Domain();
		studentDomain.put("schoolId", schoolId);
		studentDomain.put("username", username);
		studentDomain.put("lastname", lastname);
		studentDomain.put("firstname", firstname);
		Domain outputStudent = addUser.handle(studentDomain);
		log.info("Result Test {}", outputStudent);
	}
	@Test(expected = CoreException.class)
	public void testFailUnknownType() {
		Domain schoolId = new Domain();
		schoolId.put("schoolId", 1l);
		String username = "anes";
		String lastname = "Anatya Bastari";
		String firstname = "Hidayanti";
		Domain studentDomain = new Domain();
		studentDomain.put("schoolId", schoolId);
		studentDomain.put("username", username);
		studentDomain.put("lastname", lastname);
		studentDomain.put("firstname", firstname);
		studentDomain.put("type", "unknown");
		Domain outputStudent = addUser.handle(studentDomain);
		log.info("Result Test {}", outputStudent);
	}
}