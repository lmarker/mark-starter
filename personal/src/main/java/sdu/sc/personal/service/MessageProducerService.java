package sdu.sc.personal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import sdu.sc.personal.tool.MessageQueue;
import sdu.sc.personal.tool.MessageType;

@Service
public class MessageProducerService {

    @Autowired private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendTos(MessageType type,long to,String queueMessage) {
	if(type.toString().equalsIgnoreCase("hands"))
	    jmsMessagingTemplate.convertAndSend(MessageQueue.getQueue("hands"),queueMessage);
	else if(type.toString().equalsIgnoreCase("info"))
	    jmsMessagingTemplate.convertAndSend(MessageQueue.getQueue("info"),queueMessage);
	else if(type.toString().equalsIgnoreCase("log"))
	    jmsMessagingTemplate.convertAndSend(MessageQueue.getQueue("log"),queueMessage);
    }

    public void pubTo(String topicName,String topicMessage) {
	jmsMessagingTemplate.convertAndSend(MessageQueue.getTopic(topicName),topicMessage);
    }
}
