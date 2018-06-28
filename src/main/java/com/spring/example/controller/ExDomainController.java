package com.spring.example.controller;

import com.spring.example.dto.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExDomainController {

  private static final Logger logger = LoggerFactory.getLogger(ExDomainController.class);

  @RequestMapping("/doD")
  public String doD(Model model) {
    ProductDto productDto = new ProductDto("Mac", 100);
    logger.info("/doD called ...");
    model.addAttribute(productDto);

    return "example/product";
  }

}
