package cn.chenxhcloud.multithread.producerconsumer;

import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;

import cn.chenxhcloud.models.thread.ThreadInfo;
import cn.chenxhcloud.models.thread.threadlocal.MyThreadLocal;
import cn.chenxhcloud.models.thread.threadlocal.MyThreadLocal2;
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
	
	private ConcurrentLinkedQueue<MyData> queue = new ConcurrentLinkedQueue<MyData>();

	private static final AtomicLong ATOMICLONG = new AtomicLong(1);

	public  void push() {
		try {
			/*while (queue.size() >= 1000) {
				System.out.println("[生产者]push操作中的" + Thread.currentThread().getName() + "呈现wait状态");
				this.wait();
			}*/
			MyData element = new MyData();
			element.setId(ATOMICLONG.getAndIncrement());
			element.setName(UUID.randomUUID().toString());
			// LocalThread记录每个线程数据
			Long count = MyThreadLocal2.get();
			if (count == null) {
				count = 0L;
			}
			MyThreadLocal2.set(count + 1);
			String str = "[生产者]push queueSize= " + queue.size() + " | " + Thread.currentThread().getName() + "中 data："+ element + " 执行了" + MyThreadLocal2.get() + "次";
			System.out.println(str);
			ThreadInfo threadInfo = new ThreadInfo();
			threadInfo.setName(Thread.currentThread().getName());
			threadInfo.setMessage(str);
			threadInfo.setCounter(MyThreadLocal2.get());
			threadInfo.setDataId(element.getId());
			threadInfo.setQueueSize(queue.size());
			threadDbService.save(threadInfo);
			queue.add(element);
			//this.notifyAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public  MyData pop() {
		MyData data = null;
		try {
			if (queue.isEmpty()) {
				System.out.println("[消费者]pop操作中的" + Thread.currentThread().getName() + "呈现wait状态");
				//this.wait();// 线程空时等待
				return null;
			}
			if (!queue.isEmpty()) {
				data = queue.poll();
				Long count = MyThreadLocal.get();
				if (count == null) {
					count = 0L;
				}				
				MyThreadLocal.set(count + 1);
				String str1 = "[消费者]pop queueSize= " + queue.size() + " | " + Thread.currentThread().getName()+ "中 data：" + data + " 执行了" + MyThreadLocal.get() + "次";
				System.out.println(str1);
				ThreadInfo threadInfo = new ThreadInfo();
				threadInfo.setName(Thread.currentThread().getName());
				threadInfo.setMessage(str1);
				threadInfo.setCounter(MyThreadLocal.get());
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

class MyData{
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
