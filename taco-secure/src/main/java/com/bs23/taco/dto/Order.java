package com.bs23.taco.dto;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Taco_Order")
public class Order implements Serializable {

  private static final long serialVersionUID = 1L;

  @ManyToOne
  private User user;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Date placedAt;

  @NotBlank(message="Delivery name is required")
  private String deliveryName;

  @NotBlank(message="Street is required")
  private String deliveryStreet;

  @NotBlank(message="City is required")
  private String deliveryCity;

  @NotBlank(message="State is required")
  @Size(max=2, message = "State must be max 2 characters long")
  private String deliveryState;

  @NotBlank(message="Zip code is required")
  @Size(max=10, message = "Zip must be max 10 characters long")
  private String deliveryZip;

  @CreditCardNumber(message="Not a valid credit card number")
  private String ccNumber;

  @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
      message="Must be formatted MM/YY")
  private String ccExpiration;

  @Digits(integer=3, fraction=0, message="Invalid CVV")
  private String ccCVV;

  @ManyToMany(targetEntity = Taco.class)
  private List<Taco> tacos = new ArrayList<>();

  public void addDesign(Taco design) {
    this.tacos.add(design);
  }

  @PrePersist
  void placedAt() {
    this.placedAt = new Date();
  }
}