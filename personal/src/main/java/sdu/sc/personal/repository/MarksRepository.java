package sdu.sc.personal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sdu.sc.personal.entity.Marks;
import sdu.sc.personal.entity.UserAdmin;

@Repository
public interface MarksRepository extends JpaRepository<Marks, Long>{

    Marks findByUseradmin(UserAdmin admin);
    
}
