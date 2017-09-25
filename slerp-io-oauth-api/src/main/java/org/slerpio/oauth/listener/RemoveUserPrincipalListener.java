package org.slerpio.oauth.listener;

import org.slerp.core.Domain;
import org.slerp.core.business.BusinessTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import io.reactivex.Single;

@Component
public class RemoveUserPrincipalListener {
	@Autowired
	@Qualifier("removeUserPrincipal")
	BusinessTransaction removeUser;
	Logger log = LoggerFactory.getLogger(RemoveUserPrincipalListener.class);

	@KafkaListener(topics = "${kafka.messages.rm_user_principal}", containerGroup = "${kafka.groupId}")
	public void listenToRemove(Domain userDomain) {
		Single.just(userDomain).map(input -> {
			removeUser.handle(input);
			return input.put("status", "success");
		}).doOnError(e -> log.info("Exception while removing user principal :  {}", e)).subscribe(result -> {
			log.info("Remove user principal {} success", userDomain.get("userId"));
		});
	}
}