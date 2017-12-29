package cn.chenxhcloud.multithread.concurrent.executors;

import java.util.Random;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import cn.chenxhcloud.multithread.concurrent.executor.callable.MyCallable;
/**
 * 
*   
* 项目名称：chenxh-multithread  
* 类名称：cn.chenxhcloud.multithread.concurrent.executors.CompletionServiceTest  
* @author：chenxh  
* 创建时间：2017年12月29日 下午5:14:09
* 描述：
*
 */
public class CompletionServiceTest {
	private final static Logger LOG = LoggerFactory.getLogger(MyCallable.class);
	private final static Integer COUNTER = 200;
	private final static Integer POOL_SIZE = 200;
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("completionservicetest-pool-%d").build();
		ExecutorService executor = new ThreadPoolExecutor(POOL_SIZE, POOL_SIZE, 0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(),namedThreadFactory);
		CompletionService<String> completionService = new ExecutorCompletionService<String>(executor);
		for (int i = 1; i <= COUNTER; i++) {
			final int temp = i;
			completionService.submit(()-> {
				Thread.sleep(new Random().nextInt(5000));
				LOG.debug(Thread.currentThread().getName() + " run..." );
				return Thread.currentThread().getName() + "-" + temp;
			});
		}
		LOG.debug(completionService.take().get());
		executor.shutdown();
	}
}
