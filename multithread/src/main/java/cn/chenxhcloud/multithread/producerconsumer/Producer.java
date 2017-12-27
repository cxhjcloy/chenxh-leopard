package cn.chenxhcloud.multithread.producerconsumer;

/**
 * 
*   
* 项目名称：chenxh-multithread  
* 类名称：cn.chenxhcloud.multithread.producerconsumer.Producer  
* @author：chenxh  
* 创建时间：2017年12月26日 下午6:11:42
* 描述：
*
 */
public class Producer implements Runnable{
	
    private MyQueue queue;

    public Producer(MyQueue queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        for (;;) {
        	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}        	
        	queue.push();
        }
    }
}
