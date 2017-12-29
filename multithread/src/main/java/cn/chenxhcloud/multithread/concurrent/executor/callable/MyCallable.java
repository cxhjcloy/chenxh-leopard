package cn.chenxhcloud.multithread.concurrent.executor.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
*   
* 项目名称：chenxh-multithread  
* 类名称：cn.chenxhcloud.multithread.concurrent.executor.callable.MyCallable  
* @author：chenxh  
* 创建时间：2017年12月29日 下午3:45:45
* 描述：
*
 */
public class MyCallable {

	private final static Logger LOG = LoggerFactory.getLogger(MyCallable.class);
	private final static Integer COUNTER_NUM = 10;

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		List<Future<String>> resultList = new ArrayList<Future<String>>();

		for (int i = 0; i < COUNTER_NUM; i++) {
			Future<String> future = executorService.submit(new TaskWithResult(i));
			resultList.add(future);
		}

		for (Future<String> fs : resultList) {
			try {
				String data = fs.get();
				LOG.info(data);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} finally {
				executorService.shutdown();
			}
		}
	}
}

class TaskWithResult implements Callable<String> {
	private final Logger log = LoggerFactory.getLogger(TaskWithResult.class);
	private int id;

	public TaskWithResult(int id) {
		this.id = id;
	}

	@Override
	public String call() throws Exception {
		log.debug("call()方法被自动调用！！！ " + Thread.currentThread().getName());
		return "call()方法被自动调用，任务返回的结果是：" + id + " " + Thread.currentThread().getName();
	}
}
