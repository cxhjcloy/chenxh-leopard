package cn.chenxhcloud.multithread.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
*   
* 项目名称：chenxh-multithread  
* 类名称：cn.chenxhcloud.multithread.lock.MyLock4ReentrantReadWriteLock  
* @author：chenxh  
* 创建时间：2017年12月29日 下午1:45:53
* 描述：线程A和线程B在同时进行读操作，这样就大大提升了读操作的效率。不过要注意的是，如果有一个线程已经占用了读锁，
* 则此时其他线程如果要申请写锁，则申请写锁的线程会一直等待释放读锁。如果有一个线程已经占用了写锁，
* 则此时其他线程如果申请写锁或者读锁，则申请的线程也会一直等待释放写锁
*
 */
public class MyLock4ReentrantReadWriteLock {

	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private final List<Integer> arrayList = new ArrayList<>();
	private final Logger log = LoggerFactory.getLogger(MyLock.class);
	private final Integer COUNTER = 5;

	public static void main(String[] args) {
		final MyLock4ReentrantReadWriteLock obj = new MyLock4ReentrantReadWriteLock();
		Thread thread1 = new Thread(() -> {
			obj.doSomeThing();
		}, "A");
		Thread thread2 = new Thread(() -> {
			obj.doSomeThing();
		}, "B");
		thread1.start();
		thread2.start();
	}

	private void doSomeThing() {
		rwl.writeLock().lock();
		try {
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
			rwl.writeLock().unlock();
		}
	}
}
