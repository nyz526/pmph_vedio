package com.bc.pmph_vedio.service;



import com.bc.pmph_vedio.common.CheckedException;
import com.bc.pmph_vedio.po.OrgUser;


/**
 * OrgUserService 接口
 * 
 * @author Mryang
 */
public interface OrgUserService {
	
	/**
     * 
     * @param id
     * @return OrgUser
     * @throws CheckedServiceException
     */
    OrgUser getOrgUserById(Long id) throws CheckedException;
}
