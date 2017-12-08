package cn.chenxhcloud.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.chenxhcloud.Application;
import cn.chenxhcloud.models.common.Greeting;
import cn.chenxhcloud.services.common.Services;
import cn.chenxhcloud.utils.EncodeUtils;

@RequestMapping("chenxh-leopard/common")
@RestController
public class CommonController {
	private static final Logger  logger = LoggerFactory.getLogger(Application.class);

	@Autowired
    private Services services;
	
	
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name,HttpServletRequest request) {
    	if(logger.isDebugEnabled()) {
    		logger.debug("request greeting invoke");
    	}
        return services.getMessage(name+"|"+System.getenv("HOSTNAME")+" --> "+EncodeUtils.MD5Encode(request.getSession().getId(),true));
    }
}
