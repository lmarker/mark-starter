package sdu.cs.message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sdu.cs.message.service.MessageProducerService;

@RestController
public class MessageController {

    @Autowired MessageProducerService messageProducerService;
    
    @RequestMapping("send")
    public String send(@RequestParam("source") String send,@RequestParam("target") String target) {
	messageProducerService.sendTos("hands", send+" "+target);
	return "发送Ok";
    }
    
    @JmsListener(destination="hands")
    public void receive(String text) {
	String[] str = text.split("|");
	System.out.println(str[0]+"向"+str[1]+"发送了一条好友申请");
    }
}
