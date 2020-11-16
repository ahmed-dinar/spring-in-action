package com.bs23.taco.repository;

import com.bs23.taco.dto.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

}