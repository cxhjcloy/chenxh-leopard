package cn.chenxhcloud.scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.chenxhcloud.configs.batch.BillBatchConfig;
import cn.chenxhcloud.models.world.WorldCity;
import cn.chenxhcloud.multithread.producerconsumer.More2More;
import cn.chenxhcloud.services.world.WorldService;

/**
 * 
 * 
 * 项目名称：chenxh-app 
 * 类名称：cn.chenxhcloud.scheduled.ScheduledTaskService 
 * @author：chenxh 
 * 创建时间：2017年12月13日 下午5:26:34 
 * 描述：定时任务
 *
 */
@Component
public class ScheduledTaskService {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTaskService.class);

	
	@Autowired
	private WorldService worldService;

	@Autowired
	private BillBatchConfig billBatchConfig;
	
	@Autowired
    private More2More more2More;
	
	@Autowired
    private AmqpTemplate rabbitTemplate;
	
	/**
	 * second ,minute, hour, day of month, month and day of week 每隔5分钟检测一次数据库连接
	 */
	@Scheduled(cron = "0 */5 * * * * ")
	public void testDbConnection() {
		WorldCity worldCity = worldService.getCityById(100);
		rabbitTemplate.convertAndSend("hello", worldCity.toString());
		log.info(worldCity.toString());
	}
	
	/**
	 * //@Scheduled(cron = "0 *\/2 * * * * ")
	 */
	public void fixedBillBatch() {
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		log.info("job begin {}", dataFormat.format(new Date()));
		billBatchConfig.run();
		log.info("job end {}", dataFormat.format(new Date()));
	}
	/**
	 * second, minute, hour, day of month, month and day of week.
	 *     1 秒             0-59
	 *     2 分             0-59 
	 *     3小时           0-23 
	 *     4日期           1-31
	 *     5月份           1-12 或者 JAN-DEC 
	 *     6星期           1-7 或者 SUN-SAT
	 */
	public void startThreads() {
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		log.info("job begin {}", dataFormat.format(new Date()));
		more2More.start();
		log.info("job end {}", dataFormat.format(new Date()));
	}
}
