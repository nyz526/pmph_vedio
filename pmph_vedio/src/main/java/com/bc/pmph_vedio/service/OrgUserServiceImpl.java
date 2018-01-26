package com.bc.pmph_vedio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bc.pmph_vedio.common.CheckedException;
import com.bc.pmph_vedio.dao.OrgUserDao;
import com.bc.pmph_vedio.po.OrgUser;


/**
 * OrgUserServiceImpl 接口实现
 * 
 * @author Mryang
 * 
 */
@Service
public class OrgUserServiceImpl   implements OrgUserService {
    
	@Autowired
    private OrgUserDao     orgUserDao;


	@Override
    public OrgUser getOrgUserById(Long id) throws CheckedException {
        if (null == id) {
            throw new CheckedException( "主键为空");
        }
        return orgUserDao.getOrgUserById(id);
    }

}
