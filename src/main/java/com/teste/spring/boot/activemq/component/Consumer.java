package com.teste.spring.boot.activemq.component;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	@JmsListener(destination = Producer.TEST_QUEUE)
	public void receiveMessage(String message) {
		System.out.println("Received : " + message);
	}
}
