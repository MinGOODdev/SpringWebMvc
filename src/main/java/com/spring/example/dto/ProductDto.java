package com.spring.example.dto;

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

  @Override
  public String toString() {
    return String.format("ProductDto {name = %s, price = %.2f", this.name, this.price);
  }

}
