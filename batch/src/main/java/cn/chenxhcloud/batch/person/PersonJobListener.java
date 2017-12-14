package cn.chenxhcloud.batch.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * 
 * 
 * 项目名称：chenxh-batch 类名称：cn.chenxhcloud.batch.person.PersonJobListener
 * 
 * @author：chenxh 创建时间：2017年12月14日 下午2:01:22 描述：JobExecutionListener
 *
 */

public class PersonJobListener implements JobExecutionListener {
	private Logger log = LoggerFactory.getLogger(PersonJobListener.class);
	long startTime;
	long endTime;

	@Override
	public void beforeJob(JobExecution jobExecution) {
		startTime = System.currentTimeMillis();
		log.info("任务处理开始");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		endTime = System.currentTimeMillis();
		log.info("任务处理结束耗时:{} ms",endTime-startTime);
	}

}