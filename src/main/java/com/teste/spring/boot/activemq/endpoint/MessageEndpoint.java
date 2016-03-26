package com.teste.spring.boot.activemq.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teste.spring.boot.activemq.component.Consumer;
import com.teste.spring.boot.activemq.component.Producer;

@RestController
@RequestMapping(value = "/queue")
public class MessageEndpoint {

	@Autowired
	private Producer producer;
	
	@Autowired
	private Consumer consumer;

	@RequestMapping(value = "/send", method=RequestMethod.POST)
	public ResponseEntity<String> send(@RequestParam(value="message") String message) {
		producer.sendMessage(message);
		
		return new ResponseEntity<String>("Sent", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/schedule", method=RequestMethod.POST)
	public ResponseEntity<String> sendSchedule(
			@RequestParam(value="message") String message,
			@RequestParam(value="seconds") Integer seconds) {
		
		producer.sendScheduleMessage(message, seconds);
		
		return new ResponseEntity<String>("Sent", HttpStatus.OK);
	}
}
