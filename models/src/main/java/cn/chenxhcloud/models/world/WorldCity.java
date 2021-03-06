package cn.chenxhcloud.models.world;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
*   
* 项目名称：chenxh-models  
* 类名称：cn.chenxhcloud.models.world.WorldCity  
* @author : chenxh  
* 创建时间：2017年12月12日 下午5:13:42
* 描述：
*
 */
@ApiModel(description="国家城市")
public class WorldCity implements Serializable {
	private static final long serialVersionUID = 7515518428838613549L;
	@ApiModelProperty(value = "城市id",name="id",example="221")
	private Integer id;
	@ApiModelProperty(value = "城市名称",name="name",example="Nova IguaÃ§u")
	private String name;
	@ApiModelProperty(value = "国家代码",name="countryCode",example="BRA")
	private String countryCode;
	@ApiModelProperty(value = "地区",name="countryCode",example="Rio de Janeiro")
	private String district;
	@ApiModelProperty(value = "城市人口",name="population",example="862225")
	private Integer population;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Integer getPopulation() {
		return population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

	@Override
	public String toString() {
		return "WorldCity [id=" + id + ", name=" + name + ", countryCode=" + countryCode + ", district=" + district
				+ ", population=" + population + "]";
	}
}
