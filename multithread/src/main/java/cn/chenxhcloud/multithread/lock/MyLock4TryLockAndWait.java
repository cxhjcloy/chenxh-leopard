package cn.chenxhcloud.multithread.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
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
* 描述：tryLock(long time, TimeUnit unit)方法和tryLock()方法是类似的，只不过区别在于这个方法在拿不到锁时会等待一定的时间，
* 在时间期限之内如果还拿不到锁，就返回false，同时可以响应中断。如果一开始拿到锁或者在等待期间内拿到了锁，则返回true。
*
 */
public class MyLock4TryLockAndWait {

	private final Lock lock = new ReentrantLock();
	private final List<Integer> arrayList = new ArrayList<>();
	private final Logger log = LoggerFactory.getLogger(MyLock.class);
	private final Integer COUNTER = 5;
	private final Integer TRY_LOCK_TIME = 5;

	public static void main(String[] args) {
		MyLock4TryLockAndWait obj = new MyLock4TryLockAndWait();
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
	}

	private void doSomeThing() throws InterruptedException {
		if (lock.tryLock(TRY_LOCK_TIME,TimeUnit.SECONDS)) {
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
