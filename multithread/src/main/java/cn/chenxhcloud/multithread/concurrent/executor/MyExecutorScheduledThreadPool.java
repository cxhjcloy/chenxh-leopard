package cn.chenxhcloud.multithread.concurrent.executor;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
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
* 描述：创建一个定长线程池，支持定时及周期性任务执行
*
 */
public class MyExecutorScheduledThreadPool {
	private final static Logger log = LoggerFactory.getLogger(MyExecutorScheduledThreadPool.class);

	public static void main(String[] args) {
		ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
		ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(5,namedThreadFactory);
		Runnable syncRunnable = () -> {
			/*try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			log.info("[{}]HeartBeat....",Thread.currentThread().getName());
		};
		executorService.scheduleAtFixedRate(syncRunnable, 3, 2, TimeUnit.SECONDS);
		//executorService.shutdown();
	}
}
