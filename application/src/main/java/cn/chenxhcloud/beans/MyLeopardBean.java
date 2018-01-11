package cn.chenxhcloud.beans;

import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
*   
* 项目名称：chenxh-app  
* 类名称：cn.chenxhcloud.beans.MyLeopardBean  
* @author：chenxh  
* 创建时间：2018年1月11日 上午10:01:50
* 描述：
*
 */
@SuppressWarnings("all")
public class MyLeopardBean {
	
	
	private final Logger logger = LoggerFactory.getLogger(MyLeopardBean.class);
	/**
	 * 通过xml配置文件加载
	 */
	private Properties properties;
	/**
	 * 通过xml配置文件加载
	 */
    private Map<SubMyLeopardBean1, SubMyLeopardBean2> subProperties;
	
	public void start() {
		logger.debug("MyLeopardBean.start invoke");
		properties.entrySet().iterator().forEachRemaining(item->{
			logger.debug("key={},value={}",item.getKey(),item.getValue());
		});
		
		subProperties.entrySet().iterator().forEachRemaining(item->{
			logger.debug("key={},value={}",item.getKey(),item.getValue());
		});
	}
	
	public void shutdown() {
		logger.debug("MyLeopardBean.start invoke");
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Map<SubMyLeopardBean1, SubMyLeopardBean2> getSubProperties() {
		return subProperties;
	}

	public void setSubProperties(Map<SubMyLeopardBean1, SubMyLeopardBean2> subProperties) {
		this.subProperties = subProperties;
	}
}
