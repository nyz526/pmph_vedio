package com.bc.pmph_vedio.service;

import com.bc.pmph_vedio.common.CheckedException;
import com.bc.pmph_vedio.po.Vedio;



public interface VedioService {

	/**
	 * 新增vedio
	 * @introduction 
	 * @author Mryang
	 * @createDate 2018年1月26日 下午5:43:36
	 * @param vedio
	 * @return
	 * @throws CheckedException
	 */
	Vedio  addVedio(Vedio vedio) throws CheckedException;
}
