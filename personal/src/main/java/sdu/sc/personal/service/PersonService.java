package sdu.sc.personal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sdu.sc.personal.entity.Person;
import sdu.sc.personal.entity.UserAdmin;
import sdu.sc.personal.repository.AdminRepository;
import sdu.sc.personal.repository.FriendsRepository;
import sdu.sc.personal.repository.PersonRepository;
import sdu.sc.personal.tool.SecurityTools;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private FriendsRepository friendsRepository;
    
    @Autowired
    private AdminRepository adminRepository;
    
    public void add(Person p) {
	if(personRepository.findOne(p.getPersonid())==null)
	    personRepository.save(p);
	else {
	    System.out.println("=======进行修改操作======="+p.getPersonid());
	    personRepository.updatePerson(p.getName(),p.getAge(),p.getSex(),p.getNumber(),p.getEmail(),p.getHobby(),p.getUrl(),p.getPersonid());
	    personRepository.flush();
	}
    }
    
    public void delete(Person p) {
	personRepository.delete(p);
    }
    
    public void delete(Long id) {
	personRepository.delete(id);
    }
    
    public Person getPerson(String name) {
	UserAdmin admin = adminRepository.findByUsername(name);
	Person p = personRepository.findByUser(admin);
	return p!=null?p:null;
    }
    
    public Person getPerson(UserAdmin admin) {
	return personRepository.findByUser(admin);
    }
    
    public List<Person> getListHalling() {
	return personRepository.findAll();
    }
    
    public List<Person> getList(String name) {
	UserAdmin admin = adminRepository.findByUsername(name);
	ArrayList<UserAdmin> admins =  friendsRepository.getFriends(admin.getId());
	List<Person> list = new ArrayList<>();
	for(UserAdmin a:admins) {
	    list.add(personRepository.findByUser(a));
	}
	return list;
    }
    
    public List<Person> findByName(String name) {
	return personRepository.findByName(name);
    }
    
    public Person findById(long id) {
	return personRepository.findOne(id);
    }
    
    public Person getAdminPerson() {
	String name = SecurityTools.getSubjectName();
	UserAdmin admin = adminRepository.findByUsername(name);
	return personRepository.findByUser(admin);
    }
} 
