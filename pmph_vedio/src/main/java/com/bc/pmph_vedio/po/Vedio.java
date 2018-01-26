package com.bc.pmph_vedio.po;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import com.bc.pmph_vedio.common.Const;

/**
 *@author MrYang 
 *@CreateDate 2018年1月26日 上午10:23:08
 *
 **/
@SuppressWarnings("serial")
@Alias("Vedio")
public class Vedio implements Serializable{
	/**
	 * 文件id
	 */
	private Long id;
	/**
	 * 转码后的文件大小
	 */
	private Long fileSize;
	/**
	 * 文件上传者
	 */
	private Long userId ;
	/**
	 * 文件对应书籍
	 */
	private Long bookId ;
	/**
	 * 转码后文件储存路径
	 */
	private String filePath;
	/**
	 * 原始文件名称
	 */
	private String fileName ;
	/** 
	 * 抓马后文件类型
	 */
	private String fileType = Const.NEW_TYPE ;
	 /**
     * 创建时间
     */
    private Timestamp gmtCreate;
    /**
     * 修改时间
     */
    private Timestamp gmtUpdate;
    
    public Vedio( ) {
		super();
	}
    
    public Vedio(Long fileSize, Long userId, Long bookId, String filePath,String fileName) {
		super();
		this.fileSize = fileSize;
		this.userId = userId;
		this.bookId = bookId;
		this.filePath = filePath;
		this.fileName = fileName;
	}
    
	public Long getId() {
		return id;
	}
	public Vedio setId(Long id) {
		this.id = id;
		return this;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public Vedio setFileSize(Long fileSize) {
		this.fileSize = fileSize;
		return this;
	}
	public Long getUserId() {
		return userId;
	}
	public Vedio setUserId(Long userId) {
		this.userId = userId;
		return this;
	}
	public Long getBookId() {
		return bookId;
	}
	public Vedio setBookId(Long bookId) {
		this.bookId = bookId;
		return this;
	}
	public String getFilePath() {
		return filePath;
	}
	public Vedio setFilePath(String filePath) {
		this.filePath = filePath;
		return this;
	}
	public String getFileName() {
		return fileName;
	}
	public Vedio setFileName(String fileName) {
		this.fileName = fileName;
		return this;
	}
	public Timestamp getGmtCreate() {
		return gmtCreate;
	}
	public Vedio setGmtCreate(Timestamp gmtCreate) {
		this.gmtCreate = gmtCreate;
		return this;
	}
	public Timestamp getGmtUpdate() {
		return gmtUpdate;
	}
	public Vedio setGmtUpdate(Timestamp gmtUpdate) {
		this.gmtUpdate = gmtUpdate;
		return this;
	}
	@Override
	public String toString() {
		return "{id:" + id + ",fileSize:" + fileSize + ",userId:" + userId
				+ ",bookId:" + bookId + ",filePath:" + filePath + ",fileNme:"
				+ fileName + ",fileType:" + fileType + ",gmtCreate:" + gmtCreate
				+ ",gmtUpdate:" + gmtUpdate + "}";
	}

	public String getFileType() {
		return fileType;
	}

	public Vedio setFileType(String fileType) {
		this.fileType = fileType;
		return this;
	}
    
    
    
	
}
