package cn.chenxhcloud.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * 
*   
* 项目名称：chenxh-multithread  
* 类名称：cn.chenxhcloud.multithread.MyThreadImplementsRunable  
* @author：chenxh  
* 创建时间：2017年12月26日 下午6:11:51
* 描述：
*
 */
public class MyThreadImplementsRunable {
	private final static Logger log  = LoggerFactory.getLogger(MyThreadImplementsRunable.class);
	public static void main(String[] args) {
		ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("chenxh-thread-%d").build();
		ExecutorService producerPool = new ThreadPoolExecutor(10, 500, 1110L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
		producerPool.execute(()->{
			log.debug(Thread.currentThread().getName()+" run...");
		});
		producerPool.shutdown();
	}
}