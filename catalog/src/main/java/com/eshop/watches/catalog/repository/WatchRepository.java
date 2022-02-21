package com.eshop.watches.catalog.repository;

import java.util.List;

import com.eshop.watches.catalog.entity.Watch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WatchRepository extends JpaRepository<Watch, Long>{

  @Query("SELECT w FROM Watch w WHERE w.brand.id=(:brandId)")
  List<Watch> findWatchesByBrandId(@Param("brandId") Long brandId);
  
}
