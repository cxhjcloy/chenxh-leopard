package cn.chenxhcloud.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import cn.chenxhcloud.models.world.WorldCity;
import cn.chenxhcloud.services.world.WorldService;

/**
 * 
 * 
 * 项目名称：chenxh-app 类名称：cn.chenxhcloud.scheduled.ScheduledTaskService
 * 
 * @author：chenxh 创建时间：2017年12月13日 下午5:26:34 描述：定时任务
 *
 */
@Component
public class ScheduledTaskService {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTaskService.class);

	@Autowired
	private WorldService worldService;

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job job;
	
	
	/**
	 * second minute, hour, day of month, month and day of week 每隔5分钟检测一次数据库连接
	 */
	@Scheduled(cron = "0 */5 * * * * ")
	public void testDbConnection() {
		WorldCity worldCity = worldService.getCityById(100);
		log.info(worldCity.toString());
	}
	
	
	@Scheduled(cron = "0 */2 * * * * ")
	public void runJob() {
		try {
			JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
			JobExecution jobExecution;
			jobExecution = jobLauncher.run(job, jobParameters);
			log.info(JSONObject.toJSONString(jobExecution));
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
