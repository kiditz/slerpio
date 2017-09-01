package org.slerp.oauth.service.oauth2.test;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.codec.digest.HmacUtils;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slerp.core.Domain;
import org.slerp.core.business.BusinessTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@TestExecutionListeners(listeners = { DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DependencyInjectionTestExecutionListener.class }, inheritListeners = false)
//@Rollback
@Transactional
public class AddClientTest extends AbstractTransactionalJUnit4SpringContextTests {

	static private Logger log = LoggerFactory.getLogger(AddClientTest.class);
	@Autowired
	BusinessTransaction addClient;

	@Before
	public void prepare() {
		executeSqlScript("classpath:org/slerp/oauth/service/oauth2/test/AddClientTest.sql", false);
	}

	@Test
	public void testSuccess() {
		String clientSecret = HmacUtils.hmacSha1Hex("Slerp", "rioters7");
		String clientId = HmacUtils.hmacSha1Hex("Slerp", "aditya");
		Set<String> authorizedGrantTypes = new HashSet<>();
		authorizedGrantTypes.add("authorization_code");
		Set<String> registeredRedirectUri = new HashSet<>();
		registeredRedirectUri.add("http://localhost:10021/#oauth");

		Set<String> scope = new HashSet<>();
		scope.add("read");
		Domain clientDomain = new Domain();
		clientDomain.put("clientSecret", clientSecret);
		clientDomain.put("authorizedGrantTypes", authorizedGrantTypes);
		clientDomain.put("registeredRedirectUri", registeredRedirectUri);
		clientDomain.put("clientId", clientId);
		clientDomain.put("scope", scope);
		Domain outputClient = addClient.handle(clientDomain);

		log.info("Result Test {}", outputClient);
		Assertions.assertThat(clientDomain.get("clientSecret")).isEqualTo(clientSecret);
		Assertions.assertThat(clientDomain.get("authorizedGrantTypes")).isEqualTo(authorizedGrantTypes);
		Assertions.assertThat(clientDomain.get("registeredRedirectUri")).isEqualTo(registeredRedirectUri);
		Assertions.assertThat(clientDomain.get("clientId")).isEqualTo(clientId);
		Assertions.assertThat(clientDomain.get("scope")).isEqualTo(scope);
	}
}