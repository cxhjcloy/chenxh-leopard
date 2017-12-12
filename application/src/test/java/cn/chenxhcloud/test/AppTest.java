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
@TestComponent
public class AppTest{
	private static final Logger log = LoggerFactory.getLogger(AppTest.class);
	@Test
	public void myTest() {
		log.info("aaa");
	}
	
}