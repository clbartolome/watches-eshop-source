package com.eshop.watches.catalog.controller;

import java.util.List;

import com.eshop.watches.catalog.entity.Brand;
import com.eshop.watches.catalog.service.BrandsService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brands")
public class BrandsController {

  private final BrandsService service;

  public BrandsController(BrandsService service) {
    this.service = service;
  }

  @GetMapping
  public List<Brand> getWatches() {
    return service.getAllBrands();
  }

}
