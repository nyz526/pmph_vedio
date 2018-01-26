package com.bc.pmph_vedio.dao;


import org.springframework.stereotype.Repository;
import com.bc.pmph_vedio.po.Vedio;


@Repository
public interface VedioDao {

	/**
	 * 新增 vedio
	 * @introduction 
	 * @author Mryang
	 * @createDate 2018年1月26日 下午5:43:01
	 * @param vedio
	 * @return
	 */
	Integer addVedio(Vedio vedio);

	
}
