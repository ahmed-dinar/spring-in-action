package com.bs23.taco.repository;

import com.bs23.taco.dto.Ingredient;

public interface  IngredientRepository {
  Iterable<Ingredient> findAll();
  Ingredient findOne(String id);
  Ingredient save(Ingredient ingredient);
}