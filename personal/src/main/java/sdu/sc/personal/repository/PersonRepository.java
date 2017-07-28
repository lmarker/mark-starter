package sdu.sc.personal.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sdu.sc.personal.entity.Person;
import sdu.sc.personal.entity.UserAdmin;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

    List<Person> findByName(String name);
    
    List<Person> findAll();
    
    Person findByUser(UserAdmin user);
    
    @Transactional
    @Modifying
    @Query("update Person as p set p.name=?1,p.age=?2,p.sex=?3,p.number=?4,p.email=?5,p.hobby=?6,p.url=?7 where p.id = ?8")
    int updatePerson(String name,int age,String sex,String number,String email,String hobby,String url,long id);
}
