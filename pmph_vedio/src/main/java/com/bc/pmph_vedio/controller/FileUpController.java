package com.bc.pmph_vedio.controller;


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
	
	/**
	 * 接收上传的文件 ，放入执行列表
	 * @introduction 
	 * @author Mryang
	 * @createDate 2018年1月26日 下午9:31:22
	 * @param request
	 * @param file
	 * @param userId
	 * @param userType
	 * @param bookId
	 * @return
	 */
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
}
