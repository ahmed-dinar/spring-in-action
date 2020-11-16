package com.bs23.taco.controller;

import com.bs23.taco.dto.Ingredient;
import com.bs23.taco.dto.Ingredient.Type;
import com.bs23.taco.dto.Order;
import com.bs23.taco.dto.Taco;
import com.bs23.taco.repository.IngredientRepository;
import com.bs23.taco.repository.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

  private final IngredientRepository ingredientRepository;
  private TacoRepository designRepo;

  @Autowired
  public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository designRepo) {
    this.ingredientRepository = ingredientRepository;
    this.designRepo = designRepo;
  }

  @GetMapping
  public String showDesignForm(Model model) {
    List<Ingredient> ingredients = new ArrayList<>();
    ingredientRepository.findAll().forEach(i -> ingredients.add(i));

    Type[] types = Ingredient.Type.values();
    for(Type type: types) {
      model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
    }
//    model.addAttribute("design", new Taco());

    return "design";
  }

  @ModelAttribute(name = "order")
  public Order order() {
    return new Order();
  }
  @ModelAttribute(name = "taco")
  public Taco taco() {
    return new Taco();
  }

  @PostMapping
  public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order) {
    if (errors.hasErrors()) {
      return  "design";
    }

    log.info("Processing design: " + design);

    Taco saved = designRepo.save(design);
    order.addDesign(saved);

    return "redirect:/orders/current";
  }

  private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
    return ingredients
      .stream()
      .filter(x -> x.getType().equals(type))
      .collect(Collectors.toList());
  }
}
