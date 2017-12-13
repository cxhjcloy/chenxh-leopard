package cn.chenxhcloud.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.chenxhcloud.SpringBootAppLauncher;
import cn.chenxhcloud.models.common.Greeting;
import cn.chenxhcloud.services.common.Services;
import cn.chenxhcloud.utils.EncodeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
*   
* 项目名称：chenxh-app  
* 类名称：cn.chenxhcloud.controllers.CommonController  
* @author : chenxh  
* 创建时间：2017年12月12日 下午5:10:25
* 描述：
*
 */
@RequestMapping("chenxh-leopard/common")
@RestController
@Api(tags = "World信息")
public class CommonController {
	private static final Logger  logger = LoggerFactory.getLogger(SpringBootAppLauncher.class);

	@Autowired
    private Services services;
	
	
    @GetMapping("/greeting")
    @ApiOperation(value = "获取greeting信息")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name,HttpServletRequest request) {
    	if(logger.isDebugEnabled()) {
    		logger.debug("request greeting invoke");
    	}
        return services.getMessage(name+"|"+System.getenv("HOSTNAME")+" --> "+EncodeUtils.md5Encode(request.getSession().getId(),true));
    }
}
