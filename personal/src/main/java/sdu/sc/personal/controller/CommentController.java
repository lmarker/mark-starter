package sdu.sc.personal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sdu.sc.personal.entity.Comment;
import sdu.sc.personal.service.AdminService;
import sdu.sc.personal.service.CommentService;
import sdu.sc.personal.service.PersonService;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PersonService personService;
    
    @Autowired
    private AdminService adminService;

    @RequestMapping(value="comment_save",method=RequestMethod.POST)
    public ModelAndView putComment(@RequestParam("id")long id,@RequestParam("comment")String comment,Model model) {
	String result = comment.replace("\n", "</p><p>");
	Comment com = commentService.put(id, result);
	if(com!=null) {
	    model.addAttribute("message", "添加成功");
	} else {
	    model.addAttribute("message", "录入失败");
	}
	model.addAttribute("info",personService.findById(id));
	return new ModelAndView("redirect:/person/detail?id="+id);
    }
    
    @RequestMapping(value="person/detail")
    public ModelAndView listComments(@RequestParam("id")long id,Model model) {
	
	List<Comment> comments = commentService.getByAdmin(id);
	model.addAttribute("comments", comments);
	model.addAttribute("info",personService.findById(id));
	model.addAttribute("user",id);
	return new ModelAndView("info");
    }

}
