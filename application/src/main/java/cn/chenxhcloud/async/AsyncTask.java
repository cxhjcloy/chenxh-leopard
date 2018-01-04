package cn.chenxhcloud.async;

import java.util.Random;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;


/**
 * 
*   
* 项目名称：chenxh-app  
* 类名称：cn.chenxhcloud.async.AsyncTask  
* @author：chenxh  
* 创建时间：2018年1月4日 下午4:34:03
* 描述：定义异步调用的方法
*
 */
@Component
public class AsyncTask {

	private static final Logger LOGGER = LoggerFactory.getLogger(AsyncTask.class);
	public static final Random RANDOM = new Random();

	/**
	 * 注：Async所修饰的函数不要定义为static类型，这样异步调用不会生
	 * 
	 * @return
	 * @throws Exception
	 */
	@Async
	public Future<String> doTaskOne() throws Exception {
		LOGGER.debug("开始做任务一");
		long start = System.currentTimeMillis();
		Thread.sleep(RANDOM.nextInt(10000));
		long end = System.currentTimeMillis();
		LOGGER.debug("完成任务一，耗时：{}毫秒", end - start);
		return new AsyncResult<>("任务一完成");
	}
	
	@Async
	public Future<String> doTaskTwo() throws Exception {
		LOGGER.debug("开始做任务二");
		long start = System.currentTimeMillis();
		Thread.sleep(RANDOM.nextInt(10000));
		long end = System.currentTimeMillis();
		LOGGER.debug("完成任务二，耗时：{}毫秒", end - start);
		return new AsyncResult<>("任务二完成");
	}
	
	@Async
	public Future<String> doTaskThree() throws Exception {
		LOGGER.debug("开始做任务三");
		long start = System.currentTimeMillis();
		Thread.sleep(RANDOM.nextInt(10000));
		long end = System.currentTimeMillis();
		LOGGER.debug("完成任务三，耗时：{}毫秒", end - start);
		return new AsyncResult<>("任务三完成");
	}
}
