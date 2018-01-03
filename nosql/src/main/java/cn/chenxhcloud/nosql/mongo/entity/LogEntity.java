package cn.chenxhcloud.nosql.mongo.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
*   
* 项目名称：chenxh-nosql  
* 类名称：cn.chenxhcloud.nosql.mongo.entity.LogEntity  
* @author：chenxh  
* 创建时间：2018年1月3日 下午5:08:00
* 描述：
*
 */
@ApiModel(description = "日志信息")
public class LogEntity implements Serializable {
	private static final long serialVersionUID = -4050563700062450356L;
	@ApiModelProperty(value = "时间戳", name = "timeStamp")
	private long timeStamp;
	@ApiModelProperty(value = "接口返回结果", name = "result")
	private String result;
	@ApiModelProperty(value = "请求参数", name = "params")
	private String params;
	@ApiModelProperty(value = "请求详情", name = "request")
	private String request;

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}
}
