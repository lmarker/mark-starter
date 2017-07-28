package sdu.sc.personal.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sdu.sc.personal.entity.Marks;
import sdu.sc.personal.entity.UserAdmin;
import sdu.sc.personal.repository.AdminRepository;
import sdu.sc.personal.repository.MarksRepository;
import sdu.sc.personal.tool.SecurityTools;
import sdu.sc.personal.tool.TimeController;

@Component
public class DailyServiceImpl implements DailyService,TimeController {

    //用HashMap存储签到状况
    //当涉及到多线程，多并发时，可以采用redis,Memcached代替
    private ConcurrentMap<String, Boolean> dailyRecord = new ConcurrentHashMap<String, Boolean>();
    
    @Autowired MarksRepository marksRepository;
    
    @Autowired AdminRepository adminRepository;
    
    public DailyServiceImpl() {
	clearStorage();
    }
    
    @Override
    public void Up() {
	String name = SecurityTools.getSubjectName();
	UserAdmin admin = adminRepository.findByUsername(name);
	Marks mark = marksRepository.findByUseradmin(admin);
	if(mark==null) {
	    mark = new Marks();
	}
	mark.setUseradmin(admin);
	mark.setAmount(mark.getAmount()+1);
	marksRepository.saveAndFlush(mark);
	dailyRecord.put(name, true);
    }

    @Override
    public boolean isUp() {
	return dailyRecord.getOrDefault(SecurityTools.getSubjectName(),false);
    }

    /**
     * 清理缓存
     */
    @Override
    public void clearStorage() {
	Timer m = new Timer();
	//设置执行时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);//每天
        //定制每天的00:00:00执行，
        calendar.set(year, month, day, 00, 00, 00);
        Date date = calendar.getTime();
        Timer timer = new Timer();
        int period = 2 * 1000;
        //每天的date时刻执行task, 仅执行一次
        //timer.schedule(task, date);
	m.schedule(new TimerTask(){

	    @Override
	    public void run() {
		dailyRecord.clear();
	    }
	    
	}, date);
    }

}
