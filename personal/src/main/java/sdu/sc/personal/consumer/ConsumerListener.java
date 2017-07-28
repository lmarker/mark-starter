package sdu.sc.personal.consumer;

import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public interface ConsumerListener {
    void receive(String message) throws IOException;
}
