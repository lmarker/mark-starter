package sdu.sc.personal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sdu.sc.personal.entity.Person;
import sdu.sc.personal.entity.UserAdmin;

@Repository
public interface AdminRepository extends JpaRepository<UserAdmin, Long>{

    @Query("from UserAdmin as u where u.username=:username and u.password=:password")
    UserAdmin login(@Param("username")String username,@Param("password")String password);

    UserAdmin findByUsername(String username);
}
