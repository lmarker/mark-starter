package sdu.cs.document;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DocumentApplication {

    public static void main(String[] args) {
	SpringApplication.run(DocumentApplication.class, args);
    }
    
    @RequestMapping("/{name}")
    public String index(@PathVariable("name") String name) {
	return "hello "+name+"!";
    }
}
