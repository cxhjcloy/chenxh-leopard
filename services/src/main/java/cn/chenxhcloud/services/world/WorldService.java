package cn.chenxhcloud.services.world;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.chenxhcloud.models.annotation.SelectDS;
import cn.chenxhcloud.models.world.WorldCity;
import cn.chenxhcloud.services.world.mapper.WorldMapper;
import cn.chenxhcloud.services.world.repository.CustomerRepository;
import cn.chenxhcloud.services.world.repository.domain.Customer;

@Service
public class WorldService {
	private static final Logger log = LoggerFactory.getLogger(WorldService.class);
	@Autowired
	private WorldMapper worldMapper;
	
	@Autowired
	private CustomerRepository customerRepository;

	public List<WorldCity> getCityList(Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		if (log.isInfoEnabled()) {
			log.info("getCityList pageNo={},pageSize={}", pageNo, pageSize);
		}
		return worldMapper.getCityList();
	}

	@SelectDS("test")
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
	
	@SelectDS("cjyun")
	public Iterable<Customer> getAllCustomer() {
		if (log.isInfoEnabled()) {
			log.info("getAllCustomer");
		}
		return customerRepository.findAll();
	}
	
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
