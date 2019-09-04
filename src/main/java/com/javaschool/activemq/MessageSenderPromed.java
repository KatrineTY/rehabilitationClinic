package com.javaschool.activemq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageSenderPromed {
    @Autowired
    @Qualifier("jmsTemplatePromed")
    private JmsTemplate jmsTemplate;

    public void send(String promedName, String count) {
        log.info("Send message to promed with promed:" + promedName + " and count:" + count);
        jmsTemplate.convertAndSend("Promed:" + promedName + ",count:" + count);

    }

}
