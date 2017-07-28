package sdu.sc.personal.consumer;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.stereotype.Component;

@Component
public class MessageConsumerProxy {

    Timer timer = new Timer();
    
    /**
     * 每5分钟检测consumer的状态，若连续8次状态为false，则关闭通道
     * @param consumer
     */
    public void proxy(BaseLogMessage consumer) {
	timer.schedule(new TimerTask(){
	    
	    int count = 0;
	    
	    @Override
	    public void run() {
		if(!consumer.status) {
		    count++;
		    if(count==10) {
			try {
			    consumer.close_fc();
			} catch (IOException e) {
			    e.printStackTrace();
			}
		    }
		} else
		    count = 0;
	    }
	    
	}, 0,30*1000);
    } 
    
}
