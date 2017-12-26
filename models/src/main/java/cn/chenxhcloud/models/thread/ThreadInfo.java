package cn.chenxhcloud.models.thread;

import java.util.Date;


/**
 * 
*   
* 项目名称：chenxh-models  
* 类名称：cn.chenxhcloud.models.thread.ThreadInfo  
* @author：chenxh  
* 创建时间：2017年12月26日 下午6:12:45
* 描述：
*
 */
public class ThreadInfo {
	private Long id;
	private Long dataId;
	private String name;
	private Integer queueSize;
	private Long counter;
	private String message;
	private Date crated;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDataId() {
		return dataId;
	}
	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getQueueSize() {
		return queueSize;
	}
	public void setQueueSize(Integer queueSize) {
		this.queueSize = queueSize;
	}
	public Long getCounter() {
		return counter;
	}
	public void setCounter(Long counter) {
		this.counter = counter;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCrated() {
		return crated;
	}
	public void setCrated(Date crated) {
		this.crated = crated;
	}
	@Override
	public String toString() {
		return id + "|" + dataId + "|" + name + "|" + queueSize + "|" + counter + "|" + message + "|" + crated;
	}
}
