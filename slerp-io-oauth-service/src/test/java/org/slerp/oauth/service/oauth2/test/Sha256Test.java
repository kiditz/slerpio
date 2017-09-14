package org.slerp.oauth.service.oauth2.test;

import org.apache.commons.codec.digest.HmacUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sha256Test {
	Logger log = LoggerFactory.getLogger(getClass());

	@Test
	public void test() {
		String hash = HmacUtils.hmacSha1Hex("Slerp", "kiditz");
		log.info(hash);
	}

}
