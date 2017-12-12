package cn.chenxhcloud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 
*   
* 项目名称：chenxh-app  
* 类名称：cn.chenxhcloud.controllers.PageIndexController  
* @author : chenxh  
* 创建时间：2017年12月12日 下午5:10:48
* 描述：
*
 */
@RequestMapping("chenxh-leopard/page")
@Controller
public class PageIndexController {
	
	@GetMapping("/info")
	public String getPageInfo() {
		return "info";		
	}
}
