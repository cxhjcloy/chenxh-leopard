package cn.chenxhcloud.controllers;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
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
	Job job;
	

	@RequestMapping("/batch/read")
	public JobExecution imp(String fileName) throws Exception {
		JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
		JobExecution jobExecution = jobLauncher.run(job, jobParameters);
		return jobExecution;
	}
}
