package com.jinzht.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

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
			File f = new File(path);
			
			// 创建文件夹
			if (!f.exists()) {
				f.mkdirs();
			}
			
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
	
	
	/***
	 * 保存文件
	 * @param file
	 * @param fileName
	 */
	public static String savePicture(MultipartFile file, String fileName,String path)
	{
		String headerPicture = "";
		if(file !=null && !file.isEmpty()){
			 //定义一个数组，用于保存可上传的文件类型  
	         List fileTypes = new ArrayList();  
	         fileTypes.add("jpg");  
	         fileTypes.add("jpeg");  
	         fileTypes.add("bmp");  
	         fileTypes.add("gif"); 
	         
	       //获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名 
	        String orignalFileName = file.getOriginalFilename();
	        String ext = orignalFileName.substring(orignalFileName.lastIndexOf(".")+1,orignalFileName.length()); 
			
	        System.out.println("文件长度: " + file.getSize());  
            System.out.println("文件类型: " + file.getContentType());  
            System.out.println("文件名称: " + file.getName());  
            System.out.println("文件原名: " + file.getOriginalFilename());  
            System.out.println("========================================");
            
            //保存
            fileName += "." + ext;
            if(saveFile(fileName, file, path)){
            	return fileName;
            }
            return "";
		}
		return "";
	}

}
