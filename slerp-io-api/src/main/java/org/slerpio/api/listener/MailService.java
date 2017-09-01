package org.slerpio.api.listener;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slerp.core.Domain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailService {
	@Autowired
	MailTemplate mailTemplate;
	@Autowired
	JavaMailSender sender;

	public void sendMail(Domain mailDomain) throws MessagingException {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setTo(mailDomain.getString("email"));
		helper.setSubject("Activate Your SlerpIO Account");
		// Set true if is html
		helper.setText(mailTemplate.build(mailDomain.getString("username"), mailDomain.getString("activationCode")),
				true);
		sender.send(helper.getMimeMessage());
	}
}
