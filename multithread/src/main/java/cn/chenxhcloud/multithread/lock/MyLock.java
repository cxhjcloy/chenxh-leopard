package cn.chenxhcloud.multithread.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
*   
* 项目名称：chenxh-multithread  
* 类名称：cn.chenxhcloud.multithread.lock.MyLock  
* @author：chenxh  
* 创建时间：2017年12月29日 上午10:41:32
* 描述：ReentrantLock的使用 得到锁的线程执行，得不到锁的线程等待锁的释放
*
 */
public class MyLock {

	private final static ReentrantLock LOCK = new ReentrantLock();
	private final static List<Integer> ARRAY_LIST = new ArrayList<>();
	private final static Integer COUNTER = 5;
	private final static Logger LOG = LoggerFactory.getLogger(MyLock.class);

	public static void main(String[] args) {
		Thread thread1 = new Thread(() -> {
			try {
				doSomeThing();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "A");
		Thread thread2 = new Thread(() -> {
			try {
				doSomeThing();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "B");
		thread1.start();
		thread2.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread1.interrupt();
		thread2.interrupt();
	}

	public static void doSomeThing() throws InterruptedException {
		LOCK.lock();
		try {
			LOG.debug("线程{}得到了锁...", Thread.currentThread().getName());
			for (int i = 0; i < COUNTER; i++) {
				Thread.sleep(1000);
				ARRAY_LIST.add(i);
				LOG.debug("线程{} arrayList={}", Thread.currentThread().getName(), ARRAY_LIST.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			LOG.debug("线程{}释放了锁...", Thread.currentThread().getName());
			LOCK.unlock();
		}
	}
}
