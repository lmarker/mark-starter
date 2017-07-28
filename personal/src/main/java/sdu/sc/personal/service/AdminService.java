package sdu.sc.personal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sdu.sc.personal.entity.UserAdmin;
import sdu.sc.personal.repository.AdminRepository;
import sdu.sc.personal.repository.PersonRepository;

@Service
public class AdminService {
    
    @Autowired private AdminRepository adminRepository;
    
    public boolean save(UserAdmin admin) {
	return adminRepository.saveAndFlush(admin)!=null;
    }
    
    public UserAdmin getAdmin(String username) {
	return adminRepository.findByUsername(username);
    }
    
}
