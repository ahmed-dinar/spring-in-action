package com.bs23.taco.rest.repository;

import com.bs23.taco.rest.dto.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> { }