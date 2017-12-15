package cn.chenxhcloud.configs.batch;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import cn.chenxhcloud.batch.dto.AlipayTranDO;
import cn.chenxhcloud.batch.dto.MyPayTranDO;
import cn.chenxhcloud.batch.processor.AlipayItemProcessor;
import cn.chenxhcloud.batch.reader.AlipayFileItemReader;
import cn.chenxhcloud.batch.writer.AlipayFileItemWriter;

/**
 * 
 * @author Administrator
 *
 */
@Component
public class BillBatchConfig {
	
	private final Logger log = LoggerFactory.getLogger(BillBatchConfig.class);
	
	@Autowired
	public JobLauncher jobLauncher;

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	private AlipayFileItemReader alipayFileItemReader;

	@Autowired
	private AlipayItemProcessor alipayItemProcessor;

	@Autowired
	private AlipayFileItemWriter alipayFileItemWriter;


	public void run() {
		try {
			String dateParam = new Date().toString();
			JobParameters param =    new JobParametersBuilder().addString("date", dateParam).toJobParameters();
			log.info(dateParam);
			JobExecution execution = jobLauncher.run(importAliJob(), param);             //执行job
			log.info("Exit Status :{} " , execution.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Bean
	public Job importAliJob() {
		return jobBuilderFactory.get("importAliJob")
				.incrementer(new RunIdIncrementer())
				.flow(step1())
				.end()
				.build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<AlipayTranDO, MyPayTranDO> chunk(400)
				.reader(alipayFileItemReader.getMultiAliReader())
				.processor(alipayItemProcessor)
				.writer(alipayFileItemWriter.getAlipayItemWriter())
				.build();
	}
}
