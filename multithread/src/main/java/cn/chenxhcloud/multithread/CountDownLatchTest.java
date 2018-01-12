package cn.chenxhcloud.multithread;

import java.util.concurrent.CountDownLatch;

/**
 * 
*   
* 项目名称：chenxh-multithread  
* 类名称：cn.chenxhcloud.multithread.CountDownLatchTest  
* @author：chenxh  
* 创建时间：2018年1月12日 下午4:56:24
* 描述：类似于thread.join() 线程执行完毕后，才继续执行主线程。
* 同join，如果我们不想一直让主线程等待，可以设置超时时间await（long time，TimeUnit unit）
		使用场景：
		1 实现最大的并行性：有时我们想同时启动多个线程，实现最大程度的并行性。例如，我们想测试一个单例类。如果我们创建一个初始计数为1的CountDownLatch，并让所有线程都在这个锁上等待，那么我们可以很轻松地完成测试。我们只需调用 一次countDown()方法就可以让所有的等待线程同时恢复执行。
		2 开始执行前等待n个线程完成各自任务：例如应用程序启动类要确保在处理用户请求前，所有N个外部系统已经启动和运行了。
		3 死锁检测：一个非常方便的使用场景是，你可以使用n个线程访问共享资源，在每次测试阶段的线程数目是不同的，并尝试产生死锁。
 */
public class CountDownLatchTest {
	/**
	 * 不达到0的话主线程一直堵塞
	 */
	static CountDownLatch c = new CountDownLatch(12);
	 
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(c.getCount());
                c.countDown();
                System.out.println(c.getCount());
                c.countDown();
                System.out.println(c.getCount());
                c.countDown();
                System.out.println(c.getCount());
                c.countDown();
            }
        });
        t1.start();
        c.await();
        System.out.println("3");
    }
}
