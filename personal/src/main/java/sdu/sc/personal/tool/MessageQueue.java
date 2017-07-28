package sdu.sc.personal.tool;

import javax.jms.Queue;
import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

public class MessageQueue {

    public static Queue getQueue(String queueName) {
	return new ActiveMQQueue(queueName);
    }
    
    public static Topic getTopic(String topicName) {
	return new ActiveMQTopic(topicName);
    }
}
