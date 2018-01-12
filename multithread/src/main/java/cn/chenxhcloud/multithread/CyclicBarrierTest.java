package cn.chenxhcloud.multithread;

import java.util.concurrent.CyclicBarrier;

/**
 * 
*   
* 项目名称：chenxh-multithread  
* 类名称：cn.chenxhcloud.multithread.CyclicBarrierTest  
* @author：chenxh  
* 创建时间：2018年1月12日 下午5:14:58
* 描述：同步屏障CyclicBarrier
	CyclicBarrier的字面意思是可循环使用（Cyclic）的屏障（Barrier）。它要做的事情是，让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续运行。
	我自己的理解CyclicBarrier非常类似集齐七颗龙珠召唤神龙，尤其CyclicBarrier（int parties，Runnable barrierAction），barrierAction就是那个神龙
 	CyclicBarrier默认的构造方法是CyclicBarrier（int parties），其参数表示屏障拦截的线程数量，每个线程调用await方法告诉CyclicBarrier我已经到达了屏障，然后当前线程被阻塞
*
 */
public class CyclicBarrierTest {
	/**
	 *  如果把new CyclicBarrier(3)修改成newCyclicBarrier(4)，则主线程和子线程会永远等待，因为没有其他线程执行await方法，
	 *  即没有其他线程到达屏障，所以之前到达屏障的三个线程都不会继续执行。
	 */
	static CyclicBarrier c = new CyclicBarrier(1);

	/**
	 * 因为主线程和子线程的调度是由CPU决定的，主线程和子线程都有可能先执行
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println(Thread.currentThread().getName()+ " start");
					c.await();
					System.out.println("tootootoo");
					System.out.println(Thread.currentThread().getName()+ " end");
				} catch (Exception e) {

				}
			}
		},"Thread1").start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println(Thread.currentThread().getName()+ " start");
					c.await();
					System.out.println("barbarbarbar");
					System.out.println(Thread.currentThread().getName()+ " end");
				} catch (Exception e) {

				}
			}
		},"Thread2").start();		
		
		try {
			System.out.println(Thread.currentThread().getName()+ " start");
			c.await();
		} catch (Exception e) {
			
		}
		System.out.println(Thread.currentThread().getName()+" done");
	}
}
