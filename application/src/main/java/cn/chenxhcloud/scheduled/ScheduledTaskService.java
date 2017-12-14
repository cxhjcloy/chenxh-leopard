package cn.chenxhcloud.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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

	
	/**
	 * second minute, hour, day of month, month and day of week 每隔5分钟检测一次数据库连接
	 */
	@Scheduled(cron = "0 */5 * * * * ")
	public void reportCurrentTime() {
		WorldCity worldCity = worldService.getCityById(100);
		log.info(worldCity.toString());
	}
	
}
