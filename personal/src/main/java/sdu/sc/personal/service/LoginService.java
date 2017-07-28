package sdu.sc.personal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sdu.sc.personal.repository.AdminRepository;

@Component
public class LoginService {

    @Autowired private AdminRepository adminRepository;
    
    public boolean login(String username,String password) {
	return adminRepository.login(username, password)!=null;
    }
    
}
