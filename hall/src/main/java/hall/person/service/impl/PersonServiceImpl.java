package hall.person.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hall.person.service.PersonService;
import sdu.sc.personal.entity.Person;
import sdu.sc.personal.repository.PersonRepository;

@Component
public class PersonServiceImpl implements PersonService {

    @Autowired private PersonRepository personRepository;
    
    @Override
    public List<Person> getList() {
	return personRepository.findAll();
    }

}
