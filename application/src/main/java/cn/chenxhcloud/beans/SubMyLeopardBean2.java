package cn.chenxhcloud.beans;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
*   
* 项目名称：chenxh-app  
* 类名称：cn.chenxhcloud.beans.SubMyLeopardBean2  
* @author：chenxh  
* 创建时间：2018年1月11日 上午10:02:03
* 描述：
*
 */
public class SubMyLeopardBean2 {
	
	private final Logger logger = LoggerFactory.getLogger(SubMyLeopardBean2.class);
	private List<String> subList;
	public SubMyLeopardBean2() {
		logger.debug("SubMyLeopardBean2 instenced");
	}
	public List<String> getSubList() {
		return subList;
	}
	public void setSubList(List<String> subList) {
		this.subList = subList;
	}
	@Override
	public String toString() {
		return "SubMyLeopardBean2 [subList=" + subList + "]";
	}	
}
