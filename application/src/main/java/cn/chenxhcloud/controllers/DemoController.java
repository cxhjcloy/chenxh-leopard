package cn.chenxhcloud.controllers;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job importJob1;
	
	public JobParameters jobParameters;

	@RequestMapping("/read")
	public String imp(String fileName) throws Exception {
		jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
		jobLauncher.run(importJob1, jobParameters);
		return "ok";
	}
}
