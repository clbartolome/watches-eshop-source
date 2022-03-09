package com.eshop.watches.catalog.controller;

import java.util.List;

import com.eshop.watches.catalog.entity.Brand;
import com.eshop.watches.catalog.service.BrandsService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/brands")
public class BrandsController {

  private final BrandsService service;

  public BrandsController(BrandsService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<Brand>> getBrands() {
    List<Brand> watches = service.getAllBrands();
    HttpStatus status = HttpStatus.OK;
    if (0 == watches.size()) status = HttpStatus.NO_CONTENT;
    return ResponseEntity.status(status).body(watches);
  }

}
