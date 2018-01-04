package cn.chenxhcloud.controllers;


import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.chenxhcloud.async.AsyncTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 
*   
* 项目名称：chenxh-app  
* 类名称：cn.chenxhcloud.controllers.AsyncTaskController  
* @author：chenxh  
* 创建时间：2018年1月4日 下午4:39:48
* 描述：
*
 */
@RequestMapping("chenxh-leopard/async")
@RestController
@Api(tags = "异步调用")
public class AsyncTaskController {

	private final Logger logger = LoggerFactory.getLogger(AsyncTaskController.class);

	@Autowired
	private AsyncTask asyncTask;

	@GetMapping("/task")
	@ApiOperation(value = "异步任务")
	public String startTask() throws Exception {
		long start = System.currentTimeMillis();
		if (logger.isDebugEnabled()) {
			logger.debug("startTask invoke");
		}
		
		Future<String> task2 = asyncTask.doTaskOne();
		Future<String> task1 = asyncTask.doTaskTwo();
		Future<String> task3 = asyncTask.doTaskThree();

		while (true) {
			if (task1.isDone() && task2.isDone() && task3.isDone()) {
				break;
			}
			Thread.sleep(10);
		}

		long end = System.currentTimeMillis();
		logger.debug("任务全部完成，总耗时：{}毫秒", end - start);
		return "OK";
	}
}
