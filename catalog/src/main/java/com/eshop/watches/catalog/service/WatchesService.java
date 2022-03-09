package com.eshop.watches.catalog.service;

import java.util.List;
import java.util.Optional;

import com.eshop.watches.catalog.entity.Watch;
import com.eshop.watches.catalog.repository.WatchRepository;

import org.springframework.stereotype.Service;

@Service
public class WatchesService {

  private final WatchRepository watchRepository;

  public WatchesService(WatchRepository watchRepository) {
    this.watchRepository = watchRepository;
  }

  public List<Watch> getAllWatches(){
    return watchRepository.findAll();    
  }
  
  public Optional<Watch> getWatch(Long id){
    return watchRepository.findById(id);
  }

  public List<Watch> getWatchesByBrandId(Long brandId){

    return watchRepository.findWatchesByBrandId(brandId);
  }
  
}
