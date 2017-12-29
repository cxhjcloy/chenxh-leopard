package cn.chenxhcloud.multithread.lock;

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
* 描述：通过Lock.tryLock功能，对竞争到锁的线程处理，每个线程仅有一次抢占锁资源的机会。并且tryLock返回是否成功抢到所资源，立即返回，不堵塞线程。
*
 */
public class MyLock4TryLock {

	private final Lock lock = new ReentrantLock();
	private final List<Integer> arrayList = new ArrayList<>();
	private final Logger log = LoggerFactory.getLogger(MyLock.class);
	private final Integer COUNTER = 5;

	public static void main(String[] args) {
		MyLock4TryLock obj = new MyLock4TryLock();
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
		if (lock.tryLock()) {
			try {
				log.debug("线程{}得到了锁...", Thread.currentThread().getName());
				for (int i = 0; i < COUNTER; i++) {
					Thread.sleep(1000);
					arrayList.add(i);
					log.debug("线程{} arrayList={}" ,Thread.currentThread().getName(),arrayList.size());
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				log.debug("线程{}释放了锁...",Thread.currentThread().getName());
				lock.unlock();
			}
		} else {
			log.debug("线程{}获取锁失败...",Thread.currentThread().getName());
		}
	}
}
