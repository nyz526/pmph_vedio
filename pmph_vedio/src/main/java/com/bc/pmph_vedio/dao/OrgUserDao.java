package com.bc.pmph_vedio.dao;



import com.bc.pmph_vedio.po.OrgUser;


/**
 * OrgUser 实体类数据访问层接口
 * 
 * @author mryang
 */
public interface OrgUserDao {
	/**
     * 根据主键Id查询对象
     * 
     * @param id 主键id
     * @return OrgUser OrgUser对象
     */
    OrgUser getOrgUserById(Long id);

}
