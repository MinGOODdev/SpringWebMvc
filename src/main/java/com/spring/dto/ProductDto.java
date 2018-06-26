package com.spring.dto;

public class ProductDto {

  private String name;
  private double price;

  public ProductDto(String name, double price) {
    super();
    this.name = name;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public double getPrice() {
    return price;
  }

}
