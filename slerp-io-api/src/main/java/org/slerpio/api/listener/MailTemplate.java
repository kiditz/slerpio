package org.slerpio.api.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class MailTemplate {
	@Autowired
	TemplateEngine templateEngine;

	public String build(String username, String activationCode) {
		Context context = new Context();
		context.setVariable("username", username);
		context.setVariable("activationCode", activationCode);		
		return templateEngine.process("mailActivateStudentTemplate", context);
	}
}
