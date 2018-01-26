package com.bc.pmph_vedio.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.bc.pmph_vedio.common.ResponseBean;
import com.bc.pmph_vedio.general.FileService;

/**
 *@author MrYang 
 *@CreateDate 2018年1月24日 下午4:11:09
 *
 **/
@Controller
public class FileUpController extends BaseController{
	
	@Autowired
	private FileService fileService;
	
	@ResponseBody
    @RequestMapping(value = "/fileUpTest", method = RequestMethod.POST)
    public ResponseBean<String> uploadT(HttpServletRequest request, 
    		@RequestParam("file") MultipartFile file,
    		@RequestParam("userId") Long userId,
    		@RequestParam("userType") Integer userType,
    		@RequestParam("bookId") Long bookId
    		) {
		try {
			return   new ResponseBean<String>(fileService.fileUp(request, file, userId,userType, bookId));
		} catch (Exception e) {
			return   handlerException(e);
		}
	}
	
	
	
	@ResponseBody
    @RequestMapping(value = "/fileUp", method = RequestMethod.POST)
    public ResponseBean<String> upload(HttpServletRequest request, 
    		@RequestParam("file") MultipartFile file,
    		@RequestParam("userId") Long userId,
    		@RequestParam("bookId") Long bookId
    		) throws Exception {
		String name = file.getOriginalFilename();
		String type = name.substring(name.lastIndexOf(".") + 1,name.length()) .toLowerCase(); 
		type = type == null ?"":type;
		if (type.equals("avi") || type.equals("mpg") || type.equals("wmv") || type.equals("3gp") || 
			type.equals("mov") || type.equals("mp4") || type.equals("asf") || type.equals("asx") || 
			type.equals("flv")){  
        } else{//不支持的格式 
        	return null;
        }
		//WINDOWS 系统
		if((System.getProperty("os.name").toUpperCase()).contains("WINDOWS")){
			String path = request.getSession().getServletContext().getRealPath("/");
			String tempPath = path+"temp\\"+name ;
			//创建目录 
			if(!new File(path+"temp\\").exists()){
				new File(path+"temp\\").mkdirs();
			}
			if(!new File(path+"file\\").exists()){
				new File(path+"file\\").mkdirs();
			}
			file.transferTo(new File(tempPath));
			//String cmd = "C:\\Users\\wuyun\\Desktop\\ffmpeg-3.4.1-win64-static\\bin\\ffmpeg.exe  -i C:\\Users\\wuyun\\Desktop\\movie\\2016-09-02_14：44：09.wmv  -ab 128 -acodec libmp3lame -ac 1 -ar 22050 -r 29.97 -qscale 4 -y C:\\Users\\wuyun\\Desktop\\test\\c.mp4";
//            C:\Users\wuyun\Desktop\ffmpeg-3.4.1-win64-static\bin\\ffmpeg.exe
//			-i 
//			C:\Users\wuyun\Desktop\movie\2016-09-02_14：44：09.wmv
//			-ab 
//			128
//			-acodec
//			libmp3lame 
//			-ac 
//			1 
//			-ar 
//			22050
//			-r 
//			29.97 
//			-qscale
//			4 
//			-y 
//			C:\Users\wuyun\Desktop\test\c.mp4
			
			String newName = java.util.UUID.randomUUID().toString().replaceAll("-", "")+".mp4";
			List<String> commend = new ArrayList<String>();  
			commend.add(path+"ffmpegbin\\ffmpeg.exe");
			commend.add("-i");
			commend.add(tempPath);
			commend.add("-ab");
			commend.add("128");
			commend.add("-acodec");
			commend.add("libmp3lame");
			commend.add("-ac");
			commend.add("1");
			commend.add("-ar");
			commend.add("22050");
			commend.add("-r");
			commend.add("29.97");
			commend.add("-qscale");
			commend.add("4");
			commend.add("-y");
			commend.add(path+"file\\"+newName);
			StringBuffer test=new StringBuffer();  
			for(int i=0;i<commend.size() ;i++){
	            test.append(commend.get(i)+" ");  
	        }
			TS(test.toString()) ;
		}else if("LINUX".equals( System.getProperty("os.name").toUpperCase()  )){ //linnux操作系统
			String inputPath ="/mnt/hgfs/share/2016-09-02_14：44：09.wmv";
			String outputPath="/mnt/hgfs/share/movieOut.mp4";
	        List<String> commend = new ArrayList<String>();  
	        commend.add("ffmpeg"); 
	        commend.add("-i");   
	        commend.add(inputPath);  
	        commend.add("-ab");      
	        commend.add("128");           
	        commend.add("-ac");      
	        commend.add("1");      
	        commend.add("-ar");      
	        commend.add("22050");      
	        commend.add("-r");      
	        commend.add("24");     
	        commend.add("-y"); 
	        commend.add(outputPath);  
	        StringBuffer test=new StringBuffer();      
	        for(int i=0;i<commend.size();i++)   {   
	        	 test.append(commend.get(i)+"  "); 
	        } 
	        
	        TS(test.toString()) ;
		}
		
		
		return null;
	}
	
	private void TS(String commend) throws IOException{
		System.out.println(commend);  
		Runtime rt = Runtime.getRuntime();  
        Process proc = rt.exec(commend);  
        InputStream stderr = proc.getErrorStream();  
        InputStreamReader isr = new InputStreamReader(stderr);  
        BufferedReader br = new BufferedReader(isr);  
        String line = null;  
        while ( (line = br.readLine()) != null)
        	System.out.println(line);
        System.out.println("OUT");
		
		
	}

}
