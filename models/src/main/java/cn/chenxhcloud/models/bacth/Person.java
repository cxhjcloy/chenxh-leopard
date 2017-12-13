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
@ApiModel(description = "Persion")
public class Person implements Serializable {
	private static final long serialVersionUID = 8388007160028706292L;
	
	
	@ApiModelProperty(value = "id", name = "id")
	private String id;	
    @Size(max=4, min=2) // 此处使用JSR-303注解来校验数据
    @ApiModelProperty(value = "name", name = "name")
    private String name;
    @ApiModelProperty(value = "age", name = "age")
    private Integer age;
    @ApiModelProperty(value = "nation", name = "nation")
    private String nation;
    @ApiModelProperty(value = "address", name = "address")
    private String address;
}
