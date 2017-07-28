package sdu.sc.personal.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

    @RequestMapping("/upload")
    public String uploadFile(@RequestParam("uploadFile") MultipartFile[] uploadFile) {
	StringBuffer sb = new StringBuffer(); 
	String basePath="/static";//设置服务器中文件保存的根目录
	Date now = new Date();
	SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM");
	String filePath = dateformat.format(now);//根据当前时间设置文件保存的子目录
	if(uploadFile !=null && uploadFile.length >0) {
	    for(int i=0;i<uploadFile.length;i++) {
		String fileName;
		fileName = new String(uploadFile[i].getOriginalFilename());
		if(i!=0) {
		    sb.append(",");
		}
		sb.append(fileName);
	    }
	}
	return sb.toString();
    }
    
}
