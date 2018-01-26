package com.bc.pmph_vedio.service;

import com.bc.pmph_vedio.common.CheckedException;
import com.bc.pmph_vedio.po.PmphUser;



/**
 * PmphUserService 接口
 * 
 * @author Mryang
 * 
 */
public interface PmphUserService {
	/**
	 * 根据用户id查询
	 * @introduction 
	 * @author Mryang
	 * @createDate 2018年1月26日 下午3:37:41
	 * @param id
	 * @return
	 */
	PmphUser getPmphUserById(Long id)throws CheckedException;
   
}
