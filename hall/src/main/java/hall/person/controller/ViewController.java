package hall.person.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping("index")
    public String ViewHalling() {
	return "halling";
    }
    
}
