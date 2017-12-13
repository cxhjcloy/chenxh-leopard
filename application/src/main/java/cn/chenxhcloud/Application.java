package cn.chenxhcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;


/**
 * 
*   
* 项目名称：chenxh-app  
* 类名称：cn.chenxhcloud.Application  
* @author：chenxh  
* 创建时间：2017年12月12日 下午5:26:51
* 描述：
*
 */
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableCaching
@EnableScheduling
public class Application {
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    /**
     * redisTemplate 采用FastJson序列号缓存数据
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        GenericFastJsonRedisSerializer fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
        redisTemplate.setDefaultSerializer(fastJsonRedisSerializer);
        return redisTemplate;
    }
}
