package sdu.sc.personal.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sdu.sc.personal.consumer.LogMessageConsumer;
import sdu.sc.personal.entity.Person;
import sdu.sc.personal.entity.UserAdmin;
import sdu.sc.personal.service.AdminService;
import sdu.sc.personal.service.LoginService;
import sdu.sc.personal.service.PersonService;
import sdu.sc.personal.tool.SecurityTools;

@Controller
public class ViewController {

    private static final String DEFAULT_USER = "none";
    
    @Autowired private LogMessageConsumer consumer;
    
    @Autowired private LoginService loginService;
    
    @Autowired private PersonService personService;
    
    @Autowired private AdminService adminService;

    @RequestMapping("/person")
    public ModelAndView login(@RequestParam("username") String username,@RequestParam("password")String password) {
	if(loginService.login(username, password)) {
	    return new ModelAndView("redirect:/index");
	} else {
	    return new ModelAndView("redirect:/login");
	}

    }
    
    @RequestMapping("/person/insert")
    public String login(Model model) {
	String name = SecurityTools.getSubjectName();
	Person person = personService.getPerson(name);
	if(person==null)
	    person = new Person();
	model.addAttribute("person", person);
	return "home";
    }

    @RequestMapping("/index")
    public String hello(Model model) {
	Subject sub = SecurityUtils.getSubject();
	String username;
	if(sub==null || sub.getPrincipals() == null) {
	    username = DEFAULT_USER;
	}
	username = (String)sub.getPrincipals().getPrimaryPrincipal();
	model.addAttribute("list",personService.getList(username));
	model.addAttribute("user", username);
	return "list";
    }
    
    @RequestMapping("/info")
    public String detail() {
	return "info";
    }
    
    @RequestMapping("/markHalling") 
    public String home(){
	return "index";
    }
    
    @RequestMapping("/register")
    public String register(Model model) {
	model.addAttribute("user",new UserAdmin());
	return "register";
    }
    
    @RequestMapping("/sduhall")
    public String halling() {
	return "halling";
    }
    
}
