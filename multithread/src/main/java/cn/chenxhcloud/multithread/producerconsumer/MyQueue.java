package cn.chenxhcloud.multithread.producerconsumer;

import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

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
@Component
public class MyQueue {
	
	private static final int QUEUE_SIZE = 50;
	
	@Autowired
	private ThreadDbService threadDbService;
	
	@Autowired
    private RedisTemplate<String, ?> redisTemplate;
	
	/**
	 *定义volatile变量
	 */
	private volatile ConcurrentLinkedQueue<MyQueueData> queue = new ConcurrentLinkedQueue<MyQueueData>();


	public void push() {
		try {
			while (queue.size() >= QUEUE_SIZE) {
				return;
			}
			String threadName = System.getenv("HOSTNAME")+"-"+Thread.currentThread().getName();
			MyQueueData element = new MyQueueData();
			element.setId(redisTemplate.opsForValue().increment("chenxh_leopard_thread_data_id", 1));
			element.setName(UUID.randomUUID().toString());
			Long counter = MyProducerThreadLocal.get();
			MyProducerThreadLocal.set(counter + 1);
			counter = MyProducerThreadLocal.get();
			String str = "[生产者]push queueSize= " + queue.size() + " | " + threadName + "中 data："+ element + " 执行了" + counter + "次";
			ThreadInfo threadInfo = new ThreadInfo();
			threadInfo.setName(threadName);
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

	public MyQueueData pop() {
		MyQueueData data = null;
		try {
			while (queue.isEmpty()) {
				return null;
			}
			String threadName = System.getenv("HOSTNAME")+"-"+Thread.currentThread().getName();
			data = queue.poll();
			if (data != null) {
				Long counter = MyCustomerThreadLocal.get();						
				MyCustomerThreadLocal.set(counter + 1);
				counter = MyCustomerThreadLocal.get();
				String str1 = "[消费者]pop queueSize= " + queue.size() + " | " + threadName+ "中 data：" + data + " 执行了" + counter + "次";
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
	@Override
	public String toString() {
		return "MyQueueData [id=" + id + ", name=" + name + "]";
	}
}
