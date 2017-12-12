package cn.chenxhcloud.services.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import cn.chenxhcloud.models.common.Greeting;
/**
 * 
*   
* 项目名称：chenxh-services  
* 类名称：cn.chenxhcloud.services.common.Services  
* @author : chenxh  
* 创建时间：2017年12月12日 下午5:14:04
* 描述：
*
 */
@Service
public class Services {

	private static final Logger logger = LoggerFactory.getLogger(Services.class);

	private static final String TEMPLATE = ":) Hello, %s";
	
	
	@Autowired
    private RedisTemplate<String, ?> redisTemplate;
	
	public Greeting getMessage(String name) {
		if(logger.isDebugEnabled()) {
			logger.debug("Services.getMessage invoke");
		}		
		return new Greeting(redisTemplate.opsForValue().increment("counter", 1), String.format(TEMPLATE, name));
	}
}