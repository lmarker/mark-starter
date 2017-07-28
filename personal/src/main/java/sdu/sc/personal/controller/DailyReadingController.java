package sdu.sc.personal.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sdu.sc.personal.consumer.LogMessageConsumer;
import sdu.sc.personal.entity.Person;
import sdu.sc.personal.service.DailyService;
import sdu.sc.personal.service.MessageProducerService;
import sdu.sc.personal.tool.MessageType;
import sdu.sc.personal.tool.SecurityTools;

/**
 * 日常签到接口
 * @author ljh_2015
 *
 */
@RestController
@RequestMapping("daily")
public class DailyReadingController {


    @Autowired private DailyService dailyServiceImpl;

    @Autowired private MessageProducerService messageProducerService;

    @RequestMapping("/up")
    public String dailyReading() {
	String name = SecurityTools.getSubjectName();
	if(!dailyServiceImpl.isUp()) {
	    dailyServiceImpl.Up();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
	    StringBuilder builder = new StringBuilder();
	    builder.append(name+" % ");
	    builder.append("dailys % ");
	    builder.append(sdf.format(new Date()));
	    builder.append("\r\n");
	    messageProducerService.sendTos(MessageType.log,0,builder.toString());
	    return name+"您好，您已完成签到!";
	}
	return name+"您好，您已经签到过了!";
    }

}
