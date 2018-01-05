package cn.chenxhcloud.amqp.rabbitmq;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
*   
* 项目名称：chenxh-amqp  
* 类名称：cn.chenxhcloud.amqp.rabbitmq.Sender  
* @author：chenxh  
* 创建时间：2018年1月5日 上午10:11:52
* 描述：消息生产者
*
 */
@Component
public class Sender {
	
	private static final SimpleDateFormat DATE_FORMAT= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	private final Logger log = LoggerFactory.getLogger(Receiver.class);
	@Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello " + DATE_FORMAT.format(new Date());
        log.debug("Send:{}"+context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }
}
