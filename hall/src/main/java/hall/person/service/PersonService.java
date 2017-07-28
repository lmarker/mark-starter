package hall.person.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sdu.sc.personal.entity.Person;

@Service
public interface PersonService {

    List<Person> getList();
    
}
