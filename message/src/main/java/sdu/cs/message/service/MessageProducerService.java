package sdu.cs.message.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import sdu.cs.message.tool.MessageQueue;

@Service
public class MessageProducerService {

    @Autowired private JmsMessagingTemplate jmsMessagingTemplate;
    
    public void sendTos(String adminName,String queueMessage) {
	jmsMessagingTemplate.convertAndSend(MessageQueue.getQueue(adminName),queueMessage);
    }
    
    public void pubTo(String topicName,String topicMessage) {
	jmsMessagingTemplate.convertAndSend(MessageQueue.getTopic(topicName),topicMessage);
    }
}
