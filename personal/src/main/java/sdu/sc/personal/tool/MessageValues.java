package sdu.sc.personal.tool;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Component;

@Component
public class MessageValues {

    private static ConcurrentMap<String,String> queues = new ConcurrentHashMap<String,String>();
    private static ConcurrentMap<String,String> topics = new ConcurrentHashMap<String,String>();
    
    public static void putQueue(String queueName) {
	queues.put(queueName, queueName);
    }
    
    public static void putTopic(String topicName) {
	topics.put(topicName, topicName);
    }
    
    public static String getQueue(String queueName) {
	return queues.get(queueName);
    }
    
    public static String getTopic(String topicName) {
	return topics.get(topicName);
    }
}
