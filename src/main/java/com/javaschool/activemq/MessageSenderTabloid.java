package com.javaschool.activemq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageSenderTabloid {
    @Autowired
    @Qualifier("jmsTemplateTabloid")
    private JmsTemplate jmsTemplate;

    public void send(String message) {
        log.info("Send message to tabloid, message: " + message);
        jmsTemplate.convertAndSend(message);
    }

}
