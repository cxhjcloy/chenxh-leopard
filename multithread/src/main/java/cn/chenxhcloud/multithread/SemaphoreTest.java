package cn.chenxhcloud.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


/**
 * 
*   
* 项目名称：chenxh-multithread  
* 类名称：cn.chenxhcloud.multithread.SemaphoreTest  
* @author：chenxh  
* 创建时间：2018年1月12日 下午6:20:00
* 描述：
* 	控制并发线程数的Semaphore
	Semaphore（信号量）是用来控制同时访问特定资源的线程数量，它通过协调各个线程，以保证合理的使用公共资源。
	我感觉用驾考科目一考试来形容比较合适。北京玉马考场科目一考试只有100台机器，进入考场的只能有100个考生，当有考生考试结束时，
	排队的人才能进入考试。这里的考生就是线程，进入考场考试表示线程在执行，离开考场表示线程执行完成，考场有100人在考试，就表示线程被阻塞，不能被执行。
	Semaphore应用场景
	流量控制
	特别是公用资源有限的应用场景，比如数据库连接。假如有一个需求，要读取几万个文件的数据，因为都是IO密集型任务，我们可以启动几十个线程并发地读取，
	但是如果读到内存后，还需要存储到数据库中，而数据库的连接数只有5个，这时我们必须控制只有5个线程同时获取数据库连接保存数据，否则会报错无法获取数据库连接。
	
	其他方法
	Semaphore还提供一些其他方法，具体如下。
	·int availablePermits()：返回此信号量中当前可用的许可证数。
	·int getQueueLength()：返回正在等待获取许可证的线程数。
	·boolean hasQueuedThreads()：是否有线程正在等待获取许可证。
	·void reducePermits（int reduction）：减少reduction个许可证，是个protected方法。
	·Collection getQueuedThreads()：返回所有等待获取许可证的线程集合，是个protected方法。

 */
public class SemaphoreTest {
	private static final int THREAD_COUNT = 30;
	private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
	private static Semaphore s = new Semaphore(5);

	public static void main(String[] args) {
		for (int i = 0; i < THREAD_COUNT; i++) {
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						s.acquire();
						System.out.println(Thread.currentThread().getName()+":save data");
						// 如果注释掉s.release(),打印5条save data后就会阻塞
						Thread.sleep(500);
						s.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		threadPool.shutdown();
	}
}