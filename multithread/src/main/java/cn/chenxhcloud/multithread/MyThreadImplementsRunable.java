package cn.chenxhcloud.multithread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		ThreadImplementRunable threadImplementRunable = new ThreadImplementRunable();
		Thread thread1 = new Thread(threadImplementRunable,"thread1");
		Thread thread2 = new Thread(threadImplementRunable,"thread2");
		Thread thread3 = new Thread(()-> {
			log.info(Thread.currentThread().getName()+" running.");
			
		},"thread3");
		thread1.start();
		thread2.start();
		thread3.start();
	}
}

class ThreadImplementRunable implements Runnable{
	private final static Logger log  = LoggerFactory.getLogger(ThreadImplementRunable.class);
	@Override
	public void run() {
		log.info(Thread.currentThread().getName()+" running.");
	}
}
