package com.bc.pmph_vedio.general;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import com.bc.pmph_vedio.common.Const;
import com.bc.pmph_vedio.po.Vedio;

/**
 *文件转换任务分发器
 *@author MrYang 
 *@CreateDate 2018年1月26日 上午9:53:01
 *
 **/
@Component
public class TaskDispatcher {
	/**
	 * 最大允许同时运行的线程数
	 */
	private  Integer maxRun = Const.MAX_RUN;
	
	/**
	 * 分发器是否正在运行
	 */
	public static   boolean isRuning = false; 
	
	/**
	 * 正在运行的线程数目
	 */
	public static   Integer runingNum = 0; 
	
	/**
	 * 分发器文件集合对象
	 */
	public static  List<Vedio> filesInfo = new ArrayList<Vedio>();
	
	Logger logger = Logger.getLogger(this.getClass());

	public void startDispatcher(){
		TaskDispatcher.isRuning = true ;
		logger.info("我醒了，开始分发任务给线程");
		final Timer timer = new Timer();
	    timer.schedule (new TimerTask (){
	        public void run() {
	        	//如果没有文件信息 ,那么分发器休息
	        	if(null == filesInfo || filesInfo.size() == 0){
	        		TaskDispatcher.isRuning = false ;
	        		timer.cancel();
	        		logger.info("我又开始睡大觉了");
	        		return ;
	        	}
	        	//如果当前正在处理的线程 >= 设置的最大线程 数目 ,等待下一轮 
	        	if(runingNum.intValue() >= maxRun.intValue()){
	        		return ;
	        	}
	        	// 文件列表有数据并且线程有空余的执行
	        	Vedio vedio = filesInfo.get(0);
	        	runingNum ++;
	        	//把文件信息交给线程
	        	new FileConversion(vedio,runingNum).start();
	        	filesInfo.remove(0);
	        }
        }, 1000, 2);	
	}
	
	
	public Integer getMaxRun() {
		return maxRun;
	}

	public void setMaxRun(Integer maxRun) {
		this.maxRun = maxRun;
	}

	public TaskDispatcher() {
		super();
	}
	
	

}
