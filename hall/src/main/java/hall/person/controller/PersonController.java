package hall.person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import hall.person.service.PersonService;

@Controller
public class PersonController {

    @Autowired PersonService personService;
    
    public ModelAndView getPersons(Model model) {
	model.addAttribute("list", personService.getList());
	return new ModelAndView("halling");
    }
    
}
