package com.eshop.watches.catalog.controller;

import java.util.List;

import com.eshop.watches.catalog.entity.Watch;
import com.eshop.watches.catalog.service.WatchesService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/watches")
public class WatchesController {

  private final WatchesService service;

  public WatchesController(WatchesService service) {
    this.service = service;
  }

  @GetMapping
  public List<Watch> getWatches() {
    return service.getAllWatches();
  }

}
