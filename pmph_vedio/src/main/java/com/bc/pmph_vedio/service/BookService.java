package com.bc.pmph_vedio.service;

import com.bc.pmph_vedio.common.CheckedException;
import com.bc.pmph_vedio.po.Book;



public interface BookService {

	/**
	 * 查询一个 Book 通过主键id
	 * 
	 * @param Book
	 *            必须包含主键ID
	 * @return Book
	 */
	Book getBookById(Long id) throws CheckedException;
}
