package cn.chenxhcloud.multithread.producerconsumer;

import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;

import cn.chenxhcloud.models.thread.ThreadInfo;
import cn.chenxhcloud.models.thread.threadlocal.MyCustomerThreadLocal;
import cn.chenxhcloud.models.thread.threadlocal.MyProducerThreadLocal;
import cn.chenxhcloud.services.thread.ThreadDbService;



/**
 * 
*   
* 项目名称：chenxh-multithread  
* 类名称：cn.chenxhcloud.multithread.producerconsumer.MyQueue  
* @author：chenxh  
* 创建时间：2017年12月26日 下午6:11:35
* 描述：
*
 */
public class MyQueue {
	
	@Autowired
	private ThreadDbService threadDbService;
	
	/**
	 *定义volatile变量
	 */
	private volatile ConcurrentLinkedQueue<MyQueueData> queue = new ConcurrentLinkedQueue<MyQueueData>();

	private static final AtomicLong ATOMICLONG = new AtomicLong(1);

	public  void push() {
		try {
			while (queue.size() >= 50) {
				System.out.println("[生产者]push操作中的" + Thread.currentThread().getName() + "呈现wait状态");
				return;
			}
			MyQueueData element = new MyQueueData();
			element.setId(ATOMICLONG.getAndIncrement());
			element.setName(UUID.randomUUID().toString());
			// LocalThread记录每个线程数据
			Long counter = MyProducerThreadLocal.get();
			MyProducerThreadLocal.set(counter + 1);
			counter = MyProducerThreadLocal.get();
			String str = "[生产者]push queueSize= " + queue.size() + " | " + Thread.currentThread().getName() + "中 data："+ element + " 执行了" + counter + "次";
			System.out.println(str);
			ThreadInfo threadInfo = new ThreadInfo();
			threadInfo.setName(Thread.currentThread().getName());
			threadInfo.setMessage(str);
			threadInfo.setCounter(counter);
			threadInfo.setDataId(element.getId());
			threadInfo.setQueueSize(queue.size());
			threadDbService.save(threadInfo);
			queue.add(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public  MyQueueData pop() {
		MyQueueData data = null;
		try {
			if (queue.isEmpty()) {
				System.out.println("[消费者]pop操作中的" + Thread.currentThread().getName() + "呈现wait状态");
				return null;
			}
			String threadName = Thread.currentThread().getName();
			if (!queue.isEmpty()) {
				data = queue.poll();
				Long counter = MyCustomerThreadLocal.get();						
				MyCustomerThreadLocal.set(counter + 1);
				counter = MyCustomerThreadLocal.get();
				String str1 = "[消费者]pop queueSize= " + queue.size() + " | " + threadName+ "中 data：" + data + " 执行了" + counter + "次";
				System.out.println(str1);
				ThreadInfo threadInfo = new ThreadInfo();
				threadInfo.setName(threadName);
				threadInfo.setMessage(str1);
				threadInfo.setCounter(counter);
				threadInfo.setDataId(data.getId());
				threadInfo.setQueueSize(queue.size());
				threadDbService.save(threadInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
}

class MyQueueData{
	private Long id;
	private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
}
