package sdu.sc.personal.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import sdu.sc.personal.entity.Person;
import sdu.sc.personal.entity.UserAdmin;
import sdu.sc.personal.service.AdminService;
import sdu.sc.personal.service.MessageProducerService;
import sdu.sc.personal.service.PersonService;
import sdu.sc.personal.tool.FTPUtil;
import sdu.sc.personal.tool.MessageType;
import sdu.sc.personal.tool.SecurityTools;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;
    
    @Autowired AdminService adminService;
    
    @Autowired
    private MessageProducerService messageProducerService;

    @RequestMapping(value="/save",method=RequestMethod.POST)
    public ModelAndView save(@ModelAttribute Person person,@RequestParam("path") MultipartFile file,Model model) {
	UserAdmin admin = adminService.getAdmin(SecurityTools.getSubjectName());
	Person p = personService.getPerson(admin);
	if(p!=null)
	    person=p;
	String success="";
	try {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	    String name = file.getOriginalFilename();
	    String fileName = null;
	    if(name.lastIndexOf('.')!=-1) {
		fileName = sdf.format(new Date())+name.substring(name.lastIndexOf('.'));
		if(FTPUtil.uploadPicture(fileName, file)) {
		    success=fileName;
		}
	    }
	} catch (IllegalStateException e) {
	    e.printStackTrace();
	} 
	if(success.length()<2) {
	    person.setUrl("default.svg");
	} else
	    person.setUrl(success);
	person.setUser(admin);
	personService.add(person);
	model.addAttribute("person",person);
	return new ModelAndView("redirect:/index");
    }

    @RequestMapping(value="delete")
    public ModelAndView delete(@RequestParam("id") long id) {
	personService.delete(id);
	return new ModelAndView("redirect:/index");
    }
    
    @RequestMapping(value="halling")
    public ModelAndView getPersons(Model model) {
	model.addAttribute("list", personService.getListHalling());
	return new ModelAndView("halling");
    }
    
    @RequestMapping(value="hands")
    @ResponseBody
    public boolean hands(@RequestParam("id") long id) {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
	String src = SecurityTools.getSubjectName();
	if(src==null)
	    return false;
	Person p = personService.findById(id);
	if(p==null)
	    return false;
	long name = p.getPersonid();
	StringBuilder builder = new StringBuilder();
	builder.append(name+" % ");
	builder.append("hands % ");
	builder.append(src+" % ");
	builder.append(sdf.format(new Date()));
	builder.append("\r\n");
	messageProducerService.sendTos(MessageType.log,name,builder.toString());
	return true;
    }

}
