package com.bc.pmph_vedio.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bc.pmph_vedio.common.CheckedException;
import com.bc.pmph_vedio.dao.BookDao;
import com.bc.pmph_vedio.po.Book;


@Service
public class BookServiceImpl  implements BookService {
	

	@Autowired
	BookDao bookDao;
	
	@Override
	public Book getBookById(Long id) throws CheckedException{
		if(null == id){
			throw new CheckedException("书籍主键为空");
		}
		return bookDao.getBookById(id);
	}
}
