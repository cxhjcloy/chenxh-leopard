package cn.chenxhcloud.services.world;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.chenxhcloud.models.annotation.SelectDS;
import cn.chenxhcloud.models.world.WorldCity;
import cn.chenxhcloud.services.world.mapper.WorldMapper;
import cn.chenxhcloud.services.world.repository.CustomerRepository;
import cn.chenxhcloud.services.world.repository.domain.Customer;

/**
 * 
*   
* 项目名称：chenxh-services  
* 类名称：cn.chenxhcloud.services.world.WorldService  
* @author : chenxh  
* 创建时间：2017年12月12日 下午5:16:22
* 描述：Demo示例展示动态数据库切换，多数据源事务问题，Mybatis和JPA混用
*
 */
@Service
public class WorldService {
	private static final Logger log = LoggerFactory.getLogger(WorldService.class);
	@Autowired
	private WorldMapper worldMapper;
	
	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * 使用PageHelper插件实现分页
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<WorldCity> getCityList(Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		if (log.isInfoEnabled()) {
			log.info("getCityList pageNo={},pageSize={}", pageNo, pageSize);
		}
		return worldMapper.getCityList();
	}

	/**
	 * 设置数据缓存，采用redis作为缓存服务器，同时采用注解获取动态数据源
	 * @param cityName
	 * @return
	 */
	@SelectDS("test")
	@Cacheable(value = "cityName", key = "#root.targetClass + #cityName", unless = "#result eq null")
	public List<WorldCity> getCityByName(String cityName) {
		if (log.isInfoEnabled()) {
			log.info("getCityByName cityName={}", cityName);
		}
		return worldMapper.getCityByName(cityName);
	}

	public WorldCity getCityById(Integer cityId) {
		if (log.isInfoEnabled()) {
			log.info("getCityById cityId={}", cityId);
		}
		return worldMapper.getCityById(cityId);
	}
	
	/**
	 * 动态数据源切换
	 * @return
	 */
	@SelectDS("mysql")
	public Iterable<Customer> getAllCustomer() {
		if (log.isInfoEnabled()) {
			log.info("getAllCustomer");
		}
		return customerRepository.findAll();
	}
	
	/**
	 * 采用代理对象AopContext，但被调用方法为public或protect，不能为private！（切记）
	 * @param name
	 * @return
	 */
	public Integer compDemo(String name) {
		if (log.isInfoEnabled()) {
			log.info("getAllCustomer");
		}
		Iterable<Customer> data =  ((WorldService)AopContext.currentProxy()).getAllCustomer();
		data.forEach( customer ->{ 
			if (log.isInfoEnabled()) {
				log.info(customer.toString());
			}
		});
		List<WorldCity> list  = getCityByName(name);
		return list.size();
	}
}
