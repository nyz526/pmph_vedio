package com.bc.pmph_vedio.general;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import com.bc.pmph_vedio.common.Const;
import com.bc.pmph_vedio.po.Vedio;
import com.bc.pmph_vedio.service.VedioService;


/**
 *@author MrYang 
 *@CreateDate 2018年1月26日 上午11:25:00
 *
 **/
public class FileConversion extends Thread  {
		
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
		String oldName = this.vedio.getFileName();
		String tempName= this.vedio.getFilePath();
		String oldType = oldName.substring(oldName.lastIndexOf(".") + 1,oldName.length()) .toLowerCase(); 
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
		Vedio vedio = null;
		try {
			TS(test.toString()) ;
			vedio = new Vedio() ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		//删除原临时文件
		if(Const.DELETE_OLD && new File(FileService.filePath+newName+"."+Const.NEW_TYPE).exists()){
			new File(FileService.filePath+newName+"."+Const.NEW_TYPE).delete();
		}
		//保存文件信息
		if(null != vedio){
			vedio = vedio
					.setFileSize(new File(FileService.filePath+newName+"."+Const.NEW_TYPE).length())
					.setUserId(this.vedio.getUserId())
					.setBookId(this.vedio.getBookId())
					.setFilePath(newName)
					.setFileName(oldName.substring(0, oldName.length()-1-oldType.length()))
					.setFileType(Const.NEW_TYPE);
			WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
			VedioService vedioService = (VedioService) wac.getBean("vedioService");
			if(null != vedioService){
				vedioService.addVedio(vedio);
			}
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
