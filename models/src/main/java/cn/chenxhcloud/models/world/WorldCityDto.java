package cn.chenxhcloud.models.world;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="国家列表")
public class WorldCityDto {
	@ApiModelProperty(value = "记录总数",name="total",example="1")
	private Long total;
	@ApiModelProperty(value = "国家列表",name="total")
	private List<WorldCity> data;
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<WorldCity> getData() {
		return data;
	}
	public void setData(List<WorldCity> data) {
		this.data = data;
	}
	
}
