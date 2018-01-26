package com.bc.pmph_vedio.service;

import com.bc.pmph_vedio.common.CheckedException;
import com.bc.pmph_vedio.po.WriterUser;


/**
 * WriterUserService 接口
 * 
 * @author 曾庆峰
 * 
 */
public interface WriterUserService {
	
	/**
	 * 根据主键 id 加载用户对象
	 * 
	 * @param id
	 * @return
	 */
	WriterUser getWriterUserById(Long id) throws CheckedException;

}
