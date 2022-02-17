package com.eshop.watches.catalog.repository;

import com.eshop.watches.catalog.entity.Watch;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchRepository extends JpaRepository<Watch, Long>{
  
}
