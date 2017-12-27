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
 * 项目名称：chenxh-multithread 类名称：cn.chenxhcloud.multithread.MyThreadExtendsThread
 * 
 * @author：chenxh 创建时间：2017年12月26日 下午6:11:47 描述：
 *
 */
public class MyThreadExtendsThread {
	private final static Logger log = LoggerFactory.getLogger(MyThreadExtendsThread.class);

	public static void main(String[] args) {
		ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("mythread-pool-%d").build();
		ExecutorService pool = new ThreadPoolExecutor(50, 500, 1110L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
		pool.execute(() -> log.info(Thread.currentThread().getName() + " running."));
		pool.execute(() -> log.info(Thread.currentThread().getName() + " running."));
		pool.execute(() -> log.info(Thread.currentThread().getName() + " running."));
		pool.shutdown();
	}
}
