package com.bc.pmph_vedio.common;


/**
 * 
 * @introduction
 *
 * @author Mryang
 *
 * @createDate 2018年1月26日 上午9:31:41
 *
 */
@SuppressWarnings("serial")
public class ResponseBean<T> implements java.io.Serializable {
	/**
	 * 状态标识     1- 成功 2-失败
	 */
    private int             code                = 1;
    /**
     * 状态信息     success-成功 
     */
    private String          msg                 = "success";
    private T               data                ;

    public ResponseBean() {
        super();
    }

    public ResponseBean(T data) {
        super();
        this.data = data;
    }

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "{code:" + code + ",msg:" + msg + ",data:" + data + "}";
	}
    
    
    
}
