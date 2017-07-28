package sdu.sc.personal.tool;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FTPUtil {

    private static Logger log = LoggerFactory.getLogger(FTPUtil.class);

    private static final String FTP_IP = "192.168.9.38";
    private static final String FTP_USERNAME = "ftpuser";
    private static final String FTP_PASSWORD = "lijiahao";

    /** 
     * Description: 向FTP服务器上传文件 
     * @Version1.0 
     * @param url FTP服务器hostname 
     * @param port FTP服务器端口 
     * @param username FTP登录账号 
     * @param password FTP登录密码 
     * @param path FTP服务器保存目录 
     * @param filename 上传到FTP服务器上的文件名 
     * @param input 输入流 
     * @return 成功返回true，否则返回false 
     */  
    public static boolean uploadFile(String path,String filename,MultipartFile input) {
	boolean success = false;  
	FTPClient ftp = new FTPClient();
	try {  
	    ftp.connect(FTP_IP);
	    boolean loginstatus = ftp.login(FTP_USERNAME, FTP_PASSWORD); 
	    String route="upload/"+path;
	    if(loginstatus) {
		//登录成功
		ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
		if(!ftp.changeWorkingDirectory(route)) {
		    ftp.makeDirectory(route);
		    ftp.changeWorkingDirectory(route);
		} else
		    log.info("当前目录存在，切换目录状态:"+ftp.getReplyCode());
		log.info(ftp.getReplyString()+" "+ftp.getStatus());
		if(ftp.storeFile(filename+".tmp", input.getInputStream())) {
		    ftp.rename(filename+".tmp", filename);
		    log.info("文件上传成功");
		} else
		    log.info("文件上传失败："+ftp.getReplyString());
		ftp.logout(); 
		return true;
	    }
	} catch (IOException e) {  
	    e.printStackTrace();  
	} finally {  
	    try {
		ftp.disconnect();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}  
	return success;
    }

    public static boolean uploadPicture(String fileName,MultipartFile input) {
	return uploadFile("pic",fileName,input);
    }
}
