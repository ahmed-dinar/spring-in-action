package com.bs23.taco.repository;

import com.bs23.taco.dto.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {

}