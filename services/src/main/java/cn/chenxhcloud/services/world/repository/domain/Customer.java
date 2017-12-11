package cn.chenxhcloud.services.world.repository.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "help_category")
@ApiModel(description="用户信息")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(value = "id",name="id")
	@Column(name="help_category_id")
	private Long id;
	
	@ApiModelProperty(value = "姓",name="firstName", required = true,example="小明")
	@Column(name="name")
	private String firstName;

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + "]";
	}
}
