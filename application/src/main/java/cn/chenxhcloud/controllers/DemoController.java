package cn.chenxhcloud.controllers;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
*   
* 项目名称：chenxh-app  
* 类名称：cn.chenxhcloud.controllers.DemoController  
* @author：chenxh  
* 创建时间：2017年12月14日 下午5:23:28
* 描述：批处理页面调度demo
*
 */
@RestController
@RequestMapping("chenxh-leopard")
public class DemoController {

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job importJob1;
	
	public JobParameters jobParameters;

	@RequestMapping("/batch/read")
	public String imp(String fileName) throws Exception {
		jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
		jobLauncher.run(importJob1, jobParameters);
		return "ok";
	}
}
