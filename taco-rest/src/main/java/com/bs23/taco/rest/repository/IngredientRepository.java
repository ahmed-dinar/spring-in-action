package com.bs23.taco.rest.repository;

import com.bs23.taco.rest.dto.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface  IngredientRepository extends CrudRepository<Ingredient, String> { }