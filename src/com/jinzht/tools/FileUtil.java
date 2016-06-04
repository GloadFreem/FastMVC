package com.jinzht.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	public static boolean saveFile(String fileName,MultipartFile file,String filePath)
	{
		//获取文件存储路径  
        String path =System.getProperty("jinzht.root")+filePath;
        //输出流  
        OutputStream os;
        InputStream is;
		try {
			os = new FileOutputStream(new File(path,fileName));
			//输入流  
	        is = file.getInputStream();
	          
	        byte[] buf = new byte[1024];  
	        int length = 0 ;  
	          
	        while(-1 != (length = is.read(buf) ) )  
	        {  
	            os.write(buf, 0, length) ;  
	        }  
	        
	        is.close();  
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
        
        return true;
	}

}
