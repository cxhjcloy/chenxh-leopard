package cn.chenxhcloud.models.bacth;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
*   
* 项目名称：chenxh-models  
* 类名称：cn.chenxhcloud.models.bacth.Person  
* @author：chenxh  
* 创建时间：2017年12月13日 下午3:24:03
* 描述：Person object
*
 */
@ApiModel(description = "Persion")
public class Person implements Serializable {
	private static final long serialVersionUID = 8388007160028706292L;
	
	@ApiModelProperty(value = "lastName", name = "lastName")
	private String lastName;
	
	@ApiModelProperty(value = "firstName", name = "firstName")
	private String firstName;

	public Person() {

	}

	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "firstName: " + firstName + ", lastName: " + lastName;
	}
}
