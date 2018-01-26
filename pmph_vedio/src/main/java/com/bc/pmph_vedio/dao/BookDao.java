package com.bc.pmph_vedio.dao;


import org.springframework.stereotype.Repository;
import com.bc.pmph_vedio.po.Book;


@Repository
public interface BookDao {

	
	/**
	 * 查询一个 Book 通过主键id
	 * 
	 * @param Book
	 *            必须包含主键ID
	 * @return Book
	 */
	Book getBookById(Long id);

	
}
