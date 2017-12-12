package cn.chenxhcloud.services.world.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cn.chenxhcloud.services.world.repository.domain.Customer;

/**
 * 
*   
* 项目名称：chenxh-services  
* 类名称：cn.chenxhcloud.services.world.repository.CustomerRepository  
* @author : chenxh  
* 创建时间：2017年12月12日 下午5:15:11
* 描述：JPA CrudRepository实现
*
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	/**
	 * lastName
	 * @return 列表
	 * @param lastName
	 */
    List<Customer> findByFirstName(String lastName);
}
