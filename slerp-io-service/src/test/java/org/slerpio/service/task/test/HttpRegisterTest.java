package org.slerpio.service.task.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.slerp.core.Domain;
import org.slerp.core.utils.Net.HttpRequest;
import org.slerp.core.utils.net.Network;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpRegisterTest {
	static private final Logger log = LoggerFactory.getLogger(HttpRegisterTest.class);

	@Test
	public void testRegister() throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(1);

		Domain domain = new Domain();
		domain.put("username", "kiditz");
		domain.put("password", "rioters7");
		domain.put("email", "kiditzbastara@gmail.com");
		List<Domain> userAuthorityList = new ArrayList<>();
		userAuthorityList.add(new Domain().put("authority", "STUDENT"));
		domain.put("userAuthorityList", userAuthorityList);
		log.info("Request Body {}", domain.toString());

		HttpRequest request = new HttpRequest().post("http://localhost:8020/slerp-oauth/register")
				.accept("application/json").contentType("application/json").content(domain);

		Network.instances.send(request, r -> {
			Domain d = r.getResultAsDomain();
			
			log.info("Response {}", d);
		});
		Network.instances.cancel(request);
		latch.await(2, TimeUnit.SECONDS);

	}

}
