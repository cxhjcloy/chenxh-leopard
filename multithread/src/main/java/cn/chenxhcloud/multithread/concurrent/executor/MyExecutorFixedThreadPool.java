package cn.chenxhcloud.multithread.concurrent.executor;

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
* 类名称：cn.chenxhcloud.multithread.concurrent.executor.MyExecutorFixedThreadPool  
* @author：chenxh  
* 创建时间：2017年12月29日 下午3:04:23
* 描述：newFixedThreadPool创建一个可重用固定线程数的线程池，以共享的无界队列方式来运行这些线程
*
 */
public class MyExecutorFixedThreadPool {
	private final static Logger log = LoggerFactory.getLogger(MyExecutorFixedThreadPool.class);
	private final static Integer COUNTER_NUM = 20;

	public static void main(String[] args) {
		ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
		ExecutorService executorService = new ThreadPoolExecutor(10, 100, 0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(), namedThreadFactory);
		Runnable syncRunnable = new Runnable() {
			@Override
			public void run() {
				log.info(Thread.currentThread().getName());
			}
		};
		for (int i = 0; i < COUNTER_NUM; i++) {
			executorService.execute(syncRunnable);
		}
		executorService.shutdown();
	}
}
