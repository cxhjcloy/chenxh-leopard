package cn.chenxhcloud.amqp.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * 
*   
* 项目名称：chenxh-amqp  
* 类名称：cn.chenxhcloud.amqp.rabbitmq.Receiver  
* @author：chenxh  
* 创建时间：2018年1月5日 上午10:16:55
* 描述：消息消费者
*
 */
@Component
@RabbitListener(queues = "hello")
public class Receiver {
	private final Logger log = LoggerFactory.getLogger(Receiver.class);
    
	@RabbitHandler
    public void process(String hello) {
    	log.debug("Receive: {}", hello);
    }
}