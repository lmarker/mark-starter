package sdu.sc.personal.tool;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.ModelAndView;

public class SecurityTools {

    public static String getSubjectName() {
	Subject subject = SecurityUtils.getSubject();
	if(subject==null || subject.getPrincipals() == null) {
	    return null;
	}
	return (String)subject.getPrincipals().getPrimaryPrincipal();
    }
    
}
