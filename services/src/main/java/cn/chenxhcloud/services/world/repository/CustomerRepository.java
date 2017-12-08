package cn.chenxhcloud.services.world.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cn.chenxhcloud.services.world.repository.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByFirstName(String lastName);
}
