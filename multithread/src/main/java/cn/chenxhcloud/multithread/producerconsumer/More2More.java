package cn.chenxhcloud.multithread.producerconsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * 
 * 
 * 项目名称：chenxh-multithread
 * 类名称：cn.chenxhcloud.multithread.producerconsumer.More2More
 * 
 * @author：chenxh 创建时间：2017年12月26日 下午6:11:27 描述：
 *
 */
@Service
public class More2More {
	
	
	private static final int CONCURRENT_NUM = 50;
	
	@Autowired
	MyQueue queue;

	public static void main(String[] args) {
		More2More more2More = new More2More();
		more2More.start();
	}

	public void start() {

		ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("producer-thread-%d").build();
		ExecutorService producerPool = new ThreadPoolExecutor(50, 100, 0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
		for (int i = 0; i < CONCURRENT_NUM; i++) {
			producerPool.execute(new Producer(queue));
		}

		namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("customer-thread-%d").build();
		ExecutorService customerPool = new ThreadPoolExecutor(50, 100, 0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
		for (int i = 0; i < CONCURRENT_NUM; i++) {
			customerPool.execute(new Customer(queue));
		}

	}
}
