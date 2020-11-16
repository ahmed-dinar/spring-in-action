package com.bs23.taco.repository;

import com.bs23.taco.dto.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface  IngredientRepository extends CrudRepository<Ingredient, String> { }