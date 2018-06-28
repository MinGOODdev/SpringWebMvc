package com.spring.example.controller;

import com.spring.example.dto.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExJsonController {

  private static final Logger logger = LoggerFactory.getLogger(ExJsonController.class);

  @RequestMapping("/doJson")
  @ResponseBody
  public ProductDto doJson() {
    ProductDto productDto = new ProductDto("Mac", 1000);
    logger.info("/doJson called ...");
    return productDto;
  }

}
