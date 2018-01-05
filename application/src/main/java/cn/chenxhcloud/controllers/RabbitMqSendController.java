package cn.chenxhcloud.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.chenxhcloud.amqp.rabbitmq.Sender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 
*   
* 项目名称：chenxh-leopard  
* 类名称：.RabbitMqSendController  
* @author：chenxh  
* 创建时间：2018年1月5日 上午11:17:37
* 描述：
*
 */
@RequestMapping("chenxh-leopard/rabbitmq")
@RestController
@Api(tags = "rabbitmq消息发送")
public class RabbitMqSendController {
	@Autowired
    private Sender sender;
	private static final Logger  logger = LoggerFactory.getLogger(RabbitMqSendController.class);
	@GetMapping("/sendmessage")
    @ApiOperation(value = "发送消息")
    public void sendMessage() {
    	if(logger.isDebugEnabled()) {
    		logger.debug("RabbitMqSendController sendMessage invoke");
    	}
        sender.send();
    }
}
