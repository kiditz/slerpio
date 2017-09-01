package org.slerpio.api.listener;

import java.time.Instant;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ScheduledMessage;
import org.slerp.core.Domain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class MailListener {
	Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	MailService mailService;
	@Autowired
	JmsTemplate jmsTemplate;

	@JmsListener(destination = "slerp.mail", containerFactory = "")
	public void listen(Domain  domain) throws JMSException {		
		log.info("Send Mail Message {}", domain);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(domain.getString("email"));
		try {
			mailService.sendMail(domain);
		} catch (Exception e) {
			log.error("Failed to send email!, will be retry in {}", Instant.now().plusSeconds(30));
			jmsTemplate.send("slerp.mail", new MessageCreator() {
				@Override
				public Message createMessage(Session session) throws JMSException {
					TextMessage m = session.createTextMessage();
					m.setText(domain.toString());	
					m.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, 30 * 1000);
					m.setJMSCorrelationID("_type");
					return m;
				}
			});
		}
	}
}
