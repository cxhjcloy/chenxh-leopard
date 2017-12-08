package cn.chenxhcloud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("chenxh-leopard/page")
@Controller
public class PageIndexController {
	
	@GetMapping("/info")
	public String getPageInfo() {
		return "info";		
	}
}
