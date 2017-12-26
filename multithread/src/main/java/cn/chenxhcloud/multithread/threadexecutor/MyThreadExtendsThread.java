package cn.chenxhcloud.multithread.threadexecutor;

public class MyThreadExtendsThread extends Thread {
	
	public MyThreadExtendsThread(String name) {
		super(name);
	}
	
	public MyThreadExtendsThread() {
		super();
	}
	
	
	@Override
	public void run() {
		System.out.println(getName()+" run ...");
	}

	public static void main(String[] args) {
		MyThreadExtendsThread thread1 = new MyThreadExtendsThread("thread1");
		MyThreadExtendsThread thread2 = new MyThreadExtendsThread("thread2");
		MyThreadExtendsThread thread3 = new MyThreadExtendsThread("thread3");
		thread1.start();
		thread2.start();
		thread3.start();		
	}
}
