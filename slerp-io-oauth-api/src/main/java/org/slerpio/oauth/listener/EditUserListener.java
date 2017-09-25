package org.slerpio.oauth.listener;

import org.slerp.core.Domain;
import org.slerp.core.business.BusinessTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

/**
 * @author kiditz
 * @since 23 September 2017
 */
@Component
public class EditUserListener {
	@Autowired
	BusinessTransaction editUserPrincipal;
	Logger log = LoggerFactory.getLogger(getClass());

	@KafkaListener(containerGroup = "${kafka.groupId}", topics="${kafka.messages.edit_user}")
	public void execute(Domain inputDomain) {
		//inputDomain.put("password", BCrypt.hashpw(inputDomain.getString("password"), BCrypt.gensalt(10)));
		inputDomain.put("enabled", true);
		try {
			log.info("User Domain : {}", inputDomain);
			editUserPrincipal.handle(inputDomain);
		} catch (Exception e) {
			log.error("Exception while edit user : {}", e);
		}
	}
}
