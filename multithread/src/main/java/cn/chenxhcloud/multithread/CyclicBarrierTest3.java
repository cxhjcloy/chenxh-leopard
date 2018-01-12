package cn.chenxhcloud.multithread;

import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 
*   
* 项目名称：chenxh-multithread  
* 类名称：cn.chenxhcloud.multithread.CyclicBarrierTest3  
* @author：chenxh  
* 创建时间：2018年1月12日 下午5:14:58
* 描述：同步屏障CyclicBarrier
	CyclicBarrier的字面意思是可循环使用（Cyclic）的屏障（Barrier）。它要做的事情是，让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续运行。
	我自己的理解CyclicBarrier非常类似集齐七颗龙珠召唤神龙，尤其CyclicBarrier（int parties，Runnable barrierAction），barrierAction就是那个神龙
 	CyclicBarrier默认的构造方法是CyclicBarrier（int parties），其参数表示屏障拦截的线程数量，每个线程调用await方法告诉CyclicBarrier我已经到达了屏障，然后当前线程被阻塞
	 
	 CyclicBarrier的应用场景
     CyclicBarrier可以用于多线程计算数据，最后合并计算结果的场景
		 < 银行流水处理服务类 
		 用一个Excel保存了 用户所有银行流水，每个Sheet保存一个账户近一年的每笔银行流水，现在需要统计用户的日均银行
		  流水，先用多线程处理每个sheet里的银行流水，都执行完之后，得到每个sheet的日均银行流水，最
		  后，再用barrierAction用这些线程的计算结果，计算出整个Excel的日均银行流水>
		  
		  
	CyclicBarrier和CountDownLatch的区别：
	CountDownLatch的计数器只能使用一次，而CyclicBarrier的计数器可以使用reset()方法重置。所以CyclicBarrier能处理更为复杂的业务场景。例如，如果计算发生错误，可以重置计数器，并让线程重新执行一次。
	CyclicBarrier还提供其他有用的方法，比如getNumberWaiting方法可以获得Cyclic-Barrier阻塞的线程数量。isBroken()方法用来了解阻塞的线程是否被中断。		  
		  
 */
public class CyclicBarrierTest3 implements Runnable {
    /**
     * 创建4个屏障，处理完之后执行当前类的run方法
     */
    private CyclicBarrier c = new CyclicBarrier(4, this);
    /**
     * 假设只有4个sheet，所以只启动4个线程
     */
    private ExecutorService executor = Executors.newFixedThreadPool(4);
    /**
     * 保存每个sheet计算出的银流结果
     */
    private ConcurrentHashMap<String, Integer> sheetBankWaterCount = new ConcurrentHashMap<String, Integer>();
 
    private void calculate() {
        for (int i = 0; i < 4; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    // 计算当前sheet的银流数据，计算代码省略
                	Random random =  new Random();
                	int randomData = random.nextInt(100);
                    sheetBankWaterCount.put(Thread.currentThread().getName(),randomData);
                    System.out.println(Thread.currentThread().getName()+"计算结果:"+randomData);
                    // 银流计算完成，插入一个屏障
                    try {
                        c.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executor.shutdown();
    }
 
    @Override
    public void run() {
        int result = 0;
        // 汇总每个sheet计算出的结果
        for (Entry<String, Integer> sheet : sheetBankWaterCount.entrySet()) {
        	System.out.println(Thread.currentThread().getName()+":"+sheet);
            result += sheet.getValue();
        }
        // 将结果输出
        sheetBankWaterCount.put("result", result);
        System.out.println(Thread.currentThread().getName()+":"+result);
    }
 
    public static void main(String[] args) throws Exception {
    	CyclicBarrierTest3 cyclicBarrierTest3 = new CyclicBarrierTest3();
    	cyclicBarrierTest3.calculate();
    }
}
