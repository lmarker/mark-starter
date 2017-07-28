package sdu.sc.personal.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sdu.sc.personal.entity.Friends;
import sdu.sc.personal.entity.Person;
import sdu.sc.personal.entity.UserAdmin;

@Repository
public interface FriendsRepository extends JpaRepository<Friends,Long>{

    @Query("select distinct u from UserAdmin u,Friends f where f.foid=:admin and f.frid=u.id")
    ArrayList<UserAdmin> getFriends(@Param("admin") long admin);
    
}
