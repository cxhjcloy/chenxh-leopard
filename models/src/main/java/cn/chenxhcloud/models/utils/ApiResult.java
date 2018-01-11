package cn.chenxhcloud.models.utils;




/**
 * 
*   
* 项目名称：chenxh-models  
* 类名称：cn.chenxhcloud.models.utils.ApiResult  
* @author：chenxh  
* 创建时间：2018年1月11日 下午3:12:51
* 描述：API接口返回值包装类
*
 */
public class ApiResult<T> {
	
	/**
	 * code = 0 success
	 * code !=0 abnormal
	 */
	private Integer code;
	/**
	 * message  
	 */
	private String message;
	
	/**
	 * data
	 */
	private T result;

	
	public ApiResult(Integer code, String message, T result) {
		super();
		this.code = code;
		this.message = message;
		this.result = result;
	}

	
	public ApiResult(Integer code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	

	public ApiResult() {
		super();
	}


	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
}
