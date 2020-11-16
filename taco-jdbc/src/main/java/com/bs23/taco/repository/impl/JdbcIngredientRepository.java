package com.bs23.taco.repository.impl;

import com.bs23.taco.dto.Ingredient;
import com.bs23.taco.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {
  private JdbcTemplate jdbc;

  @Autowired
  public JdbcIngredientRepository(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  @Override
  public Iterable<Ingredient> findAll() {
    String q = "SELECT id, name, type FROM Ingredient";
    return jdbc.query(q, this::mapRowToIngredient);
  }

  @Override
  public Ingredient findOne(String id) {
    String q = "SELECT id, name, type FROM Ingredient WHERE id=?";
    return jdbc.queryForObject(q, this::mapRowToIngredient, id);
  }

  @Override
  public Ingredient save(Ingredient ingredient) {
    String q = "INSERT INTO Ingredient (id, name, type) VALUES (?, ?, ?)";
    jdbc.update(q, ingredient.getId(), ingredient.getName(), ingredient.getType().toString());
    return ingredient;
  }

  private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
    return new Ingredient(
      rs.getString("id"),
      rs.getString("name"),
      Ingredient.Type.valueOf(rs.getString("type"))
    );
  }
}
