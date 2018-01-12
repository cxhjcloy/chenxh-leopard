package cn.chenxhcloud.multithread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
*   
* 项目名称：chenxh-multithread  
* 类名称：cn.chenxhcloud.multithread.ExchangerTest  
* @author：chenxh  
* 创建时间：2018年1月12日 下午6:28:34
* 描述：
* 线程间交换数据的Exchanger
	Exchanger（交换者）是一个用于线程间协作的工具类。Exchanger用于进行线程间的数据交换。
	它提供一个同步点，在这个同步点，两个线程可以交换彼此的数据。这两个线程通过exchange方法交换数据，
	如果第一个线程先执行exchange()方法，它会一直等待第二个线程也执行exchange方法，当两个线程都到达同步点时，
	这两个线程就可以交换数据，将本线程生产出来的数据传递给对方
 */
public class ExchangerTest {
	
    private static final Exchanger<String> exgr = new Exchanger<String>();
    
    public static void main(String[] args) {
    	
    	ExecutorService threadPool = Executors.newFixedThreadPool(2);
    	
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String A = "银行流水A";// A录入银行流水数据
                    System.out.println(Thread.currentThread().getName()+" start!!!");
                    exgr.exchange(A);
                    System.out.println(Thread.currentThread().getName()+" done!!!");
                } catch (InterruptedException e) {
                }
            }
        });
        
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String B = "银行流水C";// B录入银行流水数据
                    System.out.println(Thread.currentThread().getName()+" start!!!");
                    Thread.sleep(5000);
                    String C = exgr.exchange("B");
                    System.out.println(C);
                    System.out.println("A和B数据是否一致：" + C.equals(B) + "，A录入的是：" + C + "，B录入是：" + B);
                    System.out.println(Thread.currentThread().getName()+" done!!!");
                } catch (InterruptedException e) {
                }
            }
        });
        
        threadPool.shutdown();
    }
}
