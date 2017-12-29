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
* 类名称：cn.chenxhcloud.multithread.concurrent.executor.MyExecutorCachedThreadPool  
* @author：chenxh  
* 创建时间：2017年12月29日 下午3:08:57
* 描述：创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
*
 */
public class MyExecutorSingleThreadExecutor {
	private final static Logger log = LoggerFactory.getLogger(MyExecutorSingleThreadExecutor.class);

	public static void main(String[] args) {
		ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("single-pool-%d").build();
		ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(),namedThreadFactory);
		Runnable syncRunnable1 = () -> {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			log.info(Thread.currentThread().getName()+"-syncRunnable1 ");
		};
		Runnable syncRunnable2 = () -> {
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			log.info(Thread.currentThread().getName()+"-syncRunnable2 ");
		};
		Runnable syncRunnable3 = () -> {
			try {
				Thread.sleep(2500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			log.info(Thread.currentThread().getName()+"-syncRunnable3 ");
		};
		executorService.execute(syncRunnable1);
		executorService.execute(syncRunnable2);
		executorService.execute(syncRunnable3);
		executorService.shutdown();
	}
}