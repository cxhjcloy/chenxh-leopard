package cn.chenxhcloud.services.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import cn.chenxhcloud.models.common.Greeting;

@Service
public class Services {

	private static final Logger logger = LoggerFactory.getLogger(Services.class);

	private static final String template = ":) Hello, %s";
	
	
	@Autowired
    private StringRedisTemplate stringRedisTemplate;
	
	public Greeting getMessage(String name) {
		if(logger.isDebugEnabled()) {
			logger.debug("Services.getMessage invoke");
		}		
		return new Greeting(stringRedisTemplate.opsForValue().increment("counter", 1), String.format(template, name));
	}
}