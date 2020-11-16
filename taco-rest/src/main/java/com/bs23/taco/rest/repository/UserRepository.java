package com.bs23.taco.rest.repository;

import com.bs23.taco.rest.dto.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
  User findByUsername(String username);
}
