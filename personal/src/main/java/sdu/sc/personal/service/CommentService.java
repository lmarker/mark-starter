package sdu.sc.personal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sdu.sc.personal.entity.Comment;
import sdu.sc.personal.entity.Person;
import sdu.sc.personal.entity.UserAdmin;
import sdu.sc.personal.repository.AdminRepository;
import sdu.sc.personal.repository.CommentRepository;
import sdu.sc.personal.repository.PersonRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired PersonRepository personRepository;

    public List<Comment> getByAdmin(long personid) {
	Person p = personRepository.findOne(personid);
	return commentRepository.findByAdmin(p.getUser());
    }

    public Comment put(long personid,String Comment) {
	Comment comment = new Comment();
	Person p = personRepository.findOne(personid);
	comment.setCadmin(p.getUser());
	comment.setComment(Comment);
	return commentRepository.save(comment);
    }
}
