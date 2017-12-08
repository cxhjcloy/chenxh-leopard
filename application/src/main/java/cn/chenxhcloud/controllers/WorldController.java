package cn.chenxhcloud.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;

import cn.chenxhcloud.models.world.WorldCity;
import cn.chenxhcloud.models.world.WorldCityDto;
import cn.chenxhcloud.services.world.WorldService;
import cn.chenxhcloud.services.world.repository.domain.Customer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("chenxh-leopard/world")
@Api(tags = "World信息")
public class WorldController {
	private static final Logger logger = LoggerFactory.getLogger(WorldController.class);
	@Autowired
	private WorldService worldService;

	@RequestMapping(value = "getCityList/{pageNo}/{pageSize}", method = RequestMethod.GET)
	@ApiOperation(value = "获取城市列表")
	public WorldCityDto getCityList(@PathVariable(name = "pageNo") int pageNo, @PathVariable("pageSize") int pageSize) {
		WorldCityDto retData = new WorldCityDto();
		try {
			List<WorldCity> cityList = worldService.getCityList(pageNo, pageSize);
			if (cityList.size() > 0) {
				Page<WorldCity> pageData = (Page<WorldCity>) cityList;
				retData.setData(pageData);
				retData.setTotal(pageData.getTotal());
				return retData;
			} else {
				return retData;
			}
		} catch (Exception e) {
			logger.error("WorldController.getCityList 系统异常：" + e.getMessage());
			e.printStackTrace();
			return retData;
		}
	}

	@GetMapping(value = "getCityByName")
	@ApiOperation(value = "根据城市名称查询城市列表")
	public WorldCityDto getCityByName(
			@ApiParam(value = "城市名称<支持模糊查询>", name = "cityName", required = true, example = "Hefei") @RequestParam(name = "cityName", required = true) String cityName) {
		try {
			WorldCityDto retData = new WorldCityDto();
			List<WorldCity> data = worldService.getCityByName(cityName);
			retData.setData(data);
			retData.setTotal((long) data.size());
			return retData;
		} catch (Exception e) {
			logger.error("WorldController.getCityByName 系统异常：" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	@GetMapping(value = "getCityById")
	@ApiOperation(value = "根据城市Id查询城市")
	public WorldCity getCityById(
			@ApiParam(value = "城市Id", name = "cityId", required = true, example = "1") @RequestParam(name = "cityId", required = true) Integer cityId) {
		try {
			WorldCity data = worldService.getCityById(cityId);
			return data;
		} catch (Exception e) {
			logger.error("WorldController.getCityById 系统异常：" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	@GetMapping(value = "getAllCustomer")
	@ApiOperation(value = "查询所有Customer",notes="采用JPA获取数据")
	public List<Customer> getAllCustomer() {
		try {
			Iterable<Customer> data = worldService.getAllCustomer();
			return (List<Customer>) data;
		} catch (Exception e) {
			logger.error("WorldController.getAllCustomer 系统异常：" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping(value = "demo")
	@ApiOperation(value = "demo",notes="一个测试用例，连接两个数据库进行数据的操作，分别使用了Mybatis和JPA")
	public Integer demo(@RequestParam(name = "name", required = true) String name) {
		try {
			return  worldService.compDemo(name);
		} catch (Exception e) {
			logger.error("WorldController.compDemo 系统异常：" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}
