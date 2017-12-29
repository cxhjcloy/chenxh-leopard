package cn.chenxhcloud.multithread.concurrent.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
*   
* 项目名称：chenxh-multithread  
* 类名称：cn.chenxhcloud.multithread.lock.MyLock4Try  
* @author：chenxh  
* 创建时间：2017年12月29日 上午11:13:27
* 描述：lockInterruptibly()方法比较特殊，当通过这个方法去获取锁时，如果线程 正在等待获取锁，则这个线程能够 响应中断，
* 即中断线程的等待状态。例如，当两个线程同时通过lock.lockInterruptibly()想获取某个锁时，
* 假若此时线程A获取到了锁，而线程B只有在等待，那么对线程B调用threadB.interrupt()方法能够中断线程B的等待过程
*
 */
public class MyLock4LockInterruptibly {

	private final Lock lock = new ReentrantLock();
	private final List<Integer> arrayList = new ArrayList<>();
	private final Logger log = LoggerFactory.getLogger(MyLock.class);
	private final Integer COUNTER = 5;

	public static void main(String[] args) {
		MyLock4LockInterruptibly obj = new MyLock4LockInterruptibly();
		Thread thread1 = new Thread(() -> {
			try {
				obj.doSomeThing();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "A");
		Thread thread2 = new Thread(() -> {
			try {
				obj.doSomeThing();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "B");
		thread1.start();
		thread2.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread2.interrupt();
	}

	/**
	 * 下面代码就将锁的获取操作放在try语句块里，则必定会执行finally语句块中的解锁操作。在准备获取锁的 线程B 被中断后，
	 * 再执行解锁操作就会抛出 IllegalMonitorStateException，因为该线程并未获得到锁却执行了解锁操作
	 * @throws InterruptedException
	 */
	private void doSomeThing() throws InterruptedException {
		try {
			lock.lockInterruptibly();
		} catch (InterruptedException e1) {
			log.debug("线程{}被终止了...", Thread.currentThread().getName());
			throw new InterruptedException();
		}
		try {
			//lock.lockInterruptibly();
			log.debug("线程{}得到了锁...", Thread.currentThread().getName());
			for (int i = 0; i < COUNTER; i++) {
				Thread.sleep(1000);
				arrayList.add(i);
				log.debug("线程{} arrayList={}", Thread.currentThread().getName(), arrayList.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			log.debug("线程{}释放了锁...", Thread.currentThread().getName());
			lock.unlock();
		}

	}
}
