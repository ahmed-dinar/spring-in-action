package com.bs23.taco.rest.repository;

import com.bs23.taco.rest.dto.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> { }