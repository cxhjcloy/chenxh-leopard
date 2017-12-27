package cn.chenxhcloud.multithread.producerconsumer;

/**
 * 
*   
* 项目名称：chenxh-multithread  
* 类名称：cn.chenxhcloud.multithread.producerconsumer.Customer  
* @author：chenxh  
* 创建时间：2017年12月26日 下午6:11:18
* 描述：
*
 */
public class Customer implements Runnable {

    private MyQueue queue;

    public Customer(MyQueue queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        for (;;) {
            queue.pop();
        }
    }
}
