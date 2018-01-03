package cn.chenxhcloud.nosql.mongo.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
*   
* 项目名称：chenxh-nosql  
* 类名称：cn.chenxhcloud.nosql.mongo.entity.UserEntity  
* @author：chenxh  
* 创建时间：2018年1月3日 下午5:08:17
* 描述：
*
 */
@ApiModel(description = "用户信息")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = -4686747649506565527L;
	@ApiModelProperty(value = "id", name = "id")
	private Long id;
	@ApiModelProperty(value = "用户名", name = "userName", example = "小明")
	private String userName;
	@ApiModelProperty(value = "密码", name = "passWord", example = "123")
	private String passWord;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
}
