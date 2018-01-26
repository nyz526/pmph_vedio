package com.bc.pmph_vedio.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmph_vedio.common.CheckedException;
import com.bc.pmph_vedio.dao.VedioDao;
import com.bc.pmph_vedio.po.Vedio;


@Service("vedioService")
public class VedioServiceImpl  implements VedioService {
	

	@Autowired
	private VedioDao vedioDao;
	

	@Override
	public Vedio addVedio(Vedio vedio) throws CheckedException {
		// 验证 、、。。。。。。。。。。。。。
		vedioDao.addVedio(vedio);
		return vedio;
	}
}
