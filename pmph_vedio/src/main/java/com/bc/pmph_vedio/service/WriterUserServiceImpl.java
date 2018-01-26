package com.bc.pmph_vedio.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmph_vedio.common.CheckedException;
import com.bc.pmph_vedio.dao.WriterUserDao;
import com.bc.pmph_vedio.po.WriterUser;


/**
 * WriterUserService 实现
 * 
 * @author 曾庆峰
 * 
 */
@Service
public class WriterUserServiceImpl implements WriterUserService {

	@Autowired
	private WriterUserDao writerUserDao;
	
	@Override
	public WriterUser getWriterUserById(Long id) throws CheckedException{
		if(null == id){
			throw new CheckedException("主键为空");
		}
		return writerUserDao.getWriterUserById(id);
		
	}

}
