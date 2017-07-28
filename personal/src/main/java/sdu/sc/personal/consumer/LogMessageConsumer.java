package sdu.sc.personal.consumer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class LogMessageConsumer extends BaseLogMessage implements ConsumerListener {

    private final String DEFAULT_PATH = "E://active-message/";
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
    
    
    /**
     * 初始化构造NIO存储通道
     */
    public LogMessageConsumer() {
	init_base(DEFAULT_PATH);
	init_log_name(sdf.format(new Date())+"_logs");
	init_fc();
    }
    
    /**
     * 接收消息，对消息进行存储
     * @param text
     * @throws IOException
     */
    @JmsListener(destination = "log")
    public void receive(String message) throws IOException {
	transfer_message(message);
    }
    
    
}
