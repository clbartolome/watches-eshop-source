package com.eshop.watches.catalog.service;

import java.util.List;

import com.eshop.watches.catalog.entity.Brand;
import com.eshop.watches.catalog.repository.BrandRepository;

import org.springframework.stereotype.Service;

@Service
public class BrandsService {

  private final BrandRepository brandRepository;

  public BrandsService(BrandRepository brandRepository) {
    this.brandRepository = brandRepository;
  }

  public List<Brand> getAllBrands(){
    return brandRepository.findAll();    
  }

  
}
