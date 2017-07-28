package sdu.sc.personal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import sdu.sc.personal.entity.UserAdmin;
import sdu.sc.personal.service.AdminService;

@Controller
public class AdminController {

    Logger log = LoggerFactory.getLogger(AdminController.class);
    
    @Autowired AdminService adminService;
    
    @RequestMapping(value="register/reg",method=RequestMethod.POST)
    public ModelAndView register(@ModelAttribute UserAdmin userAdmin) {
	log.info(userAdmin.getUsername()+" "+userAdmin.getPassword());
	if(adminService.save(userAdmin)) {
	    System.out.println("注册成功");
	    return new ModelAndView("redirect:/index");
	}
	else
	    return new ModelAndView("redirect:/register");
    }
    
}
