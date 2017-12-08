package cn.chenxhcloud.models.world;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="国家城市")
public class WorldCity {
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
}
