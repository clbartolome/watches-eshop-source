package com.eshop.watches.catalog.controller;

import java.util.List;

import com.eshop.watches.catalog.entity.Watch;
import com.eshop.watches.catalog.service.WatchesService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/watches")
public class WatchesController {

  private final WatchesService service;

  public WatchesController(WatchesService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<Watch>> getWatches() {
    List<Watch> watches = service.getAllWatches();
    HttpStatus status = HttpStatus.OK;
    if (0 == watches.size()) status = HttpStatus.NO_CONTENT;
    return ResponseEntity.status(status).body(watches);
  }

  @GetMapping("/brands/{brandId}")
  public ResponseEntity<List<Watch>> getWatchesByBrandId(@PathVariable Long brandId) {
    List<Watch> watches = service.getWatchesByBrandId(brandId);
    HttpStatus status = HttpStatus.OK;
    if (0 == watches.size()) status = HttpStatus.NO_CONTENT;
    return ResponseEntity.status(status).body(watches);
  }

}
