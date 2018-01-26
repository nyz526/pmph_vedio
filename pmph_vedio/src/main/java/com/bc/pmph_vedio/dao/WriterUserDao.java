package com.bc.pmph_vedio.dao;

import org.springframework.stereotype.Repository;
import com.bc.pmph_vedio.po.WriterUser;


/**
 * WriterUser 实体类的数据访问层接口
 * 
 * @author 曾庆峰
 * 
 */
@Repository
public interface WriterUserDao {

	WriterUser getWriterUserById(Long id);

}
