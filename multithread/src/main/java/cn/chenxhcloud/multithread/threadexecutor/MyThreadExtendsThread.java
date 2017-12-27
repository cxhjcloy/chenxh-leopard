package cn.chenxhcloud.multithread.threadexecutor;


/**
 * 
*   
* 项目名称：chenxh-leopard  
* 类名称：.MyThreadExtendsThread  
* @author：chenxh  
* 创建时间：2017年12月27日 下午6:02:12
* 描述：
*
 */
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
