package cn.chenxhcloud.configs.batch;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import cn.chenxhcloud.batch.person.PersonJobListener;
import cn.chenxhcloud.batch.person.PersonProcessor;
import cn.chenxhcloud.batch.validators.CsvBeanValidator;
import cn.chenxhcloud.models.bacth.Person;

/**
 * 
 * 
 * 项目名称：chenxh-app
 * 类名称：cn.chenxhcloud.batch.BatchConfig
 * @author：chenxh 
 * 创建时间：2017年12月13日 下午5:31:07 
 * 描述：Person batch 批处理配置
 *
 */
@Configuration
@Component
public class PersonBatchConfig {
	
	private Logger log = LoggerFactory.getLogger(PersonBatchConfig.class);

	@Autowired
	@Qualifier("test")
	private DataSource dataSource;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Bean
	public ItemReader<Person> reader() throws Exception {
		FlatFileItemReader<Person> reader = new FlatFileItemReader<Person>();
		reader.setResource(new ClassPathResource("people.csv"));
		reader.setLineMapper(new DefaultLineMapper<Person>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { "name", "age", "nation", "address" });
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {
					{
						setTargetType(Person.class);
					}
				});
			}
		});
		return reader;
	}

	public ItemProcessor<Person, Person> processor() {
		PersonProcessor processor = new PersonProcessor();
		processor.setValidator(csvBeanValidator());
		return processor;
	}

	@Bean
	public ItemWriter<Person> writer() {
		log.info("PersonBatchConfig.writer invoke");
		JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<Person>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
		writer.setSql("INSERT INTO `people` (`id`, `name`, `age`, `nation`, `address`) VALUES (:id,:name,:age,:nation,:address)");
		writer.setDataSource(dataSource);
		return writer;
	}

	@Bean
	public JobRepository jobRepository() throws Exception {
		JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
		jobRepositoryFactoryBean.setDataSource(dataSource);
		return jobRepositoryFactoryBean.getObject();
	}

	@Bean
	public SimpleJobLauncher jobLauncher() throws Exception {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobRepository());
		return jobLauncher;
	}

	@Bean
	public Job personJob() throws Exception {
		return jobBuilderFactory.get("personJob").incrementer(new RunIdIncrementer()).flow(step1()).end().listener(personJobListener()).build();
	}

	@Bean
	public Step step1() throws Exception {
		return stepBuilderFactory.get("personJobStep1").<Person, Person>chunk(100).reader(reader()).processor(processor()).writer(writer()).build();
	}

	@Bean
	public PersonJobListener personJobListener() {
		return new PersonJobListener();
	}
	@Bean
	public Validator<Person> csvBeanValidator() {
		return new CsvBeanValidator<Person>();
	}
}