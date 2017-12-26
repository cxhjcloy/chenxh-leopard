package cn.chenxhcloud.multithread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
*   
* 项目名称：chenxh-multithread  
* 类名称：cn.chenxhcloud.multithread.MyThreadExtendsThread  
* @author：chenxh  
* 创建时间：2017年12月26日 下午6:11:47
* 描述：
*
 */
public class MyThreadExtendsThread {
	private final static Logger log = LoggerFactory.getLogger(MyThreadExtendsThread.class);

	public static void main(String[] args) {
		log.info(Thread.currentThread().getName() + " running.");
		MyThreadDemo thread1 = new MyThreadDemo("thread1");
		MyThreadDemo thread2 = new MyThreadDemo("thread2");
		Thread thread3 = new Thread(new MyThreadDemo(), "thread3");
		thread1.start();
		thread2.start();
		thread3.start();
	}
}

class MyThreadDemo extends Thread {

	private final static Logger log = LoggerFactory.getLogger(Thread.class);

	public MyThreadDemo() {
		super();
	}

	public MyThreadDemo(String name) {
		super(name);
	}

	@Override
	public void run() {
		log.info(Thread.currentThread().getName() + " running.");
	}
}