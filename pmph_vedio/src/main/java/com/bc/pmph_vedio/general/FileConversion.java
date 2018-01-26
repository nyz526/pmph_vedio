package com.bc.pmph_vedio.general;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.bc.pmph_vedio.common.Const;
import com.bc.pmph_vedio.po.Vedio;


/**
 *@author MrYang 
 *@CreateDate 2018年1月26日 上午11:25:00
 *
 **/
public class FileConversion extends Thread{
		
	private Vedio vedio = new Vedio();
	
	private Integer num = 0;
	
	private String osName=System.getProperty("os.name").toUpperCase();
	
	Logger logger = Logger.getLogger(this.getClass());
	
	public FileConversion() {
		super();
	}
	
	public FileConversion(Vedio vedio,Integer num) {
		super();
		this.vedio = vedio;
		this.num = num;
		logger.info(num+"号线程初始化完毕");
	}
	

	@Override
	public void run() {
		logger.info(num+"号线程执行任务");
		//String oldName = vedio.getFileNme();
		String tempName= vedio.getFilePath();
		String newName = java.util.UUID.randomUUID().toString().replaceAll("-", "");
		StringBuffer test=new StringBuffer();  
		List<String> commend = new ArrayList<String>();  
		if(osName.contains("WINDOWS")){ //WINDOWS 系统
			commend.add(FileService.servicePath+"ffmpegbin"+System.getProperty("file.separator")+"ffmpeg.exe");
			commend.add("-i");
			commend.add(FileService.tempPath+tempName);
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
			commend.add(FileService.filePath+newName+"."+Const.NEW_TYPE);
			
		}else if("LINUX".equals( System.getProperty("os.name").toUpperCase()  )){ //linnux操作系统
			commend.add("ffmpeg"); 
	        commend.add("-i");   
	        commend.add(FileService.tempPath+tempName);  
	        commend.add("-ab");      
	        commend.add("128");           
	        commend.add("-ac");      
	        commend.add("1");      
	        commend.add("-ar");      
	        commend.add("22050");      
	        commend.add("-r");      
	        commend.add("24");     
	        commend.add("-y"); 
	        commend.add(FileService.filePath+newName+"."+Const.NEW_TYPE);  
	    }
		for(int i=0;i<commend.size() ;i++){
            test.append(commend.get(i)+" ");  
        }
		try {
			TS(test.toString()) ;
			//vedio = new Vedio(Long fileSize, Long userId, Long bookId, String filePath,String fileNme);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info(num+"任务完毕");
		(TaskDispatcher.runingNum)--;
	}

	private void TS(String commend) throws IOException {
		logger.info(commend);
		Runtime rt = Runtime.getRuntime();  
        Process proc = rt.exec(commend);  
        InputStream stderr = proc.getErrorStream();  
        InputStreamReader isr = new InputStreamReader(stderr);  
        BufferedReader br = new BufferedReader(isr);  
        String line = null;  
        while ( (line = br.readLine()) != null){
        	logger.info(line);
        }
    }
	
	

}
