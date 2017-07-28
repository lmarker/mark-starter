package sdu.sc.personal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sdu.sc.personal.entity.Comment;
import sdu.sc.personal.entity.UserAdmin;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("from Comment as c where c.cadmin=?1 order by c.id desc")
    List<Comment> findByAdmin(UserAdmin admin);
   
}
