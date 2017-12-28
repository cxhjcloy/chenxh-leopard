package cn.chenxhcloud.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.chenxhcloud.multithread.producerconsumer.More2More;


/**
 * 
* 多线程Controller  
* 项目名称：chenxh-app  
* 类名称：cn.chenxhcloud.controllers.MultiThreadController  
* @author：chenxh  
* 创建时间：2017年12月27日 上午9:08:26
* 描述：
*
 */
@RestController
@RequestMapping("chenxh-leopard/multithread")
public class MultiThreadController {
	private static final Logger  logger = LoggerFactory.getLogger(MultiThreadController.class);

	@Autowired
    private More2More more2More;
	
	@GetMapping("/start")
    public void start() {
    	if(logger.isDebugEnabled()) {
    		logger.debug("multiThread start invoke");
    	}
    	int a = 1;
    	if(a == 1) {
    		more2More.start();
    	}else {
    		
    	}
    }
}
