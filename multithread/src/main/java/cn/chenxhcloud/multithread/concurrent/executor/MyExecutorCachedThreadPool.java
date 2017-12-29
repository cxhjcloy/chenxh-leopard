package cn.chenxhcloud.multithread.concurrent.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
*   
* 项目名称：chenxh-multithread  
* 类名称：cn.chenxhcloud.multithread.concurrent.executor.MyExecutorCachedThreadPool  
* @author：chenxh  
* 创建时间：2017年12月29日 下午3:08:57
* 描述：创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程
*
 */
public class MyExecutorCachedThreadPool {
	private final static Logger log = LoggerFactory.getLogger(MyExecutorCachedThreadPool.class);
	private final static Integer COUNTER_NUM = 250;

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Runnable syncRunnable = new Runnable() {
			@Override
			public void run() {
				log.info(Thread.currentThread().getName());
			}
		};
		for (int i = 0; i <COUNTER_NUM; i++) {
			executorService.execute(syncRunnable);
		}
		executorService.shutdown();
	}
}
