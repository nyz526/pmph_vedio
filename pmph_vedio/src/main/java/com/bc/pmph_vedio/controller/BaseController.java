package com.bc.pmph_vedio.controller;

import com.bc.pmph_vedio.common.CheckedException;
import com.bc.pmph_vedio.common.ResponseBean;



public class BaseController {
	/**
	 * 对异常的处理
	 * @introduction 
	 * @author Mryang
	 * @createDate 2018年1月26日 上午9:37:47
	 * @param ex
	 * @return
	 */
    public ResponseBean<String> handlerException(Throwable ex) {
        ResponseBean<String> responseBean = new ResponseBean<String>();
        responseBean.setCode(2);
        responseBean.setData(null);
        if (null == ex){
        	responseBean.setMsg("空异常");
        }else if(ex instanceof CheckedException) {
        	responseBean.setMsg(ex.getMessage());
        } else if (ex instanceof IndexOutOfBoundsException) {
            responseBean.setMsg("下标越界异常");
        } else if (ex instanceof ClassCastException) {
            responseBean.setMsg("类型转换异常");
        } else {
            responseBean.setMsg(ex.toString());
        }
        return responseBean;
    }
}
