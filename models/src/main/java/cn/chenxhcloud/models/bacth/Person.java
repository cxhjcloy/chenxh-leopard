package cn.chenxhcloud.models.bacth;

import java.io.Serializable;

import javax.validation.constraints.Size;

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
/**
 * 
*   
* 项目名称：chenxh-models  
* 类名称：cn.chenxhcloud.models.bacth.Person  
* @author：chenxh  
* 创建时间：2017年12月14日 下午2:06:25
* 描述：
*
 */
@ApiModel(description = "Persion")
public class Person implements Serializable {
	
	private static final long serialVersionUID = 8388007160028706292L;
	
	@ApiModelProperty(value = "id", name = "id")
	private String id;
    @Size(max=4, min=2)
    @ApiModelProperty(value = "name", name = "name")
    private String name;
    @ApiModelProperty(value = "age", name = "age")
    private Integer age;
    @ApiModelProperty(value = "nation", name = "nation")
    private String nation;
    @ApiModelProperty(value = "address", name = "address")
    private String address;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + ", nation=" + nation + ", address=" + address + "]";
	}
}
