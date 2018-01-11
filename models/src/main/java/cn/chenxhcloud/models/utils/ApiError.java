package cn.chenxhcloud.models.utils;

/**
 * 
*   
* 项目名称：chenxh-models  
* 类名称：cn.chenxhcloud.models.utils.ApiError  
* @author：chenxh  
* 创建时间：2018年1月11日 下午3:18:20
* 描述：
*
 */
public enum ApiError {
	/**
	 * 
	 */
	OK(0, "成功"),
	PARAS_INCOMPLETE(5001, "请求参数缺失"),
	NO_LOGIN(5002, "用户没有登录"),
	NO_TOKEN(5003, "获取不到用户token"),
	UNKNOWN_ERROR(5555,"未知错误");
	
	private String message;
	private int code;
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	private ApiError(int code, String message) {
		this.code = code;
		this.message = message;
	}
}
