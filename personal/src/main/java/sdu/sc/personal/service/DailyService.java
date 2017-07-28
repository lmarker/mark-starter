package sdu.sc.personal.service;

import org.springframework.stereotype.Service;

@Service
public interface DailyService {

    void Up();
    
    boolean isUp();
}
