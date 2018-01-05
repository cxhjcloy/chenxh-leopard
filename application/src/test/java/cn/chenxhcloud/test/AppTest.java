package cn.chenxhcloud.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.TestComponent;
/**
 * 
 * @author chenxh
 *
 */
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
@TestComponent
public class AppTest{
	
	private static final Logger log = LoggerFactory.getLogger(AppTest.class);
	

	@Test
	public void myTest() {
		log.info("aaa");
	}
	
	@Test
	public void test1() {
		RestTemplate rest = new RestTemplate();
		JSONObject restData = rest.getForObject("http://localhost:7001/loggers/cn.chenxhcloud", JSONObject.class);
		log.debug(restData.toJSONString());
	}
	
	
	/**
	 * 动态修改日志级别
	 */
	@Test
	public void test2() {
		RestTemplate rest = new RestTemplate();
		JSONObject postData = new JSONObject();
		postData.put("configuredLevel", "INFO");
		JSONObject restData = rest.postForObject("http://localhost:7001/loggers/cn.chenxhcloud",postData, JSONObject.class);
		log.debug(restData.toJSONString());
	}
	
}