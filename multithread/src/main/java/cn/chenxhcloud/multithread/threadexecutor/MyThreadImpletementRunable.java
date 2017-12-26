package cn.chenxhcloud.multithread.threadexecutor;

public class MyThreadImpletementRunable {
	public static void main(String[] args) {
		Runnable run1 = () -> {
			System.out.println(Thread.currentThread().getName() + " run ... ");
		};

		Runnable run2 = () -> {
			System.out.println(Thread.currentThread().getName() + " run ... ");
		};

		Runnable run3 = () -> {
			System.out.println(Thread.currentThread().getName() + " run ... ");
		};

		Thread thread1 = new Thread(run1, "thread1");
		Thread thread2 = new Thread(run2, "thread2");
		Thread thread3 = new Thread(run3, "thread3");

		thread1.start();
		thread2.start();
		thread3.start();
	}
}