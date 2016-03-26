package com.teste.spring.boot.activemq.component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ScheduledMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class Producer {

	public static final String TEST_QUEUE = "test.queue";

	@Autowired
    private JmsTemplate jmsTemplate;	
	
	public void sendMessage(final String message) {
        jmsTemplate.send(TEST_QUEUE, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
    }
	
	public void sendScheduleMessage(final String message, final Integer secondsDelay) {
		jmsTemplate.send(TEST_QUEUE, new MessageCreator() {
			
            @Override
            public Message createMessage(Session session) throws JMSException {
                
                TextMessage messageToSend = session.createTextMessage(message);
                
                messageToSend.setLongProperty("_DeliverAfter_", secondsDelay);
//                messageToSend.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, secondsDelay * 1000);
                
                return messageToSend;
            }
        });
    }
}
