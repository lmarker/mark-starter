package sdu.sc.personal.consumer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

@Component
public class BaseLogMessage {

    protected String base_route;

    protected String log_name;

    protected FileChannel fc;
    
    protected ByteBuffer buffer = ByteBuffer.allocate(1024*2);

    protected boolean status = true;
    
    /**
     * 初始化
     * @param base
     * @param name
     */
    public void init(String base,String name) {
	init_base(base);
	init_log_name(name);
	init_datas();
	init_fc();
    }
    
    
    /**
     * 通过扫描日志，初始化数据
     */
    protected void init_datas() {
//	init_fc();
//	try {
//	    byte[] bs = new byte[1024];  
//            StringBuffer strBuf = new StringBuffer("");  
//	    fc = new FileInputStream(base_route+log_name+".log").getChannel();
//	    ByteBuffer bf = ByteBuffer.allocate(1024*2);
//	    while(fc.read(bf)!=-1) {
//		
//	    }
//	} catch (FileNotFoundException e) {
//	    e.printStackTrace();
//	} catch (IOException e) {
//	    e.printStackTrace();
//	}
    }
    
    protected void init_base(String base) {
	this.base_route = base;
    }

    protected void transfer_message(String message) throws IOException {
	buffer.put(message.getBytes());
	if(fc!=null && fc.isOpen()) {
	    while(buffer.hasRemaining()) {
		buffer.flip();
		fc.write(buffer);
	    }
	    buffer.clear();
	    status = false;
	} else {
	    System.err.println("通道已关闭,正在重新打开...");
	    init_fc();
	}
    }


    protected void init_log_name(String name) {
	this.log_name = name;
    }

    @SuppressWarnings("resource")
    protected void init_fc() {
	try {
	    fc = new FileOutputStream(base_route+log_name+".log").getChannel();
	} catch (FileNotFoundException e) {
	    try {
		Files.createFile(Paths.get(base_route+log_name));
		fc = new FileOutputStream(base_route+log_name).getChannel();
	    } catch (IOException e1) {
		e1.printStackTrace();
	    }
	}
    }

    protected void close_fc() throws IOException {
	fc.close();
    }
}
