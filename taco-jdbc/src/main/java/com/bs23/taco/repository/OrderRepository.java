package com.bs23.taco.repository;

import com.bs23.taco.dto.Order;

public interface OrderRepository {
  Order save(Order order);
}