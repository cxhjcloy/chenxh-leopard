package cn.chenxhcloud.multithread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 
*   
* 项目名称：chenxh-multithread  
* 类名称：cn.chenxhcloud.multithread.FairAndUnFailLockTest  
* @author：chenxh  
* 创建时间：2018年1月15日 上午9:51:27
* 描述：公平锁指的是哪个线程先运行，那就可以先得到锁。非公平锁是不管线程是否是先运行，都是随机获得锁的
* 在公平的锁上，线程按照他们发出请求的顺序获取锁，但在非公平锁上，则允许‘插队’：当一个线程请求非公平锁时，
* 如果在发出请求的同时该锁变成可用状态，那么这个线程会跳过队列中所有的等待线程而获得锁。     
* 非公平的ReentrantLock 并不提倡 插队行为，但是无法防止某个线程在合适的时候进行插队。在公平的锁中，
* 如果有另一个线程持有锁或者有其他线程在等待队列中等待这个锁，那么新发出的请求的线程将被放入到等待队列中。
* 而非公平锁上，只有当锁被某个线程持有时，新发出请求的线程才会被放入队列中。
* 非公平锁性能高于公平锁。
* 
* 公平锁（Fair）：加锁前检查是否有排队等待的线程，优先排队等待的线程，先来先得 
* 非公平锁（Nonfair）：加锁时不考虑排队等待问题，直接尝试获取锁，获取不到自动到队尾等待
* 非公平锁性能比公平锁高5~10倍，因为公平锁需要在多核的情况下维护一个队列
* 首先Java中的ReentrantLock 默认的lock()方法采用的是非公平锁
*
 */
public class FairAndUnFailLockTest {
	private ReentrantLock lock;
	
	private FairAndUnFailLockTest(boolean isFair) {
		super();
		lock = new ReentrantLock(isFair);
	}
	
	private void testMethod() {
		try {
			lock.lock();
			//Thread.sleep(100);
			System.out.println("TheadName="+Thread.currentThread().getName()+"获得锁");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) throws Exception {
		//公平锁 与 非公平锁 10058:
		//long startTime = System.currentTimeMillis();
		FairAndUnFailLockTest demo = new FairAndUnFailLockTest(true);
		//CountDownLatch cdl = new CountDownLatch(100);
		Thread thread = new Thread(
				()->{
					System.out.println("我执行了"+Thread.currentThread().getName());
					demo.testMethod();
					//cdl.countDown();
				}
		);
		ExecutorService exec = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			exec.execute(thread);
		}
		//cdl.await();
		exec.shutdown();
		//long endTime = System.currentTimeMillis();
		//System.out.println("花费时间:"+(endTime-startTime));
	}
}