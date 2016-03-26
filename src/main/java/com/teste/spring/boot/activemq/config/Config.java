package com.teste.spring.boot.activemq.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJms
@EnableScheduling
@ComponentScan(basePackages="com.teste.spring.boot.activemq")
public class Config {

	private static final String HOST = "localhost";
	private static final String PORT = "61616";
	
    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://"+ HOST + ":" + PORT);
        activeMQConnectionFactory.setRedeliveryPolicy(redeliveryPolicy());
        activeMQConnectionFactory.setNonBlockingRedelivery(true);
		return activeMQConnectionFactory;
    }
    
    @Bean
    public RedeliveryPolicy redeliveryPolicy(){
    	RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
    	redeliveryPolicy.setQueue("*");
    	redeliveryPolicy.setMaximumRedeliveries(2);
    	redeliveryPolicy.setRedeliveryDelay(15000);
    	return redeliveryPolicy;
    }
    
}
