package com.bc.pmph_vedio.general;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.bc.pmph_vedio.common.CheckedException;
import com.bc.pmph_vedio.general.TaskDispatcher;
import com.bc.pmph_vedio.po.Book;
import com.bc.pmph_vedio.po.OrgUser;
import com.bc.pmph_vedio.po.PmphUser;
import com.bc.pmph_vedio.po.Vedio;
import com.bc.pmph_vedio.po.WriterUser;
import com.bc.pmph_vedio.service.BookService;
import com.bc.pmph_vedio.service.OrgUserService;
import com.bc.pmph_vedio.service.PmphUserService;
import com.bc.pmph_vedio.service.WriterUserService;

/**
 *@author MrYang 
 *@CreateDate 2018年1月26日 上午9:43:56
 *
 **/
@Component
public class FileService  {
	
	/**
	 * 文件分隔符
	 */
	private String  fileSeparator = System.getProperty("file.separator"); 
		
	@Autowired
	private TaskDispatcher taskDispatcher;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private OrgUserService orgUserService;
	
	@Autowired
	private PmphUserService pmphUserService;
	
	@Autowired
	private WriterUserService writerUserService;
	
	public static String tempPath   = null ;
	public static String filePath    = null ;
	public static String servicePath = null ;
	
	
	public String fileUp(HttpServletRequest request, MultipartFile file, Long userId, Integer userType,Long bookId) throws CheckedException, IllegalStateException, IOException{
		if(null == file || file.isEmpty() ){
			throw new CheckedException("未获取到上传文件");
		}
		if(null == userId ){
			throw new CheckedException("未获取到上传用户");
		}
		if(null == userType ){
			throw new CheckedException("未获取到上传用户类型");
		}
		if(null == bookId ){
			throw new CheckedException("未获取到上传对应图书");
		}
		Book book =  bookService.getBookById(bookId);
		if(null == book){
			throw new CheckedException("找不到对应书籍");
		}
		//1=社内用户/2=作家用户/3=机构用户
		if(userType.intValue() == 1 ){
			PmphUser pmphUser =pmphUserService.getPmphUserById(userId);
			if(null == pmphUser){
				throw new CheckedException("未获取到对应的用户");
			}
		}else if(userType.intValue() == 2){
			WriterUser writerUser =writerUserService.getWriterUserById(userId);
			if(null == writerUser){
				throw new CheckedException("未获取到对应的用户");
			}
		}else if(userType.intValue() == 3){
			OrgUser orgUser =orgUserService.getOrgUserById(userId);
			if(null == orgUser){
				throw new CheckedException("未获取到对应的用户");
			}
		}else{
			throw new CheckedException("未获取到对应的用户");
		}
		//文件名称原来的 
		String name = file.getOriginalFilename();
		//文件类型
		String type = name.substring(name.lastIndexOf(".") + 1,name.length()) .toLowerCase(); 
		type = type == null ?"":type;
		if (type.equals("avi") || type.equals("mpg") || type.equals("wmv") || type.equals("3gp") || 
			type.equals("mov") || type.equals("mp4") || type.equals("asf") || type.equals("asx") || 
			type.equals("flv")){  
        } else{//不支持的格式 
        	throw new CheckedException("上传文件格式不支持");
        }
		//下面开始文件上传
		String path = request.getSession().getServletContext().getRealPath(fileSeparator); 
		path = path.endsWith(fileSeparator)?path:path+fileSeparator;
		servicePath = path;
		//临时文件名称
		String tempName = java.util.UUID.randomUUID().toString().replaceAll("-", "")+"."+type;
		//文件上传到临时目录
		String tempFilePath = path+"temp"+fileSeparator+tempName ;
		//创建临时文件目录 
		if(null == tempPath){
			new File(         path+"temp"+fileSeparator).mkdirs();
			tempPath        = path+"temp"+fileSeparator;
		}
		//创建正式文件目录 
		if(null == filePath ){
			new File(           path+"file"+fileSeparator).mkdirs();
			filePath        =   path+"file"+fileSeparator;
		}
		//存储文件
		file.transferTo(new File(tempFilePath));
		//放置文件信息到分发器
		TaskDispatcher.filesInfo.add(new Vedio(0L,userId, bookId, tempName,name));
		//如果分发器没有运行那么唤醒分发器
		if(!TaskDispatcher.isRuning){
			taskDispatcher.startDispatcher();
		}
		return "文件上传成功";
	}
}
