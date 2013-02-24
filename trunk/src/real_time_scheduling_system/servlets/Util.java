package real_time_scheduling_system.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Util {
	public static void uploadFile(String fileDestinationName, InputStream inputStream) throws Exception{
		FileOutputStream outFileOutputStream=new FileOutputStream(new File(fileDestinationName));
    	byte[] buffer=new byte[1024];
    	while(true){
    		int readedLength=inputStream.read(buffer);
    		outFileOutputStream.write(buffer, 0, readedLength);
    		if(readedLength<buffer.length){
    			break;
    		}
    	}
    	inputStream.close();
    	outFileOutputStream.close();
	}
}
