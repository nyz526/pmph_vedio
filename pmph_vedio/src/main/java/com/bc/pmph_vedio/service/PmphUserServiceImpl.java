package com.bc.pmph_vedio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmph_vedio.common.CheckedException;
import com.bc.pmph_vedio.dao.PmphUserDao;
import com.bc.pmph_vedio.po.PmphUser;



/**
 * PmphUserService 实现
 * 
 * @author 曾庆峰
 * 
 */
@Service
public class PmphUserServiceImpl implements PmphUserService {

	@Autowired
	PmphUserDao pmphUserDao;
	
	@Override
	public PmphUser getPmphUserById(Long id ) throws CheckedException{
		if(null == id ){
			throw new CheckedException("主键为空");
		}
		return pmphUserDao.getPmphUserById(id);
	}
}
